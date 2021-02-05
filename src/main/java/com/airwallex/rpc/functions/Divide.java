package com.airwallex.rpc.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Divide implements RPCFunction {

    @Override
    public BigDecimal evaluateBinaryFunction(BigDecimal p1, BigDecimal p2) {
        return p1.divide(p2, 15, RoundingMode.HALF_UP);
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
