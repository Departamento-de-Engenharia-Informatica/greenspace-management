package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void createValidSkill() {
        // Arrange
        String skillName = "Java";

        // Act
        Skill skill = new Skill(skillName);

        // Assert
        assertEquals(skillName, skill.getSkillName());
    }

    @Test
    void createSkillWithNullName() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Skill(null));
    }

    @Test
    void createSkillWithEmptyName() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Skill(""));
    }

    @Test
    void createSkillWithInvalidNameContainingDigits() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Skill("Java8"));
    }

    @Test
    void createSkillWithInvalidNameContainingSpecialCharacters() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Skill("Java@Skills"));
    }

    @Test
    void equals_sameInstance_returnsTrue() {
        // Arrange
        Skill skill = new Skill("Java");

        // Act & Assert
        assertEquals(skill, skill);
    }

    @Test
    void equals_equalSkills_returnsTrue() {
        // Arrange
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Java");

        // Act & Assert
        assertEquals(skill1, skill2);
    }

    @Test
    void equals_differentSkills_returnsFalse() {
        // Arrange
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");

        // Act & Assert
        assertNotEquals(skill1, skill2);
    }

    @Test
    void addingDuplicateSkillFails() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Java");

        // Act
        skillRepository.addSkill("Java");
        skillRepository.addSkill("Java");

        // Assert
        assertEquals(1, skillRepository.getAllSkills().size(), "Adding duplicate skill should fail");
    }
}
