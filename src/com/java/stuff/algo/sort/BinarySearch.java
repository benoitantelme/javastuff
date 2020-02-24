package com.java.stuff.algo.sort;

public class BinarySearch {
    int[] array;

    public BinarySearch(int[] array) {
        this.array = array;
    }

    int findValue(int value, int min, int max) {
        if(min > max)
            return -1;
        int middle = (min+max)/2;

        if(array[middle] < value)
            return findValue(value, middle+1, max);
        else if(array[middle] > value)
            return findValue(value, min, middle-1);
        else
            return middle;
    }

    int indexOf(int value) {
        return findValue(value, 0, array.length-1);
    }

    public static void main(String args[])
    {
        BinarySearch bs = new BinarySearch(
                new int[]{ 2, 3, 4, 10, 22, 40, 55, 67, 71, 72, 73, 79, 81, 99, 100, 111, 112, 113, 114, 156, 178, 520, 1203, 65888 });

        System.out.println("Index of 81 is: " + bs.indexOf(81));
        System.out.println("Index of 3 is: " + bs.indexOf(3));
        System.out.println("Index of -2 is: " + bs.indexOf(-2));
    }

}
