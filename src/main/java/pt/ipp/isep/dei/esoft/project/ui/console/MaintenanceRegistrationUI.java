package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MaintenanceRegistrationController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Scanner;

public class MaintenanceRegistrationUI implements Runnable {

    private final MaintenanceRegistrationController maintenanceRegistrationController;

    public MaintenanceRegistrationUI() {
        this.maintenanceRegistrationController = new MaintenanceRegistrationController(
                Repositories.getInstance().getMaintenanceRepository(),
                Repositories.getInstance().getVehicleRepository()
        );
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
                    displayVehicleListAndRegisterMaintenance(scanner);
                    break;
                case 2:
                    displayVehicleListAndShowPreviousMaintenances(scanner);
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

    private void displayVehicleListAndRegisterMaintenance(Scanner scanner) {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available. Please register a vehicle first.");
            return;
        }

        System.out.println("\nSelect a Vehicle to Register Maintenance:");
        displayVehicleList(vehicles);

        System.out.print("Enter the index of the vehicle: ");
        int vehicleIndex = readIntegerInput(scanner);

        if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
            System.out.println("Invalid vehicle index.");
            return;
        }

        if(vehicleIndex == 0){
            run();
        }

        Vehicle selectedVehicle = vehicles.get(vehicleIndex-1);

        System.out.print("Enter maintenance kilometers: ");
        int maintenanceKm = readIntegerInput(scanner);

        System.out.print("Enter maintenance date (dd-MM-yyyy): ");
        String maintenanceDate = scanner.nextLine();

        maintenanceRegistrationController.registerMaintenance(selectedVehicle.getPlateID(), maintenanceKm, maintenanceDate);
        System.out.println("Maintenance successfully registered for vehicle: " + selectedVehicle.getPlateID());
    }

    private void displayVehicleListAndShowPreviousMaintenances(Scanner scanner) {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        List<Vehicle> vehicles = vehicleRepository.getAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available. Please register a vehicle first.");
            return;
        }

        System.out.println("\nSelect a Vehicle to Show Previous Maintenances:");
        displayVehicleList(vehicles);

        System.out.print("Enter the index of the vehicle: ");
        int vehicleIndex = readIntegerInput(scanner);

        if (vehicleIndex < 1 || vehicleIndex >= vehicles.size() +1) {
            System.out.println("Invalid vehicle index.");
            return;
        }

        if(vehicleIndex == vehicles.size()+1){
            run();
        }

        Vehicle selectedVehicle = vehicles.get(vehicleIndex-1);

        List<String> maintenanceHistory = maintenanceRegistrationController.getMaintenanceHistoryByVehicle(selectedVehicle.getPlateID());
        if (maintenanceHistory.isEmpty()) {
            System.out.println("No maintenance history available for vehicle: " + selectedVehicle.getPlateID());
        } else {
            System.out.println("Maintenance History for Vehicle: " + selectedVehicle.getPlateID());
            for (String maintenanceInfo : maintenanceHistory) {
                System.out.println("- " + maintenanceInfo);
            }
        }
    }


    private void displayVehicleList(List<Vehicle> vehicles) {
        for (int i = 1; i <= vehicles.size()+1; i++) {
            System.out.println(i + ". " + vehicles.get(i-1).getPlateID());
        }
        System.out.println("0. Exit");
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

    public static void main(String[] args) {
        MaintenanceRegistrationUI ui = new MaintenanceRegistrationUI();
        ui.run();
    }
}
