package com.airwallex.rpc.functions.enums;

public enum OperatorEnum {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    SQRT("sqrt"),
    CLEAR("clear"),
    UNDO("undo");

    private final String operatorSymbol;

    OperatorEnum(String s) {
        this.operatorSymbol = s;
    }

    public static OperatorEnum valueOfOperatorSymbol(String label) {
        for(OperatorEnum e: values()) {
            if(e.operatorSymbol.equals(label))
                return e;
        }
        return null;
    }
}
