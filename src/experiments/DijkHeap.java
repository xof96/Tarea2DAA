package experiments;

import javafx.util.Pair;
import structs.Graph;
import structs.Heap;

import java.util.ArrayList;
import java.util.List;

public class DijkHeap {
    public void Dijsktra(double[][] graph, double origin) {
        Heap Q = new Heap();
        int rows = graph.length;
        int cols = graph[0].length;
        List<Pair<Integer, Integer>> dist, prev;
        dist = new ArrayList<Pair<Integer, Integer>>();
        prev = new ArrayList<Pair<Integer, Integer>>();


        for (int i = 0; i < rows; i++) {
            for (int j = 0; i < cols; j++) {
                if (graph[i][j] == origin) {
                    //dist[] =0;


                } else {
                    //dist[]=-1;//infinito
                }
                //prev[v] = null;
                //Q.insert(v,dist[v]); //agrega a cola con distancia de origen como prioridad


            }

        }
        while(!Q.isEmpty()){
            //m = Q.extractMin();//sacar vertice con mayor prioridad
            //cada vecino que sigue en Q
            //necesito saber que nodos estan en el heap

        }

    }
}
