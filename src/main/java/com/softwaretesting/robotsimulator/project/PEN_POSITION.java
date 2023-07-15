package com.softwaretesting.robotsimulator.project;

public enum PEN_POSITION {

    UP("up"), DOWN("down");

    public String getPosition() {
        return position;
    }

    private String position;

    PEN_POSITION(String position) {
        this.position = position;
    }
}
