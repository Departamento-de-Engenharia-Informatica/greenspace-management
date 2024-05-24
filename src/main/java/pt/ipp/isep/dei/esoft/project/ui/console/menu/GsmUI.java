package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GsmUI implements Runnable {

    private final RegisterGreenSpaceController registerGreenSpaceController;
    private final String userEmail;

    /**
     * Constructs a new {@code GsmUI} object.
     */
    public GsmUI(String userEmail) {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        this.registerGreenSpaceController = new RegisterGreenSpaceController(greenSpaceRepository);
        this.userEmail = userEmail;
    }

    /**
     * Runs the Green Space Manager user interface.
     * Displays a menu with options for managing green spaces.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI(registerGreenSpaceController, userEmail)));
        options.add(new MenuItem("List all Green Spaces", new ListGreenSpacesUI(registerGreenSpaceController, userEmail)));
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
