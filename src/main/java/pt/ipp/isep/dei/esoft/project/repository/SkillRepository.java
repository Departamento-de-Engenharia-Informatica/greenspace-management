package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The repository for managing skills.
 */
public class SkillRepository {
    private List<Skill> skills;

    /**
     * Constructs a new SkillRepository.
     */
    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    /**
     * Adds a new skill to the repository if it does not already exist.
     *
     * @param skillName The name of the skill to be added.
     */
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

    /**
     * Checks if a skill with the given name already exists in the repository.
     *
     * @param skillName The name of the skill to check.
     * @return {@code true} if the skill already exists, {@code false} otherwise.
     */
    private boolean skillExists(String skillName) {
        for (Skill skill : skills) {
            if (skill.getSkillName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a skill from the repository.
     *
     * @param skillId The ID of the skill to be removed.
     */
    public void removeSkill(int skillId) {
        for (Skill skill : this.skills) {
            if (skill.getId() == skillId) {
                this.skills.remove(skill);
                break;
            }
        }
    }
    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    /**
     * Updates the name of a skill in the repository.
     *
     * @param skillId      The ID of the skill to be updated.
     * @param newSkillName The new name for the skill.
     */
    public void updateSkill(int skillId, String newSkillName) {
        Skill skillToUpdate = findSkillById(skillId);
        if (skillToUpdate != null) {
            skillToUpdate.setSkillName(newSkillName);
        }
    }

    /**
     * Retrieves a list of all skills in the repository.
     *
     * @return A list containing all skills in the repository.
     */
    public List<Skill> getAllSkills() {
        return new ArrayList<>(skills);
    }

    /**
     * Finds a skill in the repository by its ID.
     *
     * @param skillId The ID of the skill to find.
     * @return The skill with the specified ID, or {@code null} if not found.
     */
    private Skill findSkillById(int skillId) {
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return skill;
            }
        }
        return null;
    }


}
