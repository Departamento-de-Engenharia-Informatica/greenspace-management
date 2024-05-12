package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.util.List;

public class ShowCollaboratorSkillsController {
    private final CollaboratorRepository collaboratorRepository;

    public ShowCollaboratorSkillsController(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getAll();
    }

    public List<Skill> getCollaboratorSkills(Collaborator collaborator) {
        List<Skill> skills = collaborator.getSkills();
        System.out.println("Retrieved skills for collaborator: " + collaborator.getName());
        return skills;
    }

}
