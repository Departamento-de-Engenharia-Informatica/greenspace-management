package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListVehiclesNeedingCheckupController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

/**
 * This class provides a user interface for listing vehicles that need checkups.
 * It retrieves the list of vehicles needing checkups from the controller and displays their details.
 */
public class ListVehiclesNeedingCheckupUI implements Runnable {

    private final ListVehiclesNeedingCheckupController controller;

    /**
     * Constructs a ListVehiclesNeedingCheckupUI and initializes the controller.
     * The controller is responsible for retrieving the list of vehicles needing checkup.
     */
    public ListVehiclesNeedingCheckupUI() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        this.controller = new ListVehiclesNeedingCheckupController(vehicleRepository);
    }

    /**
     * Runs the UI, triggering the display of the list of vehicles needing checkups.
     * This method is called when the Runnable is executed.
     */
    @Override
    public void run() {
        listVehiclesNeedingCheckupList();
    }

    /**
     * Retrieves the list of vehicles needing checkups from the controller and prints their details.
     * If no vehicles need checkups, a corresponding message is displayed.
     */
    public void listVehiclesNeedingCheckupList() {
        List<Vehicle> vehiclesNeedingCheckup = controller.getVehiclesNeedingCheckup();
        if (vehiclesNeedingCheckup.isEmpty()) {
            System.out.println("No vehicles needing check-up at the moment.");
        } else {
            System.out.println("--- Vehicles Needing Check-Up ---");
            for (Vehicle vehicle : vehiclesNeedingCheckup) {
                System.out.println(vehicle.formatVehicleDetails());
            }
        }
    }
}
