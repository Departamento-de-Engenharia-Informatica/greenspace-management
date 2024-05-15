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

            String[] parts = input.split("<");

            String[] teamSize = parts[0].trim().split(";");
            String skills = parts[1].trim();

            int maxTeamSize = Integer.parseInt(teamSize[0]);
            int minTeamSize = Integer.parseInt(teamSize[1]);

            //trim the max and min

            System.out.println(maxTeamSize+" "+minTeamSize);

            if (teamSize.length < 2) {
                System.out.println("Invalid input format. Please provide maximum team size, minimum team size.");
                continue;
            }

            if (maxTeamSize <= minTeamSize) {
                System.out.println("Maximum team size must be greater than minimum team size. Please provide valid sizes.");
                continue;
            }

            String[] modifiedString = formatString(skills);

            for (int i = 0; i < modifiedString.length; i++) {
                System.out.println(modifiedString[i]);
            }
            String[] stringArray = new String[999];

            Set<String> skillNames = setRequiredSkills(stringArray);

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

    public static String[] formatString(String str){
        char i = '<';
        char f = '>';

        if (!str.isEmpty() && str.charAt(0) == i) {
            str = str.substring(1);
        }

        // Remove specified character from the end
        int lastIndex = str.length() - 1;
        if (lastIndex >= 0 && str.charAt(lastIndex) == f) {
            str = str.substring(0, lastIndex);
        }

        String[] strparts = str.split(";");

        return strparts;
    }


    public static Set<String> setRequiredSkills(String[] stringArray) {
        Set<String> skillSet = new HashSet<>();

        // Iterate over the string array
        for (String str : stringArray) {
            // Add trimmed skill to the set (automatically handles duplicates)
            skillSet.add(str.trim());
        }

        // Print the set of unique skills (optional)
        System.out.println("Unique Skills:");
        System.out.println(skillSet);

        return skillSet;
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
