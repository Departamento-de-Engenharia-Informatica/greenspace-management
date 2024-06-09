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
