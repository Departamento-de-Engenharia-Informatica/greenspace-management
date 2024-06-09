package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the Agenda class.
 */
public class AgendaTest {

    /**
     * Tests the constructor and getters of Agenda.
     */
    @Test
    public void testAgendaConstructorAndGetters() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        TeamProposal teamProposal = new TeamProposal(5, 3, requiredSkills, selectedCollaborators);

        // Act
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        // Assert
        assertEquals("Task", agenda.getTaskDescription());
        assertEquals("Greenspace", agenda.getGreenspaceName());
        assertEquals(expectedDate, agenda.getExpectedDate());
        assertEquals("Pending", agenda.getStatus());
        assertEquals(teamProposal, agenda.getTeamProposal());
    }

    /**
     * Tests the setters and getters of Agenda.
     */
    @Test
    public void testAgendaSetterAndGetters() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        TeamProposal teamProposal = new TeamProposal(5, 3, requiredSkills, selectedCollaborators);
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        // Act
        agenda.setStatus("Completed");

        // Assert
        assertEquals("Completed", agenda.getStatus());

        // Arrange
        TeamProposal newTeamProposal = new TeamProposal(3, 2, new HashSet<>(), List.of());

        // Act
        agenda.setTeamProposal(newTeamProposal);

        // Assert
        assertEquals(newTeamProposal, agenda.getTeamProposal());
    }

    /**
     * Tests the toString method of Agenda.
     */
    @Test
    public void testToString() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        TeamProposal teamProposal = new TeamProposal(5, 3, requiredSkills, selectedCollaborators);
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        // Act
        String expectedString = "Task Description: Task, Expected Date: " + expectedDate +
                ", Greenspace: Greenspace, Status: Pending, Team:[John]";

        // Assert
        assertEquals(expectedString, agenda.toString());
    }

    /**
     * Tests the getStartTime method of Agenda.
     */
    @Test
    public void testGetStartTime() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        // Act & Assert
        assertEquals(expectedDate.atStartOfDay(), agenda.getStartTime());
    }

    /**
     * Tests the getEndTime method of Agenda.
     */
    @Test
    public void testGetEndTime() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        // Act & Assert
        assertEquals(expectedDate.atTime(23, 59), agenda.getEndTime());
    }

    /**
     * Tests the constructor of Agenda with a null TeamProposal, which should throw an IllegalArgumentException.
     */
    @Test
    public void testAgendaConstructorWithNullTeamProposal() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Agenda("Task", "Greenspace", expectedDate, "Pending", null));
    }

    /**
     * Tests the setStatus method of Agenda with null, which should throw an IllegalArgumentException.
     */
    @Test
    public void testSetStatusWithNull() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> agenda.setStatus(null));
    }
}
