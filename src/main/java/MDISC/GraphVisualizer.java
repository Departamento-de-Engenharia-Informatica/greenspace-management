package MDISC;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GraphVisualizer {

    public static void visualizeGraph(Graph graph, String fileName) {
        // Generate DOT content
        StringBuilder dotContent = new StringBuilder();
        dotContent.append("graph G {\n");

        // Add vertices
        for (Vertex vertex : graph.getAllVertices()) {
            dotContent.append(vertex.getVertexId()).append(";\n");
        }

        // Add edges
        for (Vertex vertex : graph.getAllVertices()) {
            List<Route> routes = graph.getAdjacentRoutes(vertex);
            for (Route route : routes) {
                dotContent.append(vertex.getVertexId()).append(" -- ").append(route.getEndPoint().getVertexId());
                dotContent.append(" [label=\"").append(route.getDistance()).append("\"];\n");
            }
        }

        dotContent.append("}");

        // Write DOT content to a file
        try (FileWriter writer = new FileWriter(fileName + ".dot")) {
            writer.write(dotContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Use Graphviz to generate the graph image
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", fileName + ".png", fileName + ".dot");
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
