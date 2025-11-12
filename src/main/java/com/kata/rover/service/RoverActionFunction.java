package com.kata.rover.service;

import com.kata.rover.domain.Plateau;
import com.kata.rover.domain.Rover;

public interface RoverActionFunction {

    void apply(Rover current, Plateau plateau);
}
