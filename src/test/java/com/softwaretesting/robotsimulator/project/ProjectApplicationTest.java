package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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