package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing skill assignments.
 */
public class SkillAssignmentRepository {
    private List<SkillAssignment> skillAssignments;

    /**
     * Constructs a new SkillAssignmentRepository.
     */
    public SkillAssignmentRepository() {
        this.skillAssignments = new ArrayList<>();
    }

    /**
     * Adds a skill assignment to the repository.
     *
     * @param assignment the skill assignment to be added
     */
    public void addSkillAssignment(SkillAssignment assignment) {
        skillAssignments.add(assignment);
    }

    /**
     * Retrieves all skill assignments from the repository.
     *
     * @return a list of all skill assignments
     */
    public List<SkillAssignment> getAllSkillAssignments() {
        return new ArrayList<>(skillAssignments);
    }

    public List<SkillAssignment> getSkillAssignmentsBySkill(Skill skill) {
        List<SkillAssignment> matchingAssignments = new ArrayList<>();
        List<SkillAssignment> allAssignments = getAllSkillAssignments();

        for (SkillAssignment assignment : allAssignments) {
            if (assignment.getSkill().equals(skill)) {
                matchingAssignments.add(assignment);
            }
        }
        return matchingAssignments;
    }
}
