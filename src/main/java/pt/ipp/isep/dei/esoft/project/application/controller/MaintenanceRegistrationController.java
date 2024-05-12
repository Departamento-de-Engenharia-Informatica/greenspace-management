package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;

public class MaintenanceRegistrationController {
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceRegistrationController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public void registerMaintenance(int maintenanceKm) {
        Maintenance maintenance = new Maintenance(maintenanceKm);
        maintenanceRepository.addMaintenance(maintenance);
    }

}

