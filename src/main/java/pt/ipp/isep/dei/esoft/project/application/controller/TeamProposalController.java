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

    /*public TeamProposal generateTeamProposal(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills) {
        // Check if enough collaborators are available for the required skills
        Map<Skill, Integer> requiredSkillCounts = getRequiredSkillCounts(requiredSkills);
        Map<Skill, List<Collaborator>> availableCollaborators = getAvailableCollaboratorsForSkills(requiredSkillCounts);

        if (validateRequiredSkills(requiredSkillCounts, availableCollaborators)) {
            List<Collaborator> selectedCollaborators = selectCollaborators(requiredSkillCounts, availableCollaborators, maxTeamSize);
            TeamProposal teamProposal = new TeamProposal(maxTeamSize, minTeamSize, requiredSkills);
            teamProposal.setSelectedCollaborators(selectedCollaborators);
            teamProposalRepository.addTeamProposal(teamProposal);
            return teamProposal;
        } else {
            System.out.println("Not enough collaborators with the required skills.");
            return null;
        }
    }*/

    private Map<Skill, Integer> getRequiredSkillCounts(Set<Skill> requiredSkills) {
        Map<Skill, Integer> skillCounts = new HashMap<>();
        for (Skill skill : requiredSkills) {
            skillCounts.put(skill, Collections.frequency(requiredSkills, skill));
        }
        return skillCounts;
    }



    private boolean validateRequiredSkills(Map<Skill, Integer> requiredSkillCounts, Map<Skill, List<Collaborator>> availableCollaborators) {
        for (Map.Entry<Skill, Integer> entry : requiredSkillCounts.entrySet()) {
            Skill skill = entry.getKey();
            int requiredCount = entry.getValue();
            List<Collaborator> collaborators = availableCollaborators.get(skill);
            if (collaborators == null || collaborators.size() < requiredCount) {
                return false;
            }
        }
        return true;
    }

    private List<Collaborator> selectCollaborators(Map<Skill, Integer> requiredSkillCounts, Map<Skill, List<Collaborator>> availableCollaborators, int maxTeamSize) {
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        for (Map.Entry<Skill, Integer> entry : requiredSkillCounts.entrySet()) {
            Skill skill = entry.getKey();
            int requiredCount = entry.getValue();
            List<Collaborator> collaborators = availableCollaborators.get(skill);
            if (collaborators != null) {
                for (int i = 0; i < requiredCount && i < collaborators.size() && selectedCollaborators.size() < maxTeamSize; i++) {
                    selectedCollaborators.add(collaborators.get(i));
                }
            }
        }
        return selectedCollaborators;
    }

    public static List<TeamProposal> getAllTeamProposals() {
        return teamProposalRepository.getAllTeamProposals();
    }
    public Set<Collaborator> findCollaboratorsForTeam(TeamProposal teamProposal) {
        Set<Collaborator> potentialCollaborators = new HashSet<>();

        for (Skill requiredSkill : teamProposal.getRequiredSkills()) {
            List<Collaborator> collaboratorsWithSkill = SkillAssignment.getCollaboratorsWithSkill(requiredSkill);
            potentialCollaborators.addAll(collaboratorsWithSkill);
        }

        return potentialCollaborators;
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
