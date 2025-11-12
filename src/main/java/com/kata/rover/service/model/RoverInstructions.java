package com.kata.rover.service.model;

import com.kata.rover.domain.Rover;
import com.kata.rover.service.RoverAction;

import java.util.List;

public record RoverInstructions(
        Rover rover,
        List<RoverAction> actionList
) {
}
