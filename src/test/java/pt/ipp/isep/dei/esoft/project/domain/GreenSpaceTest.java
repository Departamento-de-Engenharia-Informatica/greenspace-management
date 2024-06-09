package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testNegativeArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", -100.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

    @Test
    public void testZeroArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 0.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

    @Test
    public void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("", 100.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

    @Test
    public void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace(null, 100.0, GreenSpaceType.GARDEN, "test@example.com");
        });
    }

    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, "invalid-email");
        });
    }

    @Test
    public void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 100.0, GreenSpaceType.GARDEN, null);
        });
    }

    @Test
    public void testNullType() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestGreenSpace("Test", 100.0, null, "test@example.com");
        });
    }

    private static class TestGreenSpace extends GreenSpace {
        public TestGreenSpace(String name, double area, GreenSpaceType type, String email) {
            super(name, area, type, email);
        }

        @Override
        public String displayDetails() {
            return "TestGreenSpace{" +
                    "name='" + getName() + '\'' +
                    ", area=" + getArea() +
                    ", type=" + getType() +
                    ", email='" + getEmail() + '\'' +
                    '}';
        }
    }
}
