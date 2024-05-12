package MDISC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisjointSet {
    private Map<Vertex, Vertex> parentMap;

    public DisjointSet(List<Vertex> vertices) {
        parentMap = new HashMap<>();
        for (Vertex vertex : vertices) {
            parentMap.put(vertex, vertex);
        }
    }

    public Vertex find(Vertex vertex) {
        if (!parentMap.get(vertex).equals(vertex)) {
            parentMap.put(vertex, find(parentMap.get(vertex)));
        }
        return parentMap.get(vertex);
    }

    public void union(Vertex vertex1, Vertex vertex2) {
        Vertex root1 = find(vertex1);
        Vertex root2 = find(vertex2);
        parentMap.put(root1, root2);
    }
}
