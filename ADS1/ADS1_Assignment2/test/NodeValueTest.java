import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeValueTest {
    NodeValue nodeInt = new NodeValue<>(123);
    NodeValue nodeDouble = new NodeValue<>(123.456);

    @Test
    void getValueInt() {
        assertEquals(123, nodeInt.getValue());
    }

    @Test
    void getValueDouble() {
        assertEquals(123.456, nodeDouble.getValue());
    }

    @Test
    void compareToIntSame() {
        assertEquals(0, nodeInt.compareTo(123));
    }

    @Test
    void compareToIntLess() {
        assertEquals(-1, nodeInt.compareTo(555));
    }

    @Test
    void compareToIntHigher() {
        assertEquals(1, nodeInt.compareTo(100));
    }

    @Test
    void compareToDoubleSame() {
        assertEquals(0, nodeDouble.compareTo(123.456));
    }

    @Test
    void compareToDoubleLess() {
        assertEquals(-1, nodeDouble.compareTo(555.55));
    }

    @Test
    void compareToDoubleHigher() {
        assertEquals(1, nodeDouble.compareTo(100.123));
    }
}