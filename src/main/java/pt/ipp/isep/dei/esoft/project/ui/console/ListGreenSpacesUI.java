package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

public class ListGreenSpacesUI implements Runnable {

    private final String userEmail;

    public ListGreenSpacesUI( String userEmail) {
        this.userEmail = userEmail;
    }

    public void run() {
        System.out.println("\n--- Registered Green Spaces ---");
        List<GreenSpace> greenSpaces = RegisterGreenSpaceController.getAllGreenSpaces();
        if (greenSpaces.isEmpty()) {
            System.out.println("No green spaces registered.");
        } else {
            boolean found = false;
            for (GreenSpace greenSpace : greenSpaces) {
                if (greenSpace.getEmail().equals(userEmail)) {
                    greenSpace.displayDetails();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No green spaces registered for this user.");
            }
        }
    }
}
