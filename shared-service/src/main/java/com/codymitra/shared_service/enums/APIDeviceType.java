package com.codymitra.shared_service.enums;

import lombok.Getter;

@Getter
public enum APIDeviceType {
    ALL("all"),
    MOBILE("mobile"),
    DESKTOP("desktop"),
    WEB("web");

    private final String apiDeviceType;

    APIDeviceType(String apiDeviceType){
        this.apiDeviceType = apiDeviceType;
    }

}
