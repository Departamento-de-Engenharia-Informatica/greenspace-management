package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.util.List;

/**
 * Controller for displaying the skills of collaborators.
 */
public class ShowCollaboratorSkillsController {
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Constructs an instance of {@code ShowCollaboratorSkillsController} with the provided collaborator repository.
     *
     * @param collaboratorRepository the collaborator repository
     */
    public ShowCollaboratorSkillsController(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Retrieves all collaborators.
     *
     * @return a list of collaborators
     */
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getAll();
    }

    /**
     * Retrieves the skills of a specific collaborator.
     *
     * @param collaborator the collaborator whose skills will be retrieved
     * @return a list of skills of the specified collaborator
     */
    public List<Skill> getCollaboratorSkills(Collaborator collaborator) {
        List<Skill> skills = collaborator.getSkills();
        System.out.println("Retrieved skills for collaborator: " + collaborator.getName());
        return skills;
    }
}
