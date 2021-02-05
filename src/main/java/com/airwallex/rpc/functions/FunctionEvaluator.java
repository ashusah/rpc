package com.airwallex.rpc.functions;

import com.airwallex.rpc.exception.OperatorNotFoundException;
import com.airwallex.rpc.functions.enums.OperatorEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Stack;

@Service
public class FunctionEvaluator {

    private static FunctionContext functionContext;
    private static RPCFunction rpcFunction;
    private static final Stack<Stack<BigDecimal>> momentoStack = new Stack<>();


    public static Stack<BigDecimal> call(Stack<BigDecimal> stack, String operator, int pos) {

        switch (OperatorEnum.valueOfOperatorSymbol(operator)) {

            case UNDO:
                return momentoStack.pop();

            case CLEAR:
                return new Stack<BigDecimal>();

            default:

                rpcFunction = resolveOperatorClass(operator);

                if (!isParameterSufficient(stack, rpcFunction)) {
                    String.format("Operator %s (position: %d): Insufficient Parameters", operator, (pos * 2) -1 );
                    return stack;
                }

                momentoStack.push((Stack<BigDecimal>) stack.clone());

                final BigDecimal result = evaluate(stack, operator, rpcFunction);

                stack.push(result);
                return stack;
        }

    }

    private static BigDecimal evaluate(Stack<BigDecimal> stack, String operator, RPCFunction rpcFunction) {

        functionContext = new FunctionContext(rpcFunction);

        final BigDecimal operand2 = stack.pop();

        if (rpcFunction.isUnaryOperator()) {
            return functionContext.evaluate(operand2);
        }

        final BigDecimal operand1 = stack.pop();

        return functionContext.evaluate(operand1, operand2);
    }

    private static boolean isParameterSufficient(Stack stack, RPCFunction rpcFunction) {
        return (rpcFunction.isUnaryOperator() && stack.size() > 0)
                || (!rpcFunction.isUnaryOperator() && stack.size() > 1);
    }

    private static RPCFunction resolveOperatorClass(String operator) {

        switch (OperatorEnum.valueOfOperatorSymbol(operator)) {
            case ADD:
                return new Add();
            case SUBTRACT:
                return new Subtract();
            case MULTIPLY:
                return new Multiply();
            case DIVIDE:
                return new Divide();
            case SQRT:
                return new SquareRoot();
            default:
                throw new OperatorNotFoundException("Operator Not Found");
        }
    }
}
