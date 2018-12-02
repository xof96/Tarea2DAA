package structs;

import java.util.Random;

public class Graph {

    public double[][] generateGraph(int n, int x) {
        Random rand = new Random();
        double[][] mat = new double[n][n];
        //Se inicializa la matriz con puros 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 0;
            }
        }
        //Se garantiza conectividad
        for (int i = 0; i < n - 1; i++) {
            mat[i][i + 1] = rand.nextDouble();
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
            mat[a][b] = rand.nextDouble();
            aristas++;
        }
        return mat;
    }
}
