package structs.fibheap;

import structs.GraphWay;

public class FibonacciNode {
    private FibonacciNode p;
    private FibonacciNode child;
    private FibonacciNode left;
    private FibonacciNode right;
    private int degree;
    private GraphWay key;
    private boolean mark;

    public FibonacciNode(GraphWay key) {
        this.p = null;
        this.child = null;
        this.left = this;
        this.right = this;
        this.degree = 0;
        this.key = key;
        this.mark = false;
    }


    FibonacciNode getP() {
        return p;
    }

    void setP(FibonacciNode p) {
        this.p = p;
    }

    FibonacciNode getChild() {
        return child;
    }

    void setChild(FibonacciNode child) {
        this.child = child;
    }

    FibonacciNode getLeft() {
        return left;
    }

    void setLeft(FibonacciNode left) {
        this.left = left;
    }

    FibonacciNode getRight() {
        return right;
    }

    void setRight(FibonacciNode right) {
        this.right = right;
    }

    int getDegree() {
        return degree;
    }

    void setDegree(int degree) {
        this.degree = degree;
    }

    public GraphWay getKey() {
        return key;
    }

    public void setKey(GraphWay key) {
        this.key = key;
    }

    boolean hasMark() {
        return mark;
    }

    void setMark(boolean mark) {
        this.mark = mark;
    }

    public String toString() {
        return this.getKey().toString();
    }

    void print(int tabs) {
        FibonacciNode first = this;
        FibonacciNode currNode = this.right;
        while (true) {
            for (int i = 0; i < tabs; i++)
                System.out.print("\t");
            System.out.println(String.format("|%s|; ", currNode));
            FibonacciNode child = currNode.getChild();
            if (child != null) {
                child.print(tabs + 1);
            }
            if (currNode == first)
                break;
            currNode = currNode.getRight();
        }
    }

    void makeOrphanedChildren() {
        FibonacciNode first = this;
        this.setP(null);
        FibonacciNode sibling = first.right;
        while (sibling != first) {
            sibling.setP(null);
            sibling = sibling.right;
        }
    }
}
