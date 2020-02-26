package com.java.stuff.leetcode.medium;

import java.util.*;

public class ArrayMedium {

    public int countSquares(int[][] matrix) {
        int res = 0;

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != 0 && j != 0 && matrix[j][i] == 1) {
                    // 2 for the first bloc + 3 in a 4 blocs square
                    // 3 for the first, 6 for the 3 blocs next + 5 for a 9 blocs square
                    // rewriting the value to be reused for mins
                    int min = Math.min(
                            Math.min(matrix[j - 1][i], matrix[j - 1][i - 1]),
                            matrix[j][i - 1]) + 1;
                    matrix[j][i] = min;
                }

                res += matrix[j][i];
            }
        }

        return res;
    }

    public int countBattleships(char[][] board) {
        int result = 0;

        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[0].length; i++) {
                if (board[j][i] == 'X') {
                    if (j > 0 && board[j - 1][i] == 'X')
                        continue;

                    while (i + 1 < board[j].length && board[j][i] == 'X')
                        i++;

                    result++;
                }
            }
        }

        return result;
    }

    private void helpColOrRow(List<List<Integer>> set, int[] king, int[] queen, int rowcol) {
        if (!set.isEmpty()) {
            ListIterator<List<Integer>> iter = set.listIterator();
            while (iter.hasNext()) {
                List<Integer> oldQ = iter.next();
                if (set.size() == 1) {
                    //one side found
                    if ((oldQ.get(rowcol) > king[rowcol] && queen[rowcol] > king[rowcol])
                            || (oldQ.get(rowcol) < king[rowcol] && queen[rowcol] < king[rowcol])) {
                        if (Math.abs(king[rowcol] - queen[rowcol]) < Math.abs(king[rowcol] - oldQ.get(rowcol))) {
                            iter.remove();
                            iter.add(List.of(queen[0], queen[1]));
                            break;
                        }
                    } else {
                        // add the other side
                        set.add(List.of(queen[0], queen[1]));
                        break;
                    }
                } else {
                    // both sides already found check only the same side
                    if ((oldQ.get(rowcol) > king[rowcol] && queen[rowcol] > king[rowcol])
                            || (oldQ.get(rowcol) < king[rowcol] && queen[rowcol] < king[rowcol])) {
                        if (Math.abs(king[rowcol] - queen[rowcol]) < Math.abs(king[rowcol] - oldQ.get(rowcol))) {
                            iter.remove();
                            iter.add(List.of(queen[0], queen[1]));
                            break;
                        }
                    }
                }
            }

        } else
            set.add(List.of(queen[0], queen[1]));
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();

        List<List<Integer>> row = new ArrayList<>();
        List<List<Integer>> column = new ArrayList<>();
        List<List<Integer>> diagonal = new ArrayList<>();
        List<List<Integer>> diagonal2 = new ArrayList<>();

        for (int[] queen : queens) {
            if (queen[0] == king[0]) {
                helpColOrRow(row, king, queen, 1);
            } else if (queen[1] == king[1]) {
                helpColOrRow(column, king, queen, 0);
            } else if (queen[0] - king[0] == queen[1] - king[1]) {
                helpColOrRow(diagonal, king, queen, 0);
            }else if (queen[0] - king[0] == -(queen[1] - king[1])) {
                helpColOrRow(diagonal2, king, queen, 1);
            }

        }

        result.addAll(row);
        result.addAll(column);
        result.addAll(diagonal);
        result.addAll(diagonal2);
        return result;
    }

    public static void main(String[] args) {
        ArrayMedium am = new ArrayMedium();
        System.out.println(am.countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        }));

        char[][] table = new char[][]{
                {'X', '.', '.', 'X' },
                {'.', '.', '.', 'X' },
                {'.', '.', '.', 'X' }
        };
        System.out.println(am.countBattleships(table));


        System.out.println(am.queensAttacktheKing(new int[][]
                {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}}, new int[]{0, 0}));
        System.out.println(am.queensAttacktheKing(new int[][]
        {{0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}, {4, 2}}, new int[]{3,3}));

    }

}
