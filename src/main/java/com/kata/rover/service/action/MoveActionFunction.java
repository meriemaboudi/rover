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
        var nextPosition = new Position(
                rover.getPosition().x() + rover.getDirection().getX(),
                rover.getPosition().y() + rover.getDirection().getY()
        );
        if (plateau.isInBoundaries(nextPosition)) {
            rover.setPosition(nextPosition);
        } else {
            // TODO define out of bound strategy, for now just skipping
        }
    }
}
