package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * The Agenda class represents a task entry in the project domain.
 */
public class Agenda {
    private String taskDescription;
    private String greenspaceName;
    private LocalDate expectedDate;
    private String status;
    private TeamProposal teamProposal;
    private List<Vehicle> vehicles;

    /**
     * Constructs a new Agenda with the specified task description, greenspace name, expected date, status, and team proposal.
     *
     * @param taskDescription the description of the task
     * @param greenspaceName  the name of the greenspace associated with the task
     * @param expectedDate    the expected date of the task
     * @param status          the status of the task
     * @param teamProposal    the team proposal associated with the task
     */
    public Agenda(String taskDescription, String greenspaceName, LocalDate expectedDate, String status, TeamProposal teamProposal) {
//        if (teamProposal == null) {
//            throw new IllegalArgumentException("Team proposal cannot be null");
//        }
        this.taskDescription = taskDescription;
        this.greenspaceName = greenspaceName;
        this.expectedDate = expectedDate;
        this.status = status;
        this.teamProposal = teamProposal;
    }

    // Getters and setters
    public String getTaskDescription() {
        return taskDescription;
    }

    public String getGreenspaceName() {
        return greenspaceName;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }
    public void setTeamProposal(TeamProposal teamProposal) {
        this.teamProposal = teamProposal;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Returns a string representation of the Agenda object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Task Description: " + taskDescription +
                ", Expected Date: " + expectedDate + "," +
                " Greenspace: " + greenspaceName +
                ", Status: " + status +
                ", Team:" + (teamProposal != null ? teamProposal.getSelectedCollaborators().toString() : "No team assigned");
    }

    /**
     * Returns the start time of the task (for vehicle availability check).
     *
     * @return the start time of the task
     */
    public LocalDateTime getStartTime() {
        return expectedDate.atStartOfDay();
    }

    /**
     * Returns the end time of the task (for vehicle availability check).
     *
     * @return the end time of the task
     */
    public LocalDateTime getEndTime() {
        return expectedDate.atTime(23, 59);
    }

    public TeamProposal getTeamProposal() {
        return teamProposal;
    }
}
