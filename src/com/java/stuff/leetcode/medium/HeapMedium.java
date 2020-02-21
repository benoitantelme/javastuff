package com.java.stuff.leetcode.medium;

import java.util.*;

public class HeapMedium {


    public static int[][] kClosest(int[][] points, int K) {
        HashMap<Double, List<Integer>> distanceToIndex = new HashMap<>();
        PriorityQueue<Double> heap = new PriorityQueue<>();

        for(int i = 0; i < points.length; i++){
            int[] point = points[i];
            double distance = Math.sqrt(point[0]*point[0]+point[1]*point[1]);
            heap.add(distance);
            List<Integer> l = distanceToIndex.getOrDefault(distance, new ArrayList<>());
            l.add(i);
            distanceToIndex.put(distance, l);
        }

        int[][] result = new int[K][];
        int i = 0;
        while(i < K){
            for(int index : distanceToIndex.get(heap.poll())){
                if(i >= K)
                    break;
                result[i] = points[index];
                i++;
            }
        }

        return result;
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurencesToValue = new HashMap<>();
        List<Integer> tmp = new ArrayList<>();

        for(int num : nums){
            occurencesToValue.put(num, occurencesToValue.getOrDefault(num, 0)+1);
            if(!tmp.contains(num))
                tmp.add(num);
        }

        Collections.sort(tmp, (Integer a, Integer b) -> Integer.compare(occurencesToValue.get(b), occurencesToValue.get(a)));
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < k; i++)
            result.add(tmp.get(i));

        return result;
    }

    public static void main(String[] args){
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{1,3},{-2,2}}, 1)));
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2)));

        System.out.println(topKFrequent( new int[]{1,1,1,2,2,3}, 2));
        System.out.println(topKFrequent(new int[]{2,3,4,1,4,0,4,-1,-2,-1}, 2));
        System.out.println(topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2));

    }


}
