package com.kata.rover.service;

import com.kata.rover.exception.ValidationException;
import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class InputParserTest {

    public static Stream<Arguments> should_fail_on_invalid_program_argument() {
        return Stream.of(
                Arguments.arguments(new String[0], "Invalid program arguments, expecting exactly 1 argument"),
                Arguments.arguments(new String[2], "Invalid program arguments, expecting exactly 1 argument"),
                Arguments.arguments(new String[]{"dummy.file.xyz"}, "Invalid file path "),
                Arguments.arguments(new String[]{"src/test/resources/data/emptyInput.txt"}, "Invalid file format expecting at least one line"),
                Arguments.arguments(new String[]{"src/test/resources/data/invalidNumberOfLines.txt"}, "Invalid file format, expecting an odd number of lines"),
                Arguments.arguments(new String[]{"src/test/resources/data/invalidDimensions.txt"}, "Failed to parse dimensions line as 2 integers")
        );
    }

    public static Stream<Arguments> should_fail_on_invalid_rover_input() {
        return Stream.of(
                Arguments.arguments("1 3 N", new Plateau(1, 2), "is out of bounds"),
                Arguments.arguments("-1 3 N", new Plateau(3, 3), "is out of bounds"),
                Arguments.arguments("1 3", new Plateau(1, 2), "expecting 3 input data"),
                Arguments.arguments("1 N 3", new Plateau(1, 2), "could not convert coordinates to integer")
        );
    }

    public static Stream<Arguments> should_fail_on_invalid_plateau_input() {
        return Stream.of(
                Arguments.arguments("1 -3", "Invalid Plateau dimensions "),
                Arguments.arguments("X Y", "Failed to parse dimensions line as 2 integers."),
                Arguments.arguments("1 2 3", "Invalid dimensions input. Expected 2 inputs in the first line"),
                Arguments.arguments("", "Invalid dimensions input. Expected 2 inputs in the first line"),
                Arguments.arguments("3.2 4", "Failed to parse dimensions line as 2 integers.")
        );
    }

    public static Stream<Arguments> should_fail_on_invalid_action_list_input() {
        return Stream.of(
                Arguments.arguments("LMX", "Invalid Action 'X'"),
                Arguments.arguments("LMm", "Invalid Action 'm'"),
                Arguments.arguments("LM R", "Invalid Action ' '")
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_fail_on_invalid_program_argument(String[] args, String expectedError) {
        // WHEN
        var error = Assertions.assertThrows(
                ValidationException.class, () -> InputParser.INSTANCE.parseProgramInstructions(args)
        );

        // THEN
        Assertions.assertTrue(error.getMessage().contains(expectedError));
    }

    @Test
    void parse_valid_input() {
        // WHEN
        var result = InputParser.INSTANCE.parseProgramInstructions(new String[]{"src/test/resources/data/validInput.txt"});

        // THEN
        var plateau = result.plateau();
        var roverInstructionsList = result.roverInstructionsList();
        Assertions.assertNotNull(plateau);
        Assertions.assertNotNull(roverInstructionsList);
        Assertions.assertEquals(6, plateau.length());
        Assertions.assertEquals(5, plateau.width());
        Assertions.assertEquals(1, roverInstructionsList.size());
        var roverInstructions = roverInstructionsList.getFirst();
        Assertions.assertEquals(1, roverInstructions.rover().getPosition().x());
        Assertions.assertEquals(0, roverInstructions.rover().getPosition().y());
        Assertions.assertEquals(Direction.NORTH, roverInstructions.rover().getDirection());
        Assertions.assertEquals(3, roverInstructions.actionList().size());
        Assertions.assertEquals(RoverAction.LEFT, roverInstructions.actionList().getFirst());
        Assertions.assertEquals(RoverAction.MOVE, roverInstructions.actionList().get(1));
        Assertions.assertEquals(RoverAction.RIGHT, roverInstructions.actionList().getLast());
    }


    @ParameterizedTest
    @MethodSource
    void should_fail_on_invalid_rover_input(String line, Plateau plateau, String expectedError) {
        // WHEN
        var expected = Assertions.assertThrows(ValidationException.class, () -> InputParser.INSTANCE.parseRover(line, plateau));

        // THEN
        Assertions.assertTrue(expected.getMessage().contains(expectedError));
    }

    @ParameterizedTest
    @MethodSource
    void should_fail_on_invalid_plateau_input(String line, String expectedError) {
        // WHEN
        var expected = Assertions.assertThrows(ValidationException.class, () -> InputParser.INSTANCE.parsePlateau(line));

        // THEN
        Assertions.assertTrue(expected.getMessage().contains(expectedError));
    }

    @ParameterizedTest
    @MethodSource
    void should_fail_on_invalid_action_list_input(String line, String expectedError) {
        // WHEN
        var expected = Assertions.assertThrows(ValidationException.class, () -> InputParser.INSTANCE.parseActionList(line));

        // THEN
        Assertions.assertTrue(expected.getMessage().contains(expectedError));
    }
}
