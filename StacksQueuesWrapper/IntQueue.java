package StacksQueuesWrapper;
import java.util.List;

public class IntQueue {
    

    List<Integer> queueList;

    public IntQueue(List<Integer> list) {
        this.queueList = list;
    }


    public void addint(int i) {
        queueList.add(i);
    }

    public int remove() {
        return queueList.remove(0);
    }

    public int peek() {
        return queueList.get(0);
    }

    public long randomTime(int n) {
        IntQueue q = new IntQueue(queueList);
        long time = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int t = (int) Math.random() * n + 1;
            q.addint(t);
            q.remove();
        }
        return System.nanoTime() - time;
    }
}
