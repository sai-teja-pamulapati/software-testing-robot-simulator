package com.softwaretesting.robotsimulator.project;

import lombok.Getter;

@Getter
public enum PEN_POSITION {

    UP("up"), DOWN("down");

    private String position;

    PEN_POSITION(String position) {
        this.position = position;
    }
}
