package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController;


import java.util.HashSet;
import java.util.Set;

public class TeamProposalUI {

    public void displayTeamProposalForm() {
        // Step 1: Display form for creating a new team
        System.out.println("=== New Team Proposal ===");
        System.out.println("Please enter the details for the new team:");

        // Step 2-5: Request and capture necessary data
        String teamName = "Example Team"; // Placeholder for capturing team name
        int minTeamSize = 3; // Placeholder for capturing min team size
        int maxTeamSize = 5; // Placeholder for capturing max team size
        String description = "Example description"; // Placeholder for capturing description
        Set<Skill> selectedSkills = displaySkillSelection(); // Display skill selection UI

        // Step 6: Display all captured information for confirmation
        displayConfirmation(teamName, minTeamSize, maxTeamSize, description, selectedSkills);

        // Step 7: Ask for confirmation to generate team proposal
        boolean confirmed = confirmData();

        if (confirmed) {
            // Step 8: Create and save team proposal
            TeamProposalController teamProposalController = new TeamProposalController();
            teamProposalController.generateTeamProposal(maxTeamSize, minTeamSize, selectedSkills);

            // Step 10: Display operation success
            displayOperationSuccess();
        } else {
            System.out.println("Team not created.");
        }
    }

    private Set<Skill> displaySkillSelection() {
        // Step 4: Display list of skills and capture user selection
        Set<Skill> selectedSkills = new HashSet<>();
        // Placeholder for skill selection logic
        return selectedSkills;
    }

    private void displayConfirmation(String teamName, int minTeamSize, int maxTeamSize, String description, Set<Skill> selectedSkills) {
        // Step 6: Display captured information for user confirmation
        System.out.println("Team Name: " + teamName);
        System.out.println("Min Team Size: " + minTeamSize);
        System.out.println("Max Team Size: " + maxTeamSize);
        System.out.println("Description: " + description);
        System.out.println("Selected Skills: " + selectedSkills);
    }

    private boolean confirmData() {
        // Step 9: Display confirmation dialog and capture user response
        System.out.println("Confirm data? (yes/no)");
        // Placeholder for confirmation logic
        return true; // For demo purpose, assuming confirmation
    }

    private void displayOperationSuccess() {
        // Step 10: Display operation success message
        System.out.println("Team proposal created successfully!");
    }
}
