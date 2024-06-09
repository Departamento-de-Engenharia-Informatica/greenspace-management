# US022- Create a agenda entry

## 4.1 Tests 


**Test 1:** Tests the constructor and getters of Agenda.
  
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


**Test 2:** Tests the setters and getters of Agenda.

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

**Test 3:** Tests the toString method of Agenda.
 
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

**Test 4:** Tests the getStartTime method of Agenda.

      @Test
      public void testGetStartTime() {
      LocalDate expectedDate = LocalDate.now();
      TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
      Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
      assertEquals(expectedDate.atStartOfDay(), agenda.getStartTime());
      }

**Test 5:** Tests the getEndTime method of Agenda.

      @Test
      public void testGetEndTime() {
      LocalDate expectedDate = LocalDate.now();
      TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
      Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
      assertEquals(expectedDate.atTime(23, 59), agenda.getEndTime());
      }

**Test 6:** Tests the constructor of Agenda with a null TeamProposal, which should throw an IllegalArgumentException.

      @Test
      public void testAgendaConstructorWithNullTeamProposal() {
      LocalDate expectedDate = LocalDate.now();
      assertThrows(IllegalArgumentException.class, () -> new Agenda("Task", "Greenspace", expectedDate, "Pending", null));
      }

**Test 7:** Tests the setStatus method of Agenda with null, which should throw an IllegalArgumentException.

      @Test
      public void testSetStatusWithNull() {
      LocalDate expectedDate = LocalDate.now();
      TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
      Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
      assertThrows(IllegalArgumentException.class, () -> agenda.setStatus(null));
      }


## 4.2 Repository tests


**Test 1:**  Tests the add method of the AgendaRepository class.

    @Test
    void testAdd() {
    // Test setup
    TeamProposal teamProposal = createMockTeamProposal();
    Agenda agenda = new Agenda("Test Task", "Greenspace", LocalDate.now(), "Pending", teamProposal);

        // Exercise
        Optional<Agenda> result = repository.add(agenda);

        // Verify
        System.out.println("Add Result: " + (result.isPresent() ? "Present" : "Not Present"));
        System.out.println("Task Description: " + (result.isPresent() ? result.get().getTaskDescription() : "N/A"));
        System.out.println("Total Entries: " + AgendaRepository.getAll().size());
    }

**Test 2:** Tests the getAll method of the AgendaRepository class.

    @Test
    void testGetAll() {
        // Test setup
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        Agenda agenda2 = new Agenda("Task 2", "Greenspace 2", LocalDate.now(), "Completed", teamProposal);
        repository.add(agenda1);
        repository.add(agenda2);

        // Exercise
        List<Agenda> agendas = AgendaRepository.getAll();

        // Verify
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Contains Task 1: " + agendas.contains(agenda1));
        System.out.println("Contains Task 2: " + agendas.contains(agenda2));
    }

**Test 3:**  Tests the updateAgendaEntry method of the AgendaRepository class.
 
    @Test
    void testUpdateAgendaEntry() {
        // Test setup
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        Agenda agenda2 = new Agenda("Task 2", "Greenspace 2", LocalDate.now(), "Completed", teamProposal);
        repository.add(agenda1);
        repository.add(agenda2);

        // Exercise
        Agenda updatedAgenda = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Updated Status", teamProposal);
        repository.updateAgendaEntry(updatedAgenda);

        // Verify
        List<Agenda> agendas = AgendaRepository.getAll();
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Updated Status for Task 1: " + agendas.get(0).getStatus());
        System.out.println("Status for Task 2: " + agendas.get(1).getStatus());
    }

**Test 4:**  Tests the save method of the AgendaRepository class.

    @Test
    void testSave() {
        // Test setup
        TeamProposal teamProposal = createMockTeamProposal();
        Agenda agenda1 = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Pending", teamProposal);
        repository.add(agenda1);

        // Exercise
        Agenda updatedAgenda = new Agenda("Task 1", "Greenspace 1", LocalDate.now(), "Updated Status", teamProposal);
        repository.save(updatedAgenda);

        // Verify
        List<Agenda> agendas = AgendaRepository.getAll();
        System.out.println("Total Entries: " + agendas.size());
        System.out.println("Updated Status for Task 1: " + agendas.get(0).getStatus());
    }





## 5. Construction (Implementation)

### Class AgendaController

```java
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamProposalRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AgendaController {
    private final Repositories repositories;
    private TeamProposal teamProposal;

    public AgendaController() {
        this.repositories = Repositories.getInstance();
    }

    public List<GreenSpace> getGreenSpaces(String userEmail) {
        return repositories.getGreenSpaceRepository().findByUserEmail(userEmail);
    }

    public List<ToDoList> getToDoListEntries(String userEmail, String greenSpaceName) {
        return repositories.getToDoListRepository().findByUserEmailAndGreenSpaceName(userEmail, greenSpaceName);
    }

    public Optional<Agenda> createAgendaEntry(String taskDescription, String greenSpaceName, LocalDate expectedDate, String status, TeamProposal teamProposal) {
        // Ensure the To-Do list entry exists
        Optional<ToDoList> toDoListEntry = repositories.getToDoListRepository().findByTaskDescription(taskDescription);
        if (toDoListEntry.isPresent()) {
            Agenda agendaEntry = new Agenda(taskDescription, greenSpaceName, expectedDate, status, teamProposal);
            repositories.getAgendaRepository().add(agendaEntry);
            return Optional.of(agendaEntry);
        }
        return Optional.empty();
    }

    public List<TeamProposal> getAllTeamProposals() {
        return repositories.getTeamProposalRepository().getAllTeamProposals();
    }
    public void updateAgendaEntry(Agenda agenda) {
        repositories.getAgendaRepository().updateAgendaEntry(agenda);
    }

    public static List<Agenda> getAllAgendaEntries() {
        return AgendaRepository.getAll();
    }
}

```

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
    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
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


## 7. Observations

n/a