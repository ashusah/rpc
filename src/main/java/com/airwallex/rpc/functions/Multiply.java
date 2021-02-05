package com.airwallex.rpc.functions;

import com.airwallex.rpc.functions.enums.OperatorEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static com.airwallex.rpc.functions.enums.OperatorEnum.DIVIDE;
import static com.airwallex.rpc.functions.enums.OperatorEnum.MULTIPLY;

public class Multiply implements RPCFunction {

    private static final String FUNCT_NAME = MULTIPLY.toString();

    @Override
    public String getFunctionName() {
        return FUNCT_NAME;
    }

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
