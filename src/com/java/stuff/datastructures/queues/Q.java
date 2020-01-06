package com.java.stuff.datastructures.queues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q {

    /**
     * @param pages ordered in the way they arrive
     * @param capacity of the memory
     * @return the number of page faults, when the memory does not contain a new page after initialization
     */
    static int pageFaults(int pages[], int capacity){
        int pageFaults = 0;

        Queue<Integer> memory = new LinkedList<>();
        for(int i = 0; i < capacity; i++)
            memory.add(pages[i]);

        for(int i = capacity; i < pages.length; i++){
            int pageNumber = pages[i];
            if(!memory.contains(pageNumber)){
                pageFaults += 1;
                memory.remove();
                memory.add(pageNumber);
            }
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4,
                2, 3, 0, 3, 2};

        System.out.println("For pages: " + Arrays.toString(pages));
        System.out.println("Number of page faults with 4 pages in memory: " + pageFaults(pages, 4));
    }

}
