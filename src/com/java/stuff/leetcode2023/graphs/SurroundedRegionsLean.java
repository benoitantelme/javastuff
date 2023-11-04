package com.java.stuff.leetcode2023.graphs;

public class SurroundedRegionsLean {

    public void solve(char[][] board) {
        // check safe positions
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O')
                bfs(board, i, 0);
            if (board[i][board[0].length - 1] == 'O')
                bfs(board, i, board[0].length - 1);
        }


        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O')
                bfs(board, 0, j);
            if (board[board.length - 1][j] == 'O')
                bfs(board, board.length - 1, j);
        }

        // capture surrounded
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == 'O')
                    board[i][j] = 'X';

        // flip safe positions
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == 'S')
                    board[i][j] = 'O';
    }

    void bfs(char[][] board, int i, int j) {
        if ((i >= 0 && i < board.length && j >= 0 && j < board[0].length)
                && board[i][j] == 'O') {
            board[i][j] = 'S';

            bfs(board, i + 1, j);
            bfs(board, i - 1, j);
            bfs(board, i, j + 1);
            bfs(board, i, j - 1);
        }
    }

    public static void main(String[] args) {
        SurroundedRegionsLean sr = new SurroundedRegionsLean();
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
