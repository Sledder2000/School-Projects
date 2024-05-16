package PriorityQueues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntPriorityQueuesTest {
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

    public static void main(String[] args) {
    }
}
