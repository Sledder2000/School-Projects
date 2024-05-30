package PriorityQueues;

public interface PriorityQueue <E extends Comparable<E>> {
    void add(E value);
    E remove();
    E peak();
    void clear();
    boolean isEmpty();
    int size();
}