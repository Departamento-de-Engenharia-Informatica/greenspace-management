package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

public class DisplayTeamProposals implements Runnable {

    @Override
    public void run(){
        displayTeamProposals();
    }

    public static void displayTeamProposals() {
        List<TeamProposal> teamProposals = Repositories.getInstance().getTeamProposalRepository().getAllTeamProposals();

        if (teamProposals.isEmpty()) {
            System.out.println("No team proposals found.");
        } else {
            System.out.println("List of Team Proposals:");
            for (TeamProposal proposal : teamProposals) {
                System.out.println("- Team Proposal:");
                System.out.println("  - Maximum Team Size: " + proposal.getMaxTeamSize());
                System.out.println("  - Minimum Team Size: " + proposal.getMinTeamSize());
                System.out.println("  - Required Skills:");
                for (Skill skill : proposal.getRequiredSkills()) {
                    System.out.println("    - " + skill.getSkillName());
                }
                System.out.println("  - Assigned Collaborators:");
                for (Collaborator collaborator : proposal.getSelectedCollaborators()) {
                    System.out.println("    - " + collaborator.getName());
                }
            }
        }
    }
}
