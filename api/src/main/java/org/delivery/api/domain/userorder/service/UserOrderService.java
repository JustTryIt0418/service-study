package org.delivery.api.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithOutStatusWithInThrow(Long id, Long userId) {
        return Optional.ofNullable(
                userOrderRepository.findAllByIdAndUserId(
                        id,
                        userId
                )
        ).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public UserOrderEntity getUserOrderWithThrow(Long id, Long userId) {
        return Optional.ofNullable(
                userOrderRepository.findAllByIdAndStatusAndUserId(
                        id,
                        UserOrderStatus.REGISTERED, userId
                )
        ).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<UserOrderEntity> getUserOrderList(Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> statusLilst) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusLilst);
    }

    public List<UserOrderEntity> current(Long userId) {
        return getUserOrderList(
                userId,
                Arrays.asList(
                        UserOrderStatus.ORDER,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY,
                        UserOrderStatus.ACCEPT
                )
        );
    }

    public List<UserOrderEntity> history(Long userId) {
        return getUserOrderList(
                userId,
                Arrays.asList(
                        UserOrderStatus.RECEIVED
                )
        );
    }

    public UserOrderEntity order(UserOrderEntity userOrderEntity) {
        return Optional.ofNullable(userOrderEntity)
                .map(it -> {
                    it.setStatus(UserOrderStatus.ORDER);
                    it.setOrderedAt(LocalDateTime.now());
                    return userOrderRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status) {
        userOrderEntity.setStatus(status);
        return userOrderRepository.save(userOrderEntity);
    }

    public UserOrderEntity accept(UserOrderEntity userOrderEntity) {
        userOrderEntity.setAcceptedAt(LocalDateTime.now());
        return this.setStatus(userOrderEntity, UserOrderStatus.ACCEPT);
    }

    public UserOrderEntity cooking(UserOrderEntity userOrderEntity) {
        userOrderEntity.setCookingStartedAt(LocalDateTime.now());
        return this.setStatus(userOrderEntity, UserOrderStatus.COOKING);
    }

    public UserOrderEntity delivery(UserOrderEntity userOrderEntity) {
        userOrderEntity.setDeliveryStartedAt(LocalDateTime.now());
        return this.setStatus(userOrderEntity, UserOrderStatus.DELIVERY);
    }

    public UserOrderEntity receive(UserOrderEntity userOrderEntity) {
        userOrderEntity.setReceivedAt(LocalDateTime.now());
        return this.setStatus(userOrderEntity, UserOrderStatus.RECEIVED);
    }
}
