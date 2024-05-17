package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

public class TeamProposalRepository {

    private List<TeamProposal> teamProposals;

    public TeamProposalRepository() {
        this.teamProposals = new ArrayList<>();
    }

    public void addTeamProposal(TeamProposal teamProposal) {
        // Step 9: Save team proposal to repository
        this.teamProposals.add(teamProposal);
    }

    public List<TeamProposal> getAllTeamProposals(){
        return new ArrayList<>(teamProposals);
    }


}
