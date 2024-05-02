package MDISC;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {
    public static List<Route> kruskal(Graph graph) {
        List<Route> minimumSpanningTree = new ArrayList<>();
        List<Route> allRoutes = graph.getAllRoutes();
        List<Route> sortedRoutes = new ArrayList<>(allRoutes);
        sortedRoutes.sort((r1, r2) -> r1.getDistance() - r2.getDistance()); // Sort by distance

        DisjointSet disjointSet = new DisjointSet(graph.getAllVertices());

        for (Route route : sortedRoutes) {
            Vertex startVertex = route.getWaterPointX();
            Vertex endVertex = route.getWaterPointY();

            if (!disjointSet.find(startVertex).equals(disjointSet.find(endVertex))) {
                minimumSpanningTree.add(route);
                disjointSet.union(startVertex, endVertex);
            }
        }

        return minimumSpanningTree;
    }
}
