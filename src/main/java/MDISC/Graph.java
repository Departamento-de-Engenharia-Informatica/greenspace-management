package MDISC;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Route>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(Route route) {
        Vertex startPoint = route.getStartPoint();
        Vertex endPoint = route.getEndPoint();
        adjacencyMap.get(startPoint).add(route);
        // Uncomment the line below if the graph is undirected
        // adjacencyMap.get(endPoint).add(route);
    }

    public List<Vertex> getAllVertices() {
        return new ArrayList<>(adjacencyMap.keySet());
    }

    public List<Route> getAdjacentRoutes(Vertex vertex) {
        return adjacencyMap.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Route> getAllRoutes() {
        List<Route> allRoutes = new ArrayList<>();
        for (List<Route> routes : adjacencyMap.values()) {
            allRoutes.addAll(routes);
        }
        return allRoutes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : adjacencyMap.keySet()) {
            sb.append(vertex).append(" -> ").append(adjacencyMap.get(vertex)).append("\n");
        }
        return sb.toString();
    }
}