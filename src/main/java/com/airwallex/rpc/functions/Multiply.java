package com.airwallex.rpc.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Multiply implements RPCFunction {

    @Override
    public BigDecimal evaluateBinaryFunction(BigDecimal p1, BigDecimal p2) {
        final BigDecimal result = p1.multiply(p2);
        result.setScale(15, RoundingMode.HALF_UP);
        return result;
    }

    @Override
    public boolean isUnaryOperator() {
        return false;
    }

    @Override
    public BigDecimal evaluateUnaryFunction(BigDecimal p1) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }
}
