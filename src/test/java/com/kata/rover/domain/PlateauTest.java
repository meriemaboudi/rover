package com.kata.rover.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PlateauTest {

    public static Stream<Arguments> should_check_boundaries() {
        return Stream.of(
                Arguments.arguments(new Plateau(1, 1), new Position(0, 0), true),
                Arguments.arguments(new Plateau(1, 1), new Position(0, 1), true),
                Arguments.arguments(new Plateau(1, 1), new Position(1, 0), true),
                Arguments.arguments(new Plateau(1, 1), new Position(1, 1), true),
                Arguments.arguments(new Plateau(1, 1), new Position(-1, 1), false),
                Arguments.arguments(new Plateau(1, 1), new Position(1, -1), false),
                Arguments.arguments(new Plateau(1, 1), new Position(1, 2), false),
                Arguments.arguments(new Plateau(1, 1), new Position(2, 1), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_check_boundaries(Plateau plateau, Position position, boolean expectedResult) {
        // WHEN
        var result = plateau.isInBoundaries(position);

        // THEN
        Assertions.assertEquals(expectedResult, result);
    }
}
