package org.delivery.api.domain.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreEntity getStoreWithThrow(Long storeId) {
        return Optional.ofNullable(
                storeRepository.findFirstByIdAndStatus(
                        storeId,
                        StoreStatus.REGISTERED
                )
        ).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    @Transactional
    public StoreEntity register(StoreEntity storeEntity) {
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    it.setStar(0);
                    it.setStatus(StoreStatus.REGISTERED);
                    //TODO 등록 일시 추가

                    return storeRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreEntity> searchByCategory(StoreCategory category) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, category);
    }

    public List<StoreEntity> registeredList() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
