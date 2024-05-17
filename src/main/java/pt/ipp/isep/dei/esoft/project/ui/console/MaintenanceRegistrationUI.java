package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MaintenanceRegistrationController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MaintenanceRegistrationUI implements Runnable {

    private final MaintenanceRegistrationController maintenanceRegistrationController;
    public LocalDate currentDate = LocalDate.now();


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

        if (vehicleIndex < 1 || vehicleIndex > vehicles.size()) {
            System.out.println("Invalid vehicle index.");
            return;
        }

        Vehicle selectedVehicle = vehicles.get(vehicleIndex - 1);

        System.out.print("Enter maintenance kilometers: ");
        int maintenanceKm = readIntegerInput(scanner);

        if (selectedVehicle.getCurrentKm() > maintenanceKm) {
            System.out.println("The maintenance km needs to be equal or superior to the vehicle current km.");
            displayVehicleListAndRegisterMaintenance(scanner);
        }

        System.out.print("Enter maintenance date (dd-MM-yyyy): ");
        String maintenanceDate = validateDate(scanner);


        maintenanceRegistrationController.registerMaintenance(selectedVehicle.getPlateID(), maintenanceKm, maintenanceDate);
        selectedVehicle.setLastMaintenanceKm(maintenanceKm); // Updating the last maintenance km
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

        if (vehicleIndex < 1 || vehicleIndex > vehicles.size()) {
            System.out.println("Invalid vehicle index.");
            return;
        }

        Vehicle selectedVehicle = vehicles.get(vehicleIndex - 1);

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
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).getPlateID());
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
    private String validateDate(Scanner sc){
        String date = sc.nextLine();
        String[] strParts = date.split("-");
        if(strParts.length != 3){
            System.out.println("Wrong format. Please retry.");
            displayVehicleListAndRegisterMaintenance(sc);
        }
        int days = Integer.parseInt(strParts[0].trim());
        int months = Integer.parseInt(strParts[1].trim());
        int years = Integer.parseInt(strParts[2].trim());
        int currentYear = LocalDate.now().getYear();
        if(months < 1 || months > 12){
            System.out.println("There's only 12 months.");
            displayVehicleListAndRegisterMaintenance(sc);
        }
        if(months == 1 || months == 3 || months == 5 || months == 7 || months == 8 || months == 10 || months == 12){
            if(days < 1 || days > 31){
                System.out.println("This month has 31 days.");
                displayVehicleListAndRegisterMaintenance(sc);
            }
        }
        if(months == 4 || months == 6 || months == 9 || months == 11){
            if(days < 1 || days > 30){
                System.out.println("This month has 30 days.");
                displayVehicleListAndRegisterMaintenance(sc);
            }
        }
        if(months == 2){
            if(years % 4 == 0){
                if(days < 1 || days > 29){
                    System.out.println("Leap Year. This month has 29 days.");
                    displayVehicleListAndRegisterMaintenance(sc);
                }
            }
            if(years % 4 != 0){
                if(days < 1 || days > 28){
                    System.out.println("Normal Year. This month has 28 days.");
                    displayVehicleListAndRegisterMaintenance(sc);
                }
            }
        }
        if(years < 1700 || years > currentYear){
            System.out.println("Bet you chose the wrong year. Check again.");
            displayVehicleListAndRegisterMaintenance(sc);
        }
        LocalDate inputDate = LocalDate.of(years, months, days);
        if(inputDate.isAfter(currentDate)){
            System.out.println("This date is in the future.");
            displayVehicleListAndRegisterMaintenance(sc);
        }

        return date;
    }

    public static void main(String[] args) {
        MaintenanceRegistrationUI ui = new MaintenanceRegistrationUI();
        ui.run();
    }
}
