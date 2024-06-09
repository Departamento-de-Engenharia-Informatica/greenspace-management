package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ToDoList}.
 */
public class ToDoListTest {

    /**
     * Tests the constructor of {@link ToDoList} with valid inputs.
     */
    @Test
    public void testToDoListConstructorValidInputs() {
        ToDoList toDoList = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        assertEquals("Water plants", toDoList.getTaskDescription());
        assertEquals("High", toDoList.getUrgency());
        assertEquals(30, toDoList.getExpectedDuration());
        assertEquals("Central Park", toDoList.getGreenspaceName());
        assertEquals("Pending", toDoList.getStatus());
        assertEquals("user@example.com", toDoList.getUserEmail());
    }

    /**
     * Tests the constructor of {@link ToDoList} with an invalid urgency level.
     */
    @Test
    public void testToDoListConstructorInvalidUrgency() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ToDoList("Water plants", "Critical", 30, "Central Park", "Pending", "user@example.com")
        );
        assertEquals("Invalid urgency level.", exception.getMessage());
    }

    /**
     * Tests the constructor of {@link ToDoList} with an invalid expected duration.
     */
    @Test
    public void testToDoListConstructorInvalidDuration() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ToDoList("Water plants", "High", -5, "Central Park", "Pending", "user@example.com")
        );
        assertEquals("Expected duration must be a positive integer.", exception.getMessage());
    }

    /**
     * Tests the constructor of {@link ToDoList} with an empty task description.
     */
    @Test
    public void testToDoListConstructorEmptyTaskDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ToDoList("", "High", 30, "Central Park", "Pending", "user@example.com")
        );
        assertEquals("Task description must be provided.", exception.getMessage());
    }

    /**
     * Tests the equals method of {@link ToDoList}.
     */
    @Test
    public void testToDoListEquals() {
        ToDoList toDoList1 = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        ToDoList toDoList2 = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        ToDoList toDoList3 = new ToDoList("Prune trees", "High", 30, "Central Park", "Pending", "user@example.com");

        assertTrue(toDoList1.equals(toDoList2));
        assertFalse(toDoList1.equals(toDoList3));
    }

    /**
     * Tests the clone method of {@link ToDoList}.
     */
    @Test
    public void testToDoListClone() {
        ToDoList toDoList = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        ToDoList clonedToDoList = toDoList.clone();
        assertEquals(toDoList, clonedToDoList);
        assertNotSame(toDoList, clonedToDoList);
    }

    /**
     * Tests the toString method of {@link ToDoList}.
     */
    @Test
    public void testToDoListToString() {
        ToDoList toDoList = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        String expectedString = "Task Description: Water plants, Urgency: High, Expected Duration: 30 minutes, Greenspace: Central Park, Status: Pending";
        assertEquals(expectedString, toDoList.toString());
    }

    /**
     * Tests the setStatus method of {@link ToDoList}.
     */
    @Test
    public void testSetStatus() {
        ToDoList toDoList = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        toDoList.setStatus("Completed");
        assertEquals("Completed", toDoList.getStatus());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                toDoList.setStatus(null)
        );
        assertEquals("Status cannot be null", exception.getMessage());
    }
}
