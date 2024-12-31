package org.delivery.db.userorder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ORDER("주문"),
    ACCEPT("확인"),
    COOKING("조리중"),
    DELIVERY("배달중"),
    RECEIVED("배달완료");

    private String description;
}
