public class NodeValue<T> implements Comparable<T> {

    private final T value;

    public NodeValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int compareTo(T o) {
        // idk how else to do this..
        if (o instanceof Integer && value instanceof Integer) {
            Integer ourValue = (Integer) value;
            Integer theirValue = (Integer) o;

            return ourValue.compareTo(theirValue);
        } else if (o instanceof Double && value instanceof Double) {
            Double ourValue = (Double) value;
            Double theirValue = (Double) o;

            return ourValue.compareTo(theirValue);
        } else if (o instanceof Float && value instanceof Float) {
            Float ourValue = (Float) value;
            Float theirValue = (Float) o;

            return ourValue.compareTo(theirValue);
        } else if (o instanceof Long && value instanceof Long) {
            Long ourValue = (Long) value;
            Long theirValue = (Long) o;

            return ourValue.compareTo(theirValue);
        } else if (o instanceof String && value instanceof String) {
            String ourValue = (String) value;
            String theirValue = (String) o;

            return ourValue.compareTo(theirValue);
        } else {
            // basic data type comparisons dont match, falling back to hashcode
            if (value.hashCode() == o.hashCode())
                return 0;
            else if (value.hashCode() > o.hashCode())
                return 1;
            else
                return -1;
        }
    }
}
