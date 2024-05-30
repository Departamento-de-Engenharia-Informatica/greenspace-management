package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Agenda {
    private String taskDescription;
    private String greenspaceName;
    private LocalDate expectedDate;
    private String status;
//    private List<String> team;

    public Agenda(String taskDescription, String greenspaceName, LocalDate expectedDate, String status) {
        this.taskDescription = taskDescription;
        this.greenspaceName = greenspaceName;
        this.expectedDate = expectedDate;
        this.status = status;
//        this.team = team;
    }

    // Getters and setters
    public String getTaskDescription() { return taskDescription; }
    public String getGreenspaceName() { return greenspaceName; }
    public LocalDate getExpectedDate() { return expectedDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
//    public List<String> getTeam() { return team; }
//    public void setTeam(List<String> team) { this.team = team; }

    @Override
    public String toString() {
        return "Task Description: " + taskDescription +
                ", Expected Date: " + expectedDate + "," +
                " Greenspace: " + greenspaceName +
                ", Status: " + status;
    }
}
