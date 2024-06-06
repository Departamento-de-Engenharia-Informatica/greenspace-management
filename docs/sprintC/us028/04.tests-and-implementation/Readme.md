# US028 - Consult the tasks assigned to me between two dates.

## 4.1 Domain Tests 

**Test 1:** This test checks if the Agenda object is correctly created using its constructor and if its getter methods return the expected values.

	 @Test
    public void testAgendaConstructorAndGetters() {
        LocalDate expectedDate = LocalDate.now();
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        TeamProposal teamProposal = new TeamProposal(5, 3, requiredSkills, selectedCollaborators);
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        assertEquals("Task", agenda.getTaskDescription());
        assertEquals("Greenspace", agenda.getGreenspaceName());
        assertEquals(expectedDate, agenda.getExpectedDate());
        assertEquals("Pending", agenda.getStatus());
        assertEquals(teamProposal, agenda.getTeamProposal());
    }



**Test 2:** This test checks if the setter methods of the Agenda class correctly update the values of the object's attributes and if the getter methods return the updated values.

    @Test
    public void testAgendaSetterAndGetters() {
        LocalDate expectedDate = LocalDate.now();
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        TeamProposal teamProposal = new TeamProposal(5, 3, requiredSkills, selectedCollaborators);
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        agenda.setStatus("Completed");
        assertEquals("Completed", agenda.getStatus());

        TeamProposal newTeamProposal = new TeamProposal(3, 2, new HashSet<>(), List.of());
        agenda.setTeamProposal(newTeamProposal);
        assertEquals(newTeamProposal, agenda.getTeamProposal());
    }


**Test 3:** This test verifies that the toString() method of the Agenda class returns the correct string representation of the object.
  
    @Test
    public void testToString() {
        LocalDate expectedDate = LocalDate.now();
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        TeamProposal teamProposal = new TeamProposal(5, 3, requiredSkills, selectedCollaborators);
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        String expectedString = "Task Description: Task, Expected Date: " + expectedDate +
                ", Greenspace: Greenspace, Status: Pending, Team:[John]";
        assertEquals(expectedString, agenda.toString());
    }

**Test 4:** This test checks if the getStartTime() method correctly returns the start of the day (00:00) for the expected date.

    @Test
    public void testGetStartTime() {
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
        assertEquals(expectedDate.atStartOfDay(), agenda.getStartTime());
    }

    
**Test 5:** This test checks if the getEndTime() method correctly returns the end of the day (23:59) for the expected date.
    
    @Test
    public void testGetEndTime() {
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
        assertEquals(expectedDate.atTime(23, 59), agenda.getEndTime());
    }

**Test 6:** This test checks if the Agenda constructor throws an IllegalArgumentException when null is passed for the teamProposal parameter.

    @Test
    public void testAgendaConstructorWithNullTeamProposal() {
        LocalDate expectedDate = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> new Agenda("Task", "Greenspace", expectedDate, "Pending", null));
    }

    
**Test 7:**  This test checks if the setStatus method throws an IllegalArgumentException when null is passed as the status.

    @Test
    public void testSetStatusWithNull() {
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
        assertThrows(IllegalArgumentException.class, () -> agenda.setStatus(null));
    }


## 4.2 Repository tests


**Test 1:** This test aims to verify the functionality of adding an agenda entry to the repository.

    @Test
    void testAdd() {
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda = new Agenda("Test Task", "Greenspace", LocalDate.now(), "Pending", teamProposal);
        Optional<Agenda> result = repository.add(agenda);
        System.out.println("Add Result: " + (result.isPresent() ? "Present" : "Not Present"));
        System.out.println("Task Description: " + (result.isPresent() ? result.get().getTaskDescription() : "N/A"));
        System.out.println("Total Entries: " + AgendaRepository.getAll().size());
    }


**Test 2:** This test verifies the functionality of retrieving all agenda entries from the repository.
     
    @Test
    void testGetAll() {
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        Agenda agenda2 = new Agenda("Task 2", "Greenspace 2", LocalDate.now(), "Completed", teamProposal);
        repository.add(agenda1);
        repository.add(agenda2);

        List<Agenda> agendas = AgendaRepository.getAll();
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Contains Task 1: " + agendas.contains(agenda1));
        System.out.println("Contains Task 2: " + agendas.contains(agenda2));
    }

    
**Test 3:** This test checks the functionality of updating an existing agenda entry in the repository.

    @Test
    void testUpdateAgendaEntry() {
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        Agenda agenda2 = new Agenda("Task 2", "Greenspace 2", LocalDate.now(), "Completed", teamProposal);
        repository.add(agenda1);
        repository.add(agenda2);

        Agenda updatedAgenda = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Updated Status", teamProposal);
        repository.updateAgendaEntry(updatedAgenda);

        List<Agenda> agendas = AgendaRepository.getAll();
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Updated Status for Task 1: " + agendas.get(0).getStatus());
        System.out.println("Status for Task 2: " + agendas.get(1).getStatus());
    }

    
**Test 4:** This test ensures that the save method properly updates an existing agenda entry or adds a new one if it doesn't exist.
     
    @Test
    void testSave() {
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        repository.add(agenda1);

        Agenda updatedAgenda = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Updated Status", teamProposal);
        repository.save(updatedAgenda);

        List<Agenda> agendas = AgendaRepository.getAll();
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Updated Status for Task 1: " + agendas.get(0).getStatus());
    }

**Test 5:** This test validates the functionality of retrieving all agenda entries directly from the repository instance.

    @Test
    void testGetAllAgendas() {
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        Agenda agenda2 = new Agenda("Task 2", "Greenspace 2", LocalDate.now(), "Completed", teamProposal);
        repository.add(agenda1);
        repository.add(agenda2);

        List<Agenda> agendas = repository.getAllAgendas();
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Contains Task 1: " + agendas.contains(agenda1));
        System.out.println("Contains Task 2: " + agendas.contains(agenda2));
    }




## 5. Construction (Implementation)

### Class Agenda 

```java
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
        if (teamProposal == null) {
            throw new IllegalArgumentException("Team proposal cannot be null");
        }
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

```

### Class AgendaRepository

```java
package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The AgendaRepository class represents a repository for storing agenda entries.
 */
public class AgendaRepository {
    private static final List<Agenda> agendaEntries = new ArrayList<>();

    /**
     * Adds a new agenda entry to the repository.
     *
     * @param agenda the agenda entry to add
     * @return an Optional containing the added agenda entry, or empty if the addition fails
     */
    public Optional<Agenda> add(Agenda agenda) {
        agendaEntries.add(agenda);
        return Optional.of(agenda);
    }

    /**
     * Retrieves all agenda entries from the repository.
     *
     * @return a list of all agenda entries
     */
    public static List<Agenda> getAll() {
        return new ArrayList<>(agendaEntries);
    }

    /**
     * Updates an existing agenda entry in the repository.
     *
     * @param agenda the agenda entry to update
     */
    public void updateAgendaEntry(Agenda agenda) {
        for (int i = 0; i < agendaEntries.size(); i++) {
            if (agendaEntries.get(i).getTaskDescription().equals(agenda.getTaskDescription())) {
                agendaEntries.set(i, agenda);
                break;
            }
        }
    }

    /**
     * Saves an agenda entry by updating it in the repository.
     *
     * @param entry the agenda entry to save
     */
    public void save(Agenda entry) {
        updateAgendaEntry(entry);
    }

    /**
     * Retrieves all agenda entries from the repository.
     *
     * @return a list of all agenda entries
     */
    public List<Agenda> getAllAgendas() {
        return new ArrayList<>(agendaEntries);
    }
}

```

## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some agenda entries are bootstrapped while system starts.


## 7. Observations

n/a