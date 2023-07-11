package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MatrixTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Matrix matrix = null;

    @BeforeEach
    public void setUp() {
        matrix = new Matrix();
        matrix.initializeMatrix(10);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void show() {
    }

    @Test
    public void rotate() {
    }

    @Test
    public void moveEastPenUp() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(1);
        matrix.setYPosition(2);
        matrix.setDirection(DIRECTION.EAST);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(4);

        Assertions.assertEquals(5 , this.matrix.getXPosition());
        Assertions.assertEquals(2 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }

    @Test
    public void moveWestPenUp() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(7);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.WEST);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(5);

        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(4 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }


    @Test
    public void moveNorthPenUp() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(2);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.NORTH);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(4);

        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(8 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }


    @Test
    public void moveSouthPenUp() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(1);
        matrix.setYPosition(8);
        matrix.setDirection(DIRECTION.SOUTH);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(7);

        Assertions.assertEquals(1 , this.matrix.getXPosition());
        Assertions.assertEquals(1 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }

    @Test
    public void moveEastPenUpThrowException() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(1);
        matrix.setYPosition(2);
        matrix.setDirection(DIRECTION.EAST);
        matrix.setPenPosition(PEN_POSITION.UP);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.move(10));

        Assertions.assertEquals("Robot exceeding the matrix boundaries" , exception.getMessage());
        Assertions.assertEquals(1 , this.matrix.getXPosition());
        Assertions.assertEquals(2 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }

    @Test
    public void moveWestPenUpThrowException() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(7);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.WEST);
        matrix.setPenPosition(PEN_POSITION.UP);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.move(20));

        Assertions.assertEquals("Robot exceeding the matrix boundaries" , exception.getMessage());
        Assertions.assertEquals(7 , this.matrix.getXPosition());
        Assertions.assertEquals(4 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }


    @Test
    public void moveNorthPenUpThrowException() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(2);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.NORTH);
        matrix.setPenPosition(PEN_POSITION.UP);


        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.move(15));

        Assertions.assertEquals("Robot exceeding the matrix boundaries" , exception.getMessage());
        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(4 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }


    @Test
    public void moveSouthPenUpThrowException() {
        int[][] initialMatrix = this.matrix.getMatrix();
        matrix.setXPosition(1);
        matrix.setYPosition(1);
        matrix.setDirection(DIRECTION.SOUTH);
        matrix.setPenPosition(PEN_POSITION.UP);


        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.move(2));

        Assertions.assertEquals("Robot exceeding the matrix boundaries" , exception.getMessage());
        Assertions.assertEquals(1 , this.matrix.getXPosition());
        Assertions.assertEquals(1 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertEquals(initialMatrix , this.matrix.getMatrix());
    }

    @Test
    public void printPosition() {
        matrix.setYPosition(3);
        matrix.setXPosition(6);
        matrix.setDirection(DIRECTION.SOUTH);
        matrix.setPenPosition(PEN_POSITION.DOWN);

        matrix.printPosition();

        String expectedOutPut = "Position: (6, 3) - Pen: down - Facing: south";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void printPosition2() {
        matrix.setYPosition(4);
        matrix.setXPosition(8);
        matrix.setDirection(DIRECTION.EAST);
        matrix.setPenPosition(PEN_POSITION.UP);

        matrix.printPosition();

        String expectedOutPut = "Position: (8, 4) - Pen: up - Facing: east";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }


    @Test
    public void changePenPosition() {
    }
}