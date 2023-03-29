import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    NodeValue value = new NodeValue<>(123);
    NodeValue valueLeft = new NodeValue<>(5);
    NodeValue valueRight = new NodeValue<>(444);
    Node node;
    Node left;
    Node right;

    @BeforeEach
    void setUp() {
        node = new Node(value, null);
        left = new Node(valueLeft, node);
        right = new Node(valueRight, node);
    }

    @AfterEach
    void tearDown() {
        node = null;
    }

    @Test
    void getValue() {
        assertEquals(value, node.getValue());
    }

    @Test
    void getLeftSideNull() {
        assertEquals(null, node.getLeftSide());
    }

    @Test
    void getRightSideNull() {
        assertEquals(null, node.getRightSide());
    }

    @Test
    void setLeftSide() {
        node.setLeftSide(left);
        assertEquals(left, node.getLeftSide());
    }

    @Test
    void setRightSide() {
        node.setRightSide(right);
        assertEquals(right, node.getRightSide());
    }

    @Test
    void getParentNull() {
        assertEquals(null, node.getParent());
    }

    @Test
    void getParent() {
        assertEquals(node, left.getParent());
    }

    @Test
    void setParent() {
        node.setParent(right);
        assertEquals(right, node.getParent());
    }

    @Test
    void getHeight() {
        assertEquals(1, node.getHeight());
    }

    @Test
    void setHeight() {
        node.setHeight(3);
        assertEquals(3, node.getHeight());
    }
}