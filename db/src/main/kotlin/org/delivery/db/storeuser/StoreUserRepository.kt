package org.delivery.db.storeuser

import org.delivery.db.storeuser.enums.StoreUserStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StoreUserRepository : JpaRepository<StoreUserEntity, Long> {
    fun findFirstByEmailAndStatusOrderByIdDesc(email: String?, status: StoreUserStatus?): StoreUserEntity?
}
