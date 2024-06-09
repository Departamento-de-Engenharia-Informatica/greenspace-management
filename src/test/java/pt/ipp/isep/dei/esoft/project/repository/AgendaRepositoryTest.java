package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


class AgendaRepositoryTest {

    private AgendaRepository repository;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        repository = new AgendaRepository();
        AgendaRepository.getAll().clear();
    }

    /**
     * Creates a mock team proposal for testing purposes.
     *
     * @return a mock team proposal
     */
    private TeamProposal createMockTeamProposal() {
        Set<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(new Skill("Programming"));
        List<Collaborator> selectedCollaborators = List.of(new Collaborator("John", LocalDate.of(1990, 5, 20), LocalDate.of(2020, 1, 1),
                "123 Main St", "123456789", "john@example.com", 123456789, 12345678, "Developer"));
        return new TeamProposal(5, 3, requiredSkills, selectedCollaborators);
    }

    /**
     * Tests the add method of the AgendaRepository class.
     */
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

    /**
     * Tests the getAll method of the AgendaRepository class.
     */
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

    /**
     * Tests the updateAgendaEntry method of the AgendaRepository class.
     */
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

    /**
     * Tests the save method of the AgendaRepository class.
     */
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

    /**
     * Tests the getAllAgendas method of the AgendaRepository class.
     */
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
}