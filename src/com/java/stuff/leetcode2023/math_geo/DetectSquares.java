package com.java.stuff.leetcode2023.math_geo;

import java.util.*;

public class DetectSquares {

    Map<Point, Integer> map;

    public DetectSquares() {
        map = new HashMap<>();
    }

    public void add(int[] point) {
        map.merge(new Point(point[0], point[1]), 1, (o, n) -> o + n);
    }

    public int count(int[] point) {
        int result = 0;
        for (Map.Entry<Point, Integer> entry : map.entrySet()) {
            Point a = entry.getKey();
            if (Math.abs(point[0] - a.x) != Math.abs(point[1] - a.y) || point[0] == a.x || point[1] == a.y)
                continue;

            Point b = new Point(a.x, point[1]);
            Point c = new Point(point[0], a.y);
            if (map.containsKey(b) && map.containsKey(c))
                result += 1 * map.get(a) * map.get(b) * map.get(c);
        }

        return result;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {
        DetectSquares obj = new DetectSquares();
        obj.add(new int[]{3, 10});
        obj.add(new int[]{11, 2});
        obj.add(new int[]{3, 2});
        System.out.println(obj.count(new int[]{11, 10}));
        System.out.println(obj.count(new int[]{14, 8}));
        obj.add(new int[]{11, 2});
        System.out.println(obj.count(new int[]{11, 10}));

    }


}
