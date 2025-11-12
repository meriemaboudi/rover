package com.kata.rover.service;

import com.kata.rover.exception.TechnicalException;
import com.kata.rover.exception.ValidationException;
import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Position;
import com.kata.rover.domain.Rover;
import com.kata.rover.service.model.InputInstructions;
import com.kata.rover.service.model.RoverInstructions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static final InputParser INSTANCE = new InputParser();

    private InputParser() {
    }

    public InputInstructions parseProgramInstructions(String[] args) {
        var filePath = extractInputFilePath(args);
        try {
            var instructions = Files.readAllLines(filePath);
            validateFileFormat(instructions);

            var plateau = parsePlateau(instructions.getFirst());

            var roverInstructionList = new ArrayList<RoverInstructions>();
            for (int idx = 1; idx < instructions.size(); idx += 2) {
                var roverDescriptor = instructions.get(idx);
                var actionsDescriptor = instructions.get(idx + 1);
                var rover = parseRover(roverDescriptor, plateau);
                var actions = parseActionList(actionsDescriptor);
                roverInstructionList.add(new RoverInstructions(rover, actions));
            }
            return new InputInstructions(plateau, roverInstructionList);
        } catch (IOException e) {
            throw new TechnicalException("Failed to read file " + filePath, e);
        }
    }

    Rover parseRover(String line, Plateau plateau) {
        var positionInput = line.split(" ");
        if (positionInput.length != 3) {
            throw new ValidationException("Invalid rover position input [" + line + "], expecting 3 input data");
        }
        try {
            var startingPosition = new Position(
                    Integer.parseInt(positionInput[0]),
                    Integer.parseInt(positionInput[1])
            );
            if (!plateau.isInBoundaries(startingPosition)) {
                throw new ValidationException("Starting position for rover [" + line + "] is out of bounds");
            }
            return new Rover(
                    startingPosition,
                    Direction.resolve(positionInput[2])
            );
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid rover position input, " + line + ", could not convert coordinates to integer");
        }
    }

    List<RoverAction> parseActionList(String line) {
        return line.strip()
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(RoverAction::resolve)
                .toList();
    }


    Plateau parsePlateau(String line) {
        var dimensionInstruction = line.split(" ");
        if (dimensionInstruction.length != 2) {
            throw new ValidationException("Invalid dimensions input. Expected 2 inputs in the first line");
        }
        try {
            var width = Integer.parseInt(dimensionInstruction[0]);
            var height = Integer.parseInt(dimensionInstruction[1]);

            if (width < 1 || height < 1) {
                throw new ValidationException("Invalid Plateau dimensions " + line);
            }
            return new Plateau(width, height);
        } catch (NumberFormatException e) {
            throw new ValidationException("Failed to parse dimensions line as 2 integers.");
        }
    }

    private void validateFileFormat(List<String> instructions) {
        if (instructions.isEmpty()) {
            throw new ValidationException("Invalid file format expecting at least one line.");
        }
        if (instructions.size() % 2 != 1) {
            throw new ValidationException("Invalid file format, expecting an odd number of lines");
        }
    }

    private Path extractInputFilePath(String[] args) {
        if (args.length != 1) {
            throw new ValidationException("Invalid program arguments, expecting exactly 1 argument.");
        }
        var filePath = Paths.get(args[0]);
        if (!filePath.toFile().exists()) {
            throw new ValidationException("Invalid file path " + filePath);
        }
        return filePath;
    }
}
