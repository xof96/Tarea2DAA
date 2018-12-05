package structs;

import java.util.ArrayList;
import java.util.Random;

public class Graph {

    public ArrayList<GraphWay>[] generateGraph(int n, int x) {
        Random rand = new Random();
        ArrayList<GraphWay>[] graph=new ArrayList[n];
        ArrayList<Integer>[] vecinos=new ArrayList[n];
        for (int i=0;i<n;i++){
            graph[i]=new ArrayList<GraphWay>();
            vecinos[i]=new ArrayList<Integer>();
        }
        //Se garantiza conectividad
        for (int i = 0; i < n - 1; i++) {
            double costo=rand.nextDouble();
            graph[i].add(new GraphWay(i+1,costo));
            vecinos[i].add(i+1);
            graph[i+1].add(new GraphWay(i,costo));
            vecinos[i+1].add(i);
        }
        int e = n * x;
        int aristas = n - 1;
        //Se crean las demas aristas
        while (aristas < e) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            while (vecinos[a].contains(b) || a == b) {
                a = rand.nextInt(n);
                b = rand.nextInt(n);
            }
            double costo=rand.nextDouble();
            graph[a].add(new GraphWay(b,costo));
            vecinos[a].add(b);
            graph[b].add(new GraphWay(a,costo));
            vecinos[b].add(a);

            aristas++;
        }
        return graph;
    }
}
