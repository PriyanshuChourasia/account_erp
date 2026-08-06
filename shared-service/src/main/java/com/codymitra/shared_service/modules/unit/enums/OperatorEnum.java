package com.codymitra.shared_service.modules.unit.enums;

public enum OperatorEnum {
    MUL("*"),
    DIV("/");

    private final String operator;

    OperatorEnum(String operator){
        this.operator = operator;
    }

    public String getOperator(){
        return this.operator;
    }
}
