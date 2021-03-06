package com.airwallex.rpc.functions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SubtractTest {

    private Subtract sub = new Subtract();

    static Stream<Arguments> getNumbers(){
        return Stream.of(
                arguments(new BigDecimal(4), new BigDecimal(2), new BigDecimal(2)),
                arguments(new BigDecimal(64), new BigDecimal(8), new BigDecimal(56))
        );

    }

    @ParameterizedTest
    @MethodSource("getNumbers")
    void evaluateSquareRootTest(BigDecimal p1, BigDecimal p2, BigDecimal expectedResult) {
        assertEquals(expectedResult, sub.evaluateBinaryFunction(p1, p2));
    }
}
