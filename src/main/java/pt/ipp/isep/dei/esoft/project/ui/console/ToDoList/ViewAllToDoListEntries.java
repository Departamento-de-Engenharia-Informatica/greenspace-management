package pt.ipp.isep.dei.esoft.project.ui.console.ToDoList;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.List;

public class ViewAllToDoListEntries {

    public static void viewAllToDoListEntries(ToDoListController controller) {
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
}