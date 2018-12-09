package structs.fibheap2;
import structs.GraphWay;

public class FibNode {
    private FibNode padre;
    private FibNode child;
    private FibNode left;
    private FibNode right;
    private int degree;
    private GraphWay key;
    private boolean mark;

    public FibNode(GraphWay key) {
        this.padre = null;
        this.child = null;
        this.left = this;
        this.right = this;
        this.degree = 0;
        this.key = key;
        this.mark = false;
    }

    public FibNode getPadre() {
        return padre;
    }

    public void setPadre(FibNode p) {
        this.padre = p;
    }

    public FibNode getChild() {
        return child;
    }

    public void setChild(FibNode child) {
        this.child = child;
    }

    public FibNode getLeft() {
        return left;
    }

    public void setLeft(FibNode left) {
        this.left = left;
    }

    public FibNode getRight() {
        return right;
    }

    public void setRight(FibNode right) {
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

    public boolean isMarked() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String toString() {
        return this.getKey().toString();
    }

    public void print(int tabs) {
        FibNode currNode = this.getRight();
        int initialNode=this.getKey().getNode();
        while (true) {
            for (int i = 0; i < tabs; i++)
                System.out.print("\t");
            System.out.println(String.format("|%s|; ", currNode));
            FibNode child = currNode.getChild();
            if (child != null) {
                child.print(tabs + 1);
            }
            if (currNode.getKey().getNode()==initialNode)
                break;
            currNode = currNode.getRight();
        }
    }
}
