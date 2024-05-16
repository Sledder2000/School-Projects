package StacksQueuesWrapper;
import java.util.List;

public class IntStack {
    
    List<Integer> stackList;

    public IntStack(List<Integer> array) {
        this.stackList = array;
    }

    public void push(int i) {
        stackList.add(i);
    }

    public int pop() {
        return stackList.remove(0);
    }

    public int peek() {
        return stackList.get(0);
    }

    public long randomTime(int n) {
        IntStack s = new IntStack(stackList);
        long time = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int t = (int) Math.random() * n + 1;
            s.push(t);
            s.pop();
        }
        return System.nanoTime() - time;
    }
}
