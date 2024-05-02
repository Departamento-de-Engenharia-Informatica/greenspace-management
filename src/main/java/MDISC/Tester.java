package MDISC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Tester {

    private static final String TESTS_FOLDER = "/Users/ed/IdeaProjects/greenspace-management/src/main/java/MDISC/US14_TESTS/";

    public void runTimeTests(int numTests) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the path to the folder containing the test files (e.g., /path/to/US14_TESTS/): ");
            String folderPath = scanner.nextLine().trim();

            FileWriter csvWriter = new FileWriter("execution_times.csv");
            csvWriter.append("Input Size,Execution Time (ns)\n");

            for (int i = 1; i <= numTests; i++) {
                String filename = TESTS_FOLDER + "us14_" + i + ".csv";
                List<Route> routes = importRoutesFromCSV(filename);

                long startTime = System.nanoTime();
                List<Route> minimumSpanningTree = runAlgorithm(routes);
                long endTime = System.nanoTime();

                long executionTime = endTime - startTime;
                csvWriter.append(routes.size() + "," + executionTime + "\n");

                System.out.println("Test " + i + " completed.");
            }

            csvWriter.flush();
            csvWriter.close();

            // Generate plot using Gnuplot

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Route> runAlgorithm(List<Route> routes) {
        List<Vertex> vertices = createVertices(routes);
        Graph graph = createGraph(vertices, routes);
        return KruskalAlgorithm.kruskal(graph);
    }

    private List<Route> importRoutesFromCSV(String filename) {
        List<Route> routes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Invalid input format: " + line);
                    continue;
                }
                String startPoint = parts[0];
                String endPoint = parts[1];
                int distance = Integer.parseInt(parts[2]);

                routes.add(new Route(startPoint, endPoint, distance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return routes;
    }

    private List<Vertex> createVertices(List<Route> routes) {
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

    private Graph createGraph(List<Vertex> vertices, List<Route> routes) {
        Graph graph = new Graph();
        for (Vertex vertex : vertices) {
            graph.addVertex(vertex);
        }
        for (Route route : routes) {
            graph.addEdge(route);
        }
        return graph;
    }
}

