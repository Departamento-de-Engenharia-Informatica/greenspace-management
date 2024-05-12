package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MaintenanceRegistrationController;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleRegistrationController;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Scanner;

public class VehicleRegistrationUI implements Runnable {

    private final VehicleRegistrationController registrationController;

    public VehicleRegistrationUI() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        this.registrationController = new VehicleRegistrationController(vehicleRepository);
    }

    public void startVehicleRegistration(Scanner scanner) {
        boolean continueRegistration = true;

        while (continueRegistration) {
            System.out.println("\nVehicle Registration");
            System.out.println("Enter vehicle details or '0' to exit.");

            System.out.println("Plate:");
            String plate = scanner.nextLine();

            if (plate.equals("0")) {
                System.out.println("Exiting vehicle registration...");
                break;
            }

            System.out.println("Model:");
            String model = scanner.nextLine();

            System.out.println("Type:");
            String type = scanner.nextLine();

            System.out.println("Tare:");
            int tare = readIntegerInput(scanner);

            System.out.println("Gross Weight:");
            int grossWeight = readIntegerInput(scanner);

            System.out.println("Current KM:");
            int currentKm = readIntegerInput(scanner);

            System.out.println("Register Date (yyyy-MM-dd):");
            String registerDate = scanner.nextLine();

            System.out.println("Acquisition Date (yyyy-MM-dd):");
            String acquisitionDate = scanner.nextLine();

            System.out.println("Checkup Frequency (in kilometers):");
            int checkupFrequencyKm = readIntegerInput(scanner);

            System.out.println("Last Maintenance KM:");
            int lastMaintenanceKm = readIntegerInput(scanner);

            // Register vehicle using controller
            registrationController.registerVehicle(plate, model, type, tare, grossWeight, currentKm,
                    registerDate, acquisitionDate, checkupFrequencyKm, lastMaintenanceKm);
            System.out.println("Vehicle successfully registered.");

            System.out.println("Do you want to register another vehicle? (Y/N)");
            String userInput = scanner.nextLine().trim().toUpperCase();

            if (!userInput.equals("Y")) {
                continueRegistration = false;
            }
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        startVehicleRegistration(scanner);
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
