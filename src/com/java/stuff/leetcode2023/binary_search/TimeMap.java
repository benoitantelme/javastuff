package com.java.stuff.leetcode2023.binary_search;

import java.util.*;

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
public class TimeMap {

    private HashMap<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>()).
                put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key))
            return "";

        TreeMap<Integer, String> treemap = map.get(key);
        Integer floorTimestamp = treemap.floorKey(timestamp);
        return floorTimestamp == null ? "" : treemap.get(floorTimestamp);
    }

    public static void main(String[] args) {
        TimeMap tb = new TimeMap();


//        Example 1:
//        Input
//                ["TimeMap", "set", "get", "get", "set", "get", "get"]
//                [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
//        Output
//                [null, null, "bar", "bar", null, "bar2", "bar2"]
//
//        Explanation
//        TimeMap timeMap = new TimeMap();
//        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
//        timeMap.get("foo", 1);         // return "bar"
//        timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
//        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
//        timeMap.get("foo", 4);         // return "bar2"
//        timeMap.get("foo", 5);         // return "bar2"
    }
}
