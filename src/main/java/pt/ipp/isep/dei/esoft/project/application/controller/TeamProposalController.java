package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillAssignmentRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamProposalRepository;
import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;


import java.util.*;

public class TeamProposalController {

    private static TeamProposalRepository teamProposalRepository;
    private SkillAssignmentRepository skillAssignmentRepository;
    private CollaboratorRepository collaboratorRepository;

    public TeamProposalController() {
        this.teamProposalRepository = Repositories.getInstance().getTeamProposalRepository();
        this.skillAssignmentRepository = Repositories.getInstance().getSkillAssignmentRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }

    public static List<TeamProposal> getAllTeamProposals() {
        return teamProposalRepository.getAllTeamProposals();
    }

    public Set<Collaborator> findCollaboratorsForSkills(Set<Skill> requiredSkills) {
        Set<Collaborator> collaborators = new HashSet<>();
        for (Skill skill : requiredSkills) {
            Set<Collaborator> collaboratorsWithSkill = skillAssignmentRepository.getCollaboratorsWithSkill(skill);
            collaborators.addAll(collaboratorsWithSkill);
        }
        return collaborators;
    }
    public TeamProposal generateTeamProposal(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills) {
        // Find collaborators with the required skills
        Set<Collaborator> collaborators = findCollaboratorsForSkills(requiredSkills);

        // Check if there are enough collaborators
        if (collaborators.size() < minTeamSize) {
            System.out.println("There are not enough collaborators with the required skills to form a team.");
            return null;
        }

        // Check if there are too many collaborators
        if (collaborators.size() > maxTeamSize) {
            System.out.println("There are too many collaborators with the required skills to form a team. Selecting the first ones.");

            // Convert set to list to maintain order
            List<Collaborator> collaboratorList = new ArrayList<>(collaborators);

            // Choose the first collaborators up to the maximum team size
            collaborators = new HashSet<>(collaboratorList.subList(0, maxTeamSize));
        }

        // Here you would implement your logic to select the best team proposal based on the collaborators found
        // This is just a placeholder
        return new TeamProposal(maxTeamSize, minTeamSize, requiredSkills, new ArrayList<>(collaborators));
    }


}
