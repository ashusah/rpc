package com.airwallex.rpc.functions;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class SquareRoot implements RPCFunction {

    @Override
    public boolean isUnaryOperator() {
        return true;
    }

    @Override
    public BigDecimal evaluateUnaryFunction (BigDecimal p1) {
        final double sqrtInDouble = Math.sqrt(p1.doubleValue());
        return BigDecimal.valueOf(sqrtInDouble).setScale(15, RoundingMode.HALF_UP);

    }

    @Override
    public BigDecimal evaluateBinaryFunction(BigDecimal p1, BigDecimal p2) {
        throw new IllegalArgumentException();
    }
}
