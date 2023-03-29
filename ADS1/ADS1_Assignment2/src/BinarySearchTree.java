import java.util.ArrayList;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public ArrayList<NodeValue> preorderTraversal() {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<NodeValue> values = new ArrayList<>();
        if (root == null)
            return values;

        traverseNode(root, nodes);
        for (Node n: nodes) {
            values.add(n.getValue());
        }

        return values;
    }

    private void traverseNode(Node node, ArrayList<Node> nodeList) {
        nodeList.add(node);

        if (node.getLeftSide() != null)
            traverseNode(node.getLeftSide(), nodeList);
        if (node.getRightSide() != null)
            traverseNode(node.getRightSide(), nodeList);
    }

    public void addValue(NodeValue value) {
        if (root == null) {
            root = new Node(value, null);
            return;
        }
        _addValue(root, value);
    }

    private Node _addValue(Node node, NodeValue value) {
        if (node == null) {
            return new Node(value, null);
        }

        NodeValue curVal = node.getValue();
        if (curVal.compareTo(value) == 0 ) {
            // duplicate
            return null;
        } else if (curVal.compareTo(value) > 0) {
            // new value is smaller than current value, going left
            Node leftChild = _addValue(node.getLeftSide(), value);
            if (leftChild != null) {
                node.setHeight(getHeight(leftChild) + getHeight(node.getRightSide()) + 1);
                leftChild.setParent(node);
            }
        } else if (curVal.compareTo(value) < 0) {
            // new value is larger than current value, going right
            Node rightChild = _addValue(node.getRightSide(), value);
            if (rightChild != null) {
                node.setHeight(getHeight(rightChild) + getHeight(node.getLeftSide()) + 1);
                rightChild.setParent(node);
            }
        }

        return node;
    }

    public void removeValue(NodeValue value) {
        if (root == null)
            return;

        Node nodeToRemove = findValueNode(value, root);
        if (nodeToRemove == null)
            return;

        Node parent = nodeToRemove.getParent();
        Node leftSide = nodeToRemove.getLeftSide();
        Node rightSide = nodeToRemove.getRightSide();
        Node replacement = null;

        if (leftSide == null && rightSide == null) {
            // no children
            if (parent == null) {
                return;
            }
        } else if (leftSide != null && rightSide == null) {
            //only left side
            replacement = leftSide;
        } else if (leftSide == null && rightSide != null) {
            replacement = rightSide;
        } else {
            // both sides
            Node botRight = bottomRight(leftSide);
            Node botLeft = bottomLeft(rightSide);
            if (leftSide != botRight) {
                // can use left side bottom right node for replacement
                replacement = botRight;
                botRight.setLeftSide(leftSide);
                botRight.setRightSide(rightSide);
                botRight.getParent().setRightSide(null);

                Node brP = botRight.getParent();
                brP.setHeight(getHeight(brP)-1);
                botRight.setHeight(getHeight(leftSide) + getHeight(rightSide) + 1);
            } else if (rightSide != botLeft) {
                // can use right side bottom right left for replacement
                replacement = botLeft;
                botLeft.setLeftSide(leftSide);
                botLeft.setRightSide(rightSide);
                botLeft.getParent().setLeftSide(null);

                Node blP = botLeft.getParent();
                blP.setHeight(getHeight(blP)-1);
                botLeft.setHeight(getHeight(leftSide) + getHeight(rightSide) + 1);
            } else {
                // fallback setting left child node as top
                replacement = leftSide;
                replacement.setHeight(getHeight(leftSide) + getHeight(rightSide));
                leftSide.setRightSide(rightSide);
            }
        }

        if (parent != null) {
            replacement.setParent(parent);
            if (parent.getLeftSide() == nodeToRemove) {
                parent.setLeftSide(replacement);
                parent.setHeight(getHeight(replacement) + getHeight(parent.getRightSide()) + 1);
            } else if (parent.getRightSide() == nodeToRemove) {
                parent.setRightSide(replacement);
                parent.setHeight(getHeight(replacement) + getHeight(parent.getLeftSide()) + 1);
            }
        }

        if (nodeToRemove == root) {
            root = replacement;
        }
    }

    private Node findValueNode(NodeValue value, Node rootNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        traverseNode(rootNode, nodes);
        for (Node n: nodes) {
            if (n.getValue().compareTo(value) == 0) {
                return n;
            }
        }

        return null;
    }

    /**
     * Finds the most bottom right child node of this node
     * @param node the starting node
     * @return most bottom right child node
     */
    private Node bottomRight(Node node) {
        Node bottomRight = node.getRightSide();
        if (bottomRight == null) {
            // reached the end, returning current node
            return node;
        }

        return bottomRight(bottomRight);
    }

    /**
     * Finds the most bottom left child node of this node
     * @param node the starting node
     * @return most bottom left child node
     */
    private Node bottomLeft(Node node) {
        Node bottomLeft = node.getLeftSide();
        if (bottomLeft == null) {
            // reached the end, returning current node
            return node;
        }

        return bottomLeft(bottomLeft);
    }

    public void rebalance() {
        if (root == null) {
            return;
        }

        int balance = getHeight(root.getLeftSide()) - getHeight(root.getRightSide());
        if (balance > 1) {
            if (getHeight(root.getLeftSide().getLeftSide())
                    >= getHeight(root.getLeftSide().getRightSide())) {
                root = rotateRight(root);
            } else {
                root.setLeftSide(rotateLeft(root.getLeftSide()));
                root = rotateRight(root);
            }
        } else if (balance < -1) {
            if (getHeight(root.getRightSide().getRightSide())
                    >= getHeight(root.getRightSide().getLeftSide())) {
                root = rotateLeft(root);
            } else {
                root.setRightSide(rotateRight(root.getRightSide()));
                root = rotateLeft(root);
            }
        }

        root.setHeight(Math.max(getHeight(root.getLeftSide()),
                getHeight(root.getRightSide())) + 1);
    }

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.getRightSide();
        node.setRightSide(newRoot.getLeftSide());
        newRoot.setLeftSide(node);
        node.setHeight(Math.max(getHeight(node.getLeftSide()),
                getHeight(node.getRightSide())) + 1);
        newRoot.setHeight(Math.max(getHeight(newRoot.getLeftSide()),
                getHeight(newRoot.getRightSide())) + 1);
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.getLeftSide();
        node.setLeftSide(newRoot.getRightSide());
        newRoot.setRightSide(node);
        node.setHeight(Math.max(getHeight(node.getLeftSide()),
                getHeight(node.getRightSide())) + 1);
        newRoot.setHeight(Math.max(getHeight(newRoot.getLeftSide()),
                getHeight(newRoot.getRightSide())) + 1);
        return newRoot;
    }
}
