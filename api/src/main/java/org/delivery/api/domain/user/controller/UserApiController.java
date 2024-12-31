package org.delivery.api.domain.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.UserSession;
import org.delivery.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @Parameter(hidden = true)
            @UserSession User user
    ) {
        /*RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        Long userId = Long.parseLong(
                Objects.requireNonNull(requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST)).toString()
        );*/

        UserResponse response = userBusiness.me(user.getId());
        return Api.ok(response);
    }
}
