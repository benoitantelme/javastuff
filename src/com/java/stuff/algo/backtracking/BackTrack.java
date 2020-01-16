package com.java.stuff.algo.backtracking;

public class BackTrack {


    static class KnightTour {
        static int size = 8;
        static int[][] board = new int[size][size];

        // knight moves on x, y
        static int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        static int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        /**
         * @param x
         * @param y
         * @param xmove
         * @param ymove
         * @return an empty array if the move does not work, otherwise [nextX, nextY]
         */
        static int[] validateMove(int x, int y, int xmove, int ymove) {
            int xmoved = x + xmove;
            int ymoved = y + ymove;

            if (xmoved < 0 || xmoved > xMove.length - 1 || ymoved < 0 || ymoved > yMove.length - 1
                    || board[xmoved][ymoved] != -1)
                return new int[0];

            return new int[]{xmoved, ymoved};
        }

        /**
         * @param x     position on x
         * @param y     position on y
         * @param moves number of move
         * @return if the move works
         */
        static boolean tryMove(int x, int y, int moves) {
            if (moves == size * size)
                return true;

            for (int i = 0; i < xMove.length; i++) {
                int[] nextMove = validateMove(x, y, xMove[i], yMove[i]);

                if (nextMove.length != 0) {
                    board[nextMove[0]][nextMove[1]] = moves;
                    if (tryMove(nextMove[0], nextMove[1], moves + 1))
                        return true;
                    else
                        board[nextMove[0]][nextMove[1]] = -1;
                }
            }

            return false;
        }


        static int[][] solve() {
            for (int x = 0; x < size; x++)
                for (int y = 0; y < size; y++)
                    board[x][y] = -1;

            int x = 0, y = 0;
            // knight is initially on the first cell
            board[x][y] = 0;

            if (tryMove(x, y, 1))
                return board;
            else
                return null;
        }
    }

    static class NQueens {
        int n;
        int[][] board;

        public NQueens(int n) {
            this.n = n;
            board = new int[n][n];
        }

        private boolean isValid(int x, int y){
            int i, j;

            // up
            for(i = 0 ; i < x; i ++)
                if(board[i][y] == 1)
                    return false;

            // left up
            for(i = x, j = y; i > -1 && j > -1 ; i--, j--)
                if(board[i][j] == 1)
                    return false;

            // right up
            for(i = x, j = y; i < n && j < n; i++, j++)
                if(board[i][j] == 1)
                    return false;

            return true;
        }

        private boolean tryRow(int row, int previousColumn) {
            if(row == n)
                return true;

            for(int i = 0; i < n; i++){
                if(isValid(row, i)){
                    board[row][i] = 1;
                    if(tryRow(row+1, i))
                        return true;
                    else
                        board[row][i] = 0;
                }
            }

            return false;
        }

        int[][] solve() {
            if (tryRow(0, 0))
                return board;
            else
                return null;
        }

    }

    static void printMatrix(int[][] mtr) {
        if (mtr != null) {
            for (int i = 0; i < mtr.length; i++) {
                for (int j = 0; j < mtr.length; j++)
                    System.out.print(mtr[i][j] + " ");
                System.out.println();
            }
        } else
            System.out.println("No solution");
    }

    public static void main(String args[]) {
        System.out.println("Knight tour: ");
        int[][] solution = KnightTour.solve();
        printMatrix(solution);

        System.out.println("\nN queens 4: ");
        NQueens nq = new NQueens(4);
        solution = nq.solve();
        printMatrix(solution);

        System.out.println("\nN queens 5: ");
        nq = new NQueens(5);
        solution = nq.solve();
        printMatrix(solution);
    }

}