package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.util.ArrayList;
import java.util.List;

public class GsmUI implements Runnable {

    private GreenSpaceRepository greenSpaceRepository;
    private RegisterGreenSpaceController registerGreenSpaceController;

    /**
     * Constructs a new {@code GsmUI} object.
     */
    public GsmUI() {
        greenSpaceRepository = new GreenSpaceRepository();
        registerGreenSpaceController = new RegisterGreenSpaceController(greenSpaceRepository);
    }

    /**
     * Runs the Green Space Manager user interface.
     * Displays a menu with options for managing green spaces.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI(registerGreenSpaceController)));
        options.add(new MenuItem("List all Green Space", new ListGreenSpacesUI(registerGreenSpaceController)));
        // Adicione outras opções de menu aqui, conforme necessário

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Green Space Manager Menu -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    public static void main(String[] args) {
        GsmUI gsmUI = new GsmUI();
        gsmUI.run();
    }
}

