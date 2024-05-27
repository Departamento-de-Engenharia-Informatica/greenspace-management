package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayTasksUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ColaboratorUI implements Runnable{


    public ColaboratorUI() {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Consult my tasks", new DisplayTasksUI()));
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
