package com.java.stuff.leetcode.medium;

import java.util.*;

public class GraphMedium {

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i < rooms.size(); i++)
            set.add(i);

        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while(!queue.isEmpty()){
            Integer current = queue.poll();
            if(visited[current])
                continue;

            set.remove(current);
            visited[current] = true;

            if(!rooms.get(current).isEmpty())
                for(Integer room : rooms.get(current))
                    queue.add(room);

        }

        return set.isEmpty();
    }

    public static void main(String[] args){
        System.out.println(canVisitAllRooms(List.of(List.of(1), List.of(2), List.of(3), new ArrayList<>())));

        
    }


}
