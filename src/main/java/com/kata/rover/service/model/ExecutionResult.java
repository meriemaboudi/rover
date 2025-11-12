package com.kata.rover.service.model;

import com.kata.rover.domain.Rover;

import java.util.List;

public record ExecutionResult(
        List<Rover> finalPositions
) {
}
