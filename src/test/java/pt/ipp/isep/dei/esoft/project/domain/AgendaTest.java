package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    private Agenda agenda;
    private TeamProposal teamProposal;

    @BeforeEach
    void setUp() {
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("John Doe", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john.doe@example.com", 123456789, 12345678L, "Developer"));
        Set<Skill> skills = new HashSet<>();
        teamProposal = new TeamProposal(5, 2, skills, collaborators);

        agenda = new Agenda("Task 1", "Central Park", LocalDate.of(2024, 6, 15), "Pending", teamProposal);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Task 1", agenda.getTaskDescription());
        assertEquals("Central Park", agenda.getGreenspaceName());
        assertEquals(LocalDate.of(2024, 6, 15), agenda.getExpectedDate());
        assertEquals("Pending", agenda.getStatus());
        assertEquals(teamProposal, agenda.getTeamProposal());
    }

    @Test
    void testSetStatus() {
        agenda.setStatus("Completed");
        assertEquals("Completed", agenda.getStatus());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            agenda.setStatus(null);
        });
        assertEquals("Status cannot be null", exception.getMessage());
    }

    @Test
    void testSetTeamProposal() {
        List<Collaborator> newCollaborators = new ArrayList<>();
        newCollaborators.add(new Collaborator("Jane Doe", LocalDate.of(1992, 2, 2), LocalDate.of(2021, 2, 2),
                "456 Main St", "987654321", "jane.doe@example.com", 987654321, 87654321L, "Manager"));
        TeamProposal newTeamProposal = new TeamProposal(5, 2, new HashSet<>(), newCollaborators);
        agenda.setTeamProposal(newTeamProposal);
        assertEquals(newTeamProposal, agenda.getTeamProposal());
    }

    @Test
    void testSetVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("ABC123", "Model X", "Truck", 1500, 5000, 30000,
                "2022-01-01", "2021-01-01", 10000, 25000));
        agenda.setVehicles(vehicles);

        // Reflectively check if vehicles are set correctly
        try {
            var field = Agenda.class.getDeclaredField("vehicles");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Vehicle> retrievedVehicles = (List<Vehicle>) field.get(agenda);
            assertEquals(vehicles, retrievedVehicles);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Vehicles field not found or inaccessible");
        }
    }

    @Test
    void testSetExpectedDate() {
        LocalDate newDate = LocalDate.of(2024, 6, 20);
        agenda.setExpectedDate(newDate);
        assertEquals(newDate, agenda.getExpectedDate());
    }

    @Test
    void testToString() {
        String expected = "Task Description: Task 1, Expected Date: 2024-06-15, Greenspace: Central Park, Status: Pending, Team:[John Doe]";
        assertEquals(expected, agenda.toString());
    }

    @Test
    void testGetStartTime() {
        assertEquals(LocalDate.of(2024, 6, 15).atStartOfDay(), agenda.getStartTime());
    }

    @Test
    void testGetEndTime() {
        assertEquals(LocalDate.of(2024, 6, 15).atTime(23, 59), agenda.getEndTime());
    }
}
