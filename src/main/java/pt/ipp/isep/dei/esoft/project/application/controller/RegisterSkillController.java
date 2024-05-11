package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

/**
 * Controller for registering skills.
 */
public class RegisterSkillController {
    private Repositories repositories;

    /**
     * Constructs a new RegisterSkillController with the specified repositories.
     *
     * @param repositories the repositories to be used
     */
    public RegisterSkillController(Repositories repositories) {
        this.repositories = repositories;
    }

    /**
     * Adds a skill with the given name.
     *
     * @param skillName the name of the skill to be added
     */
    public void addSkill(String skillName) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.addSkill(skillName);
    }

    /**
     * Updates a skill with the specified ID to have the new name.
     *
     * @param skillId      the ID of the skill to be updated
     * @param newSkillName the new name for the skill
     */
    public void updateSkill(int skillId, String newSkillName) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.updateSkill(skillId, newSkillName);
    }

    /**
     * Removes the skill with the specified ID.
     *
     * @param skillId the ID of the skill to be removed
     */
    public void removeSkill(int skillId) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.removeSkill(skillId);
    }

    // Other methods
}
