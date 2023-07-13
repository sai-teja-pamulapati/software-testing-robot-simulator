package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        matrix.setXPosition(0);
        matrix.setYPosition(0);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void showMatrixSize1() {
        matrix.initializeMatrix(1);
        matrix.show();
        String value = """
                  0|   \s
                  ---
                     0
                """;
        assertEquals(value.trim() , outputStreamCaptor.toString().trim());
    }

    @Test
    public void showMatrixSize10() {
        matrix.initializeMatrix(10);
        matrix.show();
        String expectedPrintValue = """
                  9|                              \s
                  8|                              \s
                  7|                              \s
                  6|                              \s
                  5|                              \s
                  4|                              \s
                  3|                              \s
                  2|                              \s
                  1|                              \s
                  0|                              \s
                  ------------------------------
                     0  1  2  3  4  5  6  7  8  9
                """;
        assertEquals(expectedPrintValue , outputStreamCaptor.toString());
    }

    @Test
    public void showMatrixPenDown() {
        matrix.initializeMatrix(10);
        matrix.changePenPosition(PEN_POSITION.DOWN);
        matrix.move(5);
        matrix.rotate(ROTATION.RIGHT);
        matrix.move(5);
        matrix.show();
        String expectedPrintValue = """
                  9|                              \s
                  8|                              \s
                  7|                              \s
                  6|                              \s
                  5| *  *  *  *  *  *             \s
                  4| *                            \s
                  3| *                            \s
                  2| *                            \s
                  1| *                            \s
                  0| *                            \s
                  ------------------------------
                     0  1  2  3  4  5  6  7  8  9
                """;
        assertEquals(expectedPrintValue , outputStreamCaptor.toString());
    }

    @Test
    public void rotateRightDirectionNorth() {
        matrix.setDirection(DIRECTION.NORTH);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
    }

    @Test
    public void rotateRightDirectionEast() {
        matrix.setDirection(DIRECTION.EAST);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
    }

    @Test
    public void rotateRightDirectionSouth() {
        matrix.setDirection(DIRECTION.SOUTH);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
    }

    @Test
    public void rotateRightDirectionWest() {
        matrix.setDirection(DIRECTION.WEST);
        this.matrix.rotate(ROTATION.RIGHT);
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
    }

    @Test
    public void rotateLeftDirectionNorth() {
        matrix.setDirection(DIRECTION.NORTH);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.WEST , this.matrix.getDirection());
    }

    @Test
    public void rotateLeftDirectionWest() {
        matrix.setDirection(DIRECTION.WEST);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.SOUTH , this.matrix.getDirection());
    }

    @Test
    public void rotateLeftDirectionSouth() {
        matrix.setDirection(DIRECTION.SOUTH);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.EAST , this.matrix.getDirection());
    }

    @Test
    public void rotateLeftDirectionEast() {
        matrix.setDirection(DIRECTION.EAST);
        this.matrix.rotate(ROTATION.LEFT);
        Assertions.assertEquals(DIRECTION.NORTH , this.matrix.getDirection());
    }

    @Test
    public void setYPositionInvalidValueTest1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setYPosition(15));

        Assertions.assertEquals("Illegal value for Y position." , exception.getMessage());
    }

    @Test
    public void setYPositionInvalidValueTest2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setYPosition(-1));

        Assertions.assertEquals("Illegal value for Y position." , exception.getMessage());
    }

    @Test
    public void setXPositionInvalidValueTest1() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setXPosition(15));

        Assertions.assertEquals("Illegal value for X position." , exception.getMessage());
    }

    @Test
    public void setXPositionInvalidValueTest2() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class , () -> this.matrix.setXPosition(-1));

        Assertions.assertEquals("Illegal value for X position." , exception.getMessage());

    }

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