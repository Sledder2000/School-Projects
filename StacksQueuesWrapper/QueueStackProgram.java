package StacksQueuesWrapper;

import java.util.ArrayList;
import java.util.LinkedList;

public class QueueStackProgram {

    public final static int n = 100000000;
    static ArrayList<Integer> array = new ArrayList<>();
    static LinkedList<Integer> list = new LinkedList<>();
    static IntQueue qList = new IntQueue(list);
    static IntQueue qArray = new IntQueue(array);
    static IntStack sList = new IntStack(list);
    static IntStack sArray = new IntStack(array);

    public static void main(String[] args) {
        System.out.println(qList.randomTime(n));
        System.out.println(qArray.randomTime(n));
        System.out.println(sList.randomTime(n));
        System.out.println(sArray.randomTime(n));
    }
}