package org.delivery.storeadmin.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.storeadmin.common.annotation.Business;
import org.delivery.storeadmin.domain.sse.connection.SseConnectionPool;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.delivery.storeadmin.domain.storemenu.controoler.model.StoreMenuResponse;
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter;
import org.delivery.storeadmin.domain.userorder.service.UserOrderService;
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;

    private final SseConnectionPool sseConnectionPool;

    private final UserOrderMenuService userOrderMenuService;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    /**
    * 주문
     * 주문내역 찾기
     * 스토어 찾기
     * 연결된 세션 찾기
     * 푸시
    * */
    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        // user order entity
        UserOrderEntity userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
                .orElseThrow(() -> new RuntimeException("주문 내역을 찾을 수 없습니다."));

        // user order menu
        List<UserOrderMenuEntity> userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());

        // user order menu -> store menu
        List<StoreMenuResponse> storeMenuResponseList = userOrderMenuList.stream()
                .map(it -> {
                    return storeMenuService.getStoreMenuWithThrow(it.getStoreMenu().getId());
                })
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());

        // response
        UserOrderResponse userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        UserOrderDetailResponse data = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderResponse)
                .storeMenuResponseList(storeMenuResponseList)
                .build();

        // push
        UserSseConnection userConnection = sseConnectionPool.getSession(userOrderEntity.getStore().getId().toString());
        userConnection.sendMessage(data);
    }
}
