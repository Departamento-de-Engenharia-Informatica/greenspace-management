package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SkillRepositoryTest {

    private SkillRepository skillRepository;

    @BeforeEach
    public void setUp() {
        skillRepository = new SkillRepository();
    }

    @Test
    public void testAddSkill() {
        // Test AC1: There cannot be skills with the same name
        // Add a skill to the repository
        skillRepository.addSkill("Java");

        // Attempt to add the same skill again
        skillRepository.addSkill("Java");

        // Ensure the second addition fails and returns empty optional
        assertEquals(1, skillRepository.getAllSkills().size()); // Only one skill should be added
    }

    @Test
    public void testAddSkillWithNameNull() {
        // Test AC2: Each skill entry must include a name
        // Attempt to add a skill with null name
        skillRepository.addSkill(null);

        // Ensure no skill is added with null name
        assertEquals(0, skillRepository.getAllSkills().size());
    }

    @Test
    public void testAddSkillWithSpecialCharacters() {
        // Test AC3: Special characters or algarisms should not be allowed in the skill name
        // Attempt to add a skill with special characters in the name
        skillRepository.addSkill("Java!");

        // Ensure no skill is added with special characters in the name
        assertEquals(0, skillRepository.getAllSkills().size());
    }

    @Test
    public void testRemoveSkill() {
        // Add some skills to the repository
        skillRepository.addSkill("Java");
        skillRepository.addSkill("Python");

        // Get the list of skills before removal
        List<Skill> beforeRemoval = skillRepository.getAllSkills();

        // Remove a skill from the repository
        skillRepository.removeSkill(1); // Removing the skill at index 1 (Python)

        // Get the list of skills after removal
        List<Skill> afterRemoval = skillRepository.getAllSkills();

        // Ensure the size of the list decreases by 1 after removal
        assertEquals(beforeRemoval.size() - 1, afterRemoval.size());

        // Ensure the removed skill is no longer present in the list
        assertFalse(afterRemoval.contains(new Skill("Python")));
    }




    @Test
    public void testUpdateSkill() {
        // Add a skill to the repository
        skillRepository.addSkill("Java");

        // Update the skill's name
        skillRepository.updateSkill(1, "JavaScript"); // Updating the skill at index 1 (Java) to JavaScript

        // Ensure the skill is updated successfully
        assertEquals("JavaScript", skillRepository.getAllSkills().get(0).getSkillName());
    }


}
