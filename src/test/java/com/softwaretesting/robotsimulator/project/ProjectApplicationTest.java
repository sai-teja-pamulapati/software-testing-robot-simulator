package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.*;

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

    @DisplayName("T34. R1 -> Robot should be following the given instruction. (Input is capital M)")
    @Test
    void processCommandsInputCapitalM() {
        ProjectApplication.getMatrix().initializeMatrix(10);
        ProjectApplication.processCommands("M 6");
        Assertions.assertEquals(0 , ProjectApplication.getMatrix().getXPosition());
        Assertions.assertEquals(6 , ProjectApplication.getMatrix().getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @DisplayName("T35. R1 -> Robot should be following the given instruction. (Input is small M)")
    @Test
    void processCommandsInputSmallM() {
        ProjectApplication.getMatrix().initializeMatrix(10);
        ProjectApplication.processCommands("m 9");
        Assertions.assertEquals(0 , ProjectApplication.getMatrix().getXPosition());
        Assertions.assertEquals(9 , ProjectApplication.getMatrix().getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("T36. R1 -> Robot should be following the given instruction. (Input is capital P)")
    @Test
    void processCommandsInputCapitalP() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("P");
        assertNotNull(outputStreamCaptor.toString().trim());

    }
    @DisplayName("T37. R1 -> Robot should be following the given instruction. (Input is small P)")
    @Test
    void processCommandsInputSmallP() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("p");
        assertNotNull(outputStreamCaptor.toString().trim());
    }

    @DisplayName("T38. R1 -> Robot should be following the given instruction. (Input is capital L)")
    @Test
    void processCommandsInputCapitalL() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("L");
        Assertions.assertEquals(DIRECTION.WEST , ProjectApplication.getMatrix().getDirection());
    }

    @DisplayName("T39. R1 -> Robot should be following the given instruction. (Input is small L)")
    @Test
    void processCommandsInputSmallL() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("l");
        Assertions.assertEquals(DIRECTION.WEST , ProjectApplication.getMatrix().getDirection());
    }
    
    @DisplayName("T40. R1 -> Robot should be following the given instruction. (Input is capital R)")
    @Test
    void processCommandsInputCapitalR() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("R");
        Assertions.assertEquals(DIRECTION.EAST , ProjectApplication.getMatrix().getDirection());

    }
    
    @DisplayName("T41. R1 -> Robot should be following the given instruction. (Input is small R)")
    @Test
    void processCommandsInputSmallR() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("r");
        Assertions.assertEquals(DIRECTION.EAST , ProjectApplication.getMatrix().getDirection());
    }

    @DisplayName("T42. R1 -> Robot should be following the given instruction. (Input is capital U)")
    @Test
    void processCommandsInputCapitalU() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("U");
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("T43. R1 -> Robot should be following the given instruction. (Input is small U)")
    @Test
    void processCommandsInputSmallU() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("u");
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("T44. R1 -> Robot should be following the given instruction. (Input is capital D)")
    @Test
    void processCommandsInputCapitalD() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("D");
        Assertions.assertEquals(PEN_POSITION.DOWN , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("T45. R1 -> Robot should be following the given instruction. (Input is small D)")
    @Test
    void processCommandsInputSmallD() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("d");
        Assertions.assertEquals(PEN_POSITION.DOWN , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("T46. R1 -> Robot should be following the given instruction. (Input is capital C)")
    @Test
    void processCommandsInputCapitalC() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("C");
        String expectedOutPut = "Position: (0, 0) - Pen: up - Facing: north";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @DisplayName("T47. R1 -> Robot should be following the given instruction. (Input is small C)")
    @Test
    void processCommandsInputSmallC() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("c");
        String expectedOutPut = "Position: (0, 0) - Pen: up - Facing: north";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @DisplayName("T48. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void processCommandsInvalidInput() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("m"));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("T49. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void processCommandsInvalidInput2() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("  "));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("T50. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void processCommandsInvalidInput3() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("C 5"));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("T51. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void processCommandsInvalidInput4() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("M c"));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("T52. R1 -> Robot should be following the given instruction. (Input is Null )")
    @Test
    void processCommandsNullInput() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands(null));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("T53. R1 -> Robot should be following the given instruction. (First command is Null)")
    @Test
    void ProcessFirstCommandNull() {
    	IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processFirstCommand(null));
    	Assertions.assertEquals("Invalid command!", exception.getMessage() );
    }

    @DisplayName("T54. R1 -> Robot should be following the given instruction. (Initialize the matrix)")
    @Test
    void ProcessFirstCommandInitializeMatrix(){
    	ProjectApplication.processFirstCommand("I 5");
    	Assertions.assertEquals(5, ProjectApplication.getMatrix().getSize());
    	Assertions.assertEquals(0, ProjectApplication.getMatrix().getXPosition());
    	Assertions.assertEquals(0, ProjectApplication.getMatrix().getYPosition());
    	Assertions.assertEquals(DIRECTION.NORTH, ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @DisplayName("T55. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void ProcessFirstCommandInvalidInput() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processFirstCommand("D"));
        Assertions.assertEquals("Please Initialize the system first" , exception.getMessage());
    }

    @DisplayName("T56. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void ProcessFirstCommandInvalidInput2() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processFirstCommand("i g"));
        Assertions.assertEquals("Please Initialize the system first" , exception.getMessage());
    }

    @DisplayName("T57. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void ProcessFirstCommandInvalidInput3() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processFirstCommand("i 4 s"));
        Assertions.assertEquals("Please Initialize the system first" , exception.getMessage());
    }

}