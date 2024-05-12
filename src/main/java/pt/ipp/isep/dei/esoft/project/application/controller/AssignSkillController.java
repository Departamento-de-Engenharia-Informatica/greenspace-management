package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;

import java.util.List;

/**
 * Controller for managing the assignment of skills to collaborators.
 */
public class AssignSkillController {
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;

    /**
     * Constructs an instance of the AssignSkillController.
     *
     * @param collaboratorRepository the collaborator repository
     * @param skillRepository        the skill repository
     */
    public AssignSkillController(CollaboratorRepository collaboratorRepository, SkillRepository skillRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.skillRepository = skillRepository;
    }

    /**
     * Retrieves all collaborators.
     *
     * @return a list of collaborators
     */
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getCollaborators();
    }

    /**
     * Retrieves all skills.
     *
     * @return a list of skills
     */
    public List<Skill> getAllSkills() {
        return skillRepository.getSkills();
    }

    /**
     * Assigns a skill to a collaborator.
     *
     * @param collaborator the collaborator to whom the skill will be assigned
     * @param skill        the skill to be assigned
     * @return true if the assignment is successful, false otherwise
     */
    public boolean assignSkillToCollaborator(Collaborator collaborator, Skill skill) {
        // Checks if the collaborator already has the skill assigned
        if (collaborator.hasSkill(skill)) {
            System.out.println("Collaborator already has this skill.");
            return false;
        }

        // Adds the skill to the collaborator
        collaborator.addSkill(skill);

        // Creates a new skill assignment
        SkillAssignment assignment = new SkillAssignment(collaborator, skill);

        // Adds the new assignment to the skill assignment repository
        Repositories.getInstance().getSkillAssignmentRepository().addSkillAssignment(assignment);

        System.out.println("Skill assigned successfully to collaborator: " + collaborator.getName() +
                ", Skill: " + skill.getSkillName());

        // Returns true to indicate the assignment was successful
        return true;
    }

    /**
     * Removes a skill from a collaborator.
     *
     * @param collaborator the collaborator from whom the skill will be removed
     * @param skill        the skill to be removed
     * @return true if the removal is successful, false otherwise
     */
    public boolean removeSkillFromCollaborator(Collaborator collaborator, Skill skill) {
        // Checks if the collaborator has the skill to be removed
        if (!collaborator.hasSkill(skill)) {
            System.out.println("Collaborator does not have this skill.");
            return false;
        }

        // Removes the skill from the collaborator's list of skills
        collaborator.removeSkill(skill);

        System.out.println("Skill removed successfully from collaborator: " + collaborator.getName() +
                ", Skill: " + skill.getSkillName());

        // Returns true to indicate the removal was successful
        return true;
    }
}
