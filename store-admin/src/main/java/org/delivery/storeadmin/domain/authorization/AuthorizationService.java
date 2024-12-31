package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return storeUserService.getRegisterUser(username)
                .map(it -> {
                    StoreEntity storeEntity = Optional.ofNullable(storeRepository.findFirstByIdAndStatus(it.getStoreId(), StoreStatus.REGISTERED)
                    ).orElseThrow(() -> new UsernameNotFoundException("스토어 정보를 찾을 수 없습니다."));

                    return UserSession.builder()
                            .userId(it.getId())
                            .email(it.getEmail())
                            .password(it.getPassword())
                            .status(it.getStatus())
                            .role(it.getRole())
                            .registeredAt(it.getRegisteredAt())
                            .unregisteredAt(it.getUnregisteredAt())
                            .lastLoginAt(it.getLastLoginAt())
                            .storeId(storeEntity.getId())
                            .storeName(storeEntity.getName())
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. : "+username));
    }
}
