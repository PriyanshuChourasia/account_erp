package com.codymitra.shared_service.enums;


import lombok.Getter;

@Getter
public enum SectorType {
    PUBLIC_SECTOR(10001),
    PRIVATE_SECTOR(10002);

    private final int sectorType;

    SectorType(int code){
        this.sectorType = code;
    }
}
