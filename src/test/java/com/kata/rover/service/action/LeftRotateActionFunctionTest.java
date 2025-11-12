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

class LeftRotateActionFunctionTest {

    public static Stream<Arguments> should_move_rover() {
        return Stream.of(
                Arguments.arguments(Direction.EAST, Direction.NORTH),
                Arguments.arguments(Direction.NORTH, Direction.WEST),
                Arguments.arguments(Direction.WEST, Direction.SOUTH),
                Arguments.arguments(Direction.SOUTH, Direction.EAST)
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_move_rover(
            Direction direction,
            Direction expectedDirection
    ) {
        // GIVEN
        var position = new Position(1, 1);
        var rover = new Rover(position, direction);

        // WHEN
        LeftRotateActionFunction.INSTANCE.apply(rover, null);

        // THEN
        Assertions.assertEquals(position, rover.getPosition());
        Assertions.assertEquals(rover.getDirection(), expectedDirection);
    }
}
