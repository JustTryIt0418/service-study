package org.delivery.db.storemenu

import org.delivery.db.user.enums.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StoreMenuRepository : JpaRepository<StoreMenuEntity, Long> {
    fun findFirstByIdAndStatusOrderByIdDesc(menuId: Long?, status: StoreMenuStatus?): StoreMenuEntity?

    fun findAllByStoreIdAndStatusOrderBySequenceDesc(storeId: Long?, status: StoreMenuStatus?): List<StoreMenuEntity>?
}
