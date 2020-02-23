package com.java.stuff.leetcode.hard;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    // more than median
    PriorityQueue<Integer> minh;
    //less than median
    PriorityQueue<Integer> maxh;

    /** initialize your data structure here. */
    public MedianFinder() {
        minh = new PriorityQueue<>();
        maxh = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if(num > findMedian())
            minh.add(num);
        else
            maxh.add(num);

        if(Math.abs(minh.size() - maxh.size()) > 1){
            if(minh.size() > maxh.size()){
                int tmp = minh.poll();
                maxh.add(tmp);
            }else{
                int tmp = maxh.poll();
                minh.add(tmp);
            }
        }
    }

    public double findMedian() {
        if(minh.size() > maxh.size()){
            return  minh.peek();
        }else if(minh.size() < maxh.size()){
            return maxh.peek();
        }else{
            if(minh.isEmpty() || maxh.isEmpty())
                return 0;
            return ((double) (minh.peek()+maxh.peek()))/2;
        }
    }

    public static void main(String[] args){
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian()); // -> 1.5
        mf.addNum(3);
        System.out.println(mf.findMedian()); // -> 2
    }

}
