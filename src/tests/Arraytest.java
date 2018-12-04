package tests;

import experiments.DijkFib;
import structs.Graph;
import experiments.DijkArr;
import experiments.DijkHeap;

public class Arraytest {
    public static void main(String args[]) {
        Graph generador = new Graph();
        DijkArr dijkstra = new DijkArr();
        double[][] mat = generador.generateGraph(5, 2);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("\n");
        }
        int[] caminoMin = (int[]) dijkstra.makeDijsktra(mat).getKey();
        double[] distMin=(double[]) dijkstra.makeDijsktra(mat).getValue();
        for (int i = 0; i < caminoMin.length; i++) {
            System.out.print(caminoMin[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < distMin.length; i++) {
            System.out.print(distMin[i] + " ");
        }
        System.out.println();
        DijkHeap dijh = new DijkHeap();
        int[] caminoMinH = (int[]) dijh.dijsktra(mat, 0).getKey();
        double[] distMinH = (double[]) dijh.dijsktra(mat, 0).getValue();
        for (int i = 0; i < caminoMinH.length; i++) {
            System.out.print(caminoMinH[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < caminoMin.length; i++) {
            System.out.print(distMinH[i] + " ");
        }
        System.out.println();
        DijkFib dijf=new DijkFib();
        int[] caminoMinF = (int[]) dijf.dijsktra(mat, 0).getKey();
        double[] distMinF = (double[]) dijf.dijsktra(mat, 0).getValue();
        for (int i = 0; i < caminoMinF.length; i++) {
            System.out.print(caminoMinF[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < caminoMinF.length; i++) {
            System.out.print(distMinF[i] + " ");
        }

    }
}
