package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private List<Team> teams;

    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    public List<Team> findAvailableTeams(int maxTeamSize, int minTeamSize) {
        List<Team> availableTeams = new ArrayList<>();
        for (Team team : teams) {
            if (team.getSize() >= minTeamSize && team.getSize() <= maxTeamSize) {
                availableTeams.add(team);
            }
        }
        return availableTeams;
    }

    public void saveTeam(Team team) {
        teams.add(team);
    }

}

class Team {
    private String name;
    private List<Employee> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Employee employee) {
        members.add(employee);
    }

    public int getSize() {
        return members.size();
    }

}
