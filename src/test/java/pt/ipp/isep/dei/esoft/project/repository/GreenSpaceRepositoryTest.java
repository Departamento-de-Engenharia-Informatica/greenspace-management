package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.domain.Garden;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for GreenSpaceRepository.
 */
public class GreenSpaceRepositoryTest {

    /**
     * Clears the list of GreenSpace objects in the repository before each test.
     */
    @Before
    public void setUp() {
        GreenSpaceRepository.getGreenSpaces().clear();
    }

    /**
     * Clears the list of GreenSpace objects in the repository after each test.
     */
    @After
    public void tearDown() {
        GreenSpaceRepository.getGreenSpaces().clear();
    }

    /**
     * Tests the addition of a GreenSpace object to the repository and its retrieval.
     */
    @Test
    public void testAddAndGetGreenSpaces() {
        GreenSpace greenSpace = new Garden("Central Park", 500.0, "user@example.com");
        GreenSpaceRepository.addGreenSpace(greenSpace);
        List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
        assertEquals(1, greenSpaces.size());
        assertEquals(greenSpace, greenSpaces.get(0));
    }

    /**
     * Tests the retrieval of GreenSpace objects when the repository is empty.
     */
    @Test
    public void testGetGreenSpaces_EmptyList() {
        List<GreenSpace> greenSpaces = GreenSpaceRepository.getGreenSpaces();
        assertTrue(greenSpaces.isEmpty());
    }

    /**
     * Tests adding a null GreenSpace object to the repository.
     */
    @Test
    public void testAddGreenSpace_Null() {
        GreenSpace greenSpace = null;
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpaceRepository.addGreenSpace(greenSpace);
        });
    }

    /**
     * Tests adding a duplicate GreenSpace object to the repository.
     */
    @Test
    public void testAddGreenSpace_Duplicate() {
        GreenSpace greenSpace1 = new Garden("Central Park", 500.0, "user1@example.com");
        GreenSpaceRepository.addGreenSpace(greenSpace1);

        GreenSpace greenSpace2 = new Garden("Central Park", 500.0, "user1@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpaceRepository.addGreenSpace(greenSpace2);
        });
    }

    /**
     * Tests adding an invalid GreenSpace object to the repository.
     */
    @Test
    public void testAddGreenSpace_InvalidGreenSpace() {
        assertThrows(IllegalArgumentException.class, () -> {
            GreenSpace greenSpace = new Garden("", -500.0, "invalid-email");
            GreenSpaceRepository.addGreenSpace(greenSpace);
        });
    }
}
