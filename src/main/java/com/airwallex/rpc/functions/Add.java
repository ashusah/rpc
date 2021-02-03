package com.airwallex.rpc.functions;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class Add implements RPCBinaryOperatorFunction {
    private static final String FUNCT_NAME = "+";

    @Override
    public String getFunctionName() {
        return FUNCT_NAME;
    }

    @Override
    public BigDecimal evaluate(BigDecimal p1, BigDecimal p2) {
        final BigDecimal result = p1.add(p2);
        result.setScale(15, RoundingMode.HALF_UP);
        return result;
    }
}
