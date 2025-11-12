package com.kata.rover.service.action;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Position;
import com.kata.rover.domain.Rover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MoveActionFunctionTest {

    public static Stream<Arguments> should_move_rover() {
        return Stream.of(
                Arguments.arguments(new Plateau(3, 3), new Position(1, 1), Direction.EAST, new Position(2, 1)),
                Arguments.arguments(new Plateau(3, 3), new Position(1, 1), Direction.WEST, new Position(0, 1)),
                Arguments.arguments(new Plateau(3, 3), new Position(1, 3), Direction.NORTH, new Position(1, 3)),
                Arguments.arguments(new Plateau(0, 3), new Position(1, 3), Direction.WEST, new Position(0, 3))
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_move_rover(
            Plateau plateau,
            Position currentPosition,
            Direction direction,
            Position expectedPosition
    ) {
        // GIVEN
        var rover = new Rover(currentPosition, direction);

        // WHEN
        MoveActionFunction.INSTANCE.apply(rover, plateau);

        // THEN
        Assertions.assertEquals(expectedPosition, rover.getPosition());
        Assertions.assertEquals(rover.getDirection(), direction);
    }
}
