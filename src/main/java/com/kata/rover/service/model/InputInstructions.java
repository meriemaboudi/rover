package com.kata.rover.service.model;

import com.kata.rover.domain.Plateau;

import java.util.List;

public record InputInstructions(
        Plateau plateau,
        List<RoverInstructions> roverInstructionsList
) {
}
