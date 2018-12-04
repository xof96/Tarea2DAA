package tests;
import structs.GraphWay;
import structs.fibheap.*;

public class FibHeapTest {

    public static void main (String[] args) {
        FibonacciHeap h = new FibonacciHeap(10);

        h.insert(new FibonacciNode(new GraphWay(0, 29)));
        h.insert(new FibonacciNode(new GraphWay(1, 96)));
        h.insert(new FibonacciNode(new GraphWay(2, 9)));
        h.insert(new FibonacciNode(new GraphWay(3, 12)));
        h.insert(new FibonacciNode(new GraphWay(4, 7)));
        h.insert(new FibonacciNode(new GraphWay(5, 94)));
        h.insert(new FibonacciNode(new GraphWay(6, 29)));
        h.insert(new FibonacciNode(new GraphWay(7, 5)));
        h.insert(new FibonacciNode(new GraphWay(8, 91)));
        h.print();
    }
}
