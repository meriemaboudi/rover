package com.kata.rover.service.action;

import com.kata.rover.domain.Direction;
import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;
import com.kata.rover.service.RoverActionFunction;

import java.util.Map;

public class LeftRotateActionFunction implements RoverActionFunction {

    public static final LeftRotateActionFunction INSTANCE = new LeftRotateActionFunction();

    private static final Map<Direction, Direction> DIRECTION_MAPPER = Map.of(
            Direction.NORTH, Direction.WEST,
            Direction.WEST, Direction.SOUTH,
            Direction.SOUTH, Direction.EAST,
            Direction.EAST, Direction.NORTH
    );

    private LeftRotateActionFunction() {
    }

    @Override
    public void apply(Rover current, Plateau plateau) {
        current.setDirection(DIRECTION_MAPPER.get(current.getDirection()));
    }
}
