package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Set;

public class TeamProposal {

    private int minTeamSize;
    private int maxTeamSize;
    private Set<Skill> requiredSkills;

    public TeamProposal(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills) {
        this.maxTeamSize = maxTeamSize;
        this.minTeamSize = minTeamSize;
        this.requiredSkills = requiredSkills;
    }

    public int getMinTeamSize() {
        return minTeamSize;
    }

    public void setMinTeamSize(int minTeamSize) {
        this.minTeamSize = minTeamSize;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String toString() {
        return "TeamProposal{" +
                "minTeamSize=" + minTeamSize +
                ", maxTeamSize=" + maxTeamSize +
                ", requiredSkills=" + requiredSkills +
                '}';
    }
}
