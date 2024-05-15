package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MaintenanceRegistrationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Scanner;

public class MaintenanceRegistrationUI implements Runnable {

    private final MaintenanceRegistrationController maintenanceRegistrationController;

    public MaintenanceRegistrationUI() {
        try {
            VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
            maintenanceRegistrationController = new MaintenanceRegistrationController(
                    Repositories.getInstance().getMaintenanceRepository(), vehicleRepository);
        } catch (Exception e) {
            System.err.println("Error initializing MaintenanceRegistrationUI: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        boolean continueMaintenance = true;

        while (continueMaintenance) {
            System.out.println("\nMaintenance Menu");
            System.out.println("1. Register Maintenance");
            System.out.println("2. Show Previous Maintenances");
            System.out.println("0. Exit");

            int choice = readIntegerInput(scanner);

            switch (choice) {
                case 1:
                    registerMaintenance(scanner);
                    break;
                case 2:
                    showPreviousMaintenances(scanner);
                    break;
                case 0:
                    continueMaintenance = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void registerMaintenance(Scanner scanner) {
        try {
            VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
            List<Vehicle> vehicles = vehicleRepository.getAllVehicles();
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles available. Please register a vehicle first.");
                return;
            }

            System.out.println("\nRegister Maintenance");
            System.out.println("Select a vehicle:");
            displayVehicleList(vehicles);

            System.out.print("Enter the index of the vehicle: ");
            int vehicleIndex = readIntegerInput(scanner);
            if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
                System.out.println("Invalid vehicle index.");
                return;
            }

            Vehicle selectedVehicle = vehicles.get(vehicleIndex);

            System.out.print("Enter maintenance kilometers: ");
            int maintenanceKm = readIntegerInput(scanner);

            maintenanceRegistrationController.registerMaintenance(selectedVehicle, maintenanceKm);
            System.out.println("Maintenance successfully registered.");
        } catch (Exception e) {
            System.err.println("Error registering maintenance: " + e.getMessage());
        }
    }

    private void showPreviousMaintenances(Scanner scanner) {
        try {
            VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
            List<Vehicle> vehicles = vehicleRepository.getAllVehicles();
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles available. Please register a vehicle first.");
                return;
            }

            System.out.println("\nShow Previous Maintenances");
            System.out.println("Select a vehicle:");
            displayVehicleList(vehicles);

            System.out.print("Enter the index of the vehicle: ");
            int vehicleIndex = readIntegerInput(scanner);
            if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
                System.out.println("Invalid vehicle index.");
                return;
            }

            Vehicle selectedVehicle = vehicles.get(vehicleIndex);

            List<String> maintenanceHistory = maintenanceRegistrationController.getMaintenanceHistoryByVehicle(selectedVehicle.getPlateID());
            if (maintenanceHistory.isEmpty()) {
                System.out.println("No maintenance history available for this vehicle.");
            } else {
                System.out.println("Maintenance History for Vehicle: " + selectedVehicle.getPlateID());
                for (String maintenanceInfo : maintenanceHistory) {
                    System.out.println("- " + maintenanceInfo);
                }
            }
        } catch (Exception e) {
            System.err.println("Error showing previous maintenances: " + e.getMessage());
        }
    }

    private void displayVehicleList(List<Vehicle> vehicles) {
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println(i + ". " + vehicles.get(i).getPlateID());
        }
    }

    private int readIntegerInput(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer value:");
            }
        }
    }
}
