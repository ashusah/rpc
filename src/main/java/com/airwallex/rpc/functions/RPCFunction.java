package com.airwallex.rpc.functions;

import java.math.BigDecimal;

public interface RPCFunction {

    boolean isUnaryOperator();

    BigDecimal evaluateUnaryFunction (BigDecimal p1) throws IllegalArgumentException;

    BigDecimal evaluateBinaryFunction (BigDecimal p1, BigDecimal p2);
}
