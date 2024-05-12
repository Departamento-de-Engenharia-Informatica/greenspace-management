package pt.ipp.isep.dei.esoft.project.domain;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a team proposal.
 */
import java.util.Set;

public class TeamProposal {

    private String teamName;
    private int minTeamSize;
    private int maxTeamSize;
    private String description;
    private Set<Skill> requiredSkills;

    public TeamProposal(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills) {
        this.maxTeamSize = maxTeamSize;
        this.minTeamSize = minTeamSize;
        this.requiredSkills = requiredSkills;
    }

    // Getter and setter methods for TeamProposal properties
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    // Additional methods for TeamProposal functionality
}
