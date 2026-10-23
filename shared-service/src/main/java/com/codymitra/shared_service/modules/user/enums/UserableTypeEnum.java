package com.codymitra.shared_service.modules.user.enums;

public enum UserableTypeEnum {
    EMPLOYEE(Values.EMPLOYEE);

    public static final class Values{
        public static final String EMPLOYEE = "EMPLOYEE";
        private Values() {}
    }

    private final String value;

    UserableTypeEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
