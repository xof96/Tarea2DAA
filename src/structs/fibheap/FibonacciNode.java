package structs.fibheap;

class FibonacciNode {
    private FibonacciNode p;
    private FibonacciNode child;
    private FibonacciNode left;
    private FibonacciNode right;
    private int degree;
    private double key;
    private boolean mark;
    private boolean isPointed;

    public FibonacciNode(double key) {
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

        this.child = child;
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

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
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
}
