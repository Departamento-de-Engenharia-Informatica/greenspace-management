// SkillAssignment.java
package pt.ipp.isep.dei.esoft.project.domain;

public class SkillAssignment {
    private Collaborator collaborator;
    private Skill skill;

    public SkillAssignment(Collaborator collaborator, Skill skill) {
        this.collaborator = collaborator;
        this.skill = skill;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public Skill getSkill() {
        return skill;
    }
}
