package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.common.annotation.Business;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.db.store.StoreEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreService storeService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        StoreEntity storeEntity = storeService.getStoreWithThrow(request.getStoreId());

        return Optional.ofNullable(request)
                .map(it -> {
                    return storeMenuConverter.toEntity(it, storeEntity);
                })
                .map(storeMenuService::register)
                .map(storeMenuConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuResponse> search(Long storeId) {
        return storeMenuService.getStoreMenuByStoreId(storeId).stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }

}
