import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree bst;
    Node root;
    Node left;
    Node right;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree(null);
        root = new Node(new NodeValue<>(123), null);
        left = new Node(new NodeValue<>(100), root);
        right = new Node(new NodeValue<>(444), root);
        root.setHeight(3);
        root.setLeftSide(left);
        root.setRightSide(right);
    }

    @AfterEach
    void tearDown() {
        bst = null;
    }

    @Test
    void getRootNull() {
        assertEquals(null, bst.getRoot());
    }

    @Test
    void getRoot() {
        bst.setRoot(root);
        assertEquals(root, bst.getRoot());
    }

    @Test
    void setRoot() {
        bst.setRoot(left);
        assertEquals(left, bst.getRoot());
    }

    @Test
    void preorderTraversal() {
        bst.setRoot(root);
        ArrayList<NodeValue> values = bst.preorderTraversal();
        ArrayList<NodeValue> correct = new ArrayList<>();
        correct.add(root.getValue());
        correct.add(left.getValue());
        correct.add(right.getValue());

        assertEquals(correct, values);
    }

    @Test
    void addValueLeft() {
        bst.setRoot(root);
        NodeValue value = new NodeValue<>(90);
        bst.addValue(value);

        ArrayList<NodeValue> values = bst.preorderTraversal();
        ArrayList<NodeValue> correct = new ArrayList<>();
        correct.add(root.getValue());
        correct.add(left.getValue());
        correct.add(value);
        correct.add(right.getValue());

        assertEquals(correct, values);
    }

    @Test
    void addValueRight() {
        bst.setRoot(root);
        NodeValue value = new NodeValue<>(900);
        bst.addValue(value);

        ArrayList<NodeValue> values = bst.preorderTraversal();
        ArrayList<NodeValue> correct = new ArrayList<>();
        correct.add(root.getValue());
        correct.add(left.getValue());
        correct.add(right.getValue());
        correct.add(value);

        assertEquals(correct, values);
    }

    @Test
    void removeValueLeft() {
        bst.setRoot(root);
        bst.removeValue(new NodeValue<>(100));

        ArrayList<NodeValue> values = bst.preorderTraversal();
        ArrayList<NodeValue> correct = new ArrayList<>();
        correct.add(root.getValue());
        correct.add(right.getValue());

        assertEquals(correct, values);
    }

    @Test
    void removeValueRight() {
        bst.setRoot(root);
        bst.removeValue(new NodeValue<>(444));

        ArrayList<NodeValue> values = bst.preorderTraversal();
        ArrayList<NodeValue> correct = new ArrayList<>();
        correct.add(root.getValue());
        correct.add(left.getValue());

        assertEquals(correct, values);
    }

    @Test
    void getHeightNull() {
        assertEquals(0, bst.getHeight(null));
    }

    @Test
    void getHeight() {
        assertEquals(1, bst.getHeight(left));
    }
}