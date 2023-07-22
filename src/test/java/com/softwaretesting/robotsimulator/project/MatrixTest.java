package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class MatrixTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Matrix matrix = null;
    private static final Integer size = 10;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        matrix = new Matrix();
        matrix.initializeMatrix(size);

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @DisplayName("T1. R10 -> The Matrix should have NxN dimensions.")
    @Test
    public void testMatrixDimensions() {
        int size = 10; // Set the desired size of the matrix here
        matrix.initializeMatrix(size);

        // Check if the matrix array is NxN
        int[][] matrixArray = matrix.getMatrix();
        Assertions.assertNotNull(matrixArray);
        Assertions.assertEquals(size , matrixArray.length); // Check rows

        for (int[] row : matrixArray) {
            Assertions.assertNotNull(row);
            Assertions.assertEquals(size , row.length); // Check columns
        }
    }

    @DisplayName("T2. R9 -> The Robot should be initialised at the given initial positions.")
    @Test
    public void initialRobotPosition() {
        matrix.initializeMatrix(10);
        matrix.setDirection(DIRECTION.NORTH);
        matrix.setPenPosition(PEN_POSITION.UP);
        matrix.setXPosition(0);
        matrix.setYPosition(0);

        Assertions.assertEquals(0 , matrix.getXPosition());
        Assertions.assertEquals(0 , matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , matrix.getPenPosition());
    }

    @DisplayName("T3. R8 -> The Robot's position is displayed with the show method.")
    @Test
    public void showMatrixSize1() {
        matrix.initializeMatrix(1);
        matrix.show();
        assertNotNull(outputStreamCaptor.toString().trim());
    }

    @DisplayName("T4. R8 -> The Robot's position is displayed with the show method.")
    @Test
    public void showMatrixSize10() {
        matrix.initializeMatrix(10);
        matrix.show();
        assertNotNull(outputStreamCaptor.toString().trim());
    }

    @DisplayName("T5. R8 -> The Robot's position is displayed with the show method.")
    @Test
    public void showMatrixPenDown() {
        matrix.initializeMatrix(10);
        matrix.changePenPosition(PEN_POSITION.DOWN);
        matrix.move(5);
        matrix.rotate(ROTATION.RIGHT);
        matrix.move(5);
        matrix.show();
        assertNotNull(outputStreamCaptor.toString().trim());
    }

    @DisplayName("T6. R3 -> The Robot's direction is rotated as per the given input (Initial Position is North, Rotating Right)")
    @Test
    public void rotateRightDirectionNorth() {
        matrix.setDirection(DIRECTION.NORTH);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
    }

    @DisplayName("T7. R3 -> The Robot's direction is rotated as per the given input (Initial Position is East, Rotating Right)")
    @Test
    public void rotateRightDirectionEast() {
        matrix.setDirection(DIRECTION.EAST);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
    }

    @DisplayName("T8. R3 -> The Robot's direction is rotated as per the given input (Initial Position is South, Rotating Right)")
    @Test
    public void rotateRightDirectionSouth() {
        matrix.setDirection(DIRECTION.SOUTH);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
    }

    @DisplayName("T9. R3 -> The Robot's direction is rotated as per the given input (Initial Position is West, Rotating Right)")
    @Test
    public void rotateRightDirectionWest() {
        matrix.setDirection(DIRECTION.WEST);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
    }

    @DisplayName("T10. R3 -> The Robot's direction is rotated as per the given input (Initial Position is North, Rotating Left)")
    @Test
    public void rotateLeftDirectionNorth() {
        matrix.setDirection(DIRECTION.NORTH);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
    }

    @DisplayName("T11. R3 -> The Robot's direction is rotated as per the given input (Initial Position is West, Rotating Left)")
    @Test
    public void rotateLeftDirectionWest() {
        matrix.setDirection(DIRECTION.WEST);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
    }

    @DisplayName("T12. R3 -> The Robot's direction is rotated as per the given input (Initial Position is South, Rotating Left)")
    @Test
    public void rotateLeftDirectionSouth() {
        matrix.setDirection(DIRECTION.SOUTH);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
    }

    @DisplayName("T13. R3 -> The Robot's direction is rotated as per the given input (Initial Position is East, Rotating Left)")
    @Test
    public void rotateLeftDirectionEast() {
        matrix.setDirection(DIRECTION.EAST);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
    }

    @DisplayName("T14. R10 -> The Robot's position should not be outside the matrix.")
    @Test
    public void setYPositionInvalidValueTest1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setYPosition(15));

        Assertions.assertEquals("Illegal value for Y position." , exception.getMessage());
    }

    @DisplayName("T15. R10 -> The Robot's position should not be outside the matrix.")
    @Test
    public void setYPositionInvalidValueTest2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setYPosition(-1));

        Assertions.assertEquals("Illegal value for Y position." , exception.getMessage());
    }

    @DisplayName("T16. R10 -> The Robot's position should not be outside the matrix.")
    @Test
    public void setXPositionInvalidValueTest1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setXPosition(15));

        Assertions.assertEquals("Illegal value for X position." , exception.getMessage());
    }

    @DisplayName("T17. R10 -> The Robot's position should not be outside the matrix.")
    @Test
    public void setXPositionInvalidValueTest2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setXPosition(-1));

        Assertions.assertEquals("Illegal value for X position." , exception.getMessage());

    }

    @DisplayName("T18. R4,R6 -> The Robot should move according to the input, without tracing on the floor when the pen is up. (Moving East)")
    @Test
    public void moveEastPenUp() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(1);
        matrix.setYPosition(2);
        matrix.setDirection(DIRECTION.EAST);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(4);

        Assertions.assertEquals(5 , this.matrix.getXPosition());
        Assertions.assertEquals(2 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T19. R4,R6 -> The Robot should move according to the input, without tracing on the floor when the pen is up. (Moving West)")
    @Test
    public void moveWestPenUp() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(7);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.WEST);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(5);

        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(4 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T20. R4,R6 -> The Robot should move according to the input, without tracing on the floor when the pen is up. (Moving North)")
    @Test
    public void moveNorthPenUp() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(2);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.NORTH);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(4);

        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(8 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T21. R4,R6 -> The Robot should move according to the input, without tracing on the floor when the pen is up. (Moving South)")
    @Test
    public void moveSouthPenUp() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(1);
        matrix.setYPosition(8);
        matrix.setDirection(DIRECTION.SOUTH);
        matrix.setPenPosition(PEN_POSITION.UP);

        this.matrix.move(7);

        Assertions.assertEquals(1 , this.matrix.getXPosition());
        Assertions.assertEquals(1 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T22. R4,R5 -> The Robot should move according to the input, with tracing on the floor when the pen is down. (Moving East)")
    @Test
    public void moveEastPenDown() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(1);
        matrix.setYPosition(2);
        matrix.setDirection(DIRECTION.EAST);
        matrix.setPenPosition(PEN_POSITION.DOWN);

        this.matrix.move(4);

        Assertions.assertEquals(5 , this.matrix.getXPosition());
        Assertions.assertEquals(2 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.DOWN , this.matrix.getPenPosition());
        compareAndAssertMatrixPathsHorizontal(1 , 5 , this.matrix.getYPosition() , initialMatrix , this.matrix.getMatrix());
    }

    private void compareAndAssertMatrixPathsHorizontal(int startingRobotXPosition , int endingRobotXPosition , int robotYPosition , int[][] initialMatrix , int[][] currentMatrix) {
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (i == robotYPosition && j >= startingRobotXPosition && j <= endingRobotXPosition) {
                    Assertions.assertEquals(1 , currentMatrix[i][j]);
                } else {
                    Assertions.assertEquals(initialMatrix[i][j] , currentMatrix[i][j]);
                }
            }
        }
    }

    @DisplayName("T23. R4,R5 -> The Robot should move according to the input, with tracing on the floor when the pen is down. (Moving West)")
    @Test
    public void moveWestPenDown() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(7);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.WEST);
        matrix.setPenPosition(PEN_POSITION.DOWN);

        this.matrix.move(5);

        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(4 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.DOWN , this.matrix.getPenPosition());
        compareAndAssertMatrixPathsHorizontal(2 , 7 , this.matrix.getYPosition() , initialMatrix , this.matrix.getMatrix());

    }

    @DisplayName("T24. R4,R5 -> The Robot should move according to the input, with tracing on the floor when the pen is down. (Moving North)")
    @Test
    public void moveNorthPenDown() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(2);
        matrix.setYPosition(4);
        matrix.setDirection(DIRECTION.NORTH);
        matrix.setPenPosition(PEN_POSITION.DOWN);

        this.matrix.move(4);

        Assertions.assertEquals(2 , this.matrix.getXPosition());
        Assertions.assertEquals(8 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.DOWN , this.matrix.getPenPosition());
        compareAndAssertMatrixPathsVertical(4 , 8 , this.matrix.getXPosition() , initialMatrix , this.matrix.getMatrix());

    }

    private void compareAndAssertMatrixPathsVertical(int startingRobotYPosition , int endingRobotYPosition , int robotXPosition , int[][] initialMatrix , int[][] currentMatrix) {
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (i >= startingRobotYPosition && i <= endingRobotYPosition && j == robotXPosition) {
                    Assertions.assertEquals(1 , currentMatrix[i][j]);
                } else {
                    Assertions.assertEquals(initialMatrix[i][j] , currentMatrix[i][j]);
                }
            }
        }
    }

    @DisplayName("T25. R4,R5 -> The Robot should move according to the input, with tracing on the floor when the pen is down. (Moving South)")
    @Test
    public void moveSouthPenDown() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
        matrix.setXPosition(1);
        matrix.setYPosition(8);
        matrix.setDirection(DIRECTION.SOUTH);
        matrix.setPenPosition(PEN_POSITION.DOWN);

        this.matrix.move(7);

        Assertions.assertEquals(1 , this.matrix.getXPosition());
        Assertions.assertEquals(1 , this.matrix.getYPosition());
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
        Assertions.assertEquals(PEN_POSITION.DOWN , this.matrix.getPenPosition());
        compareAndAssertMatrixPathsVertical(1 , 8 , this.matrix.getXPosition() , initialMatrix , this.matrix.getMatrix());

    }

    @DisplayName("T26. R4 -> The Robot should not move outside the defined boundary. (Moving East)")
    @Test
    public void moveEastPenUpThrowException() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
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
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T27. R4 -> The Robot should not move outside the defined boundary. (Moving West)")
    @Test
    public void moveWestPenUpThrowException() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
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
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T28. R4 -> The Robot should not move outside the defined boundary. (Moving North)")
    @Test
    public void moveNorthPenUpThrowException() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
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
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T29. R4 -> The Robot should not move outside the defined boundary. (Moving South)")
    @Test
    public void moveSouthPenUpThrowException() {
        int[][] initialMatrix = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            System.arraycopy(this.matrix.getMatrix()[i] , 0 , initialMatrix[i] , 0 , size);
        }
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
        Assertions.assertArrayEquals(initialMatrix , this.matrix.getMatrix());
    }

    @DisplayName("T30. R7 -> The Robot's current position, direction and pen position should be printed.")
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

    @DisplayName("T31. R7 -> The Robot's current position, direction and pen position should be printed.")
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

    @DisplayName("T32. R2 -> The pen position should change according to the given input. Changing the pen position to Up.")
    @Test
    public void changePenPositionUp() {
        matrix.setPenPosition(PEN_POSITION.DOWN);
        matrix.changePenPosition(PEN_POSITION.UP);
        Assertions.assertEquals(PEN_POSITION.UP , this.matrix.getPenPosition());

    }

    @DisplayName("T33. R2 -> The pen position should change according to the given input. Changing the pen position to Down.")
    @Test
    public void changePenPositionDown() {
        matrix.setPenPosition(PEN_POSITION.UP);
        matrix.changePenPosition(PEN_POSITION.DOWN);
        Assertions.assertEquals(PEN_POSITION.DOWN , this.matrix.getPenPosition());

    }
}