package tests;
import structs.GraphWay;
import structs.fibheap.*;

public class FibHeapTest {

    public static void main (String[] args) {
        FibonacciHeap h = new FibonacciHeap(10);
        FibonacciNode n1 = new FibonacciNode(new GraphWay(0, 0));
        FibonacciNode n2 = new FibonacciNode(new GraphWay(1, 1000));
        FibonacciNode n3 = new FibonacciNode(new GraphWay(2, 1000));
        FibonacciNode n4 = new FibonacciNode(new GraphWay(3, 1000));
        h.insert(n1);
        h.insert(n2);
        h.insert(n3);
        h.insert(n4);
        System.out.println(h.extractMin().toString());
        System.out.println(h.extractMin().toString());
        System.out.println(h.getMin().toString());
        h.print();
//        h.insert(new FibonacciNode(new GraphWay(0, 29)));
//        h.insert(new FibonacciNode(new GraphWay(1, 96)));
//        h.insert(new FibonacciNode(new GraphWay(2, 9)));
//        h.insert(new FibonacciNode(new GraphWay(3, 12)));
//        h.insert(new FibonacciNode(new GraphWay(4, 7)));
//        h.insert(new FibonacciNode(new GraphWay(5, 94)));
//        h.insert(new FibonacciNode(new GraphWay(6, 29)));
//        h.insert(new FibonacciNode(new GraphWay(7, 5)));
//        h.insert(new FibonacciNode(new GraphWay(8, 91)));
//        h.print();
//        System.out.println("________________________________________\n\n");
//        h.extractMin();
//        h.print();
    }
}
