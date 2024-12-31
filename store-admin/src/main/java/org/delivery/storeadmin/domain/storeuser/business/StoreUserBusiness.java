package org.delivery.storeadmin.domain.storeuser.business;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.common.annotation.Business;
import org.delivery.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.storeuser.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserService storeUserService;

    private final StoreUserConverter storeUserConverter;

    private final StoreRepository storeRepository;

    public StoreUserResponse register(StoreUserRegisterRequest request) {

        StoreEntity storeEntity = Optional.ofNullable(storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED)).get();

        return Optional.ofNullable(request)
                .map(it -> {
                    return storeUserConverter.toEntity(it, storeEntity);
                })
                .map(storeUserService::register)
                .map(it -> {
                    return storeUserConverter.toResponse(it, storeEntity);
                })
                .orElseThrow(() -> new RuntimeException("Null Point"));


        /*StoreEntity storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED).get();
        StoreUserEntity storeUserEntity = storeUserConverter.toEntity(request, storeEntity);

        StoreUserEntity entity = storeUserService.register(storeUserEntity);

        return storeUserConverter.toResponse(entity, storeEntity);*/
    }
}
