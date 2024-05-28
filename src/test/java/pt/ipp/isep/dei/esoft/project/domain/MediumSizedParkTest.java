package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediumSizedParkTest {

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
        MediumSizedPark park = new MediumSizedPark("City Park", 300.0, "user@example.com");
        assertEquals("City Park", park.getName());
        assertEquals(300.0, park.getArea(), 0.001);
        assertEquals(GreenSpaceType.MEDIUM_SIZED_PARK, park.getType());
        assertEquals("user@example.com", park.getEmail());
    }

    @Test
    public void testDisplayDetails() {
        MediumSizedPark park = new MediumSizedPark("City Park", 300.0, "user@example.com");
        park.displayDetails();
        assertTrue(outContent.toString().contains("Medium-Sized Park: City Park, Area: 300.0 sqm"));
    }
}
