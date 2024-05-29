package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.List;

public class ListAgendaEntries {

    public static void ListAllAgendaEntries() {
        System.out.println("--- All Agenda Entries ---");
        List<Agenda> agendaEntries = AgendaController.getAllAgendaEntries();
        if (agendaEntries.isEmpty()) {
            System.out.println("No Agenda entries found.");
        } else {
            for (Agenda entry : agendaEntries) {
                System.out.println(entry);
            }
        }
    }
}
