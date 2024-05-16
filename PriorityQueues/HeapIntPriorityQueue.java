package PriorityQueues;

import java.util.Arrays;

public class HeapIntPriorityQueue implements IntPriorityQueue {

    private int[] elements;
    private int size;

    //#region - helper methods
    private int left(int index) {
        return 2 * index;
    }
    private int right(int index) {
        return 2 * index + 1;
    }
    private int parent(int index) {
        return index / 2;
    }
    private boolean hasParent(int index) {
        return index > 1;
    }
    private boolean hasLeft(int index) {
        return left(index) < size;
    }
    private boolean hasRight(int index) {
        return right(index) < size;
    }
    private void swap(int index1, int index2) {
        int temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }
    //#endregion - helper methods

    public HeapIntPriorityQueue() {
        elements = new int[10];
        size = 0;
    }


    @Override
    public void add(int value) {
        if (size == elements.length - 1) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[++size] = value;
        int index = size;
        while (hasParent(index)) {
            int parent = parent(index);
            if (value < elements[parent]) {
               swap(index, parent);
               index = parent;
            } else {
                break;
            }
        }
    }

    @Override
    public Integer remove() {
        if (isEmpty()) {
            return null;
        }
        Integer value = elements[1];
        elements[1] = elements[size];
        elements[size] = 0;
        int index = 1;
        while (hasLeft(index)) {
            int leftIndex = left(index);
            int rightIndex = right(index);
            int childIndex = hasRight(index) && elements[rightIndex] < elements[leftIndex] 
                    ? rightIndex 
                    : leftIndex; 
            if (elements[index] <= elements[childIndex]) {
                break;
            }
            swap(index, childIndex);
            index = childIndex;
        }
        size--;
        return value;
    }

    @Override
    public Integer peak() {
        if (isEmpty()) {
            return null;
        }
        return elements[1];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }
    
}
