package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.ArrayList;


public class MaintenanceRegistrationController {
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceRegistrationController(MaintenanceRepository maintenanceRepository, VehicleRepository vehicleRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void registerMaintenance(String plateID, int maintenanceKm, String maintenanceDate) {
        Maintenance maintenance = new Maintenance(maintenanceKm, maintenanceDate, plateID);
        maintenanceRepository.addMaintenance(maintenance);
    }

    public List<String> getMaintenanceHistoryByVehicle(String plateID) {
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceByVehiclePlateID(plateID);
        List<String> maintenanceHistory = new ArrayList<>();
        for (Maintenance maintenance : maintenanceList) {
            maintenanceHistory.add("Maintenance KM: " + maintenance.getMaintenanceKm() +
                    ", Date: " + maintenance.getMaintenanceDate());
        }
        return maintenanceHistory;
    }
}
