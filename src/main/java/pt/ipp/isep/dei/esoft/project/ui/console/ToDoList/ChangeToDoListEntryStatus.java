package pt.ipp.isep.dei.esoft.project.ui.console.ToDoList;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.List;
import java.util.Scanner;

public class ChangeToDoListEntryStatus {

    public static void changeToDoListEntryStatus(ToDoListController controller, Scanner scanner) {
        System.out.println("--- Change ToDoList Entry Status ---");
        List<ToDoList> toDoListEntries = controller.getAllToDoListEntries();
        if (toDoListEntries.isEmpty()) {
            System.out.println("No ToDoList entries found.");
        } else {
            for (int i = 0; i < toDoListEntries.size(); i++) {
                System.out.println((i + 1) + ". " + toDoListEntries.get(i).getTaskDescription() + " (Status: " + toDoListEntries.get(i).getStatus() + ")");
            }

            int selectedIndex = requestToDoListSelection(scanner, toDoListEntries.size());
            ToDoList selectedEntry = toDoListEntries.get(selectedIndex - 1);

            String newStatus;
            do {
                System.out.print("Enter new status (Not Started/In Progress/Completed): ");
                newStatus = scanner.nextLine().trim();
            } while (!newStatus.equalsIgnoreCase("Not Started") && !newStatus.equalsIgnoreCase("In Progress") && !newStatus.equalsIgnoreCase("Completed"));

            controller.updateToDoListStatus(selectedEntry.getTaskDescription(), newStatus);
            System.out.println("ToDoList entry status updated successfully!");
        }
    }

    private static int requestToDoListSelection(Scanner scanner, int maxIndex) {
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
