package com.codymitra.shared_service.modules.account_group.enums;

import lombok.Getter;

@Getter
public enum AllocationMethodEnum {
    VALUE(10000),
    WEIGHT(20000),
    QUANTITY(30000),
    PERCENTAGE(40000);

    private final int code;

    AllocationMethodEnum(int allocationMethod) {
        this.code = allocationMethod;
    }
}
