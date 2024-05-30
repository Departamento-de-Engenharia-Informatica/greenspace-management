package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;

public class CancelAgendaEntriesUI implements Runnable {
    private final Agenda agendaEntry;

    public CancelAgendaEntriesUI(Agenda agendaEntry) {
        this.agendaEntry = agendaEntry;
    }

    private void cancelEntry() {
        agendaEntry.setStatus("Canceled");
        System.out.println("Agenda entry has been canceled: " + agendaEntry);
    }

    @Override
    public void run() {
        cancelEntry();
    }
}
