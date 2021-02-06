package com.airwallex.rpc.functions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test All Examples input")
public class FunctionEvaluatorTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final Stack<BigDecimal> expectedStack = new Stack<>();
    private static String inputData;
    private static Stack<BigDecimal> actualStack;

    @BeforeAll
    static void setup() {
        inputData = "5 2";
        System.setOut(new PrintStream(outContent));
    }

    private static boolean isSameStack(Stack<BigDecimal> expectedStack, Stack<BigDecimal> actualStack) {
        Stack<BigDecimal> clone = (Stack<BigDecimal>) expectedStack.clone();
        Stack<BigDecimal> clone1 = (Stack<BigDecimal>) actualStack.clone();

        boolean flag = true;
        if (clone.size() != clone1.size()) {
            flag = false;
            return flag;
        }
        while (clone1.empty() == false) {
            if (clone.peek().equals(clone1.peek())) {
                clone.pop();
                clone1.pop();
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Nested
    @DisplayName("Test all Test Cases")
    class Example1 {
        @Test
        @DisplayName("Test for example 1")
        void testExample1() {
            expectedStack.push(new BigDecimal(5));
            expectedStack.push(new BigDecimal(2));

            actualStack = FunctionEvaluator.processInput(inputData);
            assertTrue(isSameStack(expectedStack, actualStack));

        }

        @Nested
        @DisplayName("Push and do square root")
        class Example2 {
            @Test
            @DisplayName("2 sqrt")
            void testExample2() {

                inputData = "2 sqrt";
                expectedStack.push(new BigDecimal(1.414213562373095).setScale(15, RoundingMode.HALF_UP));
                actualStack = FunctionEvaluator.processInput(inputData);
                assertTrue(isSameStack(expectedStack, actualStack));

                inputData = "clear 9 sqrt";
                expectedStack.clear();
                expectedStack.push(new BigDecimal(3));

                actualStack = FunctionEvaluator.processInput(inputData);
                assertTrue(isSameStack(expectedStack, actualStack));
            }

            @Nested
            @DisplayName("Undo Operation")
            class Example3 {

                @Test
                @DisplayName("clear 5 4 3 2 undo undo *")
                void testExample3() {
                    inputData = "clear 5 4 3 2 undo undo *";
                    expectedStack.clear();
                    expectedStack.push(new BigDecimal(20));
                    actualStack = FunctionEvaluator.processInput(inputData);
                    assertTrue(isSameStack(expectedStack, actualStack));
                }

                @Test
                @DisplayName("Insufficient Parameters")
                void testExample4() {
                    inputData = "*";
                    actualStack = FunctionEvaluator.processInput(inputData);
                    assertTrue(isSameStack(expectedStack, actualStack));
                    String actual = StringUtils.replace(outContent.toString(), "\r\n", "");
                    assertEquals("Operator * (position: 1): Insufficient Parameters", actual);
                }
            }
        }
    }
}
