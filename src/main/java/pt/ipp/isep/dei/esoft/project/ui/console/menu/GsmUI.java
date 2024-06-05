package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.ToDoList.ToDoListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GsmUI implements Runnable {


    private final String userEmail;

    /**
     * Constructs a new {@code GsmUI} object.
     */
    public GsmUI(String userEmail) {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();

        this.userEmail = userEmail;
    }

    /**
     * Runs the Green Space Manager user interface.
     * Displays a menu with options for managing green spaces.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI(userEmail)));
        options.add(new MenuItem("List all Green Spaces", new ListGreenSpacesUI(userEmail)));
        options.add(new MenuItem("To-Do List", new ToDoListUI(userEmail)));
        options.add(new MenuItem("Agenda", new AgendaUI(userEmail)));
        options.add(new MenuItem("List Agenda Entries", new ListAgendaEntries()));
        options.add(new MenuItem("Change Agenda Status", new ChangeStatusAgendaEntriesUI()));
        options.add(new MenuItem("Assign Vehicle to Agenda Entry", new AssignVehicleToAgendaEntryUI(userEmail)));
        options.add(new MenuItem("Assign Team to Agenda Entry", new AssignTeamToAgendaUI()));
        // Add other menu options here as needed

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Green Space Manager Menu -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    public static void main(String[] args) {
        // For testing purposes, provide a sample email
        GsmUI gsmUI = new GsmUI("sample@example.com");
        gsmUI.run();
    }


}
