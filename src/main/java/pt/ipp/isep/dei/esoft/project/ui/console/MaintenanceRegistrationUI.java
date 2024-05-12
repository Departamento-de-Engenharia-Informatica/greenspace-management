package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MaintenanceRegistrationController;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Scanner;

public class MaintenanceRegistrationUI implements Runnable {

    private final MaintenanceRegistrationController registrationController;


    public MaintenanceRegistrationUI() {
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();
        this.registrationController = new MaintenanceRegistrationController(maintenanceRepository);

    }


    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        startMaintenanceRegistration(scanner);
    }

    public void startMaintenanceRegistration(Scanner scanner) {
        boolean continueRegistration = true;

        while (continueRegistration) {
            System.out.println("\nMaintenance Registration");
            System.out.println("Enter maintenance kilometers:");

            int maintenanceKm = readIntegerInput(scanner);

            if (maintenanceKm < 0) {
                System.out.println("Invalid input. Maintenance kilometers must be non-negative.");
            } else {
                registrationController.registerMaintenance(maintenanceKm);
                System.out.println("Maintenance successfully registered.");
            }

            System.out.println("Do you want to register another maintenance? (Y/N)");
            String userInput = scanner.nextLine().trim().toUpperCase();

            if (!userInput.equals("Y")) {
                continueRegistration = false;
            }
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
