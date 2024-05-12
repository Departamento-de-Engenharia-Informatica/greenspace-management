package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code HrmUI} class represents the Human Resource Manager user interface.
 * It provides a menu with options for managing skills, jobs, and collaborators.
 */
public class HrmUI implements Runnable {

    /**
     * Constructs a new {@code HrmUI} object.
     */
    public HrmUI() {
    }

    /**
     * Runs the Human Resource Manager user interface.
     * Displays a menu with options for managing skills, jobs, and collaborators.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Manage Skills", new RegisterSkillUI()));
        options.add(new MenuItem("Create new job", new CreateJobUI()));
        options.add(new MenuItem("List all the jobs created", new DisplayJobsUI()));
        options.add(new MenuItem("Create new collaborator with a job", new CreateCollaboratorUI()));
        options.add(new MenuItem("Display all collaborators", new DisplayCollaboratorUI()));
        options.add(new MenuItem("Assign or Remove Skills to a Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Show Collaborators Skills", new ShowCollaboratorSkillsUI()));
        options.add(new MenuItem("Create Team Proposal", new TeamProposalUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Human Resource Manager Menu -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
