package com.softwaretesting.robotsimulator.project;

public enum DIRECTION {
    EAST("east"), WEST("west"), NORTH("north"), SOUTH("south");

    public String getDirection() {
        return direction;
    }

    private String direction;

    DIRECTION(String direction) {
        this.direction = direction;
    }

}
