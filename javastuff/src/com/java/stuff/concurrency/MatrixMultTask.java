package com.java.stuff.concurrency;

import java.util.concurrent.RecursiveAction;

public class MatrixMultTask extends RecursiveAction
{
	private static final long serialVersionUID = -113831785630215695L;
	private final int[][] A;
	private final int aRow; // first row of current quadrant of A
	private final int aCol; // first column of current quadrant of A

	private final int[][] B;
	private final int bRow;
	private final int bCol;

	private final int[][] C;
	private final int cRow;
	private final int cCol;

	private final int size;

	MatrixMultTask(int[][] A, int aRow, int aCol, int[][] B, int bRow, int bCol, int[][] C, int cRow, int cCol, int size) {
		this.A = A;
		this.aRow = aRow;
		this.aCol = aCol;
		this.B = B;
		this.bRow = bRow;
		this.bCol = bCol;
		this.C = C;
		this.cRow = cRow;
		this.cCol = cCol;
		this.size = size;
	}

	void multiply() {
		for (int j = 0; j < size; j += 2) {
			for (int i = 0; i < size; i += 2) {
				int[] a0 = A[aRow + i];
				int[] a1 = A[aRow + i + 1];

				int s00 = 0;
				int s01 = 0;
				int s10 = 0;
				int s11 = 0;

				for (int k = 0; k < size; k += 2) {
					int[] b0 = B[bRow + k];
					s00 += a0[aCol + k] * b0[bCol + j];
					s10 += a1[aCol + k] * b0[bCol + j];
					s01 += a0[aCol + k] * b0[bCol + j + 1];
					s11 += a1[aCol + k] * b0[bCol + j + 1];

					int[] b1 = B[bRow + k + 1];
					s00 += a0[aCol + k + 1] * b1[bCol + j];
					s10 += a1[aCol + k + 1] * b1[bCol + j];
					s01 += a0[aCol + k + 1] * b1[bCol + j + 1];
					s11 += a1[aCol + k + 1] * b1[bCol + j + 1];
				}

				C[cRow + i][cCol + j] += s00;
				C[cRow + i][cCol + j + 1] += s01;
				C[cRow + i + 1][cCol + j] += s10;
				C[cRow + i + 1][cCol + j + 1] += s11;
			}
		}
	}

	@Override
	protected void compute() {
		if (size <= 4) {
			multiply();
		}

		else {
			int h = size / 2;

			invokeAll(new RecursiveAction[] { new Sequence(new MatrixMultTask(A, aRow, aCol,    // A11
					B, bRow, bCol,    // B11
					C, cRow, cCol,    // C11
					h), new MatrixMultTask(A, aRow, aCol + h,  // A12
					B, bRow + h, bCol,    // B21
					C, cRow, cCol,    // C11
					h)),

			new Sequence(new MatrixMultTask(A, aRow, aCol,    // A11
					B, bRow, bCol + h,  // B12
					C, cRow, cCol + h,  // C12
					h), new MatrixMultTask(A, aRow, aCol + h,  // A12
					B, bRow + h, bCol + h,  // B22
					C, cRow, cCol + h,  // C12
					h)),

			new Sequence(new MatrixMultTask(A, aRow + h, aCol,    // A21
					B, bRow, bCol,    // B11
					C, cRow + h, cCol,    // C21
					h), new MatrixMultTask(A, aRow + h, aCol + h,  // A22
					B, bRow + h, bCol,    // B21
					C, cRow + h, cCol,    // C21
					h)),

			new Sequence(new MatrixMultTask(A, aRow + h, aCol,    // A21
					B, bRow, bCol + h,  // B12
					C, cRow + h, cCol + h,  // C22
					h), new MatrixMultTask(A, aRow + h, aCol + h,  // A22
					B, bRow + h, bCol + h,  // B22
					C, cRow + h, cCol + h,  // C22
					h)) });
		}

	}

	public class Sequence extends RecursiveAction
	{
		private static final long serialVersionUID = 5543551767476712700L;
		private final MatrixMultTask first;
		private final MatrixMultTask second;

		public Sequence(final MatrixMultTask first, final MatrixMultTask second) {
			this.first = first;
			this.second = second;
		}

		@Override
		protected void compute() {
			first.compute();
			second.compute();
		}
	}

}
