package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private List<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    public Vehicle findVehicleByPlateID(String plateID) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateID().equals(plateID)) {
                return vehicle;
            }
        }
        return null;
    }

    public void updateVehicle(Vehicle updatedVehicle) {
        // Find the existing vehicle by plateID and update its details
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlateID().equals(updatedVehicle.getPlateID())) {
                vehicles.set(i, updatedVehicle);
                break;
            }
        }
    }
}
