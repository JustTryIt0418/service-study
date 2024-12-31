package org.delivery.db.userorder

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.ToString
import org.delivery.db.store.StoreEntity
import org.delivery.db.userorder.enums.UserOrderStatus
import org.delivery.db.userordermenu.UserOrderMenuEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "user_order")
class UserOrderEntity (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long ?= null,

    @field:Column(nullable = false)
    var userId: Long ?= null,

    @field:JoinColumn(nullable = false, name = "store_id")
    @field:ManyToOne
    var store: StoreEntity ?= null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderStatus ?= null,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal ?= null,

    var orderedAt: LocalDateTime ?= null,

    var acceptedAt: LocalDateTime ?= null,

    var cookingStartedAt: LocalDateTime ?= null,

    var deliveryStartedAt: LocalDateTime ?= null,

    var receivedAt: LocalDateTime ?= null,

    @field:OneToMany(mappedBy = "userOrder")
    //@field:ToString.Exclude       // 롬복의 어노테이션이라 동작 안함
    @field:JsonIgnore               // ObjectMapper로 Json 파싱 시 재귀호출 방지
    var userOrderMenuList: MutableList<UserOrderMenuEntity> ?= null
) {
    override fun toString(): String {
        return "UserOrderEntity(receivedAt=$receivedAt, deliveryStartedAt=$deliveryStartedAt, cookingStartedAt=$cookingStartedAt, acceptedAt=$acceptedAt, orderedAt=$orderedAt, amount=$amount, status=$status, store=$store, userId=$userId, id=$id)"
    }
}