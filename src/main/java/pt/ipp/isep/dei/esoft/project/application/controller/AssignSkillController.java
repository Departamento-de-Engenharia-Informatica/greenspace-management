package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;

import java.util.List;

public class AssignSkillController {
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;

    public AssignSkillController(CollaboratorRepository collaboratorRepository, SkillRepository skillRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.skillRepository = skillRepository;
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.getCollaborators();
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getSkills();
    }

    public boolean assignSkillToCollaborator(Collaborator collaborator, Skill skill) {
        // Verifica se o colaborador já possui a skill atribuída
        if (collaborator.hasSkill(skill)) {
            System.out.println("Collaborator already has this skill.");
            return false;
        }

        // Adiciona a skill ao colaborador
        collaborator.addSkill(skill);

        // Cria uma nova atribuição de skill
        SkillAssignment assignment = new SkillAssignment(collaborator, skill);

        // Adiciona a nova atribuição ao repositório de atribuições de skills
        Repositories.getInstance().getSkillAssignmentRepository().addSkillAssignment(assignment);

        System.out.println("Skill assigned successfully to collaborator: " + collaborator.getName() +
                ", Skill: " + skill.getSkillName());

        // Retorna true para indicar que a atribuição foi bem-sucedida
        return true;
    }

    public boolean removeSkillFromCollaborator(Collaborator collaborator, Skill skill) {
        // Verifica se o colaborador possui a skill a ser removida
        if (!collaborator.hasSkill(skill)) {
            System.out.println("Collaborator does not have this skill.");
            return false;
        }

        // Remove a skill da lista de habilidades do colaborador
        collaborator.removeSkill(skill);

        System.out.println("Skill removed successfully from collaborator: " + collaborator.getName() +
                ", Skill: " + skill.getSkillName());

        // Retorna true para indicar que a remoção foi bem-sucedida
        return true;
    }

}
