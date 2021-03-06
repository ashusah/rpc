package com.airwallex.rpc.functions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SquareRootTest {

    private SquareRoot squareRoot = new SquareRoot();

    static Stream<Arguments> getNumbers(){
        return Stream.of(
                arguments(new BigDecimal(4), new BigDecimal(2)),
                arguments(new BigDecimal(64), new BigDecimal(8)));

    }

    @ParameterizedTest
    @MethodSource("getNumbers")
    void evaluateSquareRootTest(BigDecimal p1, BigDecimal expectedSqrt) {
        BigDecimal expectedResult = new BigDecimal(2);
        assertEquals(expectedSqrt, squareRoot.evaluateUnaryFunction(p1));
    }
}
