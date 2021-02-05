package com.airwallex.rpc.functions;

import com.airwallex.rpc.exception.OperatorNotFoundException;
import com.airwallex.rpc.functions.enums.OperatorEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Stack;

@Service
public class FunctionEvaluator {

    private static FunctionContext functionContext;


    public static Stack<BigDecimal> call(Stack<BigDecimal> stack, RPCFunction rpcFunction) {

               final BigDecimal result = evaluate(stack, rpcFunction);
                stack.push(result);
                return stack;
    }

    private static BigDecimal evaluate(Stack<BigDecimal> stack, RPCFunction rpcFunction) {

        functionContext = new FunctionContext(rpcFunction);

        final BigDecimal operand2 = stack.pop();

        if (rpcFunction.isUnaryOperator()) {
            return functionContext.evaluate(operand2);
        }

        final BigDecimal operand1 = stack.pop();

        return functionContext.evaluate(operand1, operand2);
    }

}
