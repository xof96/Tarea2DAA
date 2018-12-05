package experiments;

import javafx.util.Pair;
import structs.GraphWay;
import structs.fibheap.FibonacciHeap;
import structs.fibheap.FibonacciNode;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class DijkFib {

    public Pair dijsktra(ArrayList<GraphWay>[] graph, int origen) {
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
        try {
            while (Q.getN() != 0) {
                System.out.println(String.format("Voy a extraer el mínimo = %s, cuando me quedan %d nodos", Q.getMin(), Q.getN()));
                FibonacciNode min = Q.extractMin();
                if (min == null) {
                    Q.print();
                }
                int m = min.getKey().getNode();
                System.out.println(String.format("Ya extraje el mínimo y me quedaron %d nodos", Q.getN()));
                for (int v = 0; v < graph[m].size(); v++) {
                    double nuevaDist = dist[m] + graph[m].get(v).getWeight();
                    if (nuevaDist < dist[graph[m].get(v).getNode()]) {
                        dist[graph[m].get(v).getNode()] = nuevaDist;
                        prev[graph[m].get(v).getNode()] = m;
                        System.out.println(String.format("Min antes del decrease: %s", Q.getMin()));
                        Q.decreaseKey(graph[m].get(v).getNode(), nuevaDist);
                        System.out.println(String.format("Min después del decrease: %s", Q.getMin()));
                    }
                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Q.print();
        }
        return new Pair(prev, dist);
    }
}
