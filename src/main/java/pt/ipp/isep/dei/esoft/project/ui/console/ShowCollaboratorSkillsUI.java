package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ShowCollaboratorSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Scanner;

public class ShowCollaboratorSkillsUI implements Runnable {
    private final ShowCollaboratorSkillsController controller;
    private final Scanner scanner;

    public ShowCollaboratorSkillsUI() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.controller = new ShowCollaboratorSkillsController(collaboratorRepository);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("--- Show Collaborator Skills ---");
        Collaborator collaborator = selectCollaborator();
        if (collaborator != null) {
            List<Skill> skills = controller.getCollaboratorSkills(collaborator);
            System.out.println("Skills of " + collaborator.getName() + ":");
            for (Skill skill : skills) {
                System.out.println(skill.getSkillName());
            }
        }
    }


    private Collaborator selectCollaborator() {
        List<Collaborator> collaborators = controller.getAllCollaborators();
        System.out.println("Select a collaborator:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
        System.out.print("Enter collaborator number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice >= 1 && choice <= collaborators.size()) {
            return collaborators.get(choice - 1);
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }
}
