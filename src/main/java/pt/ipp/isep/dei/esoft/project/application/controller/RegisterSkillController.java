package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

public class RegisterSkillController {
    private Repositories repositories;

    public RegisterSkillController(Repositories repositories) {
        this.repositories = repositories;
    }

    public void addSkill(String skillName) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.addSkill(skillName);
    }

    public void updateSkill(int skillId, String newSkillName) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.updateSkill(skillId, newSkillName); // Corrigido para passar um int como primeiro parâmetro
    }

    public void removeSkill(int skillId) { // Corrigido para receber um int como parâmetro
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.removeSkill(skillId); // Corrigido para passar um int como parâmetro
    }

    // Other methods
}
