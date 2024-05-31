package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Agenda {
    private String taskDescription;
    private String greenspaceName;
    private LocalDate expectedDate;
    private String status;
    private TeamProposal teamProposal;
    private List<Vehicle> vehicles;

    public Agenda(String taskDescription, String greenspaceName, LocalDate expectedDate, String status, TeamProposal teamProposal) {
        this.taskDescription = taskDescription;
        this.greenspaceName = greenspaceName;
        this.expectedDate = expectedDate;
        this.status = status;
        this.teamProposal = teamProposal;
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


    public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }

    @Override
    public String toString() {
        return "Task Description: " + taskDescription +
                ", Expected Date: " + expectedDate + "," +
                " Greenspace: " + greenspaceName +
                ", Status: " + status +
                ", Team:" + teamProposal.getSelectedCollaborators().toString();
    }

    // Method to get the start time (for vehicle availability check)
    public LocalDateTime getStartTime() {
        // Adjust this according to the actual start time representation
        return expectedDate.atStartOfDay();
    }

    // Method to get the end time (for vehicle availability check)
    public LocalDateTime getEndTime() {
        // Adjust this according to the actual end time representation
        return expectedDate.atTime(23, 59);
    }
}
