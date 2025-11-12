package com.kata.rover.service.action;

import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Position;
import com.kata.rover.domain.Rover;
import com.kata.rover.service.RoverActionFunction;

public class MoveActionFunction implements RoverActionFunction {

    public static final MoveActionFunction INSTANCE = new MoveActionFunction();

    private MoveActionFunction() {
    }

    @Override
    public void apply(Rover rover, Plateau plateau) {
        // TODO implment
    }
}
