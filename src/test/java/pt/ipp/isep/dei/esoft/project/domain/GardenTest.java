package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GardenTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testConstructorAndGetters() {
        Garden garden = new Garden("Rose Garden", 100.0, "user@example.com");
        assertEquals("Rose Garden", garden.getName());
        assertEquals(100.0, garden.getArea(), 0.001);
        assertEquals(GreenSpaceType.GARDEN, garden.getType());
        assertEquals("user@example.com", garden.getEmail());
    }

    @Test
    public void testDisplayDetails() {
        Garden garden = new Garden("Rose Garden", 100.0, "user@example.com");
        garden.displayDetails();
        assertTrue(outContent.toString().contains("Garden: Rose Garden, Area: 100.0 sqm"));
    }
}
