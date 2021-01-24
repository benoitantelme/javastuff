package com.java.stuff.leetcode.medium;

import java.util.*;

public class TaskScheduler {


    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();

        if (ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0) != 6)
            System.out.println("wrong");

        if (ts.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2) != 8)
            System.out.println("wrong");

        if (ts.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2) != 16)
            System.out.println("wrong");
    }

    class Task implements Comparable<Task> {
        char c;
        int times;
        int cd;

        public Task(char c, int times) {
            this.c = c;
            this.times = times;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return c == task.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }


        @Override
        public int compareTo(Task o) {
            return  o.times - times;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "c=" + c +
                    ", times=" + times +
                    ", cd=" + cd +
                    '}';
        }
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Task> map = new HashMap<>();

        for (char c : tasks) {
            if (map.containsKey(c))
                map.get(c).times++;
            else
                map.put(c, new Task(c, 1));
        }

        PriorityQueue<Task> queue = new PriorityQueue<>();
        queue.addAll(map.values());
        int res = 0;

        while (!map.isEmpty()) {
            // update colldowns
            for (Task t : map.values())
                if (t.cd > 0)
                    t.cd--;

            Set<Task> waiting = new HashSet<>();
            while (true) {
                if (!queue.isEmpty()) {
                    Task t = queue.poll();
                    if (t.cd > 0)
                        waiting.add(t);
                    else {
                        if (t.times > 1) {
                            t.times--;
                            t.cd = n+1;
                            queue.add(t);
                        } else {
                            map.remove(t.c);
                        }

                        queue.addAll(waiting);
                        res++;
                        break;
                    }
                }else{
                    queue.addAll(waiting);
                    res++;
                    break;
                }
            }
        }

        return res;
    }

}
