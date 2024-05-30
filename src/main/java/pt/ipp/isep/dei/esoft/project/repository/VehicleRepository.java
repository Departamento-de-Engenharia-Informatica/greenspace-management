package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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

    public boolean vehicleExists(String plate){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateID().equals(plate)) {
                return true; // Vehicle with specified plate ID exists
            }
        }
        return false;
    }

    /**
     * Retrieves a list of vehicles that are due for a checkup.
     *
     * This method iterates over the list of vehicles and checks each one to determine if it is due for a checkup.
     * Vehicles that are due for a checkup are added to the list of vehicles needing checkup.
     *
     * @return a list of vehicles that are due for a checkup.
     */
    public List<Vehicle> getVehiclesNeedingCheckup() {
        List<Vehicle> vehiclesNeedingCheckup = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isCheckupDue()) {
                vehiclesNeedingCheckup.add(vehicle);
            }
        }
        return vehiclesNeedingCheckup;
    }

    public List<Vehicle> findAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.isAvailable(startTime, endTime))
                .collect(Collectors.toList());
    }

}
