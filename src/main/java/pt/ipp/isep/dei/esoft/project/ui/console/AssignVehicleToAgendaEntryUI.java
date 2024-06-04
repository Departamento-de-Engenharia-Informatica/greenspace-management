package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleToAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The AssignVehicleToAgendaEntryUI class is responsible for handling the user interface for assigning vehicles to agenda entries.
 */
public class AssignVehicleToAgendaEntryUI implements Runnable {
    private final String userEmail;
    private final AssignVehicleToAgendaEntryController controller;
    private final Scanner scanner;

    /**
     * Constructs an AssignVehicleToAgendaEntryUI with the specified user email.
     *
     * @param userEmail The email of the user.
     */
    public AssignVehicleToAgendaEntryUI(String userEmail) {
        this.userEmail = userEmail;
        this.controller = new AssignVehicleToAgendaEntryController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the user interface for assigning vehicles to agenda entries.
     */
    @Override
    public void run() {
        System.out.println("\n--- Assign Vehicles to Agenda Entries ---");
        AssignVehicleToAgendaEntryController controller = new AssignVehicleToAgendaEntryController();

        List<Agenda> entries = controller.getAllAgendaEntries();

        if (entries.isEmpty()) {
            System.out.println("No agenda entries found.");
            return;
        }

        // Select an agenda entry
        Agenda selectedEntry = selectEntry(entries, scanner);

        if (selectedEntry == null) {
            System.out.println("Exiting to previous menu.");
            return;
        }

        // Retrieve available vehicles for the selected entry's time period
        List<Vehicle> availableVehicles = controller.getAvailableVehicles(selectedEntry.getStartTime(), selectedEntry.getEndTime());
        List<Vehicle> selectedVehicles = selectVehicles(availableVehicles, scanner);

        if (selectedVehicles.isEmpty()) {
            System.out.println("No vehicles selected.");
            return;
        }

        // Assign the selected vehicles to the selected entry
        controller.assignVehiclesToEntry(selectedEntry, selectedVehicles);
        System.out.println("Vehicles assigned successfully.");
    }

    /**
     * Prompts the user to select an agenda entry from the list.
     *
     * @param entries The list of agenda entries.
     * @param scanner The scanner for user input.
     * @return The selected agenda entry, or null if the user cancels.
     */
    private Agenda selectEntry(List<Agenda> entries, Scanner scanner) {
        while (true) {
            System.out.println("Select an Agenda Entry:");
            for (int i = 0; i < entries.size(); i++) {
                System.out.println((i + 1) + ". " + entries.get(i).getTaskDescription());
            }
            System.out.println("0. Cancel");

            int index = scanner.nextInt() - 1;
            if (index == -1) {
                return null; // Exit to previous menu
            } else if (index < -1 || index >= entries.size()) {
                System.out.println("Invalid selection. Please try again.");
            } else {
                return entries.get(index);
            }
        }
    }

    /**
     * Prompts the user to select vehicles from the list.
     *
     * @param availableVehicles The list of available vehicles.
     * @param scanner           The scanner for user input.
     * @return The list of selected vehicles, or an empty list if the user cancels.
     */
    private List<Vehicle> selectVehicles(List<Vehicle> availableVehicles, Scanner scanner) {
        while (true) {
            System.out.println("Select Vehicles:");
            for (int i = 0; i < availableVehicles.size(); i++) {
                System.out.println((i + 1) + ". " + printVehicleDetails(availableVehicles.get(i)));
            }
            System.out.println("0. Cancel");
            System.out.println("Enter the numbers of the vehicles you want to select (comma-separated):");

            String input = scanner.next();
            if (input.equals("0")) {
                return new ArrayList<>(); // Exit to previous menu
            }

            String[] indices = input.split(",");
            List<Vehicle> selectedVehicles = new ArrayList<>();
            boolean invalidSelection = false;

            for (String index : indices) {
                int vehicleIndex = Integer.parseInt(index.trim()) - 1;
                if (vehicleIndex < 0 || vehicleIndex >= availableVehicles.size()) {
                    System.out.println("Invalid selection. Please try again.");
                    invalidSelection = true;
                    break;
                } else {
                    selectedVehicles.add(availableVehicles.get(vehicleIndex));
                }
            }

            if (!invalidSelection) {
                return selectedVehicles;
            }
        }
    }

    /**
     * Formats the vehicle details for display.
     *
     * @param vehicle The vehicle whose details are to be formatted.
     * @return The formatted vehicle details.
     */
    private String printVehicleDetails(Vehicle vehicle) {
        return vehicle.formatVehicleDetails(); // Adjust this to format vehicle details appropriately
    }
}
