package pt.ipp.isep.dei.esoft.project.domain;


public class Maintenence {
    private int maintenanceKm;

    public Maintenence(int maintenanceKm) {
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
}

