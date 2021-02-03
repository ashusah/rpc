package com.airwallex.rpc.functions;

import java.math.BigDecimal;

public class FunctionContext {

    private final RPCFunction rpcFunction;

    public FunctionContext(RPCFunction rpcFunction) {
        this.rpcFunction = rpcFunction;
    }

    public BigDecimal evaluate(BigDecimal p1, BigDecimal p2) {
        return rpcFunction.evaluate(p1, p2);
    }
}
