package com.java.stuff.concurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Matrix
{

	public static void main(String[] args) {
		int[][] a = new int[2][2];
		a[0][0] = 1;
		a[0][1] = 2;
		a[1][0] = 3;
		a[1][1] = 4;

		int[][] b = new int[2][2];
		b[0][0] = 2;
		b[0][1] = 0;
		b[1][0] = 1;
		b[1][1] = 2;

		int[][] c = matrixSimpleMult(a, b);
		printMatrix(c);

		a = matrixGen(32);
		b = matrixGen(32);
		c = matrixSimpleMult(a, b);
		printMatrix(c);
		
		int n = 32;
//		a = matrixOneGen(n);
//		b = matrixOneGen(n);
		c = new int[n][n];

		if (isValid(a, b)) {
			MatrixMultTask mainTask = new MatrixMultTask(a, 0, 0, b, 0, 0, c, 0, 0, n);
			ForkJoinPool forkJoinPool = new ForkJoinPool();
      forkJoinPool.invoke(mainTask);
			
      System.out.println();
      printMatrix(c);
		}

	}

	public static boolean isValid(int[][] a, int[][] b) {
		if (a.length != b.length)
			return false;

		return (a.length & (a.length - 1)) == 0;
	}

	public static int[][] matrixSimpleMult(int[][] a, int[][] b) {
		int[][] c = new int[a.length][b[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return c;
	}

	public static void printMatrix(int[][] m) {
		System.out.println("Matrix[" + m.length + "][" + m[0].length + "]");
		int rows = m.length;
		int columns = m[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.printf("%4d ", m[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int[][] matrixGen(int n) {
		int[][] matrix = new int[n][n];
		Random r = new Random();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = r.nextInt(100);
			}
		}
		return matrix;
	}

	public static int[][] matrixOneGen(int n) {
		int[][] matrix = new int[n][n];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = 1;
			}
		}
		return matrix;
	}

}
