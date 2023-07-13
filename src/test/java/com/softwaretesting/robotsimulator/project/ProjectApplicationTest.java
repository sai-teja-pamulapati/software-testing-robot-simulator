package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProjectApplicationTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void processCommandsInputCapitalM() {
        ProjectApplication.getMatrix().initializeMatrix(10);
        ProjectApplication.processCommands("M 6");
        Assertions.assertEquals(0 , ProjectApplication.getMatrix().getXPosition());
        Assertions.assertEquals(6 , ProjectApplication.getMatrix().getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @Test
    void processCommandsInputSmallM() {
        ProjectApplication.getMatrix().initializeMatrix(10);
        ProjectApplication.processCommands("m 9");
        Assertions.assertEquals(0 , ProjectApplication.getMatrix().getXPosition());
        Assertions.assertEquals(9 , ProjectApplication.getMatrix().getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @Test
    void processCommandsInputCapitalP() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("P");
        assertNotNull(outputStreamCaptor.toString().trim());

    }

    @Test
    void processCommandsInputSmallP() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("p");
        assertNotNull(outputStreamCaptor.toString().trim());
    }

    @Test
    void processCommandsInputCapitalL() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("L");
        Assertions.assertEquals(DIRECTION.WEST , ProjectApplication.getMatrix().getDirection());
    }

    @Test
    void processCommandsInputSmallL() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("l");
        Assertions.assertEquals(DIRECTION.WEST , ProjectApplication.getMatrix().getDirection());
    }

    @Test
    void processCommandsInputCapitalR() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("R");
        Assertions.assertEquals(DIRECTION.EAST , ProjectApplication.getMatrix().getDirection());

    }

    @Test
    void processCommandsInputSmallR() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("r");
        Assertions.assertEquals(DIRECTION.EAST , ProjectApplication.getMatrix().getDirection());
    }

    @Test
    void processCommandsInputCapitalU() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("U");
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @Test
    void processCommandsInputSmallU() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("u");
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @Test
    void processCommandsInputCapitalD() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("D");
        Assertions.assertEquals(PEN_POSITION.DOWN , ProjectApplication.getMatrix().getPenPosition());
    }

    @Test
    void processCommandsInputSmallD() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("d");
        Assertions.assertEquals(PEN_POSITION.DOWN , ProjectApplication.getMatrix().getPenPosition());
    }

    @Test
    void processCommandsInputCapitalC() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("C");
        String expectedOutPut = "Position: (0, 0) - Pen: up - Facing: north";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void processCommandsInputSmallC() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("c");
        String expectedOutPut = "Position: (0, 0) - Pen: up - Facing: north";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void processCommandsInvalidInput2() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("m"));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @Test
    void processCommandsInvalidInput() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("  "));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @Test
    void processCommandsNullInput() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands(null));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @Test
    void processFirstCommand() {
    }
}