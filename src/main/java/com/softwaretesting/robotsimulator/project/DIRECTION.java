package com.softwaretesting.robotsimulator.project;

import lombok.Getter;

@Getter
public enum DIRECTION {
    EAST("east"), WEST("west"), NORTH("north"), SOUTH("south");

    private String direction;

    DIRECTION(String direction) {
        this.direction = direction;
    }
}
