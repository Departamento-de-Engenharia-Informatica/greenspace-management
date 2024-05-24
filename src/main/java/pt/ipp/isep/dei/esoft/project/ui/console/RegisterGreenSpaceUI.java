package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.List;
import java.util.Scanner;

public class RegisterGreenSpaceUI implements Runnable {

    private final Scanner scanner;
    private final String userEmail;

    public RegisterGreenSpaceUI(String userEmail) {
        this.scanner = new Scanner(System.in);
        this.userEmail = userEmail;
    }

    public void run() {
        System.out.println("\n--- Green Space Management ---");
        System.out.println("Logged in as: " + userEmail); // Print the logged-in user's email
        System.out.print("Enter name of the green space: ");
        String name = scanner.nextLine();

        System.out.print("Enter area of the green space in square meters: ");
        double area = Double.parseDouble(scanner.nextLine());

        System.out.println("Select type of green space:");
        System.out.println("1. Garden");
        System.out.println("2. Medium-Sized Park");
        System.out.println("3. Large-Sized Park");
        int typeChoice = Integer.parseInt(scanner.nextLine());
        GreenSpaceType type;

        switch (typeChoice) {
            case 1:
                type = GreenSpaceType.GARDEN;
                break;
            case 2:
                type = GreenSpaceType.MEDIUM_SIZED_PARK;
                break;
            case 3:
                type = GreenSpaceType.LARGE_SIZED_PARK;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        RegisterGreenSpaceController.registerGreenSpace(name, area, type, userEmail);
        System.out.println("Green space registered successfully.");

        // Display all registered green spaces
        System.out.println("\n--- Registered Green Spaces ---");
        List<GreenSpace> greenSpaces = RegisterGreenSpaceController.getAllGreenSpaces();
        for (GreenSpace greenSpace : greenSpaces) {
            greenSpace.displayDetails();
        }
    }
}
