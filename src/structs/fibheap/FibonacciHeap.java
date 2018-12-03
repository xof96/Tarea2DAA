package structs.fibheap;

class FibonacciHeap {
    private int n;
    private FibonacciNode min;

    public FibonacciHeap() {
        this.n = 0;
        this.min = null;
    }


    public void insert(FibonacciNode x) {
        if (this.min == null) {
            this.setMin(x);
        } else {
            if (x.getKey() < this.min.getKey()) {
                this.min.setPointed(false);
                this.setMin(x);
            }
        }
        this.n++;
    }

    public static FibonacciHeap Union(FibonacciHeap h1, FibonacciHeap h2) {
        FibonacciHeap h = new FibonacciHeap();
        h.setMin(h1.getMin());
        FibonacciNode currNode = h2.getMin().getRight();
        FibonacciNode receivingNode = h.getMin();
        while (true) {
            FibonacciNode nextNode = currNode.getRight();
            currNode.setLeft(receivingNode);
            currNode.setRight(receivingNode.getRight());
            receivingNode.setRight(currNode);
            currNode.getRight().setLeft(currNode);
            if (currNode.isPointed())
                break;
            currNode = nextNode;
        }
        if (h.getMin() == null || (h2.getMin() != null && h2.getMin().getKey() < h1.getMin().getKey())) {
            if (h.getMin() != null) {
                h.getMin().setPointed(false);
            }
            h.setMin(h2.getMin());
        } else {
            h2.getMin().setPointed(false);
        }
        h.setN(h1.getN() + h2.getN());
        return h;
    }

    public FibonacciNode extractMin() {
        FibonacciNode res = this.getMin();
        if (res != null) {
            FibonacciNode currChild = res.getChild().getRight();
            FibonacciNode receivingNode = this.getMin();
            while (true) {
                FibonacciNode nextNode = currChild.getRight();
                currChild.setLeft(receivingNode);
                currChild.setRight(receivingNode.getRight());
                receivingNode.setRight(currChild);
                currChild.getRight().setLeft(currChild);
                currChild.setP(null);
                if (currChild.isPointed()) {
                    currChild.setPointed(false);
                    break;
                }
                currChild = nextNode;
            }
            res.getRight().setLeft(res.getLeft());
            res.getLeft().setRight(res.getRight());
            if (res.getRight() == res) {
                this.setMin(null);
            } else {
                this.setMin(res.getRight());
                this.consolidate();
            }
            this.n--;
        }
        return res;
    }

    private void consolidate() {
        return;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public FibonacciNode getMin() {
        return min;
    }

    public void setMin(FibonacciNode min) {
        this.min = min;
        if (min != null)
            min.setPointed(true);
    }
}
