package tests;

import structs.Graph;
import structs.GraphWay;
import experiments.DijkFib;
import experiments.DijkHeap;
import experiments.DijkArr;

import javafx.util.Pair;
import java.util.ArrayList;

public class Arraytest {
    public static void main(String args[]) {
        Graph generador = new Graph();
        DijkArr dijkstra = new DijkArr();
        ArrayList<GraphWay>[] mat = generador.generateGraph(5, 2);

//        double[][] printmat = new double[5][5];
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < mat[i].size(); j++) {
//                printmat[i][mat[i].get(j).getNode()] = mat[i].get(j).getWeight();
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                //printmat[j][i]=printmat[i][j];
//                System.out.print(printmat[i][j] + " ");
//            }
//            System.out.println();
//        }

        Pair cdArr = dijkstra.makeDijsktra(mat);
        int[] caminoMin = (int[]) cdArr.getKey();
        double[] distMin = (double[]) cdArr.getValue();

        for (int i = 0; i < caminoMin.length; i++) {
            System.out.print(caminoMin[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < distMin.length; i++) {
            System.out.print(distMin[i] + " ");
        }
        System.out.println();

        System.out.println("#############################################");
        DijkHeap dijh = new DijkHeap();
        Pair cdHeap = dijh.dijsktra(mat, 0);
        int[] caminoMinH = (int[]) cdHeap.getKey();
        double[] distMinH = (double[]) cdHeap.getValue();
        for (int i = 0; i < caminoMinH.length; i++) {
            System.out.print(caminoMinH[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < distMinH.length; i++) {
            System.out.print(distMinH[i] + " ");
        }
        System.out.println();

        System.out.println("#############################################");
        DijkFib dijf = new DijkFib();
        Pair cdFib = dijf.dijsktra(mat, 0);
        int[] caminoMinF = (int[]) cdFib.getKey();
        double[] distMinF = (double[]) cdFib.getValue();
        for (int i = 0; i < caminoMinF.length; i++) {
            System.out.print(caminoMinF[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < distMinF.length; i++) {
            System.out.print(distMinF[i] + " ");
        }

    }
}
