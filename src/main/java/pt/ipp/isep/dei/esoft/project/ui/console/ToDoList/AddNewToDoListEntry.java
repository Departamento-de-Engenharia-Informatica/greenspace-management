package pt.ipp.isep.dei.esoft.project.ui.console.ToDoList;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddNewToDoListEntry {

    public static void addNewToDoListEntry(ToDoListController controller, Scanner scanner, String email) {
        String status = "Pending";
        System.out.println("--- Add New ToDoList Entry ---");

        // Request and validate task description
        String taskDescription;
        do {
            System.out.print("Enter task description: ");
            taskDescription = scanner.nextLine().trim();
        } while (taskDescription.isEmpty());

        // Request and validate urgency
        String urgency;
        do {
            System.out.print("Enter urgency (High/Medium/Low): ");
            urgency = scanner.nextLine().trim();
        } while (!urgency.equalsIgnoreCase("High") && !urgency.equalsIgnoreCase("Medium") && !urgency.equalsIgnoreCase("Low"));

        // Request and validate expected duration
        int expectedDuration;
        do {
            System.out.print("Enter expected duration (in minutes): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.print("Enter expected duration (in minutes): ");
                scanner.next(); // Discard invalid input
            }
            expectedDuration = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        } while (expectedDuration <= 0);

        // Display green spaces available for selection

        List<GreenSpace> greenSpaces = RegisterGreenSpaceController.getAllGreenSpaces();
        List<GreenSpace> userGreenSpaces = new ArrayList<>();

        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getEmail().equals(email)) {
                userGreenSpaces.add(greenSpace);
            }
        }

        if (userGreenSpaces.isEmpty()) {
            System.out.println("No green spaces registered for this user.");
        } else {
            System.out.println("Available Green Spaces:");
            for (int i = 0; i < userGreenSpaces.size(); i++) {
                System.out.println((i + 1) + ". " + userGreenSpaces.get(i).getName());
            }

            int selectedIndex = requestGreenSpaceSelection(scanner, userGreenSpaces.size());
            String selectedGreenSpaceName = userGreenSpaces.get(selectedIndex - 1).getName();

            // Call controller to create ToDoList entry with selected green space name
            controller.createToDoListEntry(taskDescription, urgency, expectedDuration, selectedGreenSpaceName, status, email);
            System.out.println("ToDoList entry added successfully!");
        }
    }

    private static int requestGreenSpaceSelection(Scanner scanner, int maxIndex) {
        int selectedIndex;
        while (true) {
            System.out.print("Select a green space by number: ");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine());
                if (selectedIndex >= 1 && selectedIndex <= maxIndex) {
                    break;
                } else {
                    System.out.println("Invalid selection. Please select a number between 1 and " + maxIndex + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return selectedIndex;
    }
}
