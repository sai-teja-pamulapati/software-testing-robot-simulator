package com.softwaretesting.robotsimulator.project;

import lombok.Data;

import java.util.List;

@Data
public class Matrix {

    private DIRECTION direction;
    private PEN_POSITION penPosition;
    private Integer xPosition;
    private Integer yPosition;

    List<List<Integer>> matrix = null;

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    public void show() {
        for(int i=0;i<matrix.size();i++) {
        	List<Integer> subList = matrix.get(i);
        	System.out.print((matrix.size()-i-1)+"| ");
        	for(int j=0; j<subList.size();j++) {
        		System.out.print(subList.get(j)+" ");
        	}
        	System.out.println();
        }
        System.out.print("  ");
        for (int i = 0 ; i < matrix.size() ; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0 ; i < matrix.size() ; i++) {
            System.out.print(i + " ");
        }
    }

    public void rotate(ROTATION rotation) {
        //TODO
    }

    public void move(Integer steps) {
        //TODO
    }

    public void printPosition() {
    	System.out.println("Position: (" + xPosition + ", " + yPosition + ") - Pen: " + (penPosition) + " - Facing: "
				+ (direction));
    }

    public void changePenPosition(PEN_POSITION penPosition) {
        //TODO
    }

}