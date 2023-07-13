package com.softwaretesting.robotsimulator.project;

import java.util.Arrays;

public class Matrix {

    private Integer xPosition;
    private Integer yPosition;
    private DIRECTION direction;
    private PEN_POSITION penPosition;
    private Integer size;
    private int[][] matrix = null;

    public void show() {
        for (int i = getSize() - 1 ; i >= 0 ; i--) {
            int[] subArray = getMatrix()[i];
            System.out.format("%3d| " , i);
            for (int j = 0 ; j < getSize() ; j++) {
                if (subArray[j] == 0) {
                    System.out.print("   ");
                } else if (subArray[j] == 1) {
                    System.out.print("*  ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0 ; i < getSize() ; i++) {
            System.out.print("---");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0 ; i < getSize() ; i++) {
            System.out.format("%3d" , i);
        }
        System.out.println();
    }

    public void rotate(ROTATION rotation) {
        switch (rotation) {
            case RIGHT -> rotateRight();
            case LEFT -> rotateLeft();
        }
    }

	private void rotateRight() {
        if (DIRECTION.NORTH.equals(getDirection())) {
            setDirection(DIRECTION.EAST);
        } else if (DIRECTION.EAST.equals(getDirection())) {
            setDirection(DIRECTION.SOUTH);
        } else if (DIRECTION.SOUTH.equals(getDirection())) {
            setDirection(DIRECTION.WEST);
        } else if (DIRECTION.WEST.equals(getDirection())) {
            setDirection(DIRECTION.NORTH);
        }
    }
	
    private void rotateLeft() {
        if (DIRECTION.NORTH.equals(getDirection())) {
            setDirection(DIRECTION.WEST);
        } else if (DIRECTION.WEST.equals(getDirection())) {
            setDirection(DIRECTION.SOUTH);
        } else if (DIRECTION.SOUTH.equals(getDirection())) {
            setDirection(DIRECTION.EAST);
        } else if (DIRECTION.EAST.equals(getDirection())) {
            setDirection(DIRECTION.NORTH);
        }
    }

    public void move(Integer steps) {
        switch (getDirection()) {
            case EAST -> moveEast(steps);
            case WEST -> moveWest(steps);
            case NORTH -> moveNorth(steps);
            case SOUTH -> moveSouth(steps);
        }
    }

    private void moveSouth(Integer steps) {
        if (getYPosition() - steps < 0) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(getPenPosition())) {
            for (int i = getYPosition() ; i >= getYPosition() - steps ; i--) {
                getMatrix()[i][getXPosition()] = 1;
            }
        }
        setYPosition(getYPosition() - steps);
    }

    private void moveNorth(Integer steps) {
        if (getYPosition() + steps >= getSize()) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(getPenPosition())) {
            for (int i = getYPosition() ; i <= getYPosition() + steps ; i++) {
                getMatrix()[i][getXPosition()] = 1;
            }
        }
        setYPosition(getYPosition() + steps);

    }

    private void moveWest(Integer steps) {
        if (getXPosition() - steps < 0) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(getPenPosition())) {
            for (int i = getXPosition() ; i >= getXPosition() - steps ; i--) {
                getMatrix()[getYPosition()][i] = 1;
            }
        }
        setXPosition(getXPosition() - steps);
    }

    private void moveEast(Integer steps) {
        if (getXPosition() + steps >= getSize()) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(getPenPosition())) {
            for (int i = getXPosition() ; i <= getXPosition() + steps ; i++) {
                getMatrix()[getYPosition()][i] = 1;
            }
        }
        setXPosition(getXPosition() + steps);
    }

    public void printPosition() {
        System.out.printf("Position: (%d, %d) - Pen: %s - Facing: %s%n" , getXPosition() , getYPosition() , getPenPosition().getPosition() , getDirection().getDirection());
    }

    public void changePenPosition(PEN_POSITION penPosition) {
        setPenPosition(penPosition);
    }

    public void initializeMatrix(int size) {
        int[][] arrayOfArray = new int[size][size];
        for (int[] array : arrayOfArray) {
            Arrays.fill(array , 0);
        }
        setSize(size);
        setXPosition(0);
        setYPosition(0);
        setPenPosition(PEN_POSITION.UP);
        setDirection(DIRECTION.NORTH);
        setMatrix(arrayOfArray);
    }

    public DIRECTION getDirection() {
        return this.direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public PEN_POSITION getPenPosition() {
        return this.penPosition;
    }

    public void setPenPosition(PEN_POSITION penPosition) {
        this.penPosition = penPosition;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Integer getXPosition() {
        return this.xPosition;
    }

    public void setXPosition(Integer xPosition) {
        if (xPosition < 0 || xPosition > this.size) {
            throw new IllegalArgumentException("Illegal value for X position.");
        }
        this.xPosition = xPosition;
    }

    public Integer getYPosition() {
        return this.yPosition;
    }

    public void setYPosition(Integer yPosition) {
        if (yPosition < 0 || yPosition > this.size) {
            throw new IllegalArgumentException("Illegal value for Y position.");
        }
        this.yPosition = yPosition;
    }

}