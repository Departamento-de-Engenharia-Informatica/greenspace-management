package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VehicleRegistrationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Scanner;

public class VehicleRegistrationUI implements Runnable {

    private final VehicleRegistrationController registrationController;

    public VehicleRegistrationUI() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        this.registrationController = new VehicleRegistrationController(vehicleRepository);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        boolean continueRegistration = true;

        while (continueRegistration) {
            System.out.println("\nVehicle Fleet Management Menu");
            System.out.println("1. Register Vehicle");
            System.out.println("2. Update Current Kilometers");
            System.out.println("3. View All Vehicles");
            System.out.println("0. Exit");

            int choice = readIntegerInput(scanner);

            switch (choice) {
                case 1:
                    startVehicleRegistration(scanner);
                    break;
                case 2:
                    updateCurrentKilometers(scanner);
                    break;
                case 3:
                    displayAllVehicles();
                    break;

                case 0:
                    continueRegistration = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void startVehicleRegistration(Scanner scanner) {
        VehicleRegistrationUI vehicleRegistrationUI = new VehicleRegistrationUI();
        vehicleRegistrationUI.startVehicleRegistration(scanner);
    }

    private void updateCurrentKilometers(Scanner scanner) {
        try {
            VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
            List<Vehicle> vehicles = vehicleRepository.getAllVehicles();
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles available. Please register a vehicle first.");
                return;
            }

            System.out.println("\nUpdate Current Kilometers");
            System.out.println("Select a vehicle to update current kilometers:");
            displayVehicleList(vehicles);

            System.out.print("Enter the index of the vehicle: ");
            int vehicleIndex = readIntegerInput(scanner);
            if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
                System.out.println("Invalid vehicle index.");
                return;
            }

            Vehicle selectedVehicle = vehicles.get(vehicleIndex);

            System.out.print("Enter updated current kilometers: ");
            int newCurrentKm = readIntegerInput(scanner);

            // Call controller method to update current kilometers
            registrationController.updateCurrentKilometers(selectedVehicle, newCurrentKm);
            System.out.println("Current kilometers updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating current kilometers: " + e.getMessage());
        }
    }

    private void displayAllVehicles() {
        List<Vehicle> vehicles = registrationController.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
        } else {
            System.out.println("\nAll Vehicles:");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
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
