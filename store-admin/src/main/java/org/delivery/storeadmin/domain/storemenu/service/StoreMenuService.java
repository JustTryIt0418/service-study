package org.delivery.storeadmin.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.user.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        return Optional.ofNullable(
                storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(
                        id,
                        StoreMenuStatus.REGISTERED
                )
        ).orElseThrow(() -> new RuntimeException("스토어의 메뉴를 찾을 수 없습니다."));
    }
}
