package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;

import java.util.List;
import java.util.Scanner;

public class AssignTeamToAgendaUI implements Runnable {
    private final AgendaController controller;
    private final Scanner scanner;

    public AssignTeamToAgendaUI() {
        this.controller = new AgendaController();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        // Display all agenda entries and select one
        List<Agenda> agendaEntries = controller.getAllAgendaEntries();
        if (agendaEntries.isEmpty()) {
            System.out.println("No Agenda entries found.");
            return;
        }
        Agenda selectedEntry = selectEntry(agendaEntries, scanner);
        if (selectedEntry == null) {
            System.out.println("Invalid selection. Exiting.");
            return;
        }

        // Display available team proposals with only collaborator names and select one
        List<TeamProposal> teamProposals = controller.getAllTeamProposals();
        if (teamProposals.isEmpty()) {
            System.out.println("No team proposals found.");
            return;
        }
        TeamProposal selectedTeam = selectTeamProposal(teamProposals, scanner);
        if (selectedTeam == null) {
            System.out.println("Invalid selection. Exiting.");
            return;
        }

        // Assign the selected team to the agenda entry
        selectedEntry.setTeamProposal(selectedTeam);
        controller.updateAgendaEntry(selectedEntry);

        System.out.println("Team assigned successfully.");
    }

    private Agenda selectEntry(List<Agenda> entries, Scanner scanner) {
        System.out.println("Select an Agenda Entry:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getTaskDescription());
        }

        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline
        if (index < 0 || index >= entries.size()) {
            return null;
        }

        return entries.get(index);
    }

    private TeamProposal selectTeamProposal(List<TeamProposal> teamProposals, Scanner scanner) {
        System.out.println("Available Teams:");
        for (int i = 0; i < teamProposals.size(); i++) {
            TeamProposal teamProposal = teamProposals.get(i);
            System.out.println((i + 1) + ". Collaborators:");
            for (Collaborator collaborator : teamProposal.getSelectedCollaborators()) {
                System.out.println("   - " + collaborator.getName());
            }
        }

        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline
        if (index < 0 || index >= teamProposals.size()) {
            return null;
        }

        return teamProposals.get(index);
    }
}