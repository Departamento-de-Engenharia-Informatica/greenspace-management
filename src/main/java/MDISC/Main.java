package MDISC;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Input data for example 1
        List<Route> routes1 = importRoutesFromCSV("src/main/java/MDISC/US13_JardimDosSentimentos.csv");
        displayGraphStatistics("Example 1", routes1);


        // Input data for example 2
        List<Route> routes2 = importRoutesFromCSV("src/main/java/MDISC/US13_JardimEspeciesNucleoRural.csv");
        displayGraphStatistics("Example 2", routes2);
    }

    public static List<Route> importRoutesFromCSV(String filename) {
        List<Route> routes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    System.err.println("Invalid input format: " + line);
                    continue; // Skip to the next line
                }
                String startPoint = parts[0];
                String endPoint = parts[1];
                int distance = Integer.parseInt(parts[2]);

                // Create route and add it to the list
                routes.add(new Route(startPoint, endPoint, distance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return routes;
    }




    public static void displayGraphStatistics(String exampleName, List<Route> routes) {
        // Create vertices for all unique points
        List<Vertex> vertices = createVertices(routes);

        // Create the graph
        Graph graph = createGraph(vertices, routes);

        // Applying Kruskal's algorithm
        List<Route> minimumSpanningTree = KruskalAlgorithm.kruskal(graph);

        // Calculate total cost of the minimum spanning tree
        int totalCost = calculateTotalCost(minimumSpanningTree);

        // Print statistics
        System.out.println("Example: " + exampleName);
        System.out.println("Graph Dimension = " + routes.size() + " : Graph Order = " + vertices.size() +
                " : Minimum cost = " + totalCost);
    }

    public static List<Vertex> createVertices(List<Route> routes) {
        List<Vertex> vertices = new ArrayList<>();
        for (Route route : routes) {
            Vertex startVertex = route.getStartPoint();
            Vertex endVertex = route.getEndPoint();
            if (!vertices.contains(startVertex)) {
                vertices.add(startVertex);
            }
            if (!vertices.contains(endVertex)) {
                vertices.add(endVertex);
            }
        }
        return vertices;
    }

    public static Graph createGraph(List<Vertex> vertices, List<Route> routes) {
        Graph graph = new Graph();
        for (Vertex vertex : vertices) {
            graph.addVertex(vertex);
        }
        for (Route route : routes) {
            graph.addEdge(route);
        }
        return graph;
    }

    // Calculate total cost of the minimum spanning tree
    public static int calculateTotalCost(List<Route> minimumSpanningTree) {
        int totalCost = 0;
        for (Route route : minimumSpanningTree) {
            totalCost += route.getDistance();
        }
        return totalCost;
    }
}
