package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AssignSkillsTest {

    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;
    private AssignSkillController assignSkillController;
    private Collaborator testCollaborator;
    private Skill testSkill;

    @BeforeEach
    void setUp() {
        collaboratorRepository = new CollaboratorRepository();
        skillRepository = new SkillRepository();
        assignSkillController = new AssignSkillController(collaboratorRepository, skillRepository);
        testCollaborator = new Collaborator("Test Collaborator", LocalDate.of(1990, 1, 1),
                LocalDate.of(2022, 1, 1), "Address", "123456789", "test@example.com",
                123456789, 12345678, "Tester");
        testSkill = new Skill("Test Skill");
    }

    @Test
    void testAddSkillToCollaborator() {
        // Adiciona o colaborador ao repositório
        collaboratorRepository.add(testCollaborator);

        // Adiciona a habilidade ao repositório
        skillRepository.addSkill("Test Skill");

        // Verifica se o colaborador não tem a habilidade antes de atribuí-la
        assertFalse(testCollaborator.hasSkill(testSkill));

        // Atribui a habilidade ao colaborador
        boolean result = assignSkillController.assignSkillToCollaborator(testCollaborator, testSkill);

        // Verifica se a atribuição foi bem-sucedida
        assertTrue(result);
        assertTrue(testCollaborator.hasSkill(testSkill));
    }

    @Test
    void testRemoveSkillFromCollaborator() {
        // Adiciona o colaborador ao repositório
        collaboratorRepository.add(testCollaborator);

        // Adiciona a habilidade ao repositório
        skillRepository.addSkill("Test Skill");

        // Atribui a habilidade ao colaborador
        assignSkillController.assignSkillToCollaborator(testCollaborator, testSkill);

        // Verifica se o colaborador tem a habilidade antes de removê-la
        assertTrue(testCollaborator.hasSkill(testSkill));

        // Remove a habilidade do colaborador
        boolean result = assignSkillController.removeSkillFromCollaborator(testCollaborator, testSkill);

        // Verifica se a remoção foi bem-sucedida
        assertTrue(result);
        assertFalse(testCollaborator.hasSkill(testSkill));
    }
}

