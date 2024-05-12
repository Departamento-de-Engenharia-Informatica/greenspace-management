package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamProposalRepository;

import java.util.Set;

public class TeamProposalController {

    private TeamProposalRepository teamProposalRepository;
    private SkillRepository skillRepository;

    public TeamProposalController() {
        this.teamProposalRepository = Repositories.getInstance().getTeamProposalRepository();
        this.skillRepository = Repositories.getInstance().getSkillRepository();
    }

    public TeamProposal generateTeamProposal(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills) {
        TeamProposal teamProposal = new TeamProposal(maxTeamSize, minTeamSize, requiredSkills);
        teamProposalRepository.addTeamProposal(teamProposal);
        return teamProposal;
    }
}
