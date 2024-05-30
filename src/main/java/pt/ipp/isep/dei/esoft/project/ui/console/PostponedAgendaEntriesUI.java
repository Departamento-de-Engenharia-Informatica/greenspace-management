package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;

public class PostponedAgendaEntriesUI implements Runnable{
    private final Agenda agendaEntry;

    public PostponedAgendaEntriesUI(Agenda agendaEntry) {
        this.agendaEntry = agendaEntry;
    }

    private void cancelEntry() {
        agendaEntry.setStatus("PostPoned");
        System.out.println("Agenda entry has been postponed: " + agendaEntry);
    }

    @Override
    public void run() {
        cancelEntry();
    }
}
