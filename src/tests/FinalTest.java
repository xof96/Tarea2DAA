package tests;

import experiments.DijkArr;
import experiments.DijkHeap;
import javafx.util.Pair;
import structs.Graph;
import structs.GraphWay;

import java.lang.System;
import java.util.ArrayList;

public class FinalTest {

    public static void main(String args[]){
        double[] tiemposarr=new double[100];
        double[] tiempoheap=new double[100];
        for (int veces=0;veces<100;veces++) {
            Graph graph = new Graph();
            ArrayList<GraphWay>[] mat = graph.generateGraph(10000, 100);//ajustar parametros para la ram de cada uno
            DijkArr dijkstra = new DijkArr();
            double ini = System.currentTimeMillis();
            Pair cdarray = dijkstra.makeDijsktra(mat);
            double fin = System.currentTimeMillis();
            //System.out.println(fin - ini);
            tiemposarr[veces]=fin-ini;
            /*int[] caminoMin = (int[]) cdarray.getKey();
            double[] distMin = (double[]) cdarray.getValue();
            for (int i = 0; i < caminoMin.length; i++) {
                System.out.print(caminoMin[i] + " ");
            }
            System.out.println();*/
            DijkHeap dijh = new DijkHeap();
            double inih = System.currentTimeMillis();
            Pair cdheap = dijh.dijsktra(mat, 0);
            double finh = System.currentTimeMillis();
            //System.out.println(finh - inih);
            tiempoheap[veces]=finh-inih;
            /*int[] caminoMinH = (int[]) cdheap.getKey();
            double[] distMinH = (double[]) cdheap.getValue();
            for (int i = 0; i < caminoMinH.length; i++) {
                System.out.print(caminoMinH[i] + " ");
            }
            System.out.println();*/
        }
        for (int j=0;j<100;j++){
            System.out.print(tiemposarr[j]+" ");
        }
        System.out.println();
        for (int j=0;j<100;j++){
            System.out.print(tiempoheap[j]+" ");
        }
    }
}

