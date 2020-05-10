package com.java.stuff.leetcode.challenge.twenty.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindTheTownJudge {

    public int findJudge(int N, int[][] trust) {
        if(N == 1 && trust.length == 0)
            return 1;

        Map<Integer, Integer> map = new HashMap<>();
        boolean[] trustSomeone = new boolean[N];
        List<Integer> candidates = new ArrayList<>();

        for(int[] trustPair : trust){
            trustSomeone[trustPair[0]-1] = true;
            Integer updated = map.compute(trustPair[1], (k, v) -> map.containsKey(k) ? map.get(k) + 1 : 1);

            if(updated == N-1)
                candidates.add(trustPair[1]);
        }

        for(Integer candidate : candidates)
            if(!trustSomeone[candidate-1])
                return candidate;

        return -1;
    }

    public int findJudgeBetter(int N, int[][] trust) {
        int[] indegree = new int[N + 1];
        int[] outdegree = new int[N + 1];

        for(int i = 0; i < trust.length; i++){
            outdegree[trust[i][0]]++;
            indegree[trust[i][1]]++;
        }

        for(int i = 1; i <= N; i++){
            if(indegree[i] == N - 1 && outdegree[i] == 0){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FindTheTownJudge fttj = new FindTheTownJudge();
        System.out.println(fttj.findJudge(2, new int[][]{{1, 2}}));
        System.out.println(fttj.findJudge(3, new int[][]{{1,3},{2,3}}));
        System.out.println(fttj.findJudge(3, new int[][]{{1,3},{2,3},{3,1}}));
        System.out.println(fttj.findJudge(4, new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}}));

    }

}
