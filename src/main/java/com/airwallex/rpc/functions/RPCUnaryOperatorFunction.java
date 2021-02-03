package com.airwallex.rpc.functions;

import java.math.BigDecimal;

public interface RPCUnaryOperatorFunction extends RPCFunction {

    BigDecimal evaluate(BigDecimal p1);
}
