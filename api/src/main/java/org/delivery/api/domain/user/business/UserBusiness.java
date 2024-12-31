package org.delivery.api.domain.user.business;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.Business;
import org.delivery.common.api.Api;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /*
    * 사용자에 대한 가입처리 로직
    * request -> entity
    * entity -> save
    * entity -> response
    * response return
    * */
    public UserResponse register(UserRegisterRequest request) {
        /*UserEntity userEntity = userConverter.toEntity(request);
        UserEntity result = userService.register(userEntity);
        return userConverter.toResponse(result);*/

        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    /*
    * email, password 로 사용자 체크
    * user entity 로그인 확인
    * token 생성
    * token response
    * */
    public TokenResponse login(UserLoginRequest request) {
        UserEntity userEntity = userService.login(request.getEmail(), request.getPassword());

        return tokenBusiness.issueToken(userEntity);
    }

    public UserResponse me(Long userId) {
        UserEntity userEntity = userService.getUserWithThrow(userId);
        return userConverter.toResponse(userEntity);
    }
}
