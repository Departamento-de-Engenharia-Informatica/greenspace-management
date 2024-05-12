package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class MaintenanceRegistrationController {
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceRegistrationController(MaintenanceRepository maintenanceRepository, VehicleRepository vehicleRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void registerMaintenance(Vehicle vehicle, int maintenanceKm) {
        Maintenance maintenance = new Maintenance(maintenanceKm);
        maintenance.setVehicle(vehicle);
        maintenanceRepository.addMaintenance(maintenance);
    }

    public List<String> getMaintenanceHistoryByVehicle(String plateID) {
        Vehicle vehicle = vehicleRepository.findVehicleByPlateID(plateID);
        if (vehicle == null) {
            return List.of(); // Return empty list if vehicle not found
        }
        return maintenanceRepository.getMaintenanceByVehiclePlateID(plateID);
    }
}
