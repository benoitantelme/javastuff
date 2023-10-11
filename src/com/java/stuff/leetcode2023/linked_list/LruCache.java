package com.java.stuff.leetcode2023.linked_list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LruCache {
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    private LinkedList<int[]> ll;
    private Map<Integer, int[]> map;
    private int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.ll = new LinkedList<>();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int[] obj = map.get(key);
            ll.remove(obj);
            ll.add(obj);
            return obj[1];
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            int[] newEntry = new int[]{key, value};
            int[] oldEntry = map.get(key);
            ll.remove(oldEntry);
            oldEntry[1] = newEntry[1];
            ll.add(oldEntry);
        } else {
            if (ll.size() >= capacity) {
                int[] oldestEntry = ll.removeFirst();
                map.remove(oldestEntry[0]);
            }

            int[] newEntry = new int[]{key, value};
            ll.add(newEntry);
            map.put(newEntry[0], newEntry);
        }
    }

}


