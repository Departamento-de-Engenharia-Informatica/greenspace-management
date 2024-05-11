package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a skill.
 */
public class Skill {
    private static int nextId = 1;
    private int id;
    private String skillName;

    /**
     * Constructs a new Skill with the specified name.
     *
     * @param skillName the name of the skill
     * @throws IllegalArgumentException if the skill name is invalid
     */
    public Skill(String skillName) {
        if (skillName == null || skillName.isEmpty() || !skillName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid skill name: " + skillName);
        }
        this.id = nextId++;
        this.skillName = skillName;
    }

    /**
     * Gets the ID of the skill.
     *
     * @return the ID of the skill
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the skill.
     *
     * @return the name of the skill
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * Sets the name of the skill.
     *
     * @param skillName the new name of the skill
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    /**
     * Checks if two skills are equal.
     *
     * @param o the object to be compared
     * @return true if the skills are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(skillName, skill.skillName);
    }

    /**
     * Generates a hash code for the skill.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }
}
