package tests;

import structs.Heap;

public class HeapTest {

    public static void main(String[] args) {
        Heap h = new Heap();
        h.insert(29);
        h.insert(96);
        h.insert(9);
        h.insert(12);
        h.insert(7);
        h.insert(94);
        h.insert(29);
        h.insert(5);
        h.insert(91);
        h.extractMin();
        h.print();
        h.extractMin();
        h.print();
    }
}
