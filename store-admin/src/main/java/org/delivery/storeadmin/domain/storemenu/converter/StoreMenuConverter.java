package org.delivery.storeadmin.domain.storemenu.converter;

import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.storeadmin.common.annotation.Converter;
import org.delivery.storeadmin.domain.storemenu.controoler.model.StoreMenuResponse;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuResponse toResponse(StoreMenuEntity entity) {
        return StoreMenuResponse.builder()
                .id(entity.getId())
                .storeId(entity.getStoreId())
                .name(entity.getName())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .thumbnailUrl(entity.getThumbnailUrl())
                .likeCount(entity.getLikeCount())
                .sequence(entity.getSequence())
                .build();
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> entities) {
        return entities.stream().map(this::toResponse)
                .collect(Collectors.toList());
    }
}
