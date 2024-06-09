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
     * @throws IllegalArgumentException if the provided GreenSpace is null or if a duplicate GreenSpace is being added
     */
    public static void addGreenSpace(GreenSpace greenSpace) {
        if (greenSpace == null) {
            throw new IllegalArgumentException("GreenSpace cannot be null");
        }
        if (containsGreenSpace(greenSpace)) {
            throw new IllegalArgumentException("Duplicate GreenSpace cannot be added");
        }
        greenSpaces.add(greenSpace);
    }

    /**
     * Checks if the repository contains a duplicate of the provided GreenSpace.
     *
     * @param greenSpace the GreenSpace object to check for duplication
     * @return true if the repository contains a duplicate of the provided GreenSpace, false otherwise
     */
    private static boolean containsGreenSpace(GreenSpace greenSpace) {
        for (GreenSpace gs : greenSpaces) {
            if (gs.getName().equals(greenSpace.getName()) && gs.getEmail().equals(greenSpace.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a list of all GreenSpace objects stored in the repository.
     *
     * @return a list of GreenSpace objects
     */
    public static List<GreenSpace> getGreenSpaces() {
        return new ArrayList<>(greenSpaces);
    }

    /**
     * Retrieves a list of GreenSpace objects managed by the specified user.
     *
     * @param userEmail the user's email
     * @return a list of GreenSpace objects managed by the user
     */
    public static List<GreenSpace> findByUserEmail(String userEmail) {
        List<GreenSpace> userGreenSpaces = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getEmail().equals(userEmail)) {
                userGreenSpaces.add(greenSpace);
            }
        }
        return userGreenSpaces;
    }
}
