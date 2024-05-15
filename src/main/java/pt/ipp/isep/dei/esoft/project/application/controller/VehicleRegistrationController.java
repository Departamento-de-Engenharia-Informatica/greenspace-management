package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class VehicleRegistrationController {
    private final VehicleRepository vehicleRepository;

    public VehicleRegistrationController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void registerVehicle(String plate, String model, String type,
                                int tareWeight, int grossWeight, int currentKm,
                                String registerDate, String acquisitionDate,
                                int checkupFrequencyKm, int lastMaintenanceKm) {
        Vehicle vehicle = new Vehicle(plate, model, type, tareWeight, grossWeight,
                currentKm, registerDate, acquisitionDate, checkupFrequencyKm, lastMaintenanceKm);
        vehicleRepository.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAllVehicles();
    }

    public Vehicle findVehicleByPlate(String plate) {
        return vehicleRepository.findVehicleByPlateID(plate);
    }

    public void updateCurrentKilometers(Vehicle vehicle, int newCurrentKm) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        if (newCurrentKm < 0) {
            throw new IllegalArgumentException("New current kilometers must be non-negative.");
        }
        // Update the vehicle's current kilometers
        vehicle.setCurrentKm(newCurrentKm);
        // Save the updated vehicle in the repository (if necessary)
        vehicleRepository.updateVehicle(vehicle);
    }
}
