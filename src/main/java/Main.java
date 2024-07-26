import java.util.*;

class Vertex {
    long id;

    public Vertex(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vertex{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Edge {
    Vertex from;
    Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }
}

class DAG {
    private Map<Vertex, List<Vertex>> adjList = new HashMap<>();
    private Map<Vertex, Long> longestPath = new HashMap<>();

    // Method to add vertices and edges
    public void addVertex(Vertex vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex from, Vertex to) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.get(from).add(to);
    }

    // Method to get the longest path starting from a given vertex
    public long getLongestPath(Vertex start) {
        List<Vertex> topoSorted = topologicalSort();
        longestPath.put(start, 0L);

        for (Vertex u : topoSorted) {
            if (longestPath.containsKey(u)) {
                long currentLength = longestPath.get(u);
                for (Vertex v : adjList.getOrDefault(u, Collections.emptyList())) {
                    longestPath.put(v, Math.max(longestPath.getOrDefault(v, 0L), currentLength + 1));
                }
            }
        }

        return longestPath.getOrDefault(start, 0L);
    }

    // Topological sort using Kahn's algorithm
    private List<Vertex> topologicalSort() {
        List<Vertex> sortedList = new ArrayList<>();
        Map<Vertex, Integer> inDegree = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();

        // Initialize in-degree of all vertices
        for (Vertex vertex : adjList.keySet()) {
            inDegree.put(vertex, 0);
        }
        for (List<Vertex> neighbors : adjList.values()) {
            for (Vertex neighbor : neighbors) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Add vertices with in-degree 0 to the queue
        for (Vertex vertex : inDegree.keySet()) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        // Process vertices
        while (!queue.isEmpty()) {
            Vertex u = queue.poll();
            sortedList.add(u);
            for (Vertex v : adjList.getOrDefault(u, Collections.emptyList())) {
                inDegree.put(v, inDegree.get(v) - 1);
                if (inDegree.get(v) == 0) {
                    queue.add(v);
                }
            }
        }

        return sortedList;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create vertices
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        // Create edges
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v1, v3);
        Edge e3 = new Edge(v2, v4);
        Edge e4 = new Edge(v3, v4);
        Edge e5 = new Edge(v4, v5);

        // Create DAG and add vertices and edges
        DAG dag = new DAG();
        dag.addVertex(v1);
        dag.addVertex(v2);
        dag.addVertex(v3);
        dag.addVertex(v4);
        dag.addVertex(v5);

        dag.addEdge(v1, v2);
        dag.addEdge(v1, v3);
        dag.addEdge(v2, v4);
        dag.addEdge(v3, v4);
        dag.addEdge(v4, v5);

        // Test longest path from vertex v1
        System.out.println("Longest path from v1: " + dag.getLongestPath(v1)); 

        // Test longest path from vertex v2
        System.out.println("Longest path from v2: " + dag.getLongestPath(v2));

        // Test longest path from vertex v3
        System.out.println("Longest path from v3: " + dag.getLongestPath(v3)); 

        // Test longest path from vertex v4
        System.out.println("Longest path from v4: " + dag.getLongestPath(v4));

        // Test longest path from vertex v5
        System.out.println("Longest path from v5: " + dag.getLongestPath(v5)); 
    }
}
