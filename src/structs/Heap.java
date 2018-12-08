package structs;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private int[] graphNodes;
    private List<GraphWay> heapArray;

    public Heap(int n) {
        this.graphNodes = new int[n];
        this.heapArray = new ArrayList<>();
    }

    public void insert(GraphWay x) {
        if (this.isEmpty()) {
            this.heapArray.add(x);
            this.graphNodes[x.getNode()] = 0;
        } else {
            this.heapArray.add(x);
            int my_index = this.heapArray.size() - 1;
            int parent_index = this.parent_index(my_index);
            while (parent_index != my_index &&
                    this.heapArray.get(my_index).getWeight() < this.heapArray.get(parent_index).getWeight()) {
                this.swap(my_index, parent_index);
                my_index = parent_index;
                parent_index = this.parent_index(my_index);
            }
            this.graphNodes[x.getNode()] = my_index;
        }
    }

    public void decreaseKey(int g_index, double value) {
        int index = this.graphNodes[g_index];
        if (index > 0) {
            if (value < this.heapArray.get(index).getWeight()) {
                this.heapArray.get(index).setWeight(value);
                int parent_index = this.parent_index(index);
                while (index > 0 && this.heapArray.get(parent_index).getWeight() > value) {
                    this.swap(index, parent_index);
                    index = parent_index;
                    parent_index = this.parent_index(index);
                }
            }
        }
    }

    public GraphWay getMin() {
        return this.heapArray.get(0);
    }

    public GraphWay extractMin() {
        if (!this.isEmpty()) {
            if (this.size() == 1) {
                GraphWay min = this.heapArray.remove(0);
                this.graphNodes[min.getNode()] = -1;
                return min;
            } else {
                GraphWay min = this.getMin();
                this.graphNodes[min.getNode()] = -1;
                GraphWay last = this.heapArray.remove(this.size() - 1);
                this.heapArray.set(0, last);
                this.graphNodes[last.getNode()] = 0;
                int currIndex = 0, leftChildIndex = 1, rightChildIndex = 2;
                while (leftChildIndex < this.size() && rightChildIndex < this.size()) {
                    GraphWay l = this.heapArray.get(leftChildIndex);
                    GraphWay r = this.heapArray.get(rightChildIndex);
                    if (this.heapArray.get(currIndex).getWeight() < Math.min(l.getWeight(), r.getWeight()))
                        break;
                    if (r.getWeight() <= l.getWeight()) {
                        this.swap(currIndex, rightChildIndex);
                        currIndex = rightChildIndex;
                    } else {
                        this.swap(currIndex, leftChildIndex);
                        currIndex = leftChildIndex;
                    }
                    leftChildIndex = currIndex * 2 + 1;
                    rightChildIndex = currIndex * 2 + 2;
                }

                if (leftChildIndex < this.size()) {
                    GraphWay l = this.heapArray.get(leftChildIndex);
                    if (this.heapArray.get(currIndex).getWeight() > l.getWeight()) {
                        this.swap(currIndex, leftChildIndex);
                    }
                }
                return min;
            }
        } else {
            return null;
        }
    }

    public int[] getGraphNodes() {
        return graphNodes;
    }

    public boolean isEmpty() {
        return this.heapArray.isEmpty();
    }

    public int size() {
        return this.heapArray.size();
    }

    private int parent_index(int my_index) {
        if (my_index % 2 == 1) {
            return my_index / 2;
        }
        return (my_index - 1) / 2;
    }

    private void swap(int f_index, int s_index) {
        GraphWay buf = this.heapArray.get(f_index);
        this.graphNodes[this.heapArray.get(s_index).getNode()] = f_index;
        this.heapArray.set(f_index, this.heapArray.get(s_index));
        this.graphNodes[buf.getNode()] = s_index;
        this.heapArray.set(s_index, buf);
    }

    public void print() {
        for (GraphWay d : this.heapArray) {
            System.out.print(d.getWeight());
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void printGraphPos() {
        for (Integer i : this.graphNodes) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

}
