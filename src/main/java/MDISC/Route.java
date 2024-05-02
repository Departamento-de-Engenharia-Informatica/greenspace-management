package MDISC;

public class Route {
    private int waterPointX;
    private int waterPointY;
    private double distance;

    public Route(int waterPointX, int waterPointY, double distance) {
        this.waterPointX = waterPointX;
        this.waterPointY = waterPointY;
        this.distance = distance;
    }

    public int getWaterPointX() {
        return waterPointX;
    }

    public void setWaterPointX(int waterPointX) {
        this.waterPointX = waterPointX;
    }

    public int getWaterPointY() {
        return waterPointY;
    }

    public void setWaterPointY(int waterPointY) {
        this.waterPointY = waterPointY;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    @Override
    public String toString() {
        return waterPointX + " -> " + waterPointY + ": " + distance;
    }
}

