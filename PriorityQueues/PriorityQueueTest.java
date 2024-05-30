package PriorityQueues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PriorityQueueTest {
    @Test
    public void testHeapIntPriorityQueue() {
        HeapIntPriorityQueue pq = new HeapIntPriorityQueue();
        assertEquals(0, pq.size());
        assertNull(pq.peak());
        assertTrue(pq.isEmpty());
        pq.add(6);
        assertEquals(1, pq.size());
        assertEquals(6, (int)pq.peak());
        pq.add(4);
        assertEquals(2, pq.size());
        assertEquals(4, (int)pq.peak());
        pq.add(10);
        assertEquals(3, pq.size());
        assertEquals(4, (int)pq.peak());
        assertFalse(pq.isEmpty());
        assertEquals(4, (int)pq.remove());
        assertEquals(2, pq.size());
        assertEquals(6, (int)pq.remove());
        assertEquals(10, (int)pq.remove());
        assertNull(pq.remove());
        assertTrue(pq.isEmpty());
    }
    @Test
    public void testHeapPriorityQueue() {
        HeapPriorityQueue<Point> pq = new HeapPriorityQueue<Point>(Point.class);
        assertEquals(0, pq.size());
        Point p1 = new Point(1, 2);
        pq.add(p1);
        assertEquals(1, pq.size());
        Point expected = new Point(1, 2);
        //assertEquals(expected, pq.peak());
        assertEquals(0, expected.compareTo(pq.peak()));
        assertEquals(p1, pq.peak());
        Point expected2 = new Point(3, 3);
        Point expected3 = new Point(0, 4);
        pq.add(expected2);
        pq.add(expected3);
        assertEquals(3, pq.size());
        assertEquals(p1, pq.peak());
        assertEquals(p1, pq.remove());
        assertEquals(expected3, pq.peak());
        assertEquals(2, pq.size());




    }
    public static void main(String[] args) {
    }
}
