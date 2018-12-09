package tests;

import experiments.DijkArr;
import experiments.DijkFib;
import experiments.DijkHeap;
import javafx.util.Pair;
import structs.Graph;
import structs.GraphWay;

import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;

public class FinalTest {

    public static void main(String args[]) throws IOException {
        int times = 1;
        double[] timesArr = new double[times];
        double[] timesHeap = new double[times];
        double[] timesFib = new double[times];
        for (int time = 0; time < times; time++) {
            System.out.println(String.format("Experimento N°%d", time + 1));
            Graph graph = new Graph();
            ArrayList<GraphWay>[] mat = graph.generateGraph(100000, 10);//ajustar parametros para la ram de cada uno
            System.out.println("Ya se creó la lista de adyacencia");
            DijkArr dijkstra = new DijkArr();
            double ini = System.currentTimeMillis();
            Pair cdarray = dijkstra.makeDijsktra(mat);
            double fin = System.currentTimeMillis();
            timesArr[time] = fin - ini;
            /*int[] caminoMin = (int[]) cdarray.getKey();
            double[] distMin = (double[]) cdarray.getValue();
            for (int i = 0; i < caminoMin.length; i++) {
                System.out.print(caminoMin[i] + " ");
            }*/
            System.out.println("Ya terminó el Array");
            DijkHeap dijH = new DijkHeap();
            double iniH = System.currentTimeMillis();
            Pair cdheap = dijH.dijsktra(mat, 0);
            double finH = System.currentTimeMillis();
            timesHeap[time] = finH - iniH;
            /*int[] caminoMinH = (int[]) cdheap.getKey();
            double[] distMinH = (double[]) cdheap.getValue();
            for (int i = 0; i < caminoMinH.length; i++) {
                System.out.print(caminoMinH[i] + " ");
            }
            System.out.println();*/

            System.out.println("Ya terminó el Heap");
            DijkFib dijF = new DijkFib();
            double iniF = System.currentTimeMillis();
            Pair cdFib = dijF.dijsktra(mat, 0);
            double finF = System.currentTimeMillis();
            timesFib[time] = finF - iniF;
        }
        for (int j = 0; j < times; j++) {
            System.out.print(timesArr[j] + " ");
            System.out.print(timesHeap[j] + " ");
            System.out.print(timesFib[j] + "\n");
        }
    }
}

