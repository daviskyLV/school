public class Node {
    private Node leftSide;
    private Node rightSide;
    private Node parent;
    private final NodeValue value;
    private int height;

    public Node(NodeValue value, Node parent) {
        this.value = value;
        this.leftSide = null;
        this.rightSide = null;
        this.parent = parent;
        height = 1;
    }

    public NodeValue getValue() {
        return value;
    }

    public Node getLeftSide() {
        return leftSide;
    }

    public Node getRightSide() {
        return rightSide;
    }

    public void setLeftSide(Node leftSide) {
        this.leftSide = leftSide;
    }

    public void setRightSide(Node rightSide) {
        this.rightSide = rightSide;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
