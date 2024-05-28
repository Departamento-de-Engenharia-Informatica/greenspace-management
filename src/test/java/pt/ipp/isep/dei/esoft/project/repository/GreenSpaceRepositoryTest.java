package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.domain.Garden;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

import static org.junit.Assert.*;

public class GreenSpaceRepositoryTest {

    @Before
    public void setUp() {
        GreenSpaceRepository.getGreenSpaces().clear();
    }

    @After
    public void tearDown() {
        GreenSpaceRepository.getGreenSpaces().clear();
    }

    @Test
    public void testAddAndGetGreenSpaces() {
        GreenSpace greenSpace = new Garden("Central Park", 500.0, "user@example.com");
        GreenSpaceRepository.addGreenSpace(greenSpace);
        List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
        assertEquals(1, greenSpaces.size());
        assertEquals(greenSpace, greenSpaces.get(0));
    }

    @Test
    public void testGetGreenSpaces_EmptyList() {
        List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
        assertTrue(greenSpaces.isEmpty());
    }

    @Test
    public void testAddGreenSpace_Null() {
        GreenSpace greenSpace = null;
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpaceRepository.addGreenSpace(greenSpace);
        });
    }

    @Test
    public void testAddGreenSpace_Duplicate() {
        GreenSpace greenSpace1 = new Garden("Central Park", 500.0, "user1@example.com");
        GreenSpaceRepository.addGreenSpace(greenSpace1);

        GreenSpace greenSpace2 = new Garden("Central Park", 500.0, "user1@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpaceRepository.addGreenSpace(greenSpace2);
        });
    }


    @Test
    public void testAddGreenSpace_InvalidGreenSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpace greenSpace = new Garden("", -500.0, "invalid-email");
            GreenSpaceRepository.addGreenSpace(greenSpace);
        });
    }
}
