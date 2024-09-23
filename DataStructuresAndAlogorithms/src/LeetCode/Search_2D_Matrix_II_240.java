package LeetCode;

public class Search_2D_Matrix_II_240 {

	/*
	 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
	 * This matrix has the following properties:
	 * Integers in each row are sorted in ascending from left to right.
	 * Integers in each column are sorted in ascending from top to bottom.
	 */

	public static void main(String args[]) {
		Search_2D_Matrix_II_240 ob = new Search_2D_Matrix_II_240();
		int[][] scores = new int[4][4];
		scores[0][0] = 1;
		scores[0][1] = 4;
		scores[0][2] = 7;
		scores[1][0] = 2;
		scores[1][1] = 5;
		scores[1][2] = 8;
		scores[2][0] = 3;
		scores[2][1] = 6;
		scores[2][2] = 9;
    }

	public void setZeroesAccepted(int[][] matrix) {
		boolean fr = false,fc = false;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) {
					if(i == 0) fr = true;
					if(j == 0) fc = true;
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[0].length; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}}
		}
		if(fr) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}
		if(fc) {
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}
}
