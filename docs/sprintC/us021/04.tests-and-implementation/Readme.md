# US021 - Add a new entry to the To-Do List

## 4.1 Tests 

**Test 1:** Tests the creation of a valid To-Do List.

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



**Test 2:** Tests the creation of a To-Do List with a invalid urgency level

    @Test
    public void testToDoListConstructorInvalidUrgency() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ToDoList("Water plants", "Critical", 30, "Central Park", "Pending", "user@example.com")
        );
        assertEquals("Invalid urgency level.", exception.getMessage());
    }


**Test 3:** Tests the creation of a To-Do List with an invalid expected duration.
  
    @Test
    public void testToDoListConstructorInvalidDuration() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new ToDoList("Water plants", "High", -5, "Central Park", "Pending", "user@example.com")
        );
        assertEquals("Expected duration must be a positive integer.", exception.getMessage());
    }

**Test 4:** Tests the creation of a To-Do List with an empty task description.

        @Test
        public void testToDoListConstructorEmptyTaskDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        new ToDoList("", "High", 30, "Central Park", "Pending", "user@example.com")
        );
        assertEquals("Task description must be provided.", exception.getMessage());
        }

    
**Test 5:** Tests the equals method of ToDoList.
    
    @Test
    public void testToDoListEquals() {
        ToDoList toDoList1 = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        ToDoList toDoList2 = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        ToDoList toDoList3 = new ToDoList("Prune trees", "High", 30, "Central Park", "Pending", "user@example.com");

        assertTrue(toDoList1.equals(toDoList2));
        assertFalse(toDoList1.equals(toDoList3));
    }



**Test 6:** Tests the clone method of ToDoList.

    @Test
    public void testToDoListClone() {
        ToDoList toDoList = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        ToDoList clonedToDoList = toDoList.clone();
        assertEquals(toDoList, clonedToDoList);
        assertNotSame(toDoList, clonedToDoList);
    }

    
**Test 7:**  Tests the toString method of ToDoList.
     
    @Test
    public void testToDoListToString() {
        ToDoList toDoList = new ToDoList("Water plants", "High", 30, "Central Park", "Pending", "user@example.com");
        String expectedString = "Task Description: Water plants, Urgency: High, Expected Duration: 30 minutes, Greenspace: Central Park, Status: Pending";
        assertEquals(expectedString, toDoList.toString());
    }

    
**Test 8:**   Tests the setStatus method of ToDoList.
     
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



## 4.2 Repository tests


**Test 1:** Tests adding a ToDoList to the repository.

    @Test
    public void testAddToDoList() {
        Optional<ToDoList> addedToDoList = repository.add(toDoList1);
        assertTrue(addedToDoList.isPresent());
        assertEquals(toDoList1, addedToDoList.get());
    }


**Test 2:** Tests retrieving all ToDoLists from the repository.
     
    @Test
    public void testGetAllToDoLists() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        List<ToDoList> allToDoLists = ToDoListRepository.getAllToDoLists();
        assertEquals(2, allToDoLists.size());
        assertTrue(allToDoLists.contains(toDoList1));
        assertTrue(allToDoLists.contains(toDoList2));
    }

    
**Test 3:** Tests updating a ToDoList entry in the repository.
     
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

    
**Test 4:** Tests finding a ToDoList by task description.
     
    @Test
    public void testFindByTaskDescription() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        Optional<ToDoList> foundToDoList = repository.findByTaskDescription("Prune trees");
        assertTrue(foundToDoList.isPresent());
        assertEquals(toDoList2, foundToDoList.get());
    }
**Test 5:** Tests finding ToDoLists by user email and greenspace name.

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
**Test 6:** Tests finding ToDoLists by user email and greenspace name when the user does not exist.

    @Test
    public void testFindByUserEmailAndGreenSpaceNameNotFound() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        List<ToDoList> foundToDoLists = repository.findByUserEmailAndGreenSpaceName("non-existing-user@example.com", "Central Park");
        assertTrue(foundToDoLists.isEmpty());
    }

**Test 7:** Tests finding ToDoLists by user email and greenspace name when the greenspace does not exist

    @Test
    public void testFindByUserEmailAndGreenSpaceNameNoMatch() {
        repository.add(toDoList1);
        repository.add(toDoList2);
        List<ToDoList> foundToDoLists = repository.findByUserEmailAndGreenSpaceName("user1@example.com", "Non-existing park");
        assertTrue(foundToDoLists.isEmpty());
    }





## 5. Construction (Implementation)

### Class ToDoListController 

```java
package pt.ipp.isep.dei.esoft.project.application.controller;



import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

/**
 * The {@code ToDoListController} class represents a controller responsible for managing
 * the creation and retrieval of to-do list entries in the application.
 * It interacts with the to-do list repository to add new entries and retrieve existing ones.
 */
public class ToDoListController {

    private final ToDoListRepository toDoListRepository;

    /**
     * Constructs a new {@code ToDoListController} object.
     * Initializes the to-do list repository.
     */
    public ToDoListController() {
        Repositories repositories = Repositories.getInstance();
        this.toDoListRepository = repositories.getToDoListRepository();
    }

    /**
     * Creates a new to-do list entry with the provided information.
     * Adds the entry to the repository if it is valid and does not already exist.
     *
     * @param taskDescription  The description of the task.
     * @param urgency          The urgency level of the task.
     * @param expectedDuration The expected duration of the task.
     * @return An optional containing the newly created to-do list entry, if successful.
     */
    public Optional<ToDoList> createToDoListEntry(String taskDescription, String urgency, int expectedDuration, String greenspaceName, String status, String email) {
        ToDoList toDoList = new ToDoList(taskDescription, urgency, expectedDuration, greenspaceName, status, email);
        return toDoListRepository.add(toDoList);
    }

    /**
     * Retrieves a list of all to-do list entries.
     *
     * @return A list of to-do list entries.
     */

    public static void updateToDoListStatus(String taskDescription, String newStatus) {
        List<ToDoList> toDoListEntries = ToDoListRepository.getAll();
        for (ToDoList toDoListEntry : toDoListEntries) {
            if (toDoListEntry.getTaskDescription().equals(taskDescription)) {
                toDoListEntry.setStatus(newStatus);
                ToDoListRepository.updateToDoListEntry(toDoListEntry);
                break;
            }
        }
    }

    public List<ToDoList> getAllToDoListEntries() {
        return ToDoListRepository.getAllToDoLists();
    }


}


```

### Class ToDoList

```java
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
    private String userEmail; // Add user email field
    /**
     * Constructs a new {@code ToDoList} object with the specified attributes.
     *
     * @param taskDescription The description of the task.
     * @param urgency         The urgency level of the task (High, Medium, Low).
     * @param expectedDuration The expected duration of the task in minutes.
     * @throws IllegalArgumentException If any of the input parameters are invalid.
     */
    public ToDoList(String taskDescription, String urgency, int expectedDuration, String greenspaceName, String status, String userEmail) throws IllegalArgumentException {
        validateInputs(taskDescription, urgency, expectedDuration,status,userEmail);
        this.taskDescription = taskDescription;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
        this.greenspaceName = greenspaceName;
        this.status = status;
        this.userEmail = userEmail;

    }

    /**
     * Validates the input parameters for creating a to-do list entry.
     * Throws an exception if any parameter is invalid.
     */
    private void validateInputs(String taskDescription, String urgency, int expectedDuration, String status, String userEmail) throws IllegalArgumentException {
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
        return new ToDoList(taskDescription, urgency, expectedDuration,greenspaceName,status, userEmail);
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
    public String getUserEmail() {
        return userEmail;
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

```

### Class ToDoListRepository

```java
package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ToDoListRepository} class represents a repository for managing to-do lists.
 * It stores to-do list objects and provides methods to add new entries and perform validation checks.
 */
public class ToDoListRepository {

    private static List<ToDoList> toDoLists = new ArrayList<>();

    /**
     * Constructs a new {@code ToDoListRepository} object.
     */
    public ToDoListRepository() {
        // No need to initialize the list here, it's already done in the declaration
    }

    /**
     * Adds a new entry to the to-do list repository.
     *
     * @param toDoList The to-do list entry to add.
     * @return An optional containing the added to-do list entry if successful.
     */
    public Optional<ToDoList> add(ToDoList toDoList) {
        Optional<ToDoList> newToDoList = Optional.of(toDoList.clone());
        toDoLists.add(newToDoList.get());
        return newToDoList;
    }

    /**
     * Retrieves a list of all to-do list entries in the repository.
     *
     * @return A list of to-do list entries.
     */
    public static List<ToDoList> getAllToDoLists() {
        // Defensive copy to prevent modification from outside
        return List.copyOf(toDoLists);
    }

    /**
     * Updates an existing to-do list entry in the repository.
     *
     * @param updatedEntry The updated to-do list entry.
     */
    public static void updateToDoListEntry(ToDoList updatedEntry) {
        for (int i = 0; i < toDoLists.size(); i++) {
            if (toDoLists.get(i).getTaskDescription().equals(updatedEntry.getTaskDescription())) {
                toDoLists.set(i, updatedEntry);
                break;
            }
        }
    }

    /**
     * Retrieves a list of all to-do list entries in the repository.
     *
     * @return A list of to-do list entries.
     */
    public static List<ToDoList> getAll() {
        return new ArrayList<>(toDoLists);
    }

    /**
     * Finds a to-do list entry by task description.
     *
     * @param taskDescription The task description.
     * @return An optional containing the to-do list entry if found.
     */
    public Optional<ToDoList> findByTaskDescription(String taskDescription) {
        return toDoLists.stream()
                .filter(toDoList -> toDoList.getTaskDescription().equals(taskDescription))
                .findFirst();
    }

    /**
     * Retrieves a list of to-do list entries by user email and green space name.
     *
     * @param userEmail The user's email.
     * @param greenSpaceName The green space name.
     * @return A list of to-do list entries.
     */
    public List<ToDoList> findByUserEmailAndGreenSpaceName(String userEmail, String greenSpaceName) {
        List<ToDoList> filteredToDoLists = new ArrayList<>();
        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getUserEmail().equals(userEmail) && toDoList.getGreenspaceName().equals(greenSpaceName)) {
                filteredToDoLists.add(toDoList);
            }
        }
        return filteredToDoLists;
    }
}


```


## 6. Integration and Demo 

* A new option on the GSM menu options was added.


## 7. Observations

n/a