package tests;
import structs.Graph;
import experiments.DijkArr;
import experiments.DijkHeap;

public class Arraytest {
    public static void main(String args[]){
        Graph generador=new Graph();
        DijkArr dijkstra=new DijkArr();
        double[][] mat=generador.generateGraph(5,2);
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                System.out.print(mat[i][j]+ " ");
            }
            System.out.println("\n");
        }
        int[] caminoMin=(int[]) dijkstra.makeDijsktra(mat).getKey();
        for (int i=0;i<caminoMin.length;i++){
            System.out.print(caminoMin[i]+" ");
        }
        DijkHeap dijh=new DijkHeap();
        int[] caminoMinH=(int []) dijh.dijsktra(mat,0).getKey();
        for (int i=0;i<caminoMin.length;i++){
            System.out.print(caminoMinH[i]+" ");
        }
    }
}
