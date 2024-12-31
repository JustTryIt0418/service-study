package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token) {
        Map<String, Object> map = tokenHelperIfs.validationTokenWithThrow(token);

        Objects.requireNonNull(map.get("userId"), ()->{
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(map.get("userId").toString());
    }
}
