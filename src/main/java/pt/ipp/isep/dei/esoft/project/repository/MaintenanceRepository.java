package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

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

    public void removeMaintenance(Maintenance maintenance) {
        maintenanceList.remove(maintenance);
    }

    public List<Maintenance> getAllMaintenance() {
        return new ArrayList<>(maintenanceList);
    }

    public List<String> getMaintenanceByVehiclePlateID(String plateID) {
        List<String> maintenanceHistory = new ArrayList<>();
        for (Maintenance maintenance : maintenanceList) {
            Vehicle vehicle = maintenance.getVehicle();
            if (vehicle != null && vehicle.getPlateID().equals(plateID)) {
                maintenanceHistory.add("Maintenance KM: " + maintenance.getMaintenanceKm());
            }
        }
        return maintenanceHistory;
    }
}
