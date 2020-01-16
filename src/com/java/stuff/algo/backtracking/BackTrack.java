package com.java.stuff.algo.backtracking;

public class BackTrack {


    static class KnightTour {
        static int size = 8;
        static int[][] board = new int[size][size];

        // knight moves on x, y
        static int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        static int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        static void printBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }
        }

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

            if (xmoved < 0 || xmoved > xMove.length-1 || ymoved < 0 || ymoved > yMove.length-1
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
                    if(tryMove(nextMove[0], nextMove[1], moves+1))
                        return true;
                    else
                        board[nextMove[0]][nextMove[1]] = -1;
                }
            }

            return false;
        }


        static void solve() {
            for (int x = 0; x < size; x++)
                for (int y = 0; y < size; y++)
                    board[x][y] = -1;

            int x = 0, y = 0;
            board[x][y] = 0;

            if (tryMove(x, y, 1))
                printBoard();
            else
                System.out.println("No solution");


        }
    }


    public static void main(String args[]) {
        KnightTour.solve();
    }

}