package org.delivery.api.domain.storemenu.converter;

import org.delivery.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuEntity toEntity(
            StoreMenuRegisterRequest request,
            StoreEntity storeEntity
    ) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreMenuEntity.builder()
                            .store(storeEntity)
                            .name(it.getName())
                            .amount(it.getAmount())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(it.getId())
                            .storeId(it.getStore().getId())
                            .name(it.getName())
                            .status(it.getStatus())
                            .amount(it.getAmount())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .likeCount(it.getLikeCount())
                            .sequence(it.getSequence())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> entityList) {
        return entityList.stream()
                .map(it -> toResponse(it))
                .collect(Collectors.toList());
    }
}
