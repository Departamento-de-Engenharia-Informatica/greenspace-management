// SkillAssignmentRepository.java
package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;

import java.util.ArrayList;
import java.util.List;

public class SkillAssignmentRepository {
    private List<SkillAssignment> skillAssignments;

    public SkillAssignmentRepository() {
        this.skillAssignments = new ArrayList<>();
    }

    public void addSkillAssignment(SkillAssignment assignment) {
        skillAssignments.add(assignment);
    }

    public List<SkillAssignment> getAllSkillAssignments() {
        return new ArrayList<>(skillAssignments);
    }
}
