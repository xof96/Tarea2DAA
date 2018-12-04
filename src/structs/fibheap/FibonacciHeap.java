package structs.fibheap;

public class FibonacciHeap {
    private int n;
    private FibonacciNode min;
    private FibonacciNode[] graphNodes;

    public FibonacciHeap(int n) {
        this.n = 0;
        this.min = null;
        this.graphNodes = new FibonacciNode[n];
    }


    public void insert(FibonacciNode x) {
        if (this.min == null) {
            this.setMin(x);
            this.graphNodes[x.getKey().getNode()] = x;
        } else {
            FibonacciNode min = this.getMin();
            x.setLeft(min);
            x.setRight(min.getRight());
            min.getRight().setLeft(x);
            min.setRight(x);
            this.graphNodes[x.getKey().getNode()] = x;
            if (x.getKey().getWeight() < min.getKey().getWeight()) {
                min.setPointed(false);
                this.setMin(x);
            }
        }
        this.n++;
    }

    /*
        public static FibonacciHeap Union(FibonacciHeap h1, FibonacciHeap h2) {
            FibonacciHeap h = new FibonacciHeap();
            h.setMin(h1.getMin());
            FibonacciNode currNode = h2.getMin();
            currNode.setPointed(false);
            FibonacciNode currNodeR = h2.getMin().getRight();
            FibonacciNode receivingNode = h.getMin();
            FibonacciNode receivingNodeL = h.getMin().getLeft();
            // Root lists are joined.
            currNodeR.setLeft(receivingNodeL);
            receivingNodeL.setRight(currNodeR);
            currNode.setRight(receivingNode);
            receivingNode.setLeft(currNode);

            if (h1.getMin() == null ||
                    (h2.getMin() != null && h2.getMin().getKey().getWeight() < h1.getMin().getKey().getWeight())) {
                if (h.getMin() != null) {
                    h.getMin().setPointed(false);
                }
                h.setMin(h2.getMin());
            }
            h.setN(h1.getN() + h2.getN());
            return h;
        }
    */

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
                if (currNode.getKey().getWeight() > yNode.getKey().getWeight()) {
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
            y.setPointed(true);
        } else {
            y.setPointed(false);
            FibonacciNode currNode = x.getChild();
            FibonacciNode currNodeL = x.getChild().getLeft();
            currNodeL.setRight(y);
            currNode.setLeft(y);
            y.setRight(currNode);
            y.setLeft(currNodeL);
        }
        y.setP(x);
        x.setChild(y);
        y.setMark(false);
    }

    public void decreaseKey(int index, double value) {
        FibonacciNode node = this.graphNodes[index];
        if (value < node.getKey().getWeight()) {
            node.getKey().setWeight(value);
            FibonacciNode par = node.getP();
            if (par != null && node.getKey().getWeight() < par.getKey().getWeight()) {
                this.cut(node, par);
                this.cascadingCut(par);
            }
            if (node.getKey().getWeight() < this.getMin().getKey().getWeight()) {
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
            FibonacciNode b_par = par;
            par.setDegree(node.getDegree());
            while (b_par.getP() != null && b_par.getP().getDegree() == b_par.getDegree() + 2) {
                b_par.getP().setDegree(b_par.getP().getDegree() - 1);
                b_par = b_par.getP();
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

    /*
        public FibonacciNode delete(FibonacciNode x) {
            this.decreaseKey(x, Double.NEGATIVE_INFINITY);
            return this.extractMin();
        }
    */

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

    public boolean isEmpty() {
        return this.n == 0;
    }

    public void print() {
        if (this.min == null) {
            System.out.println("El árbol está vacío");
        } else {
            this.min.print();
        }
    }
}
