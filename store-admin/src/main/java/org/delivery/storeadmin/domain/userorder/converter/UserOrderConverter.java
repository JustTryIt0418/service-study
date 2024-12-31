package org.delivery.storeadmin.domain.userorder.converter;

import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.storeadmin.common.annotation.Converter;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderResponse;

@Converter
public class UserOrderConverter {

    public UserOrderResponse toResponse(UserOrderEntity entity) {
        return UserOrderResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .storeId(entity.getStoreId())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .orderedAt(entity.getOrderedAt())
                .acceptedAt(entity.getAcceptedAt())
                .cookingStartedAt(entity.getCookingStartedAt())
                .deliveryStartedAt(entity.getDeliveryStartedAt())
                .receivedAt(entity.getReceivedAt())
                .build();
    }
}
