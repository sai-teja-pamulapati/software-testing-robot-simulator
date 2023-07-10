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
        for (int i = size - 1 ; i >= 0 ; i--) {
            int[] subList = matrix[i];
            System.out.print(i + "| ");
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
        switch (rotation) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
        }
    }
    

		
	private void moveRight() {
		if(this.direction == DIRECTION.NORTH) {
			setDirection(DIRECTION.EAST);
		}
		else if(this.direction == DIRECTION.EAST){
			setDirection(DIRECTION.SOUTH);
		}
		else if(this.direction == DIRECTION.SOUTH){
			setDirection(DIRECTION.WEST);
		}
		else if(this.direction == DIRECTION.WEST){
			setDirection(DIRECTION.NORTH);
		}
    	
    }
	
    private void moveLeft() {
		if(this.direction == DIRECTION.NORTH) {
			setDirection(DIRECTION.WEST);
		}
		else if(this.direction == DIRECTION.WEST){
			setDirection(DIRECTION.SOUTH);
		}
		else if(this.direction == DIRECTION.SOUTH){
			setDirection(DIRECTION.EAST);
		}
		else if(this.direction == DIRECTION.EAST){
			setDirection(DIRECTION.NORTH);
		}
    	
    }

    public void move(Integer steps) {
        switch (this.direction) {
            case EAST -> moveEast(steps);
            case WEST -> moveWest(steps);
            case NORTH -> moveNorth(steps);
            case SOUTH -> moveSouth(steps);
        }
    }

    private void moveSouth(Integer steps) {
        if (this.yPosition - steps < 0) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {
            for (int i = this.yPosition ; i >= this.yPosition - steps ; i--) {
                this.matrix[i][this.xPosition] = 1;
            }
        }
        this.yPosition = this.yPosition - steps;
    }

    private void moveNorth(Integer steps) {
        if (this.yPosition + steps >= size) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {
            for (int i = this.yPosition ; i <= this.yPosition + steps ; i++) {
                this.matrix[i][this.xPosition] = 1;
            }
        }
        this.yPosition = this.yPosition + steps;

    }

    private void moveWest(Integer steps) {
        if (this.xPosition - steps < 0) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {
            for (int i = this.xPosition ; i >= this.xPosition - steps ; i--) {
                this.matrix[this.yPosition][i] = 1;
            }
        }
        this.xPosition = this.xPosition - steps;

    }

    private void moveEast(Integer steps) {
        if (this.xPosition + steps >= size) {
            throw new IllegalArgumentException("Robot exceeding the matrix boundaries");
        }
        if (PEN_POSITION.DOWN.equals(this.penPosition)) {
            for (int i = this.xPosition ; i <= this.xPosition + steps ; i++) {
                this.matrix[yPosition][i] = 1;
            }
        }
        this.xPosition = this.xPosition + steps;
    }

    public void printPosition() {
    	System.out.println("Position: (" + xPosition + ", " + yPosition + ") - Pen: " + (penPosition) + " - Facing: "
				+ (direction));
    }

    public void changePenPosition(PEN_POSITION penPosition) {
        setPenPosition(penPosition);
    }

	

}