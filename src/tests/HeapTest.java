package tests;

import structs.GraphWay;
import structs.Heap;

public class HeapTest {

    public static void main(String[] args) {
        Heap h = new Heap();
        h.insert(new GraphWay(0, 29));
        h.insert(new GraphWay(1, 96));
        h.insert(new GraphWay(2, 9));
        h.insert(new GraphWay(3, 12));
        h.insert(new GraphWay(4, 7));
        h.insert(new GraphWay(5, 94));
        h.insert(new GraphWay(6, 29));
        h.insert(new GraphWay(7, 5));
        h.insert(new GraphWay(8, 91));
        h.extractMin();
        h.extractMin();
        h.print();
        h.decreaseKey(4, 10);
        h.print();
    }
}
