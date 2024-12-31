package org.delivery.api.domain.store.converter;

import org.delivery.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {

    public StoreEntity toEntity(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreEntity.builder()
                            .name(it.getName())
                            .address(it.getAddress())
                            .category(it.getCategory())
                            .minimumAmount(it.getMinimumAmount())
                            .minimumDeliveryAmount(it.getMinimumDeliveryAmount())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .phoneNumber(it.getPhoneNumber())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreResponse toResponse(StoreEntity entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return StoreResponse.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .address(it.getAddress())
                            .status(it.getStatus())
                            .category(it.getCategory())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .minimumAmount(it.getMinimumAmount())
                            .minimumDeliveryAmount(it.getMinimumDeliveryAmount())
                            .star(it.getStar())
                            .phoneNumber(it.getPhoneNumber())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
