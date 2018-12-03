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
        if (this.heapArray.isEmpty()) {
            this.heapArray.add(x);
            this.graphNodes[x.getNode()] = 0;
        } else {
            this.heapArray.add(x);
            int my_index = this.heapArray.size() - 1;
            int parent_index = this.heapArray.size() / 2 - 1;
            if (this.heapArray.get(parent_index).getWeight() > x.getWeight()) {
                GraphWay buf = this.heapArray.remove(parent_index);
                this.heapArray.add(parent_index, x);
                this.graphNodes[x.getNode()] = parent_index;
                this.heapArray.remove(my_index);
                this.heapArray.add(buf);
                this.graphNodes[buf.getNode()] = my_index;
            } else {
                this.graphNodes[x.getNode()] = my_index;
                return;
            }
            my_index = parent_index;
            parent_index = (parent_index + 1) / 2 - 1;
            while (my_index > 0 && this.heapArray.get(parent_index).getWeight() > x.getWeight()) {
                GraphWay buf = this.heapArray.remove(parent_index);
                this.heapArray.add(parent_index, x);
                this.graphNodes[x.getNode()] = parent_index;
                this.heapArray.remove(my_index);
                this.heapArray.add(my_index, buf);
                this.graphNodes[buf.getNode()] = my_index;
                my_index = parent_index;
                parent_index = (parent_index + 1) / 2 - 1;
            }
            this.graphNodes[x.getNode()] = my_index;
        }
    }

    public void decreaseKey(int g_index, double value) {
        int index = this.graphNodes[g_index];
        if (index < 0)
            return;
        if (value < this.heapArray.get(index).getWeight()) {
            this.heapArray.get(index).setWeight(value);
            int parent_index = (index + 1) / 2 - 1;
            while (index > 0 && this.heapArray.get(parent_index).getWeight() > value) {
                GraphWay buf = this.heapArray.remove(parent_index);
                this.heapArray.add(parent_index, this.heapArray.get(index - 1));
                this.graphNodes[g_index] = parent_index;
                this.heapArray.remove(index);
                this.heapArray.add(index, buf);
                this.graphNodes[buf.getNode()] = index;
                index = parent_index;
                parent_index = (parent_index + 1) / 2 - 1;
            }
        }
    }

    public GraphWay getMin() {
        return this.heapArray.get(0);
    }

    public GraphWay extractMin() {
        if (!this.heapArray.isEmpty()) {
            GraphWay res = this.heapArray.remove(0);
            this.graphNodes[res.getNode()] = -1;
            if (!this.heapArray.isEmpty()) {
                int size = this.heapArray.size();
                GraphWay last = this.heapArray.remove(size - 1);
                this.heapArray.add(0, last);
                this.graphNodes[last.getNode()] = 0;
                int currIndex = 0, leftChildIndex = 1, rightChildIndex = 2;
                while (leftChildIndex < size - 1 && rightChildIndex < size - 1) {
                    GraphWay l = this.heapArray.get(leftChildIndex);
                    GraphWay r = this.heapArray.get(rightChildIndex);
                    if (this.heapArray.get(currIndex).getWeight() < Math.min(l.getWeight(), r.getWeight()))
                        break;
                    GraphWay buf = this.heapArray.remove(currIndex);
                    if (r.getWeight() <= l.getWeight()) {
                        this.heapArray.add(currIndex, r);
                        this.graphNodes[r.getNode()] = currIndex;
                        this.heapArray.add(rightChildIndex, buf);
                        this.graphNodes[buf.getNode()] = rightChildIndex;
                        this.heapArray.remove(rightChildIndex + 1);
                        currIndex = rightChildIndex;
                    } else {
                        this.heapArray.add(currIndex, l);
                        this.graphNodes[l.getNode()] = currIndex;
                        this.heapArray.add(leftChildIndex, buf);
                        this.graphNodes[buf.getNode()] = leftChildIndex;
                        this.heapArray.remove(leftChildIndex + 1);
                        currIndex = leftChildIndex;
                    }

                    leftChildIndex = currIndex * 2 + 1;
                    rightChildIndex = currIndex * 2 + 2;
                }

                if (leftChildIndex < size - 1) {
                    GraphWay l = this.heapArray.get(leftChildIndex);
                    GraphWay buf = this.heapArray.remove(currIndex);
                    this.heapArray.add(currIndex, l);
                    this.graphNodes[l.getNode()] = currIndex;
                    this.heapArray.add(leftChildIndex, buf);
                    this.graphNodes[buf.getNode()] = leftChildIndex;
                    this.heapArray.remove(leftChildIndex + 1);
                }
            }
            return res;
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

    public void print() {
        for (GraphWay d : this.heapArray) {
            System.out.print(d.getWeight());
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void printGraphPos() {
        for (Integer i: this.graphNodes) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

}
