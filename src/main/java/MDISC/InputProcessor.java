package MDISC;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputProcessor {

    private String folderPath;
    private List<String> executionTimeData;

    public InputProcessor(String folderPath) {
        this.folderPath = folderPath;
        this.executionTimeData = new ArrayList<>();
    }

    public void processInputFiles() {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Invalid folder path.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.err.println("No files found in the specified folder.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().startsWith("us14_") && file.getName().endsWith(".csv")) {
                List<Route> routes = importRoutesFromCSV(file.getAbsolutePath());
                long startTime = System.currentTimeMillis();
                List<Route> minimumSpanningTree = KruskalAlgorithm.kruskal(createGraph(createVertices(routes), routes));
                long endTime = System.currentTimeMillis();
                int executionTime = (int) (endTime - startTime);

                int inputSize = routes.size();
                executionTimeData.add(inputSize + "," + executionTime);
            }
        }

        generateExecutionTimeCSV();

        generateAndDisplayExecutionTimeGraph();
    }

    private List<Route> importRoutesFromCSV(String filename) {
        List<Route> routes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
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

    private void generateExecutionTimeCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("execution_times.csv"))) {
            writer.println("Input Size,Execution Time (ms)");
            for (String data : executionTimeData) {
                writer.println(data);
            }
            System.out.println("Execution time data saved to execution_times.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateAndDisplayExecutionTimeGraph() {
        String scriptContent =
                "set datafile separator \",\"\n" +
                        "set terminal png\n" +
                        "set output \"execution_times.png\"\n" +
                        "set xlabel \"Input Size\"\n" +
                        "set ylabel \"Execution Time (ms)\"\n" +
                        "plot \"execution_times.csv\" using 1:2 with linespoints title \"Execution Time vs. Input Size\"";

        try (PrintWriter scriptWriter = new PrintWriter(new FileWriter("plot_script.gp"))) {
            scriptWriter.println(scriptContent);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("gnuplot", "plot_script.gp");
            pb.directory(new File(".")); // Set working directory to current directory
            Process process = pb.start();

            process.waitFor();

            System.out.println("Execution time graph generated: execution_times.png");
            System.out.println("Displaying execution time graph...");

            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("mac")) {
                Runtime.getRuntime().exec("open execution_times.png");
            } else if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /c start execution_times.png");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
