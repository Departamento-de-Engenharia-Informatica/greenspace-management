package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreenSpaceTest {

    @Test
    public void testGetName() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        String name = greenSpace.getName();
        assertEquals("Test", name);
    }

    @Test
    public void testGetArea() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        double area = greenSpace.getArea();
        assertEquals(100.0, area, 0.001);
    }

    @Test
    public void testGetType() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        GreenSpaceType type = greenSpace.getType();
        assertEquals(GreenSpaceType.GARDEN, type);
    }

    @Test
    public void testGetEmail() {
        GreenSpace greenSpace = new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        String email = greenSpace.getEmail();
        assertEquals("test@example.com", email);
    }

    private class TestGreenSpace extends GreenSpace {
        public TestGreenSpace(String name, double area, GreenSpaceType type, String email) {
            super(name, area, type, email);
        }

        @Override
        public void displayDetails() {
        }
    }
}
