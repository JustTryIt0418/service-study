package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.UserSession;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션이 있는지 체크
        boolean annotation = parameter.hasParameterAnnotation(UserSession.class);
        
        // 2. 파라미터의 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // supportsParameter 에서 true 반환시 실행

        // request context holder에서 찾아오기
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        Long userId = Long.parseLong(
                Objects.requireNonNull(requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST)).toString()
        );

        UserEntity userEntity = userService.getUserWithThrow(userId);

        // 사용자 정보 셋팅
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
