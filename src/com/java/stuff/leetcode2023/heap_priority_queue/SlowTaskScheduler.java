package com.java.stuff.leetcode2023.heap_priority_queue;

import java.util.*;

public class SlowTaskScheduler {

    class Task {
        Character c;
        int cooldown;
        int number;

        public Task(Character c) {
            this.c = c;
            this.number = 1;
            this.cooldown = 0;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "c=" + c +
                    ", cooldown=" + cooldown +
                    ", number=" + number +
                    '}';
        }
    }

    Set<Task> buildMapOfTasks(char[] tasks) {
        Map<Character, Task> onCooldown = new HashMap<>();
        for (char c : tasks) {
            if (onCooldown.containsKey(c)) {
                Task task = onCooldown.get(c);
                task.number += 1;
            } else {
                Task task = new Task(c);
                onCooldown.put(c, task);
            }
        }

        return new HashSet<>(onCooldown.values());
    }

    List<Task> pollTheHeap(PriorityQueue<Task> heap) {
        List<Task> polled = new ArrayList<>();
        boolean ready = false;
        while (!ready) {
            Task task = heap.poll();

            if (task == null)
                break;
            else {
                polled.add(task);
                if (task.cooldown == 0)
                    ready = true;
            }
        }

        return polled;
    }

    public int leastInterval(char[] tasks, int n) {
        Set<Task> onCooldown = buildMapOfTasks(tasks);

        //fill heap
        PriorityQueue<Task> heap = new PriorityQueue<>((x, y) -> y.number - x.number);
        heap.addAll(onCooldown);

        int i = 0;
        while (!heap.isEmpty()) {
            i++;
            for (Task t : onCooldown)
                if (t.cooldown >= 1)
                    t.cooldown--;

            List<Task> polled = pollTheHeap(heap);

            if (polled.isEmpty()) {
                System.out.println("iddle");
            } else {
                Task task = polled.get(polled.size() - 1);

                if (task.cooldown > 0) {
                    heap.addAll(polled);
                    System.out.println("iddle");
                } else {
                    task.number--;
                    System.out.println(task.c);
                    if (task.number > 0) {
                        task.cooldown = n + 1;
                    } else {
                        polled.remove(task);
                    }

                    heap.addAll(polled);
                }
            }
        }


        return i;
    }

    public static void main(String[] args) {
        SlowTaskScheduler ts = new SlowTaskScheduler();

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
