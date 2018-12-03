package experiments;

import javafx.util.Pair;
import structs.Heap;
import structs.GraphWay;

public class DijkHeap {

    public Pair dijsktra(double[][] graph, int origen){
        Heap Q=new Heap();
        int MAX_INT=10000000;//cambiarlo
        int n=graph.length;
        double[] dist=new double[n];
        int[] prev=new int[n];
        for (int v=0;v<n;v++){
            if (v==origen){
                dist[v]=0;
            }
            else{
                dist[v]=MAX_INT;
            }
            GraphWay nodo=new GraphWay(v,dist[v]);
            Q.insert(nodo);//arreglar porque debe recibir dos weas
        }
        while (!Q.isEmpty()){
            int m=Q.extractMin().getNode();
            for (int v=0;v<n;v++){
                if(graph[m][v]>0){//se seleccionan los vecinos
                    double nuevaDist=dist[m]+graph[m][v];
                    if (nuevaDist<dist[v]){
                        dist[v]=nuevaDist;
                        prev[v]=m;
                        Q.decreaseKey(v,nuevaDist);
                    }
                }
            }
        }
        return new Pair(prev,dist);
    }
}
