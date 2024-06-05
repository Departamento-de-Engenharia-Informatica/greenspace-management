package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayTasksUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ColaboratorUI implements Runnable{

    private final String userEmail;

    public ColaboratorUI(String userEmail) {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        this.userEmail = userEmail;

    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Consult my tasks", new DisplayTasksUI(userEmail)));
        //options.add(new MenuItem("Record the completation of a task", new RegisterGreenSpaceUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Collaborator Menu -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }


}
