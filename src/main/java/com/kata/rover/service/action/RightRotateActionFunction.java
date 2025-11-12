package com.kata.rover.service.action;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;
import com.kata.rover.service.RoverActionFunction;

import java.util.Map;

public class RightRotateActionFunction implements RoverActionFunction {

    public static final RightRotateActionFunction INSTANCE = new RightRotateActionFunction();

    private static final Map<Direction, Direction> DIRECTION_MAPPER = Map.of(
            Direction.NORTH, Direction.EAST,
            Direction.EAST, Direction.SOUTH,
            Direction.SOUTH, Direction.WEST,
            Direction.WEST, Direction.NORTH
    );

    private RightRotateActionFunction() {
    }

    @Override
    public void apply(Rover rover, Plateau plateau) {
        rover.setDirection(DIRECTION_MAPPER.get(rover.getDirection()));
    }
}
