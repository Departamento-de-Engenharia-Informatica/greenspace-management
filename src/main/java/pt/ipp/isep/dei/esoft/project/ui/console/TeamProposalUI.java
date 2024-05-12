package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.*;

public class TeamProposalUI implements Runnable{

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
        System.out.println("Enter maximum team size; minimum team size; <required skills>:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        // Split the input based on semicolons
        String[] parts = input.split(";");

        if (parts.length < 3) {
            System.out.println("Invalid input format. Please provide maximum team size, minimum team size, and required skills.");
            return;
        }

        // Extract max team size and min team size
        int maxTeamSize = Integer.parseInt(parts[0].trim());
        int minTeamSize = Integer.parseInt(parts[1].trim());

        // Extract required skills
        String skillsInput = parts[2].trim();
        Set<String> skillNames = parseSkillNames(skillsInput);
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
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private Set<String> parseSkillNames(String skillsInput) {
        // Remove '<' and '>'
        skillsInput = skillsInput.substring(1, skillsInput.length() - 1);
        // Split skill names based on semicolons
        String[] skillArray = skillsInput.split(";");
        // Trim and add to set
        Set<String> skillNames = new HashSet<>();
        for (String skill : skillArray) {
            skillNames.add(skill.trim());
        }
        return skillNames;
    }

    private Set<Skill> convertSkillNamesToSkills(Set<String> skillNames) {
        Set<Skill> selectedSkills = new HashSet<>();
        for (String name : skillNames) {
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
        System.out.println("Required Skills: " + requiredSkills);
        System.out.println("Proceed with team proposal generation? (yes/no)");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    private void displayTeamProposal(TeamProposal teamProposal, Set<Skill> requiredSkills, int maxTeamSize, int minTeamSize) {
        System.out.println("Team Proposal Details:");
        System.out.println("Maximum Team Size: " + maxTeamSize);
        System.out.println("Minimum Team Size: " + minTeamSize);
        System.out.println("Required Skills: " + requiredSkills);
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
