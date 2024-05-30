package PriorityQueues;

public interface IntPriorityQueue  {
    void add(int value);
    Integer remove();
    Integer peak();
    void clear();
    boolean isEmpty();
    int size();
}