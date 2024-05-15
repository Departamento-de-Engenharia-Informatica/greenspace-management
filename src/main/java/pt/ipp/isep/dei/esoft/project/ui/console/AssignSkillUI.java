package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;
import java.util.Scanner;

/**
 * User interface for assigning skills to collaborators.
 */
public class AssignSkillUI implements Runnable {
    private final AssignSkillController controller;
    private final Scanner scanner;
    private Collaborator selectedCollaborator;

    /**
     * Constructs an instance of {@code AssignSkillUI}.
     */
    public AssignSkillUI() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        this.controller = new AssignSkillController(collaboratorRepository, skillRepository);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the AssignSkillUI, allowing users to assign skills to collaborators.
     */
    @Override
    public void run() {
        System.out.println("--- Assign Skill to Collaborator ---");
        Collaborator collaborator = selectCollaborator();
        if (collaborator != null) {
            chooseAction(collaborator);
        } else {
            addSkillToSelectedCollaborator();
        }
    }

    private void addSkillToSelectedCollaborator() {
        System.out.println("You haven't selected a collaborator yet. Do you want to add a skill without selecting a collaborator?");
        System.out.print("Enter 'Y' for Yes or 'N' for No: ");
        String choice = scanner.nextLine().trim().toUpperCase();
        if (choice.equals("Y")) {
            Collaborator collaborator = selectedCollaborator;
            Skill skill = selectSkill();
            if (skill != null) {
                if (controller.assignSkillToCollaborator(collaborator, skill)) {
                    System.out.println("Skill assigned successfully.");
                } else {
                    System.out.println("Failed to assign skill. Please try again.");
                }
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
            selectedCollaborator = collaborators.get(choice - 1);
            return selectedCollaborator;
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }

    private Skill selectSkill() {
        List<Skill> skills = controller.getAllSkills();
        System.out.println("Select a skill:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
        }
        System.out.print("Enter skill number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice >= 1 && choice <= skills.size()) {
            return skills.get(choice - 1);
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }

    private void chooseAction(Collaborator collaborator) {
        System.out.println("Choose action:");
        System.out.println("1. Add skill");
        System.out.println("2. Remove skill");
        System.out.print("Enter action number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
            case 1:
                Skill skillToAdd = selectSkill();
                if (skillToAdd != null) {
                    if (controller.assignSkillToCollaborator(collaborator, skillToAdd)) {
                        System.out.println("Skill added successfully.");
                    } else {
                        System.out.println("Failed to add skill. Please try again.");
                    }
                }
                break;
            case 2:
                List<Skill> collaboratorSkills = collaborator.getSkills();
                System.out.println("Select a skill to remove:");
                for (int i = 0; i < collaboratorSkills.size(); i++) {
                    System.out.println((i + 1) + ". " + collaboratorSkills.get(i).getSkillName());
                }
                System.out.print("Enter skill number to remove: ");
                int skillToRemoveIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (skillToRemoveIndex >= 1 && skillToRemoveIndex <= collaboratorSkills.size()) {
                    Skill skillToRemove = collaboratorSkills.get(skillToRemoveIndex - 1);
                    if (controller.removeSkillFromCollaborator(collaborator, skillToRemove)) {
                        System.out.println("Skill removed successfully.");
                    } else {
                        System.out.println("Failed to remove skill. Please try again.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
                break;
            default:
                System.out.println("Invalid action.");
        }
    }
}
