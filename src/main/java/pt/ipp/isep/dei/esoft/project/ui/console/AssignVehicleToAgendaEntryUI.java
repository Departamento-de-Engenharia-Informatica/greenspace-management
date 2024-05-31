package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleToAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignVehicleToAgendaEntryUI implements Runnable {
    private final String userEmail;
    private final AssignVehicleToAgendaEntryController controller;
    private final Scanner scanner;

    public AssignVehicleToAgendaEntryUI(String userEmail) {
        this.userEmail = userEmail;
        this.controller = new AssignVehicleToAgendaEntryController();
        this.scanner = new Scanner(System.in);
    }

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
            System.out.println("Invalid selection.");
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

    private Agenda selectEntry(List<Agenda> entries, Scanner scanner) {
        System.out.println("Select an Agenda Entry:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getTaskDescription());
        }

        int index = scanner.nextInt() - 1;
        if (index < 0 || index >= entries.size()) {
            return null;
        }

        return entries.get(index);
    }

    private List<Vehicle> selectVehicles(List<Vehicle> availableVehicles, Scanner scanner) {
        System.out.println("Select Vehicles:");
        for (int i = 0; i < availableVehicles.size(); i++) {
            System.out.println((i + 1) + ". " + printVehicleDetails(availableVehicles.get(i)));
        }

        System.out.println("Enter the numbers of the vehicles you want to select (comma-separated):");
        String[] indices = scanner.next().split(",");
        List<Vehicle> selectedVehicles = new ArrayList<>();

        for (String index : indices) {
            int vehicleIndex = Integer.parseInt(index.trim()) - 1;
            if (vehicleIndex >= 0 && vehicleIndex < availableVehicles.size()) {
                selectedVehicles.add(availableVehicles.get(vehicleIndex));
            }
        }

        return selectedVehicles;
    }

    private String printVehicleDetails(Vehicle vehicle) {
        return vehicle.toString(); // Adjust this to format vehicle details appropriately
    }
}
