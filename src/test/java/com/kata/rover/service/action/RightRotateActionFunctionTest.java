package com.kata.rover.service.action;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Position;
import com.kata.rover.domain.Rover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RightRotateActionFunctionTest {

    public static Stream<Arguments> should_move_rover() {
        return Stream.of(
                Arguments.arguments(Direction.NORTH, Direction.EAST),
                Arguments.arguments(Direction.EAST, Direction.SOUTH),
                Arguments.arguments(Direction.SOUTH, Direction.WEST),
                Arguments.arguments(Direction.WEST, Direction.NORTH)
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
        RightRotateActionFunction.INSTANCE.apply(rover, null);

        // THEN
        Assertions.assertEquals(position, rover.getPosition());
        Assertions.assertEquals(rover.getDirection(), expectedDirection);
    }
}
