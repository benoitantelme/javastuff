package com.java.stuff.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TwoCitySchedCost {
    TreeMap<Integer, List<Integer>> differenceToIndex = new TreeMap<>();
    int[] choice;

    private int toAdd(int[] candidate, int index, int low, int high){
        choice[index] = low;
        differenceToIndex.computeIfAbsent(candidate[high] - candidate[low], k -> new ArrayList<>()).add(index);

        return candidate[low];
    }
    private int cleanupDifference(int diff, int aOrB){
        int toRemove = 0;
        while (diff != 0) {
            Map.Entry<Integer, List<Integer>> entry = differenceToIndex.pollFirstEntry();
            for(Integer index : entry.getValue()) {
                if (choice[index] == aOrB && diff != 0) {
                    toRemove += entry.getKey();
                    diff--;
                }
            }
        }

        return toRemove;
    }
    public int twoCitySchedCost(int[][] costs) {
        choice = new int[costs.length];

        int i = 0;
        int sum = 0;
        int goingToB = 0;

        for (int[] candidate : costs) {
            if (candidate[0] <= candidate[1])
                sum += toAdd(candidate, i, 0, 1);
            else {
                sum += toAdd(candidate, i, 1, 0);
                goingToB++;
            }
            i++;
        }

        int diff = costs.length / 2 - goingToB;
        if (diff == 0)
            return sum;
        else
            sum += cleanupDifference(Math.abs(diff), diff > 0 ? 0 : 1);

        return sum;
    }

    public static void main(String args[]) {
        TwoCitySchedCost two = new TwoCitySchedCost();
//        System.out.println(two.twoCitySchedCost(new int[][]{{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}}));
//        System.out.println(two.twoCitySchedCost(new int[][]{{481,639},{846,131},{402,724},{151,820},{942,828},{604,465},{672,226},{11,190},{42,932},{753,451},{673,92},{196,261},{990,96},{767,820},
//                {734,539},{241,164},{43,281},{862,788},{394,6},{245,553},{779,408},{288,228},{412,17},{935,24},{894,302},{709,189},{734,602},{601,752},{774,820},
//                {783,480},{769,284},{741,515},{898,890},{46,623},{278,248},{634,33},{437,363},{140,210},{309,901},{347,220},{503,590},{345,505},{456,303},{799,597},
//                {961,45},{583,111},{320,572},{321,308},{805,361},{943,28},{22,31},{464,995},{779,35},{1000,345}}));
        System.out.println(two.twoCitySchedCost(new int[][]{{33,135},{849,791},{422,469},{310,92},{253,489},{995,760},{852,197},{658,216},{679,945},{197,341},{362,648},{22,324},{408,25},{505,734},{463,279},{885,512},
                {922,850},{784,500},{557,860},{528,367},{877,741},{554,545},{598,888},{558,104},{426,427},{449,189},{113,51},{201,221},{251,62},{981,897},{392,519},{115,70},
                {961,109},{512,678},{476,708},{28,902},{763,282},{787,774},{925,475},{253,532},{100,502},{110,857},{822,942},{231,186},{869,491},{651,344},{239,806},{651,193},
                {830,360},{427,69},{776,875},{466,81},{520,959},{798,775},{875,199},{110,396}}));
    }
}
