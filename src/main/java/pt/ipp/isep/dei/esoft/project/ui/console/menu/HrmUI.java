package pt.ipp.isep.dei.esoft.project.ui.console.menu;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateJobUI;

import java.util.ArrayList;
import java.util.List;
public class HrmUI implements Runnable {
    public HrmUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create new skill", new CreateTaskUI()));
        options.add(new MenuItem("Create new job", new CreateJobUI()));
        options.add(new MenuItem("Create new collaborator with a job", new ShowTextUI("You have chosen Option 3.")));
        options.add(new MenuItem("Option 4", new ShowTextUI("You have chosen Option 4.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Human Resource Manager Menu -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
