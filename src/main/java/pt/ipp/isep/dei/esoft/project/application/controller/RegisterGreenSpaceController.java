package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Garden;
import pt.ipp.isep.dei.esoft.project.domain.MediumSizedPark;
import pt.ipp.isep.dei.esoft.project.domain.LargeSizedPark;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List;

/**
 * The {@code RegisterGreenSpaceController} class is responsible for managing the registration
 * and retrieval of green spaces in the system.
 */
public class RegisterGreenSpaceController {

    /**
     * Constructs a new {@code RegisterGreenSpaceController} object.
     */
    public RegisterGreenSpaceController() {
    }

    /**
     * Registers a new green space in the system based on the provided name, area, type, and email.
     *
     * @param name  the name of the green space
     * @param area  the area of the green space in square meters
     * @param type  the type of the green space (GARDEN, MEDIUM_SIZED_PARK, LARGE_SIZED_PARK)
     * @param email the email associated with the green space
     * @throws IllegalArgumentException if the provided green space type is invalid
     */
    public static void registerGreenSpace(String name, double area, GreenSpaceType type, String email) {
        GreenSpace greenSpace;
        switch (type) {
            case GARDEN:
                greenSpace = new Garden(name, area, email);
                break;
            case MEDIUM_SIZED_PARK:
                greenSpace = new MediumSizedPark(name, area, email);
                break;
            case LARGE_SIZED_PARK:
                greenSpace = new LargeSizedPark(name, area, email);
                break;
            default:
                throw new IllegalArgumentException("Invalid Green Space Type");
        }
        GreenSpaceRepository.addGreenSpace(greenSpace);
    }

    /**
     * Retrieves a list of all green spaces registered in the system.
     *
     * @return a list of {@code GreenSpace} objects
     */
    public static List<GreenSpace> getAllGreenSpaces() {
        return GreenSpaceRepository.getGreenSpaces();
    }
}
