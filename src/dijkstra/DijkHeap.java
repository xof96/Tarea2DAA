package dijkstra;

import javafx.util.Pair;
import structs.GraphWay;
import structs.Heap;

import java.util.ArrayList;

public class DijkHeap {

    public Pair dijsktra(ArrayList<GraphWay>[] graph, int origen) {
        int n = graph.length;
        Heap Q = new Heap(n);
        int MAX_INT = 10000000;//cambiarlo
        double[] dist = new double[n];
        int[] prev = new int[n];
        for (int v = 0; v < n; v++) {
            if (v == origen) {
                dist[v] = 0;
            } else {
                dist[v] = MAX_INT;
            }
            GraphWay nodo = new GraphWay(v, dist[v]);
            Q.insert(nodo);
        }
        while (!Q.isEmpty()) {
            int m = Q.extractMin().getNode();
            for (int v = 0; v < graph[m].size(); v++) {
                double nuevaDist = dist[m] + graph[m].get(v).getWeight();
                if (nuevaDist < dist[graph[m].get(v).getNode()]) {
                    dist[graph[m].get(v).getNode()] = nuevaDist;
                    prev[graph[m].get(v).getNode()] = m;
                    Q.decreaseKey(graph[m].get(v).getNode(), nuevaDist);
                }
            }
        }
        return new Pair(prev, dist);
    }
}
