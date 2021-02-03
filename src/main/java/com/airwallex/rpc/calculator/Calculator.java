package com.airwallex.rpc.calculator;

import com.airwallex.rpc.functions.enums.OperatorEnum;
import com.airwallex.rpc.functions.FunctionEvaluator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

@Component
public class Calculator implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Scanner scanInput = new Scanner(System.in);
        Stack<BigDecimal> stack = new Stack<>();

       while (true){

           System.out.println("Enter operands and operator");
           String[] input = scanInput.nextLine().split(" ");

           for (int i = 0; i<input.length; i++) {

               if(OperatorEnum.valueOfOperatorSymbol(input[i]) != null) {

                   stack = FunctionEvaluator.call(stack, input[i]);

               } else {
                   stack.push(new BigDecimal(input[i]));
               }
           }

          printStack(stack);
       }
    }

    private void printStack(Stack<BigDecimal> stack) {

    }

}
