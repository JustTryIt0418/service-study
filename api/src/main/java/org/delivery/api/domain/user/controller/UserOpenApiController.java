package org.delivery.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.api.Api;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/open-api/user")
@RequiredArgsConstructor
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    // 사용자 가입 요청
    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody
            Api<UserRegisterRequest> request
    ) {
        return Api.ok(userBusiness.register(request.getBody()));
    }

    // 로그인
    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody
            Api<UserLoginRequest> request
    ) {
        return Api.ok(userBusiness.login(request.getBody()));
    }
}
