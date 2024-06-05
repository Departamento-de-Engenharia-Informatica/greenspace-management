package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.TeamProposal;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The DisplayTasksUI class represents a user interface for displaying tasks based on user input criteria.
 */
public class DisplayTasksUI implements Runnable {

    private final String userEmail;

    /**
     * Constructs a new DisplayTasksUI with the given user email.
     *
     * @param userEmail the email of the user accessing the display tasks functionality
     */
    public DisplayTasksUI(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Runs the display tasks user interface.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate;
        LocalDate endDate;
        String status;

        while (true) {
            while (true) {
                try {
                    System.out.print("Enter start date (DD-MM-YYYY): ");
                    String startDateStr = scanner.nextLine();
                    startDate = LocalDate.parse(startDateStr, dateFormatter);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use DD-MM-YYYY.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter end date (DD-MM-YYYY): ");
                    String endDateStr = scanner.nextLine();
                    endDate = LocalDate.parse(endDateStr, dateFormatter);
                    if (endDate.isBefore(startDate)) {
                        System.out.println("End date must be the same or after the start date.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use DD-MM-YYYY.");
                }
            }

            System.out.println("Please select a task status:");
            System.out.println("1. Planned");
            System.out.println("2. Postponed");
            System.out.println("3. Canceled");
            System.out.println("4. Done");
            System.out.println("5. All");
            System.out.println("6. Go back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    status = "Planned";
                    break;
                case 2:
                    status = "Postponed";
                    break;
                case 3:
                    status = "Canceled";
                    break;
                case 4:
                    status = "Done";
                    break;
                case 5:
                    status = "All";
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            DisplayTasks(startDate, endDate, status);
        }
    }

    /**
     * Displays tasks based on the specified criteria.
     *
     * @param startDate the start date for filtering tasks
     * @param endDate   the end date for filtering tasks
     * @param status    the status of tasks to display
     */
    public void DisplayTasks(LocalDate startDate, LocalDate endDate, String status) {
        List<Agenda> agendaEntries = Repositories.getInstance().getAgendaRepository().getAllAgendas();

        if (agendaEntries.isEmpty()) {
            System.out.println("No agenda entries found.");
            return;
        }

        boolean tasksFound = false;
        System.out.println("List of tasks assigned to me:");

        // Check if startDate or endDate is null
        if (startDate == null || endDate == null) {
            System.out.println("Invalid date range.");
            return;
        }

        // Filter and sort tasks by date
        List<Agenda> filteredTasks = new ArrayList<>();
        for (Agenda agenda : agendaEntries) {
            LocalDate date = agenda.getExpectedDate();
            if (date != null && isWithinDateRange(date, startDate, endDate) &&
                    (status.equals("All") || agenda.getStatus().equalsIgnoreCase(status))) {
                filteredTasks.add(agenda);
            }
        }
        Collections.sort(filteredTasks, Comparator.comparing(Agenda::getExpectedDate));

        // Display tasks in order of date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Agenda agenda : filteredTasks) {
            TeamProposal teamProposal = agenda.getTeamProposal();
            List<Collaborator> collaborators = teamProposal.getSelectedCollaborators();
            for (Collaborator collaborator : collaborators) {
                String collaboratorEmail = collaborator.getEmail();
                if (collaboratorEmail.equals(userEmail)) {
                    tasksFound = true;
                    System.out.println("Task: " + agenda.getTaskDescription() +
                            ", Date: " + agenda.getExpectedDate().format(dateFormatter) +
                            ", Greenspace: " + agenda.getGreenspaceName() +
                            ", Status: " + agenda.getStatus());
                    break;
                }
            }
        }

        if (!tasksFound) {
            System.out.println("No tasks found for the specified criteria.");
        }
    }

    /**
     * Checks if a given date is within a specified date range.
     *
     * @param date      the date to check
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @return true if the date is within the range, false otherwise
     */
    private boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}
