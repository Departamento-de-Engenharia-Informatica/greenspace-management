package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class Agenda {
    private String taskDescription;
    private String greenspaceName;
    private int expectedDuration;
    private String status;
//    private List<String> team;

    public Agenda(String taskDescription, String greenspaceName, int expectedDuration, String status) {
        this.taskDescription = taskDescription;
        this.greenspaceName = greenspaceName;
        this.expectedDuration = expectedDuration;
        this.status = status;
//        this.team = team;
    }

    // Getters and setters
    public String getTaskDescription() { return taskDescription; }
    public String getGreenspaceName() { return greenspaceName; }
    public int getExpectedDuration() { return expectedDuration; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
//    public List<String> getTeam() { return team; }
//    public void setTeam(List<String> team) { this.team = team; }
}
