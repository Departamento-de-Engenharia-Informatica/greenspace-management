package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository {
    private List<Maintenance> maintenanceList;

    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<>();
    }

    public void addMaintenance(Maintenance maintenance) {
        maintenanceList.add(maintenance);
    }

    public List<Maintenance> getAllMaintenance() {
        return new ArrayList<>(maintenanceList);
    }

    public List<Maintenance> getMaintenanceByVehiclePlateID(String plateID) {
        List<Maintenance> matchingMaintenance = new ArrayList<>();
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getPlateID().equals(plateID)) {
                matchingMaintenance.add(maintenance);
            }
        }
        return matchingMaintenance;
    }
}
