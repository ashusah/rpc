package com.airwallex.rpc.calculator;

import com.airwallex.rpc.exception.OperatorNotFoundException;
import com.airwallex.rpc.functions.*;
import com.airwallex.rpc.functions.enums.OperatorEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Stack;

import static com.airwallex.rpc.functions.enums.OperatorEnum.CLEAR;
import static com.airwallex.rpc.functions.enums.OperatorEnum.UNDO;

@Component
public class Calculator implements CommandLineRunner {
    private static final Stack<Stack<BigDecimal>> momentoStack = new Stack<>();
    private static RPCFunction rpcFunction;


    @Override
    public void run(String... args) throws Exception {
        Scanner scanInput = new Scanner(System.in);
        Stack<BigDecimal> stack = new Stack<>();

        while (true) {

            System.out.println("Enter operands and operator");
            String[] input = scanInput.nextLine().split(" ");

            for (int i = 0; i < input.length; i++) {

                final OperatorEnum operatorEnum = OperatorEnum.valueOfOperatorSymbol(input[i]);

                if (operatorEnum != null) {
                    if(isASpecialOperator(operatorEnum)) {
                        stack = performSpecialOperation(stack, operatorEnum);
                    } else {

                        rpcFunction = resolveFunctionClass(operatorEnum);

                        if (!isParameterSufficient(stack, rpcFunction)) {
                            final String message = String.format("Operator %s (position: %d): Insufficient Parameters",
                                    input[i], (i * 2) + 1);
                            System.out.println(message);
                            break;
                        }

                        momentoStack.push((Stack<BigDecimal>) stack.clone());

                        stack = FunctionEvaluator.call(stack, rpcFunction);

                    }
                } else {
                    try {
                        stack.push(new BigDecimal(input[i]));
                    } catch (Exception e) {
                        throw new OperatorNotFoundException("Operator Not Found.." + input[i], e);
                    }

                }
            }

            printStack(stack);
        }
    }

    private static RPCFunction resolveFunctionClass(OperatorEnum operator) {

        switch (operator) {
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

    private static boolean isParameterSufficient(Stack stack, RPCFunction rpcFunction) {
        return (rpcFunction.isUnaryOperator() && stack.size() > 0)
                || (!rpcFunction.isUnaryOperator() && stack.size() > 1);
    }

    private Stack<BigDecimal> performSpecialOperation(Stack<BigDecimal> stack, OperatorEnum operatorEnum) {
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

    private boolean isASpecialOperator(OperatorEnum operatorEnum) {
        return operatorEnum.equals(UNDO) || operatorEnum.equals(CLEAR);
    }

    private void printStack(Stack<BigDecimal> stack) {
        NumberFormat nf = new DecimalFormat("##.##########");
        System.out.print("Stack: ");
        stack.forEach(x -> {
            System.out.print(nf.format(x) + " ");
        });
        System.out.println();
    }

}
