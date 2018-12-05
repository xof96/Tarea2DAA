package structs;

import java.util.ArrayList;
import java.util.Random;

public class Graph {

    public ArrayList<GraphWay>[] generateGraph(int n, int x) {
        Random rand = new Random();
        int[][] mat = new int[n][n];
        //Se inicializa la matriz con puros 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 0;
            }
        }
        ArrayList<GraphWay>[] graph=new ArrayList[n];
        for (int i=0;i<n;i++){
            graph[i]=new ArrayList<GraphWay>();
        }
        //Se garantiza conectividad
        for (int i = 0; i < n - 1; i++) {
            graph[i].add(new GraphWay(i+1,rand.nextDouble()));
            mat[i][i + 1] = 1;
            mat[i + 1][i] = 1;
        }
        int e = n * x;
        int aristas = n - 1;
        //Se crean las demas aristas
        while (aristas < e) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            while (mat[a][b] != 0 || a == b) {
                a = rand.nextInt(n);
                b = rand.nextInt(n);
            }
            graph[a].add(new GraphWay(b,rand.nextDouble()));
            mat[a][b] = 1;
            mat[b][a] = 1;
            aristas++;
        }
        return graph;
    }
}
