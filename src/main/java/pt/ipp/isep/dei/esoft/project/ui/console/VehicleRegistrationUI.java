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
    private void registerNewVehicle(Scanner scanner) {
        System.out.println("\nRegister a New Vehicle");

        String plateID = null;
        String model = null;
        String type = null;
        int tare = 0;
        int grossWeight = 0;
        int currentKm = 0;
        String registerDate = null;
        String acquisitionDate = null;
        int checkupFrequencyKm = 0;
        int lastMaintenanceKm = 0;

        while (true) {
            try {
                System.out.print("Enter Plate ID: ");
                plateID = scanner.nextLine();

                System.out.print("Enter Model: ");
                model = scanner.nextLine();

                System.out.print("Enter Type: ");
                type = scanner.nextLine();

                System.out.print("Enter Tare Weight: ");
                tare = readIntegerInput(scanner);
                validateNonNegative(tare, "Tare Weight");

                System.out.print("Enter Gross Weight: ");
                grossWeight = readIntegerInput(scanner);
                validatePositive(grossWeight, "Gross Weight");

                System.out.print("Enter Current Kilometers: ");
                currentKm = readIntegerInput(scanner);
                validateNonNegative(currentKm, "Current Kilometers");

                System.out.print("Enter Registration Date: ");
                registerDate = scanner.nextLine();

                System.out.print("Enter Acquisition Date: ");
                acquisitionDate = scanner.nextLine();

                System.out.print("Enter Checkup Frequency Kilometers: ");
                checkupFrequencyKm = readIntegerInput(scanner);
                validatePositive(checkupFrequencyKm, "Checkup Frequency Kilometers");

                System.out.print("Enter Last Maintenance Kilometers: ");
                lastMaintenanceKm = readIntegerInput(scanner);
                validateNonNegative(lastMaintenanceKm, "Last Maintenance Kilometers");

                // If all inputs are valid, break out of the loop
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        // Register the vehicle using the controller with validated inputs
        registrationController.registerVehicle(plateID, model, type, tare, grossWeight,
                currentKm, registerDate, acquisitionDate, checkupFrequencyKm, lastMaintenanceKm);

        System.out.println("Vehicle registered successfully.");
    }
    private void validateNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be non-negative.");
        }
    }

    private void validatePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive.");
        }
    }

    private void startVehicleRegistration(Scanner scanner) {
        // Directly call registerNewVehicle method
        registerNewVehicle(scanner);
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
