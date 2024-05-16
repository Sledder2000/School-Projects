package PriorityQueues;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapPriorityQueue <E extends Comparable<E>> implements PriorityQueue<E> {

    private E[] elements;
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
        E temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }
    //#endregion - helper methods

    @SuppressWarnings("unchecked")
    public HeapPriorityQueue(Class elementClass) {
        elements = /*(E[])(new Object[10])*/ (E[])Array.newInstance(elementClass, 10);
        size = 0;
    }


    @Override
    public void add(E value) {
        if (size == elements.length - 1) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[++size] = value;
        int index = size;
        while (hasParent(index)) {
            int parent = parent(index);
            if (elements[index].compareTo(elements[parent]) < 0) {
               swap(index, parent);
               index = parent;
            } else {
                break;
            }
        }
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        E value = elements[1];
        elements[1] = elements[size];
        elements[size] = null;
        int index = 1;
        while (hasLeft(index)) {
            int leftIndex = left(index);
            int rightIndex = right(index);
            int childIndex = hasRight(index) && (elements[rightIndex].compareTo(elements[leftIndex]))  < 1
                    ? rightIndex 
                    : leftIndex; 
            if (elements[index].compareTo(elements[childIndex]) <= 0) {
                break;
            }
            swap(index, childIndex);
            index = childIndex;
        }
        size--;
        return value;
    }

    @Override
    public E peak() {
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
