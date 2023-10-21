package com.java.stuff.leetcode2023.heap_priority_queue;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int max = 0;
        int maxCount = 0;

        for (char c: tasks){
            count[c-'A']++;
            max = Math.max(count[c-'A'], max);
        }

        for (int i: count){
            if (i == max)
                maxCount++;
        }

        return Math.max(tasks.length, (max-1) * (n+1) + maxCount);
    }

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();

        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
        System.out.println(ts.leastInterval(
                new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));

//        Example 1:
//        Input: tasks = ["A","A","A","B","B","B"], n = 2
//        Output: 8
//        Explanation:
//        A -> B -> idle -> A -> B -> idle -> A -> B
//        There is at least 2 units of time between any two same tasks.
//
//        Example 2:
//        Input: tasks = ["A","A","A","B","B","B"], n = 0
//        Output: 6
//        Explanation: On this case any permutation of size 6 would work since n = 0.
//                ["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//        And so on.
//
//                Example 3:
//        Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//        Output: 16
//        Explanation:
//        One possible solution is
//        A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
    }

}
