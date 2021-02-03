package com.airwallex.rpc.functions;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class SquareRoot implements RPCFunction {
    private static final String FUNCT_NAME = "sqrt";

    @Override
    public String getFunctionName() {
        return FUNCT_NAME;
    }

    @Override
    public BigDecimal evaluateUnaryFunction (BigDecimal p1) {
        MathContext mc = new MathContext(15);
        final BigDecimal result = p1.sqrt(mc);
        return result;
    }

    @Override
    public BigDecimal evaluateBinaryFunction(BigDecimal p1, BigDecimal p2) {
        throw new IllegalArgumentException();
    }
}
