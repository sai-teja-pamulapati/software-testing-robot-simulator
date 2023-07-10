package com.softwaretesting.robotsimulator.project;

import lombok.Data;

@Data
public class Matrix {

    private DIRECTION direction;
    private PEN_POSITION penPosition;
    private Integer xPosition;
    private Integer yPosition;
    private Integer size;

    private int[][] matrix = null;

    public int[][] getMatrix() {
        return matrix;
    }

    public DIRECTION getDirection() {
		return direction;
	}

	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}

	public PEN_POSITION getPenPosition() {
		return penPosition;
	}

	public void setPenPosition(PEN_POSITION penPosition) {
		this.penPosition = penPosition;
	}

	public Integer getxPosition() {
		return xPosition;
	}

	public void setxPosition(Integer xPosition) {
		this.xPosition = xPosition;
	}

	public Integer getyPosition() {
		return yPosition;
	}

	public void setyPosition(Integer yPosition) {
		this.yPosition = yPosition;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void show() {
        for (int i = 0 ; i < size ; i++) {
            int[] subList = matrix[i];
            System.out.print((size - i - 1) + "| ");
            for (int j = 0 ; j < size ; j++) {
                System.out.print(subList[j] + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0 ; i < size ; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0 ; i < size ; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void rotate(ROTATION rotation) {
        //TODO
    }

    public void move(Integer steps) {
        switch (this.direction) {
            case EAST -> {
                moveEast(steps);
            }
            case WEST -> {
                moveWest(steps);
            }
            case NORTH -> {
                moveNorth(steps);
            }
            case SOUTH -> {
                moveSouth(steps);
            }
        }
    }

    private void moveSouth(Integer steps) {
        if (this.yPosition - steps < 0) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {

        }
        this.yPosition = this.yPosition - steps;
    }

    private void moveNorth(Integer steps) {
        if (this.yPosition + steps >= size) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {

        }
        this.yPosition = this.yPosition + steps;

    }

    private void moveWest(Integer steps) {
        if (this.xPosition - steps < 0) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {

        }
        this.xPosition = this.xPosition - steps;

    }

    private void moveEast(Integer steps) {
        if (this.xPosition + steps >= size) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {
            for (int i = 0 ; i < steps ; i++) {

            }
        }
        this.xPosition = this.xPosition + steps;
    }

    public void printPosition() {
    	System.out.println("Position: (" + xPosition + ", " + yPosition + ") - Pen: " + (penPosition) + " - Facing: "
				+ (direction));
    }

    public void changePenPosition(PEN_POSITION penPosition) {
        switch(penPosition) {
        case UP:
        	setPenPosition(penPosition.UP);break;
        case DOWN:
        	setPenPosition(penPosition.DOWN);break;
        }
    }

	

}