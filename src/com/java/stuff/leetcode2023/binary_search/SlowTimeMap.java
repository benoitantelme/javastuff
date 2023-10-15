package com.java.stuff.leetcode2023.binary_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
public class SlowTimeMap {

    class Tuple {
        int timestamp;
        String value;

        public Tuple(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    Map<String, List<Tuple>> map;

    public SlowTimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Tuple(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (map.containsKey(key)) {

            List<Tuple> list = new ArrayList<>(map.get(key));

            int l = 0;
            int r = list.size() - 1;
            int tmp = -1;

            while (l <= r) {
                int p = (l + r) / 2;
                if (list.get(p).timestamp > timestamp) {
                    r = p - 1;
                } else {
                    tmp = Math.max(tmp, p);
                    l = p + 1;
                }
            }
            return tmp != -1 ? map.get(key).get(tmp).value : "";

        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        SlowTimeMap tb = new SlowTimeMap();


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
