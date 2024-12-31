package org.delivery.db.userordermenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "user_order_menu")
@SuperBuilder
public class UserOrderMenuEntity extends BaseEntity {

    @JoinColumn(nullable = false, name = "user_order_id")
    @ManyToOne
    private UserOrderEntity userOrder;

    @JoinColumn(nullable = false)
    @ManyToOne
    private StoreMenuEntity storeMenu;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;

}
