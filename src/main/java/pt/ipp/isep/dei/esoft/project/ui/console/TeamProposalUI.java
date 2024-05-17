package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

public class TeamProposalUI implements Runnable {

    private final TeamProposalController teamProposalController;

    public TeamProposalUI() {
        this.teamProposalController = new TeamProposalController();
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

            String[] parts = input.split("<");

            if (parts.length < 2) {
                System.out.println("Invalid input format. Please provide maximum team size, minimum team size, and required skills.");
                continue;
            }

            String[] teamSize = parts[0].trim().split(";");
            String skills = parts[1].trim();

            if (teamSize.length < 2) {
                System.out.println("Invalid input format. Please provide maximum team size and minimum team size.");
                continue;
            }

            int maxTeamSize;
            int minTeamSize;
            try {
                maxTeamSize = Integer.parseInt(teamSize[0].trim());
                minTeamSize = Integer.parseInt(teamSize[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid team size format. Please enter numeric values.");
                continue;
            }

            if (maxTeamSize <= minTeamSize) {
                System.out.println("Maximum team size must be greater than minimum team size. Please provide valid sizes.");
                continue;
            }

            String[] modifiedString = formatString(skills);

            Set<String> skillNames = setRequiredSkills(modifiedString);
            Set<Skill> requiredSkills = convertSkillNamesToSkills(skillNames);

            boolean confirmed = confirmData(maxTeamSize, minTeamSize, skillNames);

            if (confirmed) {
                TeamProposal teamProposal = teamProposalController.generateTeamProposal(maxTeamSize, minTeamSize, requiredSkills);
                if (teamProposal != null) {
                    displayTeamProposal(teamProposal, requiredSkills, maxTeamSize, minTeamSize);
                    Repositories.getInstance().getTeamProposalRepository().addTeamProposal(teamProposal);
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

    public static String[] formatString(String str) {
        char f = '>';
        // Remove specified character from the end
        int lastIndex = str.length() - 1;
        if (lastIndex >= 0 && str.charAt(lastIndex) == f) {
            str = str.substring(0, lastIndex);
        }

        String[] strparts = str.split(";");

        for (int i = 0; i < strparts.length; i++) {
            strparts[i] = strparts[i].trim();
        }

        return strparts;
    }

    public Set<String> setRequiredSkills(String[] modifiedString) {
        return new HashSet<>(Arrays.asList(modifiedString));
    }

    private Set<Skill> convertSkillNamesToSkills(Set<String> skillNames) {
        Set<Skill> requiredSkills = new HashSet<>();
        for (String skillName : skillNames) {
            Skill skill = Repositories.getInstance().getSkillRepository().getSkillByName(skillName);
            if (skill != null) {
                requiredSkills.add(skill);
            }
        }
        return requiredSkills;
    }

    public boolean confirmData(int maxTeamSize, int minTeamSize, Set<String> skillNames) {
        System.out.println("Please confirm the entered data:");
        System.out.println("Maximum Team Size: " + maxTeamSize);
        System.out.println("Minimum Team Size: " + minTeamSize);
        System.out.println("Required Skills: " + skillNames);
        System.out.println("Is this correct? (yes/no)");

        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine().trim().toLowerCase();
        return confirmation.equals("yes");
    }

    private void displayTeamProposal(TeamProposal teamProposal, Set<Skill> requiredSkills, int maxTeamSize, int minTeamSize) {
        System.out.println("Team Proposal Generated:");
        System.out.println("Maximum Team Size: " + maxTeamSize);
        System.out.println("Minimum Team Size: " + minTeamSize);
        System.out.println("Required Skills:");
        for (Skill skill : requiredSkills) {
            System.out.println("- " + skill.getSkillName());
        }

        List<Collaborator> selectedCollaborators = teamProposal.getSelectedCollaborators();
        System.out.println("Selected Collaborators:");
        for (Collaborator collaborator : selectedCollaborators) {
            System.out.println("- " + collaborator.getName() + " (Skills: " + collaborator.getSkills() + ")");
        }
    }

    private void displayOperationSuccess() {
        System.out.println("Operation successful.");
    }
}
