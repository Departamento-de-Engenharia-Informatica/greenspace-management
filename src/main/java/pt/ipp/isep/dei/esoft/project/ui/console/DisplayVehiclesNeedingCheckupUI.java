// HOX! 11 May 2024: I placed all of the code I've done in here for now, for convenience.
// Other Vehicle classes or controllers didn't exist yet.	-Justus

package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateVehicleController; // doesn't exist yet
import pt.ipp.isep.dei.esoft.project.domain.Vehicle; // doesn't exist yet

import java.util.List;


/**
 * The {@code DisplayVehiclesNeedingCheckupUI} class represents a user interface for displaying a list of vehicles needing checkup.
 * It retrieves the list of vehicles from the controller and displays them to the user.
 * The class implements the {@code Runnable} interface, allowing it to be executed as a thread.
 */
public class DisplayVehiclesNeedingCheckupUI implements Runnable {

    /**
     * Runs the UI for displaying the list of vehicles needing checkup.
     * Retrieves the list of corresponding vehicles from the controller and displays them to the user.
     */
    @Override
    public void run() {
        displayVehiclesNeedingCheckupList();
    }

    /**
     * Displays the list of vehicles needing checkup to the user.
     * Retrieves the list of corresponding vehicles from the controller and prints their Plate, 
	 * Brand, Model, Current KMs, and Last Check-up date. // hox! not implemented in controller yet
     */
	 
	 
	// The following functions will need other Vehicle relating classes to work
	// Also some of the functions should be placed in other classes. They are just here for now.
	 
    // Printing the vehicles needing checkup
    public void displayVehiclesNeedingCheckupList() {
        List<Vehicle> vehiclesNeedingCheckup = getVehiclesNeedingCheckup();
        if (vehiclesNeedingCheckup.isEmpty()) {
            System.out.println("No vehicles needing check-up at the moment.");
        } else {
            System.out.println("--- Vehicles Needing Check-Up ---");
            for (Vehicle vehicle : vehiclesNeedingCheckup) {
                System.out.println(formatVehicleDetails(vehicle));
            }
        }
    }

    // Formatting the vehicle's information to be printed
    private String formatVehicleDetails(Vehicle vehicle) {
        return String.format("Plate: %s, Brand: %s, Model: %s, Current KMs: %.2f, Last Check-up: %s",
                vehicle.getPlate().getPlate(), // Assuming getPlate() returns a VehiclePlate object
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getCurrentKms(),
                vehicle.getLastCheckupDate().toString());
    }

    // Getting the vehicles' information from Vehicle class to check if they need checkup
    // Will need the vehicles' last checkup kilometers to calculate properly
    public List<Vehicle> getVehiclesNeedingCheckup() {
        List<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            double currentKms = vehicle.getCurrentKms();
            int checkupFrequency = vehicle.getCheckupFrequency();
            double lastCheckupKms = vehicle.getLastCheckupKms(); // this will need the vehicle's lastCheckupKms stored somewhere, it doesn't exist yet

            // Calculate the km threshold for considering a vehicle as needing check-up (95% of checkupFrequency)
            double kmThreshold = 0.95 * checkupFrequency;

            // Check if the vehicle is due for a checkup
            if (currentKms >= lastCheckupKms + checkupFrequency || currentKms >= lastCheckupKms + kmThreshold) {
                vehiclesNeedingCheckup.add(vehicle);
            }
        }

        return vehiclesNeedingCheckup;
    }
}

