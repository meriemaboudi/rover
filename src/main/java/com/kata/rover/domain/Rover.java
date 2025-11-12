package com.kata.rover.domain;

public class Rover {

    private Position position;
    private Direction direction;

    public Rover(int x, int y, Direction direction) {
        this(new Position(x, y), direction);
    }

    public Rover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
