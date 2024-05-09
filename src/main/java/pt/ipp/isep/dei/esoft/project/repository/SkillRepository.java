package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    private List<Skill> skills;

    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    public void addSkill(String skillName) {
        Skill skill = new Skill(skillName);
        skills.add(skill);
    }

    public void removeSkill(int skillId) {
        Skill skillToRemove = findSkillById(skillId);
        if (skillToRemove != null) {
            skills.remove(skillToRemove);
        }
    }

    public void updateSkill(int skillId, String newSkillName) {
        Skill skillToUpdate = findSkillById(skillId);
        if (skillToUpdate != null) {
            skillToUpdate.setSkillName(newSkillName);
        }
    }

    public List<Skill> getAllSkills() {
        return new ArrayList<>(skills);
    }

    private Skill findSkillById(int skillId) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return skill;
            }
        }
        return null;
    }
}
