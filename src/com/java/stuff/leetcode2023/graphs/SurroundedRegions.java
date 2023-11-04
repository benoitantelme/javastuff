package com.java.stuff.leetcode2023.graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SurroundedRegions {

    Set<List<Integer>> saved;

    public void solve(char[][] board) {
        saved = new HashSet<>();

        for (int i = 0; i < board.length; i++)
            if (board[i][0] == 'O')
                saved.add(List.of(i, 0));

        for (int i = 0; i < board.length; i++)
            if (board[i][board[0].length - 1] == 'O')
                saved.add(List.of(i, board[0].length - 1));

        for (int j = 0; j < board[0].length; j++)
            if (board[0][j] == 'O')
                saved.add(List.of(0, j));

        for (int j = 0; j < board[0].length; j++)
            if (board[board.length - 1][j] == 'O')
                saved.add(List.of(board.length - 1, j));

        for (List<Integer> pos : new HashSet<>(saved)) {
            Set<List<Integer>> news = new HashSet<>();
            news.add(List.of(pos.get(0), pos.get(1)));
            saved.addAll(bfs(board, news, pos.get(0), pos.get(1)));
        }

        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !saved.contains(List.of(i, j)))
                    board[i][j] = 'X';
            }
        }
    }

    Set<List<Integer>> bfs(char[][] board, Set<List<Integer>> news, int i, int j) {
        if (!news.contains(List.of(i + 1, j)) && isValid(board, i + 1, j) && board[i + 1][j] == 'O') {
            news.add(List.of(i + 1, j));
            news.addAll(bfs(board, news, i + 1, j));
        }
        if (!news.contains(List.of(i - 1, j)) && isValid(board, i - 1, j) && board[i - 1][j] == 'O') {
            news.add(List.of(i - 1, j));
            news.addAll(bfs(board, news, i - 1, j));
        }
        if (!news.contains(List.of(i, j + 1)) && isValid(board, i, j + 1) && board[i][j + 1] == 'O') {
            news.add(List.of(i, j + 1));
            news.addAll(bfs(board, news, i, j + 1));
        }
        if (!news.contains(List.of(i, j - 1)) && isValid(board, i, j - 1) && board[i][j - 1] == 'O') {
            news.add(List.of(i, j - 1));
            news.addAll(bfs(board, news, i, j - 1));
        }

        return news;
    }

    boolean isValid(char[][] in, int i, int j) {
        return i > 0 &&
                i < in.length &&
                j > 0 &&
                j < in[0].length;
    }

    public static void main(String[] args) {
        SurroundedRegions sr = new SurroundedRegions();
        char[][] in = new char[][]{{'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        sr.solve(in);
        System.out.println();

        in = new char[][]{{'X'}};
        sr.solve(in);
        System.out.println();

        in = new char[][]{{'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}};
        sr.solve(in);
        System.out.println();

//        Example 1:
//        Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
//        Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//        Explanation: Notice that an 'O' should not be flipped if:
//        - It is on the border, or
//        - It is adjacent to an 'O' that should not be flipped.
//                The bottom 'O' is on the border, so it is not flipped.
//        The other three 'O' form a surrounded region, so they are flipped.
//
//                Example 2:
//        Input: board = [["X"]]
//        Output: [["X"]]

    }

}
