package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.List;
import java.util.Scanner;

public class ChangeStatusAgendaEntriesUI implements Runnable {
    private final AgendaController agendaController;

    public ChangeStatusAgendaEntriesUI() {
        this.agendaController = new AgendaController();
    }

    private void listEntries(List<Agenda> agendaEntries) {
        System.out.println("--- All Agenda Entries ---");
        for (int i = 0; i < agendaEntries.size(); i++) {
            System.out.println((i + 1) + ". " + agendaEntries.get(i));
        }
        System.out.println((agendaEntries.size() + 1) + ". Exit");
    }

    private void displayStatusMenu(Agenda entry) {
        System.out.println("\n--- Select an action for the entry ---");
        String currentStatus = entry.getStatus();
        int optionNumber = 1;

        if (!"Done".equals(currentStatus)) {
            System.out.println(optionNumber++ + ". Done");
        }
        if (!"Postponed".equals(currentStatus)) {
            System.out.println(optionNumber++ + ". Postponed");
        }
        if (!"Canceled".equals(currentStatus)) {
            System.out.println(optionNumber++ + ". Canceled");
        }
        if (!"Planned".equals(currentStatus)) {
            System.out.println(optionNumber++ + ". Planned");
        }
        System.out.println(optionNumber++ + ". Exit");
        System.out.print("Enter your choice: ");
    }



    //DAR UPDATE NA CLASSE CRIADA PARA O POSTPONED AQUI
    private void handleStatusSelection(int choice, Agenda entry) {
        String currentStatus = entry.getStatus();
        int optionNumber = 1;

        if(choice == 4){
            run();
        }

        if (!"Done".equals(currentStatus) && choice == optionNumber++) {
            entry.setStatus("Done");
            System.out.println("Status updated to Done");
            return;
        }
        if (!"Postponed".equals(currentStatus) && choice == optionNumber++) {
            entry.setStatus("Postponed");
            System.out.println("Status updated to Postponed");
            return;
        }
        if (!"Canceled".equals(currentStatus) && choice == optionNumber++) {
            new CancelAgendaEntriesUI(entry).run();
            return;
        }
        if (!"Planned".equals(currentStatus) && choice == optionNumber++) {
            entry.setStatus("Planned");
            System.out.println("Status updated to Planned");
            return;
        }
        System.out.println("Invalid choice. Please try again.");
    }

    @Override
    public void run() {
        List<Agenda> agendaEntries = agendaController.getAllAgendaEntries();
        if (agendaEntries.isEmpty()) {
            System.out.println("No Agenda entries found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            listEntries(agendaEntries);
            System.out.print("Select an entry to change its status (or Exit): ");
            int entryChoice = scanner.nextInt();

            if (entryChoice == agendaEntries.size() + 1) {
                System.out.println("Exiting...");
                break;
            }

            if (entryChoice < 1 || entryChoice > agendaEntries.size()) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            Agenda selectedEntry = agendaEntries.get(entryChoice - 1);
            displayStatusMenu(selectedEntry);
            int statusChoice = scanner.nextInt();
            handleStatusSelection(statusChoice, selectedEntry);
        }
    }
}
