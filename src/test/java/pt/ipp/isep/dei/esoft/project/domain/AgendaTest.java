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

public class AgendaTest {

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

    @Test
    public void testGetStartTime() {
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
        assertEquals(expectedDate.atStartOfDay(), agenda.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
        assertEquals(expectedDate.atTime(23, 59), agenda.getEndTime());
    }

    @Test
    public void testAgendaConstructorWithNullTeamProposal() {
        LocalDate expectedDate = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> new Agenda("Task", "Greenspace", expectedDate, "Pending", null));
    }


    @Test
    public void testSetStatusWithNull() {
        LocalDate expectedDate = LocalDate.now();
        TeamProposal teamProposal = new TeamProposal(5, 3, new HashSet<>(), List.of());
        Agenda agenda = new Agenda("Task", "Greenspace", expectedDate, "Pending", teamProposal);
        assertThrows(IllegalArgumentException.class, () -> agenda.setStatus(null));
    }
}
