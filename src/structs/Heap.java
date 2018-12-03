package structs;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private List<GraphWay> heapArray;

    public Heap() {
        this.heapArray = new ArrayList<>();
    }

    public void insert(GraphWay x) {
        if (this.heapArray.isEmpty()) {
            this.heapArray.add(x);
        } else {
            this.heapArray.add(x);
            int my_index = this.heapArray.size() - 1;
            int parent_index = this.heapArray.size() / 2 - 1;
            if (this.heapArray.get(parent_index).getWeight() > x.getWeight()) {
                GraphWay buf = this.heapArray.remove(parent_index);
                this.heapArray.add(parent_index, x);
                this.heapArray.remove(my_index);
                this.heapArray.add(buf);
            } else {
                return;
            }
            my_index = parent_index;
            parent_index = (parent_index + 1) / 2 - 1;
            while (my_index > 0 && this.heapArray.get(parent_index).getWeight() > x.getWeight()) {
                GraphWay buf = this.heapArray.remove(parent_index);
                this.heapArray.add(parent_index, x);
                this.heapArray.remove(my_index);
                this.heapArray.add(my_index, buf);
                my_index = parent_index;
                parent_index = (parent_index + 1) / 2 - 1;
            }
        }
    }

    public void decreaseKey(int index, double value) {
        if (value < this.heapArray.get(index).getWeight()) {
            this.heapArray.get(index).setWeight(value);
            int parent_index = (index + 1) / 2 - 1;
            while (index > 0 && this.heapArray.get(parent_index).getWeight() > value) {
                GraphWay buf = this.heapArray.remove(parent_index);
                this.heapArray.add(parent_index, this.heapArray.get(index - 1));
                this.heapArray.remove(index);
                this.heapArray.add(index, buf);
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
            if (!this.heapArray.isEmpty()) {
                int size = this.heapArray.size();
                GraphWay last = this.heapArray.remove(size - 1);
                this.heapArray.add(0, last);
                int currIndex = 0, leftChildIndex = 1, rightChildIndex = 2;
                while (leftChildIndex < size - 1 && rightChildIndex < size - 1) {
                    GraphWay l = this.heapArray.get(leftChildIndex);
                    GraphWay r = this.heapArray.get(rightChildIndex);
                    if (this.heapArray.get(currIndex).getWeight() < Math.min(l.getWeight(), r.getWeight()))
                        break;
                    GraphWay buf = this.heapArray.remove(currIndex);
                    if (r.getWeight() <= l.getWeight()) {
                        this.heapArray.add(currIndex, r);
                        this.heapArray.add(rightChildIndex, buf);
                        this.heapArray.remove(rightChildIndex + 1);
                        currIndex = rightChildIndex;
                    } else {
                        this.heapArray.add(currIndex, l);
                        this.heapArray.add(leftChildIndex, buf);
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
                    this.heapArray.add(leftChildIndex, buf);
                    this.heapArray.remove(leftChildIndex + 1);
                }
            }
            return res;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return this.heapArray.isEmpty();
    }

    public void print() {
        for (GraphWay d : this.heapArray) {
            System.out.print(d.getWeight());
            System.out.print(" ");
        }
        System.out.print("\n");
    }

}
