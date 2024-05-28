package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing GreenSpace objects.
 */
public class GreenSpaceRepository {
    private static List<GreenSpace> greenSpaces = new ArrayList<>();

    /**
     * Adds a GreenSpace object to the repository.
     *
     * @param greenSpace the GreenSpace object to add
     */
    public static void addGreenSpace(GreenSpace greenSpace) {
        greenSpaces.add(greenSpace);
    }

    /**
     * Retrieves a list of all GreenSpace objects stored in the repository.
     *
     * @return a list of GreenSpace objects
     */
    public static List<GreenSpace> getGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }
}
