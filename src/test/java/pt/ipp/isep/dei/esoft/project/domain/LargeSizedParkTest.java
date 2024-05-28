package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LargeSizedParkTest {

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
        LargeSizedPark park = new LargeSizedPark("Central Park", 500.0, "user@example.com");
        assertEquals("Central Park", park.getName());
        assertEquals(500.0, park.getArea(), 0.001);
        assertEquals(GreenSpaceType.LARGE_SIZED_PARK, park.getType());
        assertEquals("user@example.com", park.getEmail());
    }

    @Test
    public void testDisplayDetails() {
        LargeSizedPark park = new LargeSizedPark("Central Park", 500.0, "user@example.com");
        park.displayDetails();
        assertTrue(outContent.toString().contains("Large-Sized Park: Central Park, Area: 500.0 sqm"));
    }
}
