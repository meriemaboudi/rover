package com.kata.rover.service;

import com.kata.rover.exception.ValidationException;
import com.kata.rover.service.action.LeftRotateActionFunction;
import com.kata.rover.service.action.MoveActionFunction;
import com.kata.rover.service.action.RightRotateActionFunction;

import java.util.Arrays;

public enum RoverAction {

    LEFT("L", LeftRotateActionFunction.INSTANCE),
    RIGHT("R", RightRotateActionFunction.INSTANCE),
    MOVE("M", MoveActionFunction.INSTANCE),

    ;

    private final String id;
    private final RoverActionFunction function;

    RoverAction(String id, RoverActionFunction function) {
        this.id = id;
        this.function = function;
    }

    public static RoverAction resolve(String id) {
        return Arrays.stream(RoverAction.values())
                .filter(positionAction -> positionAction.id.equals(id))
                .findAny()
                .orElseThrow(() -> new ValidationException("Invalid Action '" + id + "'"));
    }

    public RoverActionFunction getFunction() {
        return function;
    }
}
