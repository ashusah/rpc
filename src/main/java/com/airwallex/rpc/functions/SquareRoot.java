package com.airwallex.rpc.functions;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class SquareRoot implements RPCUnaryOperatorFunction {
    private static final String FUNCT_NAME = "sqrt";

    @Override
    public String getFunctionName() {
        return FUNCT_NAME;
    }

    @Override
    public BigDecimal evaluate(BigDecimal p1) {
        MathContext mc = new MathContext(15);
        final BigDecimal result = p1.sqrt(mc);
        return result;
    }
}
