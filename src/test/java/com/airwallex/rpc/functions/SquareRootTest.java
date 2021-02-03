package com.airwallex.rpc.functions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareRootTest {

    private SquareRoot squareRoot = new SquareRoot();

    static Stream<Arguments> getNumbers(){
        return Stream.of(Arguments.arguments(new BigDecimal(4), new BigDecimal(9)));

    }

    @ParameterizedTest
    @MethodSource("getNumbers")
    void evaluateSquareRootTest(BigDecimal p1) {
        BigDecimal expectedResult = new BigDecimal(2);
        assertEquals(expectedResult, squareRoot.evaluateUnaryFunction(p1));
    }
}
