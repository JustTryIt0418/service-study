package org.delivery.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.Converter;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;

import java.util.Objects;

@Converter
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToekn,
            TokenDto refreshToken
    ) {
        Objects.requireNonNull(accessToekn, () ->{
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        Objects.requireNonNull(refreshToken, () ->{
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToekn.getToken())
                .accessTokenExpiredAt(accessToekn.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
