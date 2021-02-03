package com.airwallex.rpc.functions;

import java.math.BigDecimal;

public interface RPCBinaryOperatorFunction extends RPCFunction {

    BigDecimal evaluate(BigDecimal p1, BigDecimal p2);
}
