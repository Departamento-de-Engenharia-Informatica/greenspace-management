package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code ToDoList} class represents a to-do list entity in the system.
 * It contains information about tasks, their urgency, and expected duration.
 */
public class ToDoList {

    private String taskDescription;
    private String urgency;
    private int expectedDuration; // in minutes
    private String greenspaceName;
    private String status;

    /**
     * Constructs a new {@code ToDoList} object with the specified attributes.
     *
     * @param taskDescription The description of the task.
     * @param urgency         The urgency level of the task (High, Medium, Low).
     * @param expectedDuration The expected duration of the task in minutes.
     * @throws IllegalArgumentException If any of the input parameters are invalid.
     */
    public ToDoList(String taskDescription, String urgency, int expectedDuration, String greenspaceName, String status) throws IllegalArgumentException {
        validateInputs(taskDescription, urgency, expectedDuration,status);
        this.taskDescription = taskDescription;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
        this.greenspaceName = greenspaceName;
        this.status = status;
    }

    /**
     * Validates the input parameters for creating a to-do list entry.
     * Throws an exception if any parameter is invalid.
     */
    private void validateInputs(String taskDescription, String urgency, int expectedDuration, String status) throws IllegalArgumentException {
        if (taskDescription == null || taskDescription.trim().isEmpty()) {
            System.out.println("Task description must be provided.");
            throw new IllegalArgumentException("Task description must be provided.");
        }

        if (!urgency.equalsIgnoreCase("High") && !urgency.equalsIgnoreCase("Medium") && !urgency.equalsIgnoreCase("Low")) {
            System.out.println("Invalid urgency level. Must be High, Medium, or Low.");
            throw new IllegalArgumentException("Invalid urgency level.");
        }

        if (expectedDuration <= 0) {
            System.out.println("Expected duration must be a positive integer.");
            throw new IllegalArgumentException("Expected duration must be a positive integer.");
        }
        if (greenspaceName == null || greenspaceName.trim().isEmpty()) {
            System.out.println("Greenspace name should be available");

        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The object to compare this instance with.
     * @return {@code true} if the specified object is equal to this to-do list entry;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList toDoList = (ToDoList) o;
        return expectedDuration == toDoList.expectedDuration &&
                Objects.equals(taskDescription, toDoList.taskDescription) &&
                Objects.equals(urgency, toDoList.urgency);
    }

    /**
     * Creates and returns a new instance of the ToDoList class with the same attribute values as this instance.
     *
     * @return A new ToDoList object with the same attribute values as this instance.
     */
    @Override
    public ToDoList clone() {
        return new ToDoList(taskDescription, urgency, expectedDuration,greenspaceName,status);
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the urgency level of the task.
     *
     * @return The urgency level of the task.
     */
    public String getUrgency() {
        return urgency;
    }

    /**
     * Returns the expected duration of the task in minutes.
     *
     * @return The expected duration of the task in minutes.
     */
    public int getExpectedDuration() {
        return expectedDuration;
    }
    public String getGreenspaceName() {
        return greenspaceName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Task Description: " + taskDescription +
                ", Urgency: " + urgency +
                ", Expected Duration: " + expectedDuration + " minutes," +
                " Greenspace: " + greenspaceName +
                ", Status: " + status;
    }
}
