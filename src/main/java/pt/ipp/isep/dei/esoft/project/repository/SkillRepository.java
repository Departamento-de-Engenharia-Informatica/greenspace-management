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
        if (skillName == null || !skillName.matches("[a-zA-Z ]+")) {
            System.out.println("Special characters or digits are not allowed in the skill name.");
        } else if (skillExists(skillName)) {
            System.out.println("Skill already exists in the system.");
        } else {
            Skill skill = new Skill(skillName);
            skills.add(skill);
        }
    }

    private boolean skillExists(String skillName) {
        for (Skill skill : skills) {
            if (skill.getSkillName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }
        return false;
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
