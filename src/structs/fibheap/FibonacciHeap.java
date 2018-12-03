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
            FibonacciNode min = this.getMin();
            x.setLeft(min);
            x.setRight(min.getRight());
            min.getRight().setLeft(x);
            min.setRight(x);
            if (x.getKey() < min.getKey()) {
                min.setPointed(false);
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
        int maxD = (int) Math.ceil(Math.log(this.n) / Math.log(2));
        FibonacciNode[] A = new FibonacciNode[maxD + 1];
        for (int i = 0; i <= maxD; i++) {
            A[i] = null;
        }
        FibonacciNode currNode = this.getMin();
        int d = currNode.getDegree();
        A[d] = currNode;
        currNode = currNode.getRight();
        while (!currNode.isPointed()) {
            d = currNode.getDegree();
            while (A[d] != null) {
                FibonacciNode yNode = A[d];
                if (currNode.getKey() > yNode.getKey()) {
                    FibonacciNode buf = yNode;
                    yNode = currNode;
                    currNode = buf;
                }
                this.link(yNode, currNode);
                A[d] = null;
                d++;
            }
            A[d] = currNode;
            currNode = currNode.getRight();
        }
        this.setMin(null);
        for (int i = 0; i <= maxD; i++) {
            if (A[i] != null) {
                if (this.getMin() == null) {
                    FibonacciNode a = A[i];
                    a.setLeft(a);
                    a.setRight(a);
                    this.setMin(a);
                } else {
                    this.insert(A[i]);
                }
            }
        }
    }

    private void link(FibonacciNode y, FibonacciNode x) {
        y.getRight().setLeft(y.getLeft());
        y.getLeft().setRight(y.getRight());
        if (x.getChild() == null) {
            y.setRight(y);
            y.setLeft(y);
            y.setP(x);
            x.setChild(y);
            y.setMark(false);
        }
    }

    public void decreaseKey(FibonacciNode node, double value) {
        if (value < node.getKey()) {
            node.setKey(value);
            FibonacciNode par = node.getP();
            if (par != null && node.getKey() < par.getKey()) {
                this.cut(node, par);
                this.cascadingCut(par);
            }

            if (node.getKey() < this.getMin().getKey()) {
                this.setMin(node);
            }
        }
    }

    private void cut(FibonacciNode node, FibonacciNode par) {
        if (node.isPointed()) {
            if (node.getRight() == node) {
                par.setChild(null);
            } else {
                par.setChild(node.getRight());
            }
            node.setPointed(false);
        }
        if (par.getDegree() == node.getDegree() + 1) {
            par.setDegree(par.getChild().getDegree() + 1);
            FibonacciNode currNode = par.getChild().getRight();
            while (!currNode.isPointed()) {
                if (currNode.getDegree() > par.getDegree() - 1) {
                    par.setDegree(currNode.getDegree() + 1);
                }
            }
        }
        this.insert(node);
        node.setP(null);
        node.setMark(false);
    }

    private void cascadingCut(FibonacciNode par) {
        FibonacciNode z = par.getP();
        if (z != null) {
            if (!par.hasMark()) {
                par.setMark(true);
            } else {
                this.cut(par, z);
                this.cascadingCut(z);
            }
        }
    }

    public FibonacciNode delete(FibonacciNode x) {
        this.decreaseKey(x, Double.NEGATIVE_INFINITY);
        return this.extractMin();
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
