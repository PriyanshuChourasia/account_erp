package com.codymitra.shared_service.modules.stock_item.enums;

import lombok.Getter;

@Getter
public enum TypeOfSupplyEnum {
    GOODS("GOODS"),
    SERVICES("SERVICES");

    private final String typeOfSupply;

    TypeOfSupplyEnum(String typeOfSupply){
        this.typeOfSupply = typeOfSupply;
    }

}
