package dijkstra;

import javafx.util.Pair;
import structs.GraphWay;

import java.util.ArrayList;

public class DijkArr {

    public Pair makeDijsktra(ArrayList<GraphWay>[] graph) {
        int MAX_INT = 10000000;//cambiarlo
        int n = graph.length;
        double[] dist = new double[n];
        dist[0] = 0;
        boolean[] marcado = new boolean[n];
        marcado[0] = false;
        int[] prev = new int[n];
        for (int i = 1; i < n; i++) {
            dist[i] = MAX_INT;
            marcado[i] = false;
        }
        for (int i = 0; i < n; i++) {
            double minDist = MAX_INT;
            int minNodo = -1;
            for (int j = 0; j < n; j++) {
                if (!marcado[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minNodo = j;
                }
            }
            int u = minNodo;
            marcado[u] = true;
            for (int v = 0; v < graph[u].size(); v++) {
                if (dist[graph[u].get(v).getNode()] > dist[u] + graph[u].get(v).getWeight()) {
                    dist[graph[u].get(v).getNode()] = dist[u] + graph[u].get(v).getWeight();
                    prev[graph[u].get(v).getNode()] = u;
                }

            }
        }
        return new Pair(prev, dist);
    }
}
