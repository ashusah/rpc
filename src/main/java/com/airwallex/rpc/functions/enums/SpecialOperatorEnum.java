package com.airwallex.rpc.functions.enums;

public enum SpecialOperatorEnum {

    CLEAR("clear"),
    UNDO("undo");

    private final String operatorSymbol;

    @Override
    public String toString() {
        return operatorSymbol;
    }

    SpecialOperatorEnum(String s) {
        this.operatorSymbol = s;
    }

    public static SpecialOperatorEnum valueOfOperatorSymbol(String label) {
        for(SpecialOperatorEnum e: values()) {
            if(e.operatorSymbol.equals(label))
                return e;
        }
        return null;
    }
}
