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
        matrix.initializeMatrix(8);
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
    public void move() {
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
    public void changePenPosition() {
    }
}