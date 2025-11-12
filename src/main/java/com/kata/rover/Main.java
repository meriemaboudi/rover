package com.kata.rover;

import com.kata.rover.service.InputParser;
import com.kata.rover.service.InstructionsExecutor;

public class Main {

    public static void main(String[] args) {
        var inputData = InputParser.INSTANCE.parseProgramInstructions(args);

        var result = InstructionsExecutor.INSTANCE.execute(inputData);

        result.finalPositions()
                .forEach(rover ->
                        System.out.printf(
                                "%d %d %s%n",
                                rover.getPosition().x(),
                                rover.getPosition().y(),
                                rover.getDirection()
                        )
                );
    }
}