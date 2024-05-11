package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Skill {
    private static int nextId = 1;
    private int id;
    private String skillName;

    public Skill(String skillName) {
        if (skillName == null || skillName.isEmpty() || !skillName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid skill name: " + skillName);
        }
        this.id = nextId++;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(skillName, skill.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }
}
