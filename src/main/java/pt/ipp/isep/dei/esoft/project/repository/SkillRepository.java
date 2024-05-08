package pt.ipp.isep.dei.esoft.project.repository;


import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    private List<String> skills;
    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    public void addSkill(String skillName) {
        if (!skills.contains(skillName)) {
            skills.add(skillName);
        }
    }

    public void removeSkill(String skillName) {
        skills.remove(skillName);
    }

    public void updateSkill(String skillNameOld, String skillNameNew) {
        if (skills.contains(skillNameOld)) {
            int index = skills.indexOf(skillNameOld);
            skills.set(index, skillNameNew);
        }
    }

    public boolean hasSkill(String skillName) {
        return skills.contains(skillName);
    }

    public List<String> getAllSkills() {
        return new ArrayList<>(skills);
    }

    // Other methods
}
