package com.kata.rover.service;

import com.kata.rover.domain.Plateau;
import com.kata.rover.service.model.ExecutionResult;
import com.kata.rover.service.model.InputInstructions;
import com.kata.rover.service.model.RoverInstructions;

public class InstructionsExecutor {

    public static final InstructionsExecutor INSTANCE = new InstructionsExecutor();

    private InstructionsExecutor() {
    }

    public ExecutionResult execute(InputInstructions inputData) {
        var finalPositions = inputData.roverInstructionsList()
                .stream()
                .peek(instructions -> applyRoverInstructions(instructions, inputData.plateau()))
                .map(RoverInstructions::rover)
                .toList();

        return new ExecutionResult(finalPositions);
    }

    private void applyRoverInstructions(RoverInstructions instructions, Plateau plateau) {
        var currentRover = instructions.rover();
        instructions.actionList()
                .stream()
                .map(RoverAction::getFunction)
                .forEach(fn -> fn.apply(currentRover, plateau));
    }

}
