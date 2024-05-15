package pt.ipp.isep.dei.esoft.project.domain;

public class Maintenance {
    private int maintenanceKm;
    private Vehicle vehicle; // Add a reference to Vehicle

    public Maintenance(int maintenanceKm) {
        if (maintenanceKm < 0) {
            throw new IllegalArgumentException("Maintenance km must be non-negative");
        }
        this.maintenanceKm = maintenanceKm;
    }

    public int getMaintenanceKm() {
        return maintenanceKm;
    }

    public void setMaintenanceKm(int maintenanceKm) {
        if (maintenanceKm < 0) {
            throw new IllegalArgumentException("Maintenance km must be non-negative");
        }
        this.maintenanceKm = maintenanceKm;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
