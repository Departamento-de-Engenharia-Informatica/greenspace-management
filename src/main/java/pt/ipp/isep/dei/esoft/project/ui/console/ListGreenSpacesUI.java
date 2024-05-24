package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.config.ConfigReader;

import java.util.List;
import java.util.Scanner;

public class ListGreenSpacesUI implements Runnable {

    private final String userEmail;
    private final Scanner scanner;

    public ListGreenSpacesUI(String userEmail) {
        this.userEmail = userEmail;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n--- Registered Green Spaces ---");
        RegisterGreenSpaceController controller = new RegisterGreenSpaceController();
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        GreenSpaceSorter sortingAlgorithm = ConfigReader.readSortingAlgorithm();
        if (sortingAlgorithm != null) {
            greenSpaces = sortingAlgorithm.sort(greenSpaces);
        } else {
            System.out.println("Error: Sorting algorithm not available.");
            return;
        }
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
