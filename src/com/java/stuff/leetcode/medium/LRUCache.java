package com.java.stuff.leetcode.medium;

import java.util.*;

public class LRUCache {
    Set<Integer> set;
    LinkedList<Item> queue;
    int max;

    public LRUCache(int capacity) {
        queue = new LinkedList();
        set = new HashSet<>();
        max = capacity;
    }

    public int get(int key) {
        if (set.contains(key)) {
            Item found = null;
            Iterator<Item> it = queue.iterator();
            while (it.hasNext()) {
                Item current = it.next();
                if (current.key == key) {
                    found = current;
                    break;
                }
            }

            queue.remove(found);
            queue.add(found);

            return found.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Item item = new Item(key, value);

        if (set.contains(item.key)) {
            queue.remove(item);
            queue.add(item);
        } else {
            if (queue.size() == max) {
                Item last = queue.remove();
                set.remove(last.key);
            }
            set.add(item.key);
            queue.add(item);
        }
    }

    class Item {
        int key;
        int value;

        public Item(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return key == item.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }


    public static void main(String[] args){
        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4



        cache = new LRUCache( 2 /* capacity */ );

        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));       // returns 2
    }

}
