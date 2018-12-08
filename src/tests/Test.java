package tests;

import structs.Graph;
import structs.GraphWay;
import experiments.DijkFib;
import experiments.DijkHeap;
import experiments.DijkArr;

import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test {
    public static void main(String args[]) throws IOException {
        Graph generador = new Graph();
        DijkArr dijkstra = new DijkArr();
        ArrayList<GraphWay>[] mat = generador.generateGraph(5, 2);

        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("./mat.txt"));

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
        double ini = System.currentTimeMillis();
        Pair cdArr = dijkstra.makeDijsktra(mat);
        double fin = System.currentTimeMillis();
        System.out.println(fin - ini);
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
        try {
            ini = System.currentTimeMillis();
            Pair cdHeap = dijh.dijsktra(mat, 0);
        } catch (Exception) {
            o.write(mat);
        }
        fin = System.currentTimeMillis();
        System.out.println(fin - ini);
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
        ini = System.currentTimeMillis();
        Pair cdFib = dijf.dijsktra(mat, 0);
        fin = System.currentTimeMillis();
        System.out.println(fin - ini);
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
