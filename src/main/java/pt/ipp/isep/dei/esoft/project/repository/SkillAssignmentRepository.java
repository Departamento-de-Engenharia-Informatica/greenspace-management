package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.SkillAssignment;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


public class SkillAssignmentRepository {

    private Set<SkillAssignment> skillAssignments = new HashSet<>();

    public void addSkillAssignment(SkillAssignment skillAssignment) {
        skillAssignments.add(skillAssignment);
    }

    public Set<Collaborator> getCollaboratorsWithSkill(Skill skill) {
        Set<Collaborator> collaborators = new HashSet<>();
        for (SkillAssignment assignment : skillAssignments) {
            if (assignment.getSkill().equals(skill)) {
                collaborators.add(assignment.getCollaborator());
            }
        }
        return collaborators;
    }
    public List<SkillAssignment> getAllSkillAssignments() {
        return new ArrayList<>(this.skillAssignments); // Return a copy of the list to prevent direct modification
    }
}
