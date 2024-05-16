package SimpleList;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import main.NumNode;

@SuppressWarnings({"rawtypes", "unused"})
public class SimpleList implements List {
    
    /**
     * SimpleNode definition of a single-linked list of Objects 
     */
    private class SimpleNode {
        private Object _data;
        private SimpleNode _next;
        
        private SimpleNode(Object data) {
            this(data,  null);
        }
        
        private SimpleNode(Object data, SimpleNode next) {
            _data = data;
            _next = next;
        }
    }
        private SimpleNode head;
        private SimpleNode tail;
        private int numNodes;
    /**
     * TODO: Class fields: Keep track of the head and tail of the list
     * TODO: and the number of nodes it contains.
     */
    public SimpleList() {
        head = null;
        tail = null;
        numNodes = 0;
        // TODO: Initialize class fields
    }

    /**
     * Appends the specified element to the end of this list (optional operation).
     * @param element - element to be appended to this list.
     * @return true
     */
    @Override
    public boolean add(Object element) {
        SimpleNode temp = new SimpleNode(element);
        if (tail == null) {
            tail = temp;
            head = temp;
            numNodes = 1;
        } else {
            tail._next = temp;
            tail = tail._next;
            numNodes++;
        }
        return true;
    }
    
    /**
     * Inserts the specified element at the specified position in this list.
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     */
    @Override
    public void add(int index, Object element) {
        SimpleNode temp = new SimpleNode(element);
        SimpleNode currant = head;
        int i = 0;
        if (tail == null) {
            tail = temp;
            head = temp;
        } else if (index == 0) {
           temp._next = head;
           head = temp;
        } else {
            while (i < index - 1) {
                i++;
                currant = currant._next;
            }
            if (currant._next == null) {
                currant._next = temp;
                tail = temp;
            } else {
                temp._next = currant._next;
                currant._next = temp;
            }
        }
        numNodes++;
    }
    
    /**
     * Removes all of the elements from this list (optional operation).
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        numNodes = 0;
    }
    
    /**
     * Returns the element at the specified position in this list.
     * @param index - index of the element to return.
     * @return the element at the specified position in this list.
     */
    @Override
    public Object get(int index) {
    SimpleNode n = head;
        for (int i = 0; i < index; i++){
            n = n._next;
        }
    return n._data;
    }
    
    /**
     * Removes the element at the specified position in this list.
     * @param index - the index of the element to be removed.
     * @return the element previously at the specified position.
     */
    @Override
    public Object remove(int index) {
        int i = 0;
        SimpleNode temp = head;
        numNodes--;
        if (index == 0) {
            head = head._next;
            return temp._data;
        }
        while (temp._next._next._next != null && i < index) {
            temp = temp._next;
            i++;
        }
        SimpleNode currant = temp._next;
        temp._next = temp._next._next;
        return currant._data;
    }


    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    @Override
    public int size() {
        int i = 0;
        SimpleNode temp = head;
        while (temp != null) {
            i++;
            temp = temp._next;
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    // #region: Overrides not supported by the SimpleList
    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    // #endregion: Overrides not supported by the SimpleList
}
