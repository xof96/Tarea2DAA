package structs.fibheap2;

public class FibHeap {
    private int n;
    private FibNode min;
    private FibNode[] graphNodes;

    public FibHeap(int n) {
        this.n = 0;
        this.min = null;
        this.graphNodes = new FibNode[n];
    }

    public void insert(FibNode x) {
        x.setDegree(0);
        x.setPadre(null);
        x.setChild(null);
        x.setMark(false);
        this.graphNodes[x.getKey().getNode()] = x;
        if (this.min == null) {
            x.setLeft(x);
            x.setRight(x);
            this.min = x;
        } else {
            FibNode left = this.min.getLeft();
            left.setRight(x);
            this.min.setLeft(x);
            x.setRight(this.min);
            x.setLeft(left);
            if (x.getKey().getWeight() < this.min.getKey().getWeight()) {
                this.min = x;
            }
        }
        this.n += 1;
    }

    public FibNode extractMin() {
        FibNode z = this.min;
        if (z != null) {
            if (z.getChild() != null) {
                FibNode x = z.getChild();
                int initialNode = x.getKey().getNode();
                x = x.getRight();
                while (true) {
                    FibNode next = x.getRight();
                    x.getLeft().setRight(x.getRight());
                    x.getRight().setLeft(x.getLeft());
                    FibNode minRight = z.getRight();
                    x.setRight(minRight);
                    x.setLeft(z);
                    z.setRight(x);
                    minRight.setLeft(x);
                    x.setPadre(null);
                    if (x.getKey().getNode() == initialNode) {
                        break;
                    } else {
                        x = next;
                    }
                }
            }
            //Se saca el minimo de la lista
            FibNode minR = z.getRight();
            FibNode minL = z.getLeft();
            minR.setLeft(minL);
            minL.setRight(minR);
            if (z == z.getRight()) {
                this.min = null;
            } else {
                this.min = z.getRight();
                this.consolidate();
            }
            this.n -= 1;
        }
        return z;
    }

    public void consolidate() {
        int D = (int) Math.ceil(Math.log(this.n) / Math.log(2)) + 1;
        FibNode[] A = new FibNode[D+1];
        for (int i = 0; i < D+1; i++) {
            A[i] = null;
        }
        FibNode nodo = this.min;
        int initnodo = nodo.getKey().getNode();
        FibNode x = nodo.getRight();
        while (true) {
            FibNode next=x.getRight();
            int d = x.getDegree();
            while (A[d] != null) {
                FibNode y = A[d];
                if (x.getKey().getWeight() > y.getKey().getWeight()) {
                    FibNode buf = x;
                    x = y;
                    y = buf;
                }
                link(y, x);
                A[d] = null;
                d++;
            }
            A[d] = x;
            if (x.getKey().getNode() == initnodo) {
                break;
            } else {
                x = next;
            }
        }
        this.min = null;
        for (int i = 0; i < D; i++) {
            if (A[i] != null) {
                if (this.min == null) {
                    A[i].setRight(A[i]);
                    A[i].setLeft(A[i]);
                    A[i].setPadre(null);
                    this.min = A[i];
                } else {
                    FibNode minR = this.min.getRight();
                    A[i].setLeft(this.min);
                    A[i].setRight(minR);
                    A[i].setPadre(null);
                    minR.setLeft(A[i]);
                    this.min.setRight(A[i]);
                    if (A[i].getKey().getWeight() < this.min.getKey().getWeight()) {
                        this.min = A[i];
                    }
                }
            }
        }
    }

    public void link(FibNode y, FibNode x) {
        y.getRight().setLeft(y.getLeft());
        y.getLeft().setRight(y.getRight());
        y.setPadre(x);
        x.setDegree(x.getDegree() + 1);
        if (x.getChild() == null) {
            y.setLeft(y);
            y.setRight(y);
            x.setChild(y);
        } else {
            y.setLeft(x.getChild());
            y.setRight(x.getChild().getRight());
            x.getChild().getRight().setLeft(y);
            x.getChild().setRight(y);
        }
        y.setMark(false);
    }

    public void decreaseKey(int inodo, double k) {
        FibNode x = this.graphNodes[inodo];
        x.getKey().setWeight(k);
        FibNode y = x.getPadre();
        if (y != null && x.getKey().getWeight() < y.getKey().getWeight()) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.getKey().getWeight() < this.min.getKey().getWeight()) {
            this.min = x;
        }
    }

    public void cut(FibNode x, FibNode y) {
        if (x.getRight() == x) {
            y.setChild(null);
        } else {
            if (y.getChild() == x) {
                y.setChild(x.getRight());
            }
            x.getRight().setLeft(x.getLeft());
            x.getLeft().setRight(x.getRight());
        }
        y.setDegree(y.getDegree() - 1);
        x.setRight(this.min.getRight());
        x.setLeft(this.min);
        this.min.getRight().setLeft(x);
        this.min.setRight(x);
        x.setPadre(null);
        x.setMark(false);
    }

    public void cascadingCut(FibNode y) {
        FibNode z = y.getPadre();
        if (z != null) {
            if (!y.isMarked()) {
                y.setMark(true);
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    public int getN() {
        return this.n;
    }

    public FibNode getMin() {
        return min;
    }

    public void print() {
        if (this.min == null) {
            System.out.println("El árbol está vacío");
        } else {
            this.min.print(0);
        }
    }

}
