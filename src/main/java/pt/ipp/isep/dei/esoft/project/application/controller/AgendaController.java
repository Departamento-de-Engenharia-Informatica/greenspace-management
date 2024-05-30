package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AgendaController {
    private final Repositories repositories;

    public AgendaController() {
        this.repositories = Repositories.getInstance();
    }

    public List<GreenSpace> getGreenSpaces(String userEmail) {
        return repositories.getGreenSpaceRepository().findByUserEmail(userEmail);
    }

    public List<ToDoList> getToDoListEntries(String userEmail, String greenSpaceName) {
        return repositories.getToDoListRepository().findByUserEmailAndGreenSpaceName(userEmail, greenSpaceName);
    }

    public Optional<Agenda> createAgendaEntry(String taskDescription, String greenSpaceName, LocalDate expectedDate, String status) {
        // Ensure the To-Do list entry exists
        Optional<ToDoList> toDoListEntry = repositories.getToDoListRepository().findByTaskDescription(taskDescription);
        if (toDoListEntry.isPresent()) {
            Agenda agendaEntry = new Agenda(taskDescription, greenSpaceName, expectedDate, status);
            repositories.getAgendaRepository().add(agendaEntry);
            return Optional.of(agendaEntry);
        }
        return Optional.empty();
    }

    public static List<Agenda> getAllAgendaEntries() {
        return AgendaRepository.getAll();
    }
}
