package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents an assignment of a skill to a collaborator.
 */
public class SkillAssignment {
    private Collaborator collaborator;
    private Skill skill;

    /**
     * Constructs a new SkillAssignment with the specified collaborator and skill.
     *
     * @param collaborator the collaborator to whom the skill is assigned
     * @param skill        the skill that is assigned to the collaborator
     */
    public SkillAssignment(Collaborator collaborator, Skill skill) {
        this.collaborator = collaborator;
        this.skill = skill;
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
}
