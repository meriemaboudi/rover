package com.kata.rover.domain;

import com.kata.rover.exception.ValidationException;

import java.util.Arrays;

public enum Direction {

    NORTH("N", 0, 1),
    SOUTH("S", 0, -1),
    EAST("E", 1, 0),
    WEST("W", -1, 0),

    ;

    private final String id;
    private final int x;
    private final int y;

    Direction(String label, int x, int y) {
        this.id = label;
        this.x = x;
        this.y = y;
    }

    public static Direction resolve(String id) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.id.equals(id))
                .findAny()
                .orElseThrow(() -> new ValidationException("Invalid Direction id " + id));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return id;
    }
}
