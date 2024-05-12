package MDISC;

public class Route {
    private Vertex startPoint;
    private Vertex endPoint;
    private int distance;

    public Route(Vertex startPoint, Vertex endPoint, int distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public Route(String startPointId, String endPointId, int distance) {
        this.startPoint = new Vertex(startPointId);
        this.endPoint = new Vertex(endPointId);
        this.distance = distance;
    }

    public Vertex getStartPoint() {
        return startPoint;
    }

    public Vertex getEndPoint() {
        return endPoint;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return startPoint + " -> " + endPoint + ": " + distance;
    }
}
