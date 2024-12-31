package org.delivery.api.domain.storemenu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.api.Api;
import org.delivery.api.domain.storemenu.business.StoreMenuBusiness;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/open-api/store-menu")
@RequiredArgsConstructor
public class MenuOpenApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(
            @Valid
            @RequestBody Api<StoreMenuRegisterRequest> request
    ) {
        log.info("request : {}", request);

        return Api.ok(storeMenuBusiness.register(request.getBody()));
    }
}
