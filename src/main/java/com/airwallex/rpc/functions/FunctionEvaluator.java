package com.airwallex.rpc.functions;

import com.airwallex.rpc.functions.enums.OperatorEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Stack;

@Service
public class FunctionEvaluator {

    private static FunctionContext functionContext;
    private static RPCFunction rpcFunction;
    private static Stack<Stack<BigDecimal>> momentoStack = new Stack<>();


    public static Stack<BigDecimal> call(Stack<BigDecimal> stack, String operator) {

        switch (OperatorEnum.valueOfOperatorSymbol(operator)) {

            case UNDO :
                return momentoStack.pop();

            case CLEAR:
                return new Stack<BigDecimal>();

            default:
                momentoStack.push((Stack<BigDecimal>)stack.clone());
                final BigDecimal operand2 = stack.pop();
                final BigDecimal operand1 = stack.pop();
                final BigDecimal result = evaluate(operand1, operand2, operator);
                stack.push(result);
                return stack;
        }

    }

    private static BigDecimal evaluate(BigDecimal operand1, BigDecimal operand2, String operator) {

        rpcFunction = resolveOperatorClass(operator);

        functionContext = new FunctionContext(rpcFunction);

        final BigDecimal result = functionContext.evaluate(operand1, operand2);

        return result;
    }

    private static RPCFunction resolveOperatorClass(String operator) {

        switch (operator) {
            case "+":
                return new Add();
            case "-":
                return new Subtract();
            default:
                throw new IllegalArgumentException("Incorrect Operator");
        }

    }
}
