package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Set;
import java.util.List;

public class TeamProposal {

    private int minTeamSize;
    private int maxTeamSize;
    private Set<Skill> requiredSkills;
    private List<Collaborator> selectedCollaborators;

    public TeamProposal(int maxTeamSize, int minTeamSize, Set<Skill> requiredSkills, List<Collaborator> selectedCollaborators) {
        this.maxTeamSize = maxTeamSize;
        this.minTeamSize = minTeamSize;
        this.requiredSkills = requiredSkills;
        this.selectedCollaborators = selectedCollaborators;

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

    public List<Collaborator> getSelectedCollaborators() {
        return selectedCollaborators;
    }

    public void setSelectedCollaborators(List<Collaborator> selectedCollaborators) {
        this.selectedCollaborators = selectedCollaborators;
    }

    @Override
    public String toString() {
        return "TeamProposal{" +
                "minTeamSize=" + minTeamSize +
                ", maxTeamSize=" + maxTeamSize +
                ", requiredSkills=" + requiredSkills +
                ", selectedCollaborators=" + selectedCollaborators +
                '}';
    }
}
