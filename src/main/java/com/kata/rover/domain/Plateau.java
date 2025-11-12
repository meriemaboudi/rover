package com.kata.rover.domain;

public record Plateau(
        int width,
        int length
) {

    public boolean isInBoundaries(Position p) {
        return p.x() >= 0 && p.y() >= 0 && p.x() <= width && p.y() <= length;
    }
}
