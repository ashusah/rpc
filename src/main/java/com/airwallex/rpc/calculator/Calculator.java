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

            String input = scanInput.nextLine();

            stack = FunctionEvaluator.processInput(input);

            printStack(stack);
        }
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
