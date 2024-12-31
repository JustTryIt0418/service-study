package org.delivery.api.domain.userorder.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.UserSession;
import org.delivery.common.api.Api;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid
            @RequestBody Api<UserOrderRequest> request,
            @UserSession
            @Parameter(hidden = true) User user
    ) {
        return Api.ok(userOrderBusiness.userOrder(user, request.getBody()));
    }

    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> current(
            @Parameter(hidden = true)
            @UserSession
            User user
    ) {
        return Api.ok(userOrderBusiness.current(user));
    }

    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(
            @Parameter(hidden = true)
            @UserSession
            User user
    ) {
        return Api.ok(userOrderBusiness.history(user));
    }

    @GetMapping("/read/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @PathVariable("orderId") Long orderId,
            @Parameter(hidden = true)
            @UserSession
            User user
    ) {
        return Api.ok(userOrderBusiness.read(user, orderId));
    }
}
