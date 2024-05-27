package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListVehiclesNeedingCheckupController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

public class ListVehiclesNeedingCheckupUI implements Runnable {

    private final ListVehiclesNeedingCheckupController controller;

    public ListVehiclesNeedingCheckupUI() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        this.controller = new ListVehiclesNeedingCheckupController(vehicleRepository);
    }

    @Override
    public void run() {
        listVehiclesNeedingCheckupList();
    }

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
