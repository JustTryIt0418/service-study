package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return UserEntity.builder()
                            .name(it.getName())
                            .email(it.getEmail())
                            .address(it.getAddress())
                            .password(it.getPassword())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return UserResponse.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .status(it.getStatus())
                            .email(it.getEmail())
                            .address(it.getAddress())
                            .registeredAt(it.getRegisteredAt())
                            .unregisteredAt(it.getUnregisteredAt())
                            .lastLoginAt(it.getLastLoginAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
