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
    private boolean isPointed;

    public FibonacciNode(GraphWay key) {
        this.p = null;
        this.child = null;
        this.left = this;
        this.right = this;
        this.degree = 0;
        this.key = key;
        this.mark = false;
        this.isPointed = false;
    }


    public FibonacciNode getP() {
        return p;
    }

    public void setP(FibonacciNode p) {
        this.p = p;
    }

    public FibonacciNode getChild() {
        return child;
    }

    public void setChild(FibonacciNode child) {
        if (this.child != null)
            this.child.setPointed(false);
        this.child = child;
        child.setPointed(true);
        this.setDegree(child.getDegree() + 1);
    }

    public FibonacciNode getLeft() {
        return left;
    }

    public void setLeft(FibonacciNode left) {
        this.left = left;
    }

    public FibonacciNode getRight() {
        return right;
    }

    public void setRight(FibonacciNode right) {
        this.right = right;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public GraphWay getKey() {
        return key;
    }

    public void setKey(GraphWay key) {
        this.key = key;
    }

    public boolean hasMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isPointed() {
        return isPointed;
    }

    public void setPointed(boolean pointed) {
        this.isPointed = pointed;
    }

    public String toString() {
        return this.getKey().toString();
    }

    public void print(int tabs) {
        FibonacciNode currNode = this.getRight();
        while (true) {
            for (int i = 0; i < tabs; i++)
                System.out.print("\t");
            System.out.println(String.format("|%s|; ", currNode));
            FibonacciNode child = currNode.getChild();
            if (child != null) {
                child.print(tabs + 1);
            }
            if (currNode.isPointed())
                break;
            currNode = currNode.getRight();
        }
    }
}
