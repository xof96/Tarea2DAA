package experiments;

import javafx.util.Pair;
import structs.GraphWay;
import structs.fibheap.FibonacciHeap;
import structs.fibheap.FibonacciNode;

public class DijkFib {

    public Pair dijsktra(double[][] graph, int origen) {
        int n = graph.length;
        FibonacciHeap Q = new FibonacciHeap(n);
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
            FibonacciNode x = new FibonacciNode(nodo);
            Q.insert(x);
        }

        while (!Q.isEmpty()) {
            FibonacciNode p = Q.extractMin();
            int m = p.getKey().getNode();
            for (int v = 0; v < n; v++) {
                if (graph[m][v] > 0) {//se seleccionan los vecinos
                    double nuevaDist = dist[m] + graph[m][v];
                    if (nuevaDist < dist[v]) {
                        dist[v] = nuevaDist;
                        prev[v] = m;
                        Q.decreaseKey(v, nuevaDist);
                    }
                }
            }
        }
        return new Pair(prev, dist);
    }
}
