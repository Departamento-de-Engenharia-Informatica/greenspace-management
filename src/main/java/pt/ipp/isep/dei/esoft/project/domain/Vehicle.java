package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {
    private String plateID;
    private String model;
    private String type;
    private int tare;
    private int grossWeight;
    private int currentKm;
    private String registerDate;
    private String acquisitionDate;
    private int checkupFrequencyKm;
    private int lastMaintenanceKm;

    public Vehicle(String plateID, String model, String type, int tare, int grossWeight, int currentKm,
                   String registerDate, String acquisitionDate, int checkupFrequencyKm, int lastMaintenanceKm) {
        if (plateID == null || model == null || type == null || registerDate == null || acquisitionDate == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if (tare < 0 || grossWeight <= 0 || currentKm < 0 || checkupFrequencyKm <= 0 || lastMaintenanceKm < 0) {
            throw new IllegalArgumentException("Tare, GrossWeight, CurrentKm, CheckupFrequencyKm, and LastMaintenanceKm must be non-negative");
        }

        this.plateID = plateID;
        this.model = model;
        this.type = type;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkupFrequencyKm = checkupFrequencyKm;
        this.lastMaintenanceKm = lastMaintenanceKm;
    }

    public String getPlateID() {
        return plateID;
    }

    public void setPlateID(String plateID) {
        this.plateID = plateID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTare() {
        return tare;
    }

    public void setTare(int tare) {
        this.tare = tare;
    }

    public int getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(int grossWeight) {
        this.grossWeight = grossWeight;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public int getCheckupFrequencyKm() {
        return checkupFrequencyKm;
    }

    public void setCheckupFrequencyKm(int checkupFrequencyKm) {
        this.checkupFrequencyKm = checkupFrequencyKm;
    }

    public int getLastMaintenanceKm() {
        return lastMaintenanceKm;
    }

    public void setLastMaintenanceKm(int lastMaintenanceKm) {
        this.lastMaintenanceKm = lastMaintenanceKm;
    }

    public void registerCheckup(int kmAtCheckup) {
        this.lastMaintenanceKm = kmAtCheckup;
    }

    public void updateCurrentKm(int newKm) {
        this.currentKm = newKm;
    }

    public boolean isCheckupDue() {
        return (this.currentKm - this.lastMaintenanceKm >= this.checkupFrequencyKm);
    }

    public void displayLastMaintenanceDetails() {
        if (this.lastMaintenanceKm == 0) {
            System.out.println("No maintenance history available for this vehicle.");
        } else {
            System.out.println("Last Maintenance Details:");
            System.out.println("Last Maintenance KM: " + this.lastMaintenanceKm);
            // You can add more details like date if available
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateID='" + plateID + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", tare=" + tare +
                ", grossWeight=" + grossWeight +
                ", currentKm=" + currentKm +
                ", registerDate='" + registerDate + '\'' +
                ", acquisitionDate='" + acquisitionDate + '\'' +
                ", checkupFrequencyKm=" + checkupFrequencyKm +
                ", lastMaintenanceKm=" + lastMaintenanceKm +
                '}';
    }
}
