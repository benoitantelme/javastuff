package com.java.stuff.leetcode2023.advanced_graphs;

import java.util.*;

public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adjacency = new HashMap<>();
        for (List<String> ticket : tickets) {
            adjacency.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            adjacency.get(ticket.get(0)).add(ticket.get(1));
        }

        LinkedList<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String tmp = stack.peek();
            if (adjacency.get(tmp) != null && !adjacency.get(tmp).isEmpty())
                stack.push(adjacency.get(tmp).poll());
            else
                result.addFirst(stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();

        List<List<String>> input = List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        System.out.println(ri.findItinerary(input));

        input = List.of(List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"), List.of("ATL", "JFK")
                , List.of("ATL", "SFO"));
        System.out.println(ri.findItinerary(input));

        input = List.of(List.of("JFK", "KUL"), List.of("JFK", "NRT"), List.of("NRT", "JFK"));
        System.out.println(ri.findItinerary(input));

//        Example 1:
//        Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//        Output: ["JFK","MUC","LHR","SFO","SJC"]
//
//        Example 2:
//        Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
    }

    public List<String> findItineraryFailBecauseNoBacktracking(List<List<String>> tickets) {
        String jfk = "JFK";
        Map<String, List<String>> adjacency = new HashMap<>();
        for (List<String> ticket : tickets) {
            adjacency.putIfAbsent(ticket.get(0), new ArrayList<>());
            adjacency.get(ticket.get(0)).add(ticket.get(1));
        }

        //lexical order
        for (List<String> neighbours : adjacency.values()) {
            neighbours.sort(Comparator.reverseOrder());
        }


        List<String> result = new ArrayList<>();
        Stack<String> queue = new Stack<>();
        queue.add(jfk);
        while (!queue.isEmpty()) {
            String tmp = queue.pop();
            if (adjacency.containsKey(tmp))
                for (String neighbour : adjacency.get(tmp))
                    queue.add(neighbour);

            // cleanup and update result
            adjacency.put(tmp, new ArrayList<>());
            result.add(tmp);
        }

        return result;
    }


}
