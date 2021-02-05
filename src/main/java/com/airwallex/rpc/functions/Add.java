package com.airwallex.rpc.functions;

import com.airwallex.rpc.functions.enums.OperatorEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.airwallex.rpc.functions.enums.OperatorEnum.ADD;

@Service
public class Add implements RPCFunction {
    private static final String FUNCT_NAME = ADD.toString();

    @Override
    public String getFunctionName() {
        return FUNCT_NAME;
    }

    @Override
    public boolean isUnaryOperator() {
        return false;
    }

    @Override
    public BigDecimal evaluateBinaryFunction(BigDecimal p1, BigDecimal p2) {
        final BigDecimal result = p1.add(p2);
        result.setScale(15, RoundingMode.HALF_UP);
        return result;
    }

    @Override
    public BigDecimal evaluateUnaryFunction(BigDecimal p1) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }
}
