package pt.ipp.isep.dei.esoft.project.ui.console.ToDoList;

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
                    AddNewToDoListEntry.addNewToDoListEntry(controller, scanner, email);
                    break;
                case 2:
                    viewAllToDoListEntries();
                    break;
                case 3:
                    changeToDoListEntryStatus();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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
        System.out.println("3. Update Entry Status");
        System.out.println("4. Exit");
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


    private void changeToDoListEntryStatus() {
        System.out.println("--- Change Status of ToDoList Entry ---");
        List<ToDoList> toDoListEntries = controller.getAllToDoListEntries();
        if (toDoListEntries.isEmpty()) {
            System.out.println("No ToDoList entries found.");
            return;
        }
        System.out.println("Available ToDoList Entries:");
        for (int i = 0; i < toDoListEntries.size(); i++) {
            System.out.println((i + 1) + ". " + toDoListEntries.get(i). getTaskDescription());
        }

        int selectedIndex = requestToDoListSelection(toDoListEntries.size());
        ToDoList selectedEntry = toDoListEntries.get(selectedIndex - 1);

        // Request and validate new status
        String newStatus;
        do {
            System.out.print("Enter new status (Pending/In Progress/Completed): ");
            newStatus = scanner.nextLine().trim();
        } while (!newStatus.equalsIgnoreCase("Pending") && !newStatus.equalsIgnoreCase("In Progress") && !newStatus.equalsIgnoreCase("Completed"));

        controller.updateToDoListStatus(selectedEntry.getTaskDescription(), newStatus);
        System.out.println("Status updated successfully!");
    }

    private int requestToDoListSelection(int maxIndex) {
        int selectedIndex;
        while (true) {
            System.out.print("Select a ToDoList entry by number: ");
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

