package com.java.stuff.concurrency;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;

public class Matrix {
	private static Map<Integer, Long> singleThreadedTimers = new TreeMap<>();
	private static Map<Integer, Long> concurrentTimers = new TreeMap<>();

	public static void main(String[] args) {
		long times = 50l;
		for (int i = 1; i < 10; i++)
			for (long j = 0l; j < times; j++)
				compareMultiplications((int) Math.pow(2, i));

		System.out.println("final reuslts:");
		for (Entry<Integer, Long> entry : singleThreadedTimers.entrySet())
			System.out.println("For single threaded " + entry.getKey() + " the value is " + entry.getValue() / times);
		for (Entry<Integer, Long> entry : concurrentTimers.entrySet())
			System.out.println("For concurrent " + entry.getKey() + " the value is " + entry.getValue() / times);

	}

	private static void compareMultiplications(int n) {
		System.out.println("Matrix multiplication for size : " + n);
		int[][] a;
		int[][] b;
		int[][] c;

		a = matrixGen(n);
		b = matrixGen(n);
		System.out.println("For single threaded calculation:");
		long startTime = System.nanoTime();
		c = matrixMult(a, b);
		startTime = printAndResetTime(startTime, n, singleThreadedTimers);

		c = new int[n][n];

		if (isValid(a, b)) {
			System.out.println("For concurrent calculation:");
			startTime = System.nanoTime();
			MatrixMultTask mainTask = new MatrixMultTask(a, 0, 0, b, 0, 0, c, 0, 0, n);
			ForkJoinPool forkJoinPool = new ForkJoinPool();
			forkJoinPool.invoke(mainTask);
			startTime = printAndResetTime(startTime, n, concurrentTimers);
		}
	}

	private static boolean isValid(int[][] a, int[][] b) {
		if (a.length != b.length)
			return false;

		return (a.length & (a.length - 1)) == 0;
	}

	private static int[][] matrixMult(int[][] a, int[][] b) {
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

	private static int[][] matrixGen(int n) {
		int[][] matrix = new int[n][n];
		Random r = new Random();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = r.nextInt(100);
			}
		}
		return matrix;
	}

	private static int[][] matrixOneGen(int n) {
		int[][] matrix = new int[n][n];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = 1;
			}
		}
		return matrix;
	}

	private static void printMatrix(int[][] m) {
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

	private static long printAndResetTime(long startTime, Integer key, Map<Integer, Long> map) {
		long time = System.nanoTime() - startTime;
		Long value = map.get(key);

		if (value == null)
			map.put(key, time);
		else
			map.put(key, time + value);

		System.out.println("Total time = " + time + " ns");
		return System.nanoTime();
	}

}
