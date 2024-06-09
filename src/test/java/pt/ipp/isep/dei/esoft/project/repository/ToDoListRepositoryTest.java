package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ToDoListRepository}.
 */
public class ToDoListRepositoryTest {

    private ToDoListRepository repository;
    private ToDoList toDoList1;
    private ToDoList toDoList2;
    private ToDoList toDoList3;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        repository = new ToDoListRepository();
        toDoList1 = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user1@example.com");
        toDoList2 = new ToDoList("Prune trees", "Medium", 60, "Green Park", "Pending", "user2@example.com");
        toDoList3 = new ToDoList("Mow lawn", "Low", 120, "Central Park", "In Progress", "user1@example.com");
    }

    /**
     * Tests adding a ToDoList to the repository.
     */
    @Test
    public void testAddToDoList() {
        Optional<ToDoList> addedToDoList = repository.add(toDoList1);
        assertTrue(addedToDoList.isPresent());
        assertEquals(toDoList1, addedToDoList.get());
    }

    /**
     * Tests retrieving all ToDoLists from the repository.
     */
    @Test
    public void testGetAllToDoLists() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        List<ToDoList> allToDoLists = ToDoListRepository.getAllToDoLists();
        assertEquals(2, allToDoLists.size());
        assertTrue(allToDoLists.contains(toDoList1));
        assertTrue(allToDoLists.contains(toDoList2));
    }

    /**
     * Tests updating a ToDoList entry in the repository.
     */
    @Test
    public void testUpdateToDoListEntry() {
        repository.add(toDoList1);
        ToDoList updatedToDoList = new ToDoList("Water plants", "Low", 45, "Central Park", "Completed", "user1@example.com");
        ToDoListRepository.updateToDoListEntry(updatedToDoList);
        Optional<ToDoList> foundToDoList = repository.findByTaskDescription("Water plants");
        assertTrue(foundToDoList.isPresent());
        assertEquals("Low", foundToDoList.get().getUrgency());
        assertEquals(45, foundToDoList.get().getExpectedDuration());
        assertEquals("Completed", foundToDoList.get().getStatus());
    }

    /**
     * Tests finding a ToDoList by task description.
     */
    @Test
    public void testFindByTaskDescription() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        Optional<ToDoList> foundToDoList = repository.findByTaskDescription("Prune trees");
        assertTrue(foundToDoList.isPresent());
        assertEquals(toDoList2, foundToDoList.get());
    }

    /**
     * Tests finding a ToDoList by task description when it does not exist.
     */
    @Test
    public void testFindByTaskDescriptionNotFound() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        Optional<ToDoList> foundToDoList = repository.findByTaskDescription("Non-existing task");
        assertFalse(foundToDoList.isPresent());
    }

    /**
     * Tests finding ToDoLists by user email and greenspace name.
     */
    @Test
    public void testFindByUserEmailAndGreenSpaceName() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        repository.add(toDoList3);
        List<ToDoList> foundToDoLists = repository.findByUserEmailAndGreenSpaceName("user1@example.com", "Central Park");
        assertEquals(2, foundToDoLists.size());
        assertTrue(foundToDoLists.contains(toDoList1));
        assertTrue(foundToDoLists.contains(toDoList3));
    }

    /**
     * Tests finding ToDoLists by user email and greenspace name when the user does not exist.
     */
    @Test
    public void testFindByUserEmailAndGreenSpaceNameNotFound() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        List<ToDoList> foundToDoLists = repository.findByUserEmailAndGreenSpaceName("non-existing-user@example.com", "Central Park");
        assertTrue(foundToDoLists.isEmpty());
    }

    /**
     * Tests finding ToDoLists by user email and greenspace name when the greenspace does not exist.
     */
    @Test
    public void testFindByUserEmailAndGreenSpaceNameNoMatch() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        List<ToDoList> foundToDoLists = repository.findByUserEmailAndGreenSpaceName("user1@example.com", "Non-existing park");
        assertTrue(foundToDoLists.isEmpty());
    }

    /**
     * Tests the toString method of {@link ToDoList}.
     */
    @Test
    public void testToString() {
        repository.add(toDoList1);
        assertEquals("Task Description: Water plants, Urgency: High, Expected Duration: 30 minutes, Greenspace: Central Park, Status: Pending", toDoList1.toString());
    }
}
