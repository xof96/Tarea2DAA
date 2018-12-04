package experiments;

import javafx.util.Pair;

public class DijkArr {

    public Pair makeDijsktra(double[][] graph) {
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
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0) {
                    if (dist[v] > dist[u] + graph[u][v]) {
                        dist[v] = dist[u] + graph[u][v];
                        prev[v] = u;
                    }
                }
            }
        }
        return new Pair(prev, dist);
    }
}
