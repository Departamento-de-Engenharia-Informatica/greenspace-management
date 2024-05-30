package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AgendaUI implements Runnable {
    private final String userEmail;
    private final AgendaController agendaController;

    public AgendaUI(String userEmail) {
        this.userEmail = userEmail;
        this.agendaController = new AgendaController();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Create Agenda Entry ---");

        // Retrieve user's green spaces
        List<GreenSpace> greenSpaces = agendaController.getGreenSpaces(userEmail);

        if (greenSpaces.isEmpty()) {
            System.out.println("No available Green Spaces for this user.");
            return;
        }

        // Display user's green spaces and let them select one
        System.out.println("Available Green Spaces:");
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }
        int greenSpaceIndex = requestSelection(scanner, greenSpaces.size(), "Green Space");
        GreenSpace selectedGreenSpace = greenSpaces.get(greenSpaceIndex - 1);

        // Retrieve To-Do list entries related to the selected green space
        List<ToDoList> toDoListEntries = agendaController.getToDoListEntries(userEmail, selectedGreenSpace.getName());

        if (toDoListEntries.isEmpty()) {
            System.out.println("No available To-Do list entries for this green space.");
            return;
        }

        // Display To-Do list entries and let the user select one
        System.out.println("Available To-Do List Entries:");
        for (int i = 0; i < toDoListEntries.size(); i++) {
            System.out.println((i + 1) + ". " + toDoListEntries.get(i).getTaskDescription());
        }
        int toDoIndex = requestSelection(scanner, toDoListEntries.size(), "To-Do List Entry");
        ToDoList selectedToDo = toDoListEntries.get(toDoIndex - 1);
        
        LocalDate expectedDate = null;
        
        System.out.print("Expectad date ");
        String input = scanner.nextLine();
        try {
            expectedDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            } 
        catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter date in dd-mm-yyyy format.");
        }
        
       
        String status;
        do {
            System.out.print("Enter status (Planned/Postponed/Canceled/Done): ");
            status = scanner.nextLine().trim();
        } while (!status.equalsIgnoreCase("Planned") && !status.equalsIgnoreCase("Postponed") && !status.equalsIgnoreCase("Canceled") && !status.equalsIgnoreCase("Done"));

        // Create the agenda entry
        Optional<Agenda> agendaEntry = agendaController.createAgendaEntry(
                selectedToDo.getTaskDescription(),
                selectedGreenSpace.getName(),
                expectedDate,
                status
        );

        if (agendaEntry.isPresent()) {
            System.out.println("Agenda entry created successfully.");
        } else {
            System.out.println("Failed to create agenda entry. Ensure the task exists in the To-Do list and the green space is managed by you.");
        }
    }

    private int requestSelection(Scanner scanner, int size, String type) {
        int selection = -1;
        do {
            System.out.printf("Select a %s (1-%d): ", type, size);
            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
            }
            scanner.nextLine(); // Consume newline
        } while (selection < 1 || selection > size);
        return selection;
    }
}
