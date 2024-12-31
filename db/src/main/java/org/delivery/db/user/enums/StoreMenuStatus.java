package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreMenuStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지");

    private String description;
}
