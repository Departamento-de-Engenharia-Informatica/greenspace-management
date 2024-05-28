package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {
    private final List<Agenda> agendaEntries = new ArrayList<>();

    public Optional<Agenda> add(Agenda agenda) {
        agendaEntries.add(agenda);
        return Optional.of(agenda);
    }

    public List<Agenda> getAll() {
        return new ArrayList<>(agendaEntries);
    }

    public void updateAgendaEntry(Agenda agenda) {
        for (int i = 0; i < agendaEntries.size(); i++) {
            if (agendaEntries.get(i).getTaskDescription().equals(agenda.getTaskDescription())) {
                agendaEntries.set(i, agenda);
                break;
            }
        }
    }
}
