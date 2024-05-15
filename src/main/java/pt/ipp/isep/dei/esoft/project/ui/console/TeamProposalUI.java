package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillAssignmentRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;

import java.util.*;

public class TeamProposalUI implements Runnable {

    private final TeamProposalController teamProposalController;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillAssignmentRepository skillAssignmentRepository;

    public TeamProposalUI() {
        this.teamProposalController = new TeamProposalController();
        this.skillRepository = new SkillRepository();
        this.collaboratorRepository = new CollaboratorRepository();
        this.skillAssignmentRepository = new SkillAssignmentRepository(); // Inject SkillAssignmentRepository
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

            teamSize[0] = teamSize[0].trim();
            teamSize[1] = teamSize[1].trim();

            int maxTeamSize = Integer.parseInt(teamSize[0]);
            int minTeamSize = Integer.parseInt(teamSize[1]);

            if (teamSize.length < 2) {
                System.out.println("Invalid input format. Please provide maximum team size, minimum team size.");
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

    public static Set<String> setRequiredSkills(String[] stringArray) {
        Set<String> skillSet = new HashSet<>();

        // Iterate over the string array
        for (String str : stringArray) {
            // Add trimmed skill to the set (automatically handles duplicates)
            skillSet.add(str.trim());
        }

        return skillSet;
    }

    private Set<Skill> convertSkillNamesToSkills(Set<String> skillNames) {
        Set<Skill> selectedSkills = new HashSet<>();
        for (String name : skillNames) {
            // Retrieve skill assignments by skill name from repository
            Skill skill = getSkillFromAssignments(name.trim());
            if (skill != null) {
                selectedSkills.add(skill);
            } else {
                System.out.println("Skill not found in assignments: " + name);
            }
        }
        return selectedSkills;
    }

    private Skill getSkillFromAssignments(String skillName) {
        List<SkillAssignment> assignments = skillAssignmentRepository.getSkillAssignmentsBySkillName(skillName);
        if (!assignments.isEmpty()) {
            // Return the skill from the first assignment (assuming one skill per assignment)
            return assignments.get(0).getSkill();
        }
        return null;
    }

    private boolean confirmData(int maxTeamSize, int minTeamSize, Set<String> requiredSkills) {
        System.out.println("Confirm data:");
        System.out.println("Maximum Team Size: " + maxTeamSize);
        System.out.println("Minimum Team Size: " + minTeamSize);
        System.out.println("Required Skills:");

        boolean allSkillsValid = true;

        for (String skillName : requiredSkills) {
            // Check if the skill exists in the repository
            Skill skill = skillRepository.getSkillByName(skillName.trim());
            if (skill == null) {
                System.out.println("- Skill not found: " + skillName);
                allSkillsValid = false;
            } else {
                System.out.println("- " + skill.getSkillName());
            }
        }

        if (!allSkillsValid) {
            System.out.println("Some required skills are not found in the repository.");
            return false;
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

            // Get all collaborators with this skill
            List<Collaborator> collaboratorsWithSkill = getCollaboratorsWithSkill(skill);

            if (collaboratorsWithSkill.isEmpty()) {
                System.out.println("No collaborators assigned with this skill.");
            } else {
                System.out.println("Collaborators with this skill:");
                for (Collaborator collaborator : collaboratorsWithSkill) {
                    System.out.println("- " + collaborator.getName());
                }
            }
        }

        // Proceed with displaying other details of the team proposal
    }

    private void displayOperationSuccess() {
        System.out.println("Team proposal created successfully!");
    }

    private List<Collaborator> getCollaboratorsWithSkill(Skill skill) {
        List<SkillAssignment> assignments = skillAssignmentRepository.getSkillAssignmentsBySkill(skill);
        List<Collaborator> collaboratorsWithSkill = new ArrayList<>();

        for (SkillAssignment assignment : assignments) {
            Collaborator collaborator = assignment.getCollaborator();
            if (collaborator != null) {
                collaboratorsWithSkill.add(collaborator);
            }
        }

        return collaboratorsWithSkill;
    }
}
