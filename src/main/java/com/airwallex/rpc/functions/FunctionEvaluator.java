package com.airwallex.rpc.functions;

import com.airwallex.rpc.exception.OperatorNotFoundException;
import com.airwallex.rpc.functions.enums.OperatorEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Stack;

import static com.airwallex.rpc.functions.enums.OperatorEnum.CLEAR;
import static com.airwallex.rpc.functions.enums.OperatorEnum.UNDO;

@Service
public class FunctionEvaluator {

    private static final Stack<Stack<BigDecimal>> momentoStack = new Stack<>();
    private static FunctionContext functionContext;
    private static RPCFunction rpcFunction;
    private static Stack<BigDecimal> stack = new Stack<>();

    public static Stack<BigDecimal> processInput(String input) {
        String[] data = input.split(" ");

        for (int i = 0; i < data.length; i++) {

            final OperatorEnum operatorEnum = OperatorEnum.valueOfOperatorSymbol(data[i]);

            if (operatorEnum != null) {

                if (isASpecialOperator(operatorEnum)) {

                    stack = performSpecialOperation(stack, operatorEnum);

                } else {

                    rpcFunction = resolveFunctionClass(operatorEnum);

                    if (isParamaeterNotSufficient(stack.size(), rpcFunction.isUnaryOperator())) {

                        final String message = String.format("Operator %s (position: %d): Insufficient Parameters",
                                data[i], (i * 2) + 1);

                        System.out.println(message);

                        return stack;
                    }

                    stack = processFunctionOperator(stack);
                }
            } else {
                try {
                    stack.push(new BigDecimal(data[i]));
                } catch (Exception e) {
                    throw new OperatorNotFoundException("Operator Not Found.." + data[i], e);
                }
            }
        }

        return stack;
    }

    private static boolean isParamaeterNotSufficient(int size, boolean isUnaryOperator) {
        return ((isUnaryOperator && size < 1) || (!isUnaryOperator && size < 2));
    }

    private static Stack<BigDecimal> processFunctionOperator(Stack<BigDecimal> stack) {

        momentoStack.push((Stack<BigDecimal>) stack.clone());

        return FunctionEvaluator.call(stack, rpcFunction);

    }

    private static Stack<BigDecimal> performSpecialOperation(Stack<BigDecimal> stack, OperatorEnum operatorEnum) {
        switch (operatorEnum) {
            case CLEAR:
                stack.clear();
                break;
            case UNDO:
                stack = momentoStack.pop();
                break;
        }
        return stack;
    }


    private static RPCFunction resolveFunctionClass(OperatorEnum operatorEnum) {
        switch (operatorEnum) {
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

    private static boolean isASpecialOperator(OperatorEnum operatorEnum) {
        return operatorEnum.equals(UNDO) || operatorEnum.equals(CLEAR);
    }


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
