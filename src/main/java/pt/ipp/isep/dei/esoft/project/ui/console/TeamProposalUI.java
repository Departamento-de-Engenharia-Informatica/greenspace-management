package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.*;

public class TeamProposalUI implements Runnable {

    private final TeamProposalController teamProposalController;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;

    public TeamProposalUI() {
        this.teamProposalController = new TeamProposalController();
        this.skillRepository = new SkillRepository();
        this.collaboratorRepository = new CollaboratorRepository();
    }

    @Override
    public void run() {
        displayTeamProposalForm();
    }

    public void displayTeamProposalForm() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter maximum team size; minimum team size; <required skills>:");
            String input = scanner.nextLine().trim();

            // Split the input based on semicolons
            String[] parts = input.split(";");

            if (parts.length < 3) {
                System.out.println("Invalid input format. Please provide maximum team size, minimum team size, and required skills.");
                continue; // Restart the loop to get valid input
            }

            // Extract max team size and min team size
            int maxTeamSize = Integer.parseInt(parts[0].trim());
            int minTeamSize = Integer.parseInt(parts[1].trim());

            // Validate that max team size is greater than min team size
            if (maxTeamSize <= minTeamSize) {
                System.out.println("Maximum team size must be greater than minimum team size. Please provide valid sizes.");
                continue; // Restart the loop to get valid input
            }

            // Extract required skills
            String skillsInput = parts[2].trim();
            Set<String> skillNames = parseRequiredSkills(skillsInput);
            Set<Skill> requiredSkills = convertSkillNamesToSkills(skillNames);

            boolean confirmed = confirmData(maxTeamSize, minTeamSize, requiredSkills);

            if (confirmed) {
                TeamProposal teamProposal = teamProposalController.generateTeamProposal(maxTeamSize, minTeamSize, requiredSkills);
                if (teamProposal != null) {
                    displayTeamProposal(teamProposal, requiredSkills, maxTeamSize, minTeamSize);
                    displayOperationSuccess();
                } else {
                    System.out.println("Failed to generate team proposal. Please try again.");
                }
                break; // Exit the loop after successful team proposal generation
            } else {
                System.out.println("Operation cancelled.");
                break; // Exit the loop if user cancels the operation
            }
        }
    }


    private Set<String> parseRequiredSkills(String skillsInput) {
        // Remove enclosing '<' and '>'
        skillsInput = skillsInput.substring(1, skillsInput.length() - 1).trim();

        // Split skills based on semicolons within the required skills section
        String[] skillArray = skillsInput.split(";");

        // Create a set to hold the extracted skill names
        Set<String> skillNames = new HashSet<>();

        for (String skill : skillArray) {
            // Trim and add each skill name to the set
            skillNames.add(skill.trim());
        }

        return skillNames;
    }

    private Set<Skill> convertSkillNamesToSkills(Set<String> skillNames) {
        Set<Skill> selectedSkills = new HashSet<>();
        for (String name : skillNames) {
            // Retrieve skill from repository (adjust for case-insensitive matching)
            Skill skill = skillRepository.getSkillByName(name.trim());
            if (skill != null) {
                selectedSkills.add(skill);
            } else {
                System.out.println("Skill not found: " + name);
            }
        }
        return selectedSkills;
    }

    private boolean confirmData(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills) {
        System.out.println("Confirm data:");
        System.out.println("Maximum Team Size: " + maxTeamSize);
        System.out.println("Minimum Team Size: " + minTeamSize);
        System.out.println("Required Skills:");
        for (Skill skill : requiredSkills) {
            System.out.println("- " + skill.getSkillName());
        }
        System.out.println("Proceed with team proposal generation? (yes/no)");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    private void displayTeamProposal(TeamProposal teamProposal, Set<Skill> requiredSkills, int maxTeamSize, int minTeamSize) {
        System.out.println("Team Proposal Details:");
        System.out.println("Maximum Team Size: " + maxTeamSize);
        System.out.println("Minimum Team Size: " + minTeamSize);
        System.out.println("Required Skills:");
        for (Skill skill : requiredSkills) {
            System.out.println("- " + skill.getSkillName());
        }
        System.out.println("Proposed Team Members:");

        List<String> teamMemberNames = new ArrayList<>();
        for (Collaborator collaborator : collaboratorRepository.getAll()) {
            if (collaboratorPossessesRequiredSkills(collaborator, requiredSkills)) {
                teamMemberNames.add(collaborator.getName());
            }
        }

        if (teamMemberNames.isEmpty()) {
            System.out.println("No suitable collaborators found with required skills.");
        } else {
            System.out.println("Selected Team Members:");
            for (String name : teamMemberNames) {
                System.out.println("- " + name);
            }
            System.out.println("Total Team Members: " + teamMemberNames.size());
        }
    }

    private boolean collaboratorPossessesRequiredSkills(Collaborator collaborator, Set<Skill> requiredSkills) {
        return collaborator.getSkills().containsAll(requiredSkills);
    }

    private void displayOperationSuccess() {
        System.out.println("Team proposal created successfully!");
    }
}
