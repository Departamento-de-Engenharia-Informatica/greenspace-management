package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

/**
 * Controller for listing vehicles needing checkup.
 * This class interacts with the VehicleRepository to retrieve the list of vehicles that require a checkup.
 */
public class ListVehiclesNeedingCheckupController {

    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a ListVehiclesNeedingCheckupController with the specified VehicleRepository.
     *
     * @param vehicleRepository the repository used to access vehicle data
     */
    public ListVehiclesNeedingCheckupController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Retrieves the list of vehicles needing checkup from the repository.
     *
     * @return a list of vehicles that require a checkup
     */
    public List<Vehicle> getVehiclesNeedingCheckup() {
        return vehicleRepository.getVehiclesNeedingCheckup();
    }
}
