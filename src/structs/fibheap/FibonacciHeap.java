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
                this.setMin(x);
            }
        }
        this.n++;
    }

    public FibonacciNode extractMin() {
        FibonacciNode min = this.getMin();
        if (min != null) {
            FibonacciNode child = min.getChild();
            if (child != null) {
                child.makeOrphanedChildren();
                FibonacciNode rightChild = child.getRight();
                min.getLeft().setRight(rightChild);
                rightChild.setLeft(min.getLeft());
                min.setLeft(child);
                child.setRight(min);
                min.setChild(null);
            }
            min.getRight().setLeft(min.getLeft());
            min.getLeft().setRight(min.getRight());
            if (min.getRight() == min) {
                this.setMin(null);
            } else {
                this.setMin(min.getRight());
                this.consolidate();
            }
            this.n--;
        }
        return min;
    }

    private void consolidate() {
        int maxD = (int) Math.ceil(Math.log(this.n) / Math.log(2)) + 2;
        FibonacciNode[] A = new FibonacciNode[maxD + 1];
        for (int i = 0; i <= maxD; i++) {
            A[i] = null;
        }
        FibonacciNode lastInReview = this.getMin().getLeft();
        FibonacciNode currNode = this.getMin();
        int d;
        while (true) {
            FibonacciNode nextToCheck = currNode.getRight();
            d = currNode.getDegree();
            FibonacciNode nodeToBeHung = currNode;
            while (A[d] != null) {
                FibonacciNode yNode = A[d];
                if (currNode.getKey().getWeight() > yNode.getKey().getWeight()) {
                    this.link(currNode, yNode);
                    nodeToBeHung = yNode;
                } else {
                    this.link(yNode, currNode);
                }
                A[d] = null;
                d++;
            }
            A[d] = nodeToBeHung;
            if (currNode == lastInReview)
                break;
            currNode = nextToCheck;
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
                    this.n--;  // Because insert increases n value;
                }
            }
        }
    }

    private void link(FibonacciNode y, FibonacciNode x) {
        y.getRight().setLeft(y.getLeft());
        y.getLeft().setRight(y.getRight());
        y.setRight(y);
        y.setLeft(y);

        if (x.getChild() != null) {
            FibonacciNode currNode = x.getChild();
            FibonacciNode currNodeL = x.getChild().getLeft();
            currNodeL.setRight(y);
            currNode.setLeft(y);
            y.setRight(currNode);
            y.setLeft(currNodeL);
        }

        y.setP(x);
        x.setChild(y);
        x.setDegree(x.getDegree() + 1);
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
            if (this.getMin() != null && node.getKey().getWeight() < this.getMin().getKey().getWeight()) {
                this.setMin(node);
            }
        }
    }

    private void cut(FibonacciNode node, FibonacciNode par) {
        if (node.getRight() == node) {
            par.setChild(null);
        } else {
            par.setChild(node.getRight());
        }
        node.getRight().setLeft(node.getLeft());
        node.getLeft().setRight(node.getRight());
        par.setDegree(par.getDegree() - 1);
        this.insert(node);
        this.n--;  // Because insert increases n value;
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
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public void print() {
        if (this.min == null) {
            System.out.println("El árbol está vacío");
        } else {
            this.min.print(0);
        }
    }
}
