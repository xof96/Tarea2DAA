package tests;
import structs.Graph;
import experiments.DijkArr;

public class Arraytest {
    public static void main(String args[]){
        Graph generador=new Graph();

        double[][] mat=generador.generateGraph(5,3);
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                System.out.print(mat[i][j]+ " ");
            }
            System.out.println("\n");
        }
    }
}
