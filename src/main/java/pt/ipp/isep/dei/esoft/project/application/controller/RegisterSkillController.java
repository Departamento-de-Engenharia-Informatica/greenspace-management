package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Employee;
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

    public void updateSkill(String skillNameOld, String skillNameNew) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.updateSkill(skillNameOld, skillNameNew);
    }

    public void removeSkill(String skillName) {
        SkillRepository skillRepository = repositories.getSkillRepository();
        skillRepository.removeSkill(skillName);
    }

    // Other methods
}
