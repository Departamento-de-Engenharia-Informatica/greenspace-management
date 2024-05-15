package pt.ipp.isep.dei.esoft.project.domain;

public class Maintenance {
    private int maintenanceKm;
    private String maintenanceDate;
    private String plateID; // Plate ID of the associated vehicle

    public Maintenance(int maintenanceKm, String maintenanceDate, String plateID) {
        if (maintenanceKm < 0) {
            throw new IllegalArgumentException("Maintenance km must be non-negative.");
        }

        this.maintenanceKm = maintenanceKm;
        this.maintenanceDate = maintenanceDate;
        this.plateID = plateID;
    }

    public int getMaintenanceKm() {
        return maintenanceKm;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public String getPlateID() {
        return plateID;
    }
}
