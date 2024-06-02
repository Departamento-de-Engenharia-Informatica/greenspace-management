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
