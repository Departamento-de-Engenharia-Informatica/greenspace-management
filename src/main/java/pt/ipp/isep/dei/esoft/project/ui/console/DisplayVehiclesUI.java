package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.VehicleRegistrationController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

public class DisplayVehiclesUI implements Runnable{

    @Override
    public void run() {
        displayVehicleList();
    }

    public void displayVehicleList(){
        List<Vehicle> vehicleList = VehicleRegistrationController.getAllVehicles();

        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles created yet.");
        } else {
            System.out.println("List of Vehicles:");
            for (Vehicle vehicle : vehicleList) {
                System.out.println(vehicle.toString());
            }
        }

    }

}
