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
                    ViewAllToDoListEntries.viewAllToDoListEntries(controller);
                    break;
                case 3:
                    ChangeToDoListEntryStatus.changeToDoListEntryStatus(controller,scanner);
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
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

