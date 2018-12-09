package structs;

import java.io.Serializable;

public class GraphWay implements Serializable {
    private int node;
    private double weight;

    public GraphWay(int node, double weight) {
        this.node = node;
        this.weight = weight;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return String.format("Nodo %s, costo: %f", this.node, this.weight);
    }
}
