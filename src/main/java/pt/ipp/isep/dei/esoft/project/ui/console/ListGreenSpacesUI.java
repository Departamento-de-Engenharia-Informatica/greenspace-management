package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

public class ListGreenSpacesUI implements Runnable {
    private RegisterGreenSpaceController controller;

    public ListGreenSpacesUI(RegisterGreenSpaceController controller) {
        this.controller = controller;
    }

    public void run() {
        System.out.println("\n--- Registered Green Spaces ---");
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        if (greenSpaces.isEmpty()) {
            System.out.println("No green spaces registered.");
        } else {
            for (GreenSpace greenSpace : greenSpaces) {
                greenSpace.displayDetails();
            }
        }
    }
}
