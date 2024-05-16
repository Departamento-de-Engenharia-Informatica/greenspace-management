package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VehicleRegistrationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class VehicleRegistrationUI implements Runnable {

    private final VehicleRegistrationController registrationController;
    public Scanner scanner = new Scanner(System.in);
    public LocalDate currentDate = LocalDate.now();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");



    public VehicleRegistrationUI() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        this.registrationController = new VehicleRegistrationController(vehicleRepository);
    }

    //FAZER VEHICULOS COMO COLABORATORS

    @Override
    public void run() {

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
                    registerNewVehicle(scanner);
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
        boolean registeringAnother = true;


        while (registeringAnother) {
            try {
                System.out.print("Enter Registration Date: ");
                registerDate = validateDate(scanner);

                System.out.print("Enter Plate ID: ");
                plateID = validatePlateID(scanner, registerDate);

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

                System.out.print("Enter Acquisition Date: ");
                acquisitionDate = validateAquisitionDate(scanner, registerDate);

                System.out.print("Enter Checkup Frequency Kilometers: ");
                checkupFrequencyKm = readIntegerInput(scanner);
                validatePositive(checkupFrequencyKm, "Checkup Frequency Kilometers");

                System.out.print("Enter Last Maintenance Kilometers: ");
                lastMaintenanceKm = readIntegerInput(scanner);
                validateNonNegative(lastMaintenanceKm, "Last Maintenance Kilometers");
                validatekm(currentKm, lastMaintenanceKm);

                // Register the vehicle using the controller with validated inputs
                registrationController.registerVehicle(plateID, model, type, tare, grossWeight,
                        currentKm, registerDate, acquisitionDate, checkupFrequencyKm, lastMaintenanceKm);

                System.out.println("Vehicle registered successfully.");
                System.out.print("Do you want to register another vehicle? (Y/N): ");
                String input = scanner.nextLine().trim();

                if (!input.equalsIgnoreCase("Y")) {
                    registeringAnother = false;
                }


                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }


    }

    private String validateDate(Scanner sc){
        String date = scanner.nextLine();
        String[] strParts = date.split("-");
        if(strParts.length != 3){
            System.out.println("Wrong format. Please retry.");
            registerNewVehicle(sc);
        }
        int days = Integer.parseInt(strParts[0].trim());
        int months = Integer.parseInt(strParts[1].trim());
        int years = Integer.parseInt(strParts[2].trim());
        int currentYear = LocalDate.now().getYear();
        if(months < 1 || months > 12){
            System.out.println("There's only 12 months.");
            registerNewVehicle(sc);
        }
        if(months == 1 || months == 3 || months == 5 || months == 7 || months == 8 || months == 10 || months == 12){
            if(days < 1 || days > 31){
                System.out.println("This month has 31 days.");
                registerNewVehicle(sc);
            }
        }
        if(months == 4 || months == 6 || months == 9 || months == 11){
            if(days < 1 || days > 30){
                System.out.println("This month has 30 days.");
                registerNewVehicle(sc);
            }
        }
        if(months == 2){
            if(years % 4 == 0){
                if(days < 1 || days > 29){
                    System.out.println("Leap Year. This month has 29 days.");
                    registerNewVehicle(sc);
                }
            }
            if(years % 4 != 0){
                if(days < 1 || days > 28){
                    System.out.println("Normal Year. This month has 28 days.");
                    registerNewVehicle(sc);
                }
            }
        }
        if(years < 1700 || years > currentYear){
            System.out.println("Bet you chose the wrong year. Check again.");
            registerNewVehicle(sc);
        }
        LocalDate inputDate = LocalDate.of(years, months, days);
        if(inputDate.isAfter(currentDate)){
            System.out.println("This date is in the future.");
            registerNewVehicle(sc);
        }

        return date;
    }
    private String validatePlateID(Scanner sc, String rd) {
        String plate = sc.nextLine().trim(); // Read and trim the plate ID input

        // Split the plate ID into parts using "-"
        String[] strParts = plate.split("-");

        // Check if the plate consists of three parts
        if (strParts.length != 3) {
            System.out.println("Plate ID format is incorrect. Please retry.");
            return validatePlateID(sc, rd); // Retry plate input
        }

        String firstPart = strParts[0].trim();
        String secondPart = strParts[1].trim();
        String thirdPart = strParts[2].trim();

        // Validate plate format based on the registration date year
        int registrationYear = LocalDate.parse(rd, formatter).getYear();

        if (registrationYear >= 2020) {
            // Format: AA-00-AA
            if (!firstPart.matches("[A-Z]{2}") || !secondPart.matches("\\d{2}") || !thirdPart.matches("[A-Z]{2}")) {
                System.out.println("Plate ID format is incorrect for registration after 2020. Please retry.");
                return validatePlateID(sc, rd); // Retry plate input
            }
        } else if (registrationYear >= 2005) {
            // Format: 00-AA-00
            if (!firstPart.matches("\\d{2}") || !secondPart.matches("[A-Z]{2}") || !thirdPart.matches("\\d{2}")) {
                System.out.println("Plate ID format is incorrect for registration between 2005 and 2020. Please retry.");
                return validatePlateID(sc, rd); // Retry plate input
            }
        } else if (registrationYear >= 1992) {
            // Format: 00-00-XX
            if (!firstPart.matches("\\d{2}") || !secondPart.matches("\\d{2}") || !thirdPart.matches("[A-Z]{2}")) {
                System.out.println("Plate ID format is incorrect for registration between 1992 and 2005. Please retry.");
                return validatePlateID(sc, rd); // Retry plate input
            }
        } else {
            System.out.println("Plate ID format validation is not defined for registration years before 1992.");
            return validatePlateID(sc, rd); // Retry plate input
        }

        return plate; // Return validated plate ID
    }

    private String validateAquisitionDate(Scanner sc, String rd){
        String ad = validateDate(sc);
        LocalDate dateAD = LocalDate.parse(ad, formatter);
        LocalDate dateRD = LocalDate.parse(rd, formatter);

        if(dateAD.isBefore(dateRD)){
            System.out.println("The registration date is after the acquisition date.");
            registerNewVehicle(sc);
        }
        return ad;
    }
    public void validatekm(int CKM, int LMKM){
        if(CKM < LMKM){
            System.out.println("Current KM must be higher than last maintenance KM");
        }
    }
    public static String checkStringType(String str) {
        char firstChar = str.charAt(0);
        char secondChar = str.charAt(1);

        boolean isNumeric = Character.isDigit(firstChar) && Character.isDigit(secondChar);
        boolean isAlphabetic = Character.isLetter(firstChar) && Character.isLetter(secondChar);

        if (isNumeric) {
            return "N";
        } else if (isAlphabetic) {
            return "A";
        }
        return "X";
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
