package tests;
import structs.Graph;
import experiments.DijkArr;

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
        int[] caminoMin=dijkstra.makeDijsktra(mat);
        for (int i=0;i<caminoMin.length;i++){
            System.out.print(caminoMin[i]+" ");
        }
    }
}
