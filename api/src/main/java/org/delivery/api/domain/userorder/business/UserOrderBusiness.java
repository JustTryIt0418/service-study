package org.delivery.api.domain.userorder.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.common.annotation.Business;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.producer.UserOrderProducer;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/*@Slf4j
@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    private final UserOrderProducer userOrderProducer;

    private final ObjectMapper objectMapper;

    public UserOrderResponse userOrder(User user, UserOrderRequest request) {

        StoreEntity storeEntity = storeService.getStoreWithThrow(request.getStoreId());

        List<StoreMenuEntity> storeMenuEntityList = request.getStoreMenuIdList().stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .collect(Collectors.toList());

        UserOrderEntity userOrderEntity = userOrderConverter.toEntity(user, storeEntity, storeMenuEntityList);

        UserOrderEntity newUserOrderEntity = userOrderService.order(userOrderEntity);

        List<UserOrderMenuEntity> userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    return userOrderMenuConverter.toEntity(
                            newUserOrderEntity,
                            it
                    );
                })
                .collect(Collectors.toList());

        userOrderMenuEntityList.forEach(userOrderMenuService::order);

        // 비동기로 가맹점에 주문 알리기
        userOrderProducer.sendOrder(newUserOrderEntity);

        return userOrderConverter.toResponse(newUserOrderEntity);
    }

    public List<UserOrderDetailResponse> current(User user) {
        List<UserOrderEntity> userOrderEntityList = userOrderService.current(user.getId());

        return userOrderEntityList.stream()
                .map(userOrderEntity -> {

                    log.info("userOrderEntity : {}", userOrderEntity);

                    try {
                        String jsonString = objectMapper.writeValueAsString(userOrderEntity);
                        log.info(jsonString);
                    } catch (JsonProcessingException e) {
                        log.error("", e);
                    }

                    // 주문 메뉴 정보 가져오기
                    List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderEntity.getUserOrderMenuList().stream()
                            .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                            .toList();

                    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
                            .map(UserOrderMenuEntity::getStoreMenu)
                            .toList();
                    
                    StoreEntity storeEntity = userOrderEntity.getStore();

                    // UserOrderDetailResponse 로 변환
                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build();
                }).collect(Collectors.toList());
    }

    public List<UserOrderDetailResponse> history(User user) {
        List<UserOrderEntity> userOrderEntityList = userOrderService.history(user.getId());

        return userOrderEntityList.stream()
                .map(userOrderEntity -> {

                    // 주문 메뉴 정보 가져오기
                    List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderEntity.getUserOrderMenuList().stream()
                            .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                            .toList();

                    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
                            .map(userOrderMenuEntity -> {
                                return userOrderMenuEntity.getStoreMenu();
                            })
                            .toList();

                    StoreEntity storeEntity = userOrderEntity.getStore();

                    // UserOrderDetailResponse 로 변환
                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build();
                }).toList();
    }

    public UserOrderDetailResponse read(User user, Long orderId) {
        UserOrderEntity userOrderEntity = userOrderService.getUserOrderWithOutStatusWithInThrow(orderId, user.getId());

        // 주문 메뉴 정보 가져오기
        List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderEntity.getUserOrderMenuList().stream()
                .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                .toList();

        List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
                .map(userOrderMenuEntity -> {
                    return userOrderMenuEntity.getStoreMenu();
                })
                .collect(Collectors.toList());

        StoreEntity storeEntity = userOrderEntity.getStore();

        // UserOrderDetailResponse 로 변환
        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build();
    }
}*/
