package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StoreRepository : JpaRepository<StoreEntity, Long> {
    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity?

    fun findFirstByIdAndStatus(storeId: Long?, status: StoreStatus?): StoreEntity?

    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>?

    fun findAllByStatusAndCategoryOrderByStarDesc(status: StoreStatus?, category: StoreCategory?): List<StoreEntity>?
}
