package com.softwaretesting.robotsimulator.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("34. R1 -> Robot should be following the given instruction. (Input is capital M)")
    @Test
    void processCommandsInputCapitalM() {
        ProjectApplication.getMatrix().initializeMatrix(10);
        ProjectApplication.processCommands("M 6");
        Assertions.assertEquals(0 , ProjectApplication.getMatrix().getXPosition());
        Assertions.assertEquals(6 , ProjectApplication.getMatrix().getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }

    @DisplayName("35. R1 -> Robot should be following the given instruction. (Input is small M)")
    void processCommandsInputSmallM() {
        ProjectApplication.getMatrix().initializeMatrix(10);
        ProjectApplication.processCommands("m 9");
        Assertions.assertEquals(0 , ProjectApplication.getMatrix().getXPosition());
        Assertions.assertEquals(9 , ProjectApplication.getMatrix().getYPosition());
        Assertions.assertEquals(DIRECTION.NORTH , ProjectApplication.getMatrix().getDirection());
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("36. R1 -> Robot should be following the given instruction. (Input is capital P)")
    @Test
    void processCommandsInputCapitalP() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("P");
        assertNotNull(outputStreamCaptor.toString().trim());

    }
    @DisplayName("37. R1 -> Robot should be following the given instruction. (Input is small P)")
    @Test
    void processCommandsInputSmallP() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("p");
        assertNotNull(outputStreamCaptor.toString().trim());
    }

    @DisplayName("38. R1 -> Robot should be following the given instruction. (Input is capital L)")
    @Test
    void processCommandsInputCapitalL() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("L");
        Assertions.assertEquals(DIRECTION.WEST , ProjectApplication.getMatrix().getDirection());
    }

    @DisplayName("39. R1 -> Robot should be following the given instruction. (Input is small L)")
    @Test
    void processCommandsInputSmallL() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("l");
        Assertions.assertEquals(DIRECTION.WEST , ProjectApplication.getMatrix().getDirection());
    }
    
    @DisplayName("40. R1 -> Robot should be following the given instruction. (Input is capital R)")
    @Test
    void processCommandsInputCapitalR() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("R");
        Assertions.assertEquals(DIRECTION.EAST , ProjectApplication.getMatrix().getDirection());

    }
    
    @DisplayName("41. R1 -> Robot should be following the given instruction. (Input is small R)")
    @Test
    void processCommandsInputSmallR() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("r");
        Assertions.assertEquals(DIRECTION.EAST , ProjectApplication.getMatrix().getDirection());
    }

    @DisplayName("42. R1 -> Robot should be following the given instruction. (Input is capital U)")
    @Test
    void processCommandsInputCapitalU() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("U");
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("43. R1 -> Robot should be following the given instruction. (Input is small U)")
    @Test
    void processCommandsInputSmallU() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("u");
        Assertions.assertEquals(PEN_POSITION.UP , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("44. R1 -> Robot should be following the given instruction. (Input is capital D)")
    @Test
    void processCommandsInputCapitalD() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("D");
        Assertions.assertEquals(PEN_POSITION.DOWN , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("45. R1 -> Robot should be following the given instruction. (Input is small D)")
    @Test
    void processCommandsInputSmallD() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("d");
        Assertions.assertEquals(PEN_POSITION.DOWN , ProjectApplication.getMatrix().getPenPosition());
    }
    
    @DisplayName("46. R1 -> Robot should be following the given instruction. (Input is capital C)")
    @Test
    void processCommandsInputCapitalC() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("C");
        String expectedOutPut = "Position: (0, 0) - Pen: up - Facing: north";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @DisplayName("47. R1 -> Robot should be following the given instruction. (Input is small C)")
    @Test
    void processCommandsInputSmallC() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        ProjectApplication.processCommands("c");
        String expectedOutPut = "Position: (0, 0) - Pen: up - Facing: north";
        Assertions.assertEquals(expectedOutPut , outputStreamCaptor.toString()
                .trim());
    }

    @DisplayName("48. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void processCommandsInvalidInput2() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("m"));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("49. R1 -> Robot should be following the given instruction. (Input is Invalid)")
    @Test
    void processCommandsInvalidInput() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands("  "));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }
    
    
    @DisplayName("50. R1 -> Robot should be following the given instruction. (Input is Null )")
    @Test
    void processCommandsNullInput() {
        ProjectApplication.getMatrix().initializeMatrix(5);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processCommands(null));
        Assertions.assertEquals("Invalid command!" , exception.getMessage());
    }

    @DisplayName("51. R1 -> Robot should be following the given instruction. (First command is Null)")
    @Test
    void ProcessFirstCommandNull(){     		
    	IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processFirstCommand(null));
    	Assertions.assertEquals("Invalid command!", exception.getMessage() );
    }
    
    @DisplayName("52. R1 -> Robot should be following the given instruction. (Initialize the matrix)")
    @Test
    void ProcessFirstCommandInitializeMatrix(){
    	ProjectApplication.processFirstCommand("I 5");
    	Assertions.assertEquals(5, ProjectApplication.getMatrix().getSize());
    	Assertions.assertEquals(0, ProjectApplication.getMatrix().getXPosition());
    	Assertions.assertEquals(0, ProjectApplication.getMatrix().getYPosition());
    	Assertions.assertEquals(DIRECTION.NORTH, ProjectApplication.getMatrix().getDirection());
    	Assertions.assertEquals(PEN_POSITION.UP, ProjectApplication.getMatrix().getPenPosition());
    }
   
    @DisplayName("53. R1 -> Robot should be following the given instruction. (Input is Random)")
    @Test
    void ProcessFirstCommandRandomInput(){
    	
    	IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class , () -> ProjectApplication.processFirstCommand("D"));
    	Assertions.assertEquals("Please Initialize the system first", exception.getMessage());
    }
   }