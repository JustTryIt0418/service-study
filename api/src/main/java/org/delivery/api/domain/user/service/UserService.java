package org.delivery.api.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.error.UserErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/*
* user 도매인 로직을 처리하는 서비스
* */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserEntity register(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }

    public UserEntity login(
            String email,
            String password
    ) {
        return getUserWithThrow(email, password);
    }

    public UserEntity getUserWithThrow(
            String email,
            String password
    ) {
        return Optional.ofNullable(
                userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                        email, password,
                        UserStatus.REGISTERED
                )
        ).orElseThrow(
                () -> new ApiException(UserErrorCode.USER_NOT_FOUNT)
        );
    }

    public UserEntity getUserWithThrow(
            Long userId
    ) {
        return Optional.ofNullable(
                userRepository.findFirstByIdAndStatusOrderByIdDesc(
                        userId,
                        UserStatus.REGISTERED
                )
        ).orElseThrow(
                () -> new ApiException(UserErrorCode.USER_NOT_FOUNT)
        );
    }
}
