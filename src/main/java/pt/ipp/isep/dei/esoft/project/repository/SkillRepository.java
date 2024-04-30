package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    private List<Skill> skills;

    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    public List<Skill> getAvailableSkills() {
        return new ArrayList<>(skills);
    }

    public void saveSkill(Skill skill) {
        skills.add(skill);
    }

}

class Skill {
    private String name;

    public Skill(String name) {
        this.name = name;
    }

}
