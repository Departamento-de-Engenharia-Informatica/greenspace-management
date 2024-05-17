package pt.ipp.isep.dei.esoft.project.domain;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents an assignment of a skill to a collaborator.
 */
public class SkillAssignment {
    private Collaborator collaborator;
    private Skill skill;
    private static List<SkillAssignment> skillAssignments = new ArrayList<>();


    /**
     * Constructs a new SkillAssignment with the specified collaborator and skill.
     *
     * @param collaborator the collaborator to whom the skill is assigned
     * @param skill        the skill that is assigned to the collaborator
     */
    public SkillAssignment(Collaborator collaborator, Skill skill) {
        this.collaborator = collaborator;
        this.skill = skill;
        skillAssignments.add(this);

    }

    /**
     * Gets the collaborator associated with this skill assignment.
     *
     * @return the collaborator
     */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    /**
     * Gets the skill associated with this skill assignment.
     *
     * @return the skill
     */
    public Skill getSkill() {
        return skill;
    }
    public static List<Collaborator> getCollaboratorsWithSkill(Skill skill) {
        List<Collaborator> collaboratorsWithSkill = new ArrayList<>();
        for (SkillAssignment assignment : skillAssignments) {
            if (assignment.getSkill().equals(skill)) {
                collaboratorsWithSkill.add(assignment.getCollaborator());
            }
        }
        return collaboratorsWithSkill;
    }
    /*public static Collaborator getCollaboratorWithSkill(Skill skill) {
        for (SkillAssignment assignment : skillAssignments) {
            if (assignment.getSkill().equals(skill)) {
                return assignment.getCollaborator();
            }
        }
        return null; // Skill not found, return null
    }*/
}
