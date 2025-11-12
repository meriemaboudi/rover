package com.kata.rover.service;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Position;
import com.kata.rover.domain.Rover;
import com.kata.rover.service.model.InputInstructions;
import com.kata.rover.service.model.RoverInstructions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class InstructionsExecutorTest {

    public static Stream<Arguments> should_move_rover() {
        return Stream.of(
                Arguments.arguments(
                        new InputInstructions(
                                new Plateau(3, 3),
                                List.of(
                                        new RoverInstructions(
                                                new Rover(0, 0, Direction.EAST),
                                                List.of(RoverAction.MOVE)
                                        )
                                )
                        ),
                        new Position(1, 0),
                        Direction.EAST
                ),
                Arguments.arguments(
                        new InputInstructions(
                                new Plateau(3, 3),
                                List.of(
                                        new RoverInstructions(
                                                new Rover(0, 0, Direction.EAST),
                                                List.of(RoverAction.MOVE, RoverAction.LEFT, RoverAction.MOVE)
                                        )
                                )
                        ),
                        new Position(1, 1),
                        Direction.NORTH
                ),
                // TODO out of bounds test
                Arguments.arguments(
                        new InputInstructions(
                                new Plateau(2, 2),
                                List.of(
                                        new RoverInstructions(
                                                new Rover(0, 0, Direction.EAST),
                                                List.of(RoverAction.MOVE, RoverAction.MOVE, RoverAction.MOVE)
                                        )
                                )
                        ),
                        new Position(2, 0),
                        Direction.EAST
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_move_rover(InputInstructions inputData, Position expectedPosition, Direction expectedDirection) {
        // WHEN
        var result = InstructionsExecutor.INSTANCE.execute(inputData);

        // THEN
        Assertions.assertEquals(1, result.finalPositions().size());
        var rover = result.finalPositions().getFirst();
        Assertions.assertEquals(expectedPosition, rover.getPosition());
        Assertions.assertEquals(expectedDirection, rover.getDirection());
    }
}
