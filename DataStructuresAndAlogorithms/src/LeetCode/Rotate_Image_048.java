package LeetCode;

import java.util.Arrays;

public class Rotate_Image_048 {
	/*
	 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
	 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
	 * DO NOT allocate another 2D matrix and do the rotation.
	 *
	 * See Accepted for Transposing a 2D array
	 */
	public static void main(String args[]) {
		Rotate_Image_048 ob = new Rotate_Image_048();
		int[][] scores = new int[3][3];
		scores[0] = new int[]{1,2,3};
		scores[1] = new int[]{4,5,6};
		scores[2] = new int[]{7,8,9};
		System.out.println(Arrays.deepToString(ob.rotateSquare(scores)));
		int[][] scores2 = new int[3][3];
		scores2[0] = new int[]{1,2,3};
		scores2[1] = new int[]{4,5,6};
		scores2[2] = new int[]{7,8,9};
		ob.rotateAccepted(scores2);
		System.out.println(Arrays.deepToString(scores2));
    }

	private int[][] rotateSquare(int[][] matrix) {
		int side=matrix.length;
		int rowD = 0;
		int colL = 0;
		int rowU = side - 1;
		int colR = side - 1;
		int count = 0;
		while (count < matrix.length * matrix[0].length) {
			int[] temp= Arrays.copyOf(matrix[rowD],side);
			for (int i = rowU; i >= rowD + 1; i--) {
				System.out.print(matrix[i][colL]);
				matrix[rowD][rowU-i]=matrix[i][colL];
				count++; //741
			}
			for (int i = colR; i >= colL + 1; i--) {//2
				System.out.print(matrix[rowU][i]);
				matrix[i][colL]=matrix[rowU][i];
				count++;
			}
			for (int i = rowD; i <= rowU - 1; i++) {//3
				System.out.print(matrix[i][colR]);
				matrix[rowU][rowU-i]=matrix[i][colR];
				count++;
			}
			for (int i = colL; i <= colR - 1; i++) {//4
				System.out.print(matrix[rowD][i]);
				matrix[i][colR]=temp[i];
				count++;
			}
			if (rowD == rowU && colR == colL) {
				count++;
			}
			rowD++;
			colR--;
			colL++;
			rowU--;
		}
		return matrix;
	}

	/* 2 steps for the algo:
	 * Step1: Transpose the matrix -> make row as col , col as row
	 * step 2: reverse the transposed matrix row by row for getting the ans
	 	1,2,3 After Transpose => 	1,4,7  After swapping the columns =>7,4,1
	 	4,5,6						2,5,8								8,5,2
		7,8,9						3,6,9								9,6,3
	 */
	public void rotateAccepted(int[][] matrix) {    // ....step1
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {
				int temp  = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		for (int i = 0; i < matrix.length; i++) {   // ....step2
			for (int j = 0; j < matrix.length / 2; j++) {
				int temp  = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1 - j];
				matrix[i][matrix.length - 1 - j] = temp;
			}
		}
	}

}
