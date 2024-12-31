package org.delivery.db.userordermenu

import org.delivery.db.userordermenu.enums.UserOrderMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderMenuRepository : JpaRepository<UserOrderMenuEntity, Long> {

    // 쿼리 메서드 기준은 변수명을 기준으로 생성됨
    fun findAllByUserOrderIdAndStatus(userOrderId: Long?, status: UserOrderMenuStatus?): List<UserOrderMenuEntity>?
}
