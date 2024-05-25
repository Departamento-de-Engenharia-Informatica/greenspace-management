package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code ToDoListUI} class represents a user interface for managing ToDoList entries.
 * It allows users to add new ToDoList entries and view existing ones.
 */
public class ToDoListUI implements Runnable {

    private final ToDoListController controller;
    private final Scanner scanner;
    private final String email;

    /**
     * Constructs a new {@code ToDoListUI} object.
     * Initializes the controller and scanner.
     */
    public ToDoListUI(String email) {
        this.controller = new ToDoListController();
        this.scanner = new Scanner(System.in);
        this.email = email;
    }

    /**
     * Runs the UI for managing ToDoList entries.
     * Displays the menu options and prompts the user for input.
     */
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addNewToDoListEntry();
                    break;
                case 2:
                    viewAllToDoListEntries();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    /**
     * Displays the menu options for managing ToDoList entries.
     */
    private void displayMenu() {
        System.out.println("--- ToDoList Menu ---");
        System.out.println("1. Add New ToDoList Entry");
        System.out.println("2. View All ToDoList Entries");
        System.out.println("3. Exit");
    }

    /**
     * Prompts the user for their choice and returns it.
     *
     * @return The user's choice.
     */
    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Adds a new ToDoList entry.
     */
    private void addNewToDoListEntry() {
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

            int selectedIndex = requestGreenSpaceSelection(userGreenSpaces.size());
            String selectedGreenSpaceName = userGreenSpaces.get(selectedIndex - 1).getName();

            // Call controller to create ToDoList entry with selected green space name
            controller.createToDoListEntry(taskDescription, urgency, expectedDuration, selectedGreenSpaceName);
            System.out.println("ToDoList entry added successfully!");
        }
    }



            /**
             * Displays all existing ToDoList entries.
             */
            private void viewAllToDoListEntries () {
                System.out.println("--- All ToDoList Entries ---");
                List<ToDoList> toDoListEntries = controller.getAllToDoListEntries();
                if (toDoListEntries.isEmpty()) {
                    System.out.println("No ToDoList entries found.");
                } else {
                    for (ToDoList entry : toDoListEntries) {
                        System.out.println(entry);
                    }
                }
            }

    private int requestGreenSpaceSelection(int maxIndex) {
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

