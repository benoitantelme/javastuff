package com.java.stuff.leetcode2023.backtracking;

public class WordSearch {
    String word;

    boolean rec(char[][] board, boolean[][] visited, int i, int j, String w) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || visited[i][j])
            return false;

        w += board[i][j];
        if (w.charAt(w.length() - 1) != word.charAt(w.length() - 1))
            return false;
        if (w.equals(word))
            return true;

        boolean result = false;
        visited[i][j] = true;
        result = result || rec(board, visited, i + 1, j, w);
        result = result || rec(board, visited, i, j + 1, w);
        result = result || rec(board, visited, i - 1, j, w);
        result = result || rec(board, visited, i, j - 1, w);
        visited[i][j] = false;

        return result;
    }

    public boolean exist(char[][] board, String word) {
        this.word = word;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (rec(board, visited, i, j, ""))
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        System.out.println(ws.exist(new char[][]{
                {'A', 'B', 'C', 'D'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCCED"));
        System.out.println(ws.exist(new char[][]{
                {'A', 'B', 'C', 'D'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "SEE"));
        System.out.println(ws.exist(new char[][]{
                {'A', 'B', 'C', 'D'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCB"));

//        Example 1:
//        Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
//        Output: true
//
//        Example 2:
//        Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
//        Output: true
//
//        Example 3:
//        Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
//        Output: false
    }

}
