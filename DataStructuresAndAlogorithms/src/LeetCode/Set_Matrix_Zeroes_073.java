package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Set_Matrix_Zeroes_073 {

	/*
	 * Given an integer array nums and an integer k, return the kth largest element in the array.
	 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
	 * Can you solve it without sorting?
	 */
	private void modifyMatrix(int mat [][]){
		int row[]= new int [mat.length];
		int col[]= new int [mat[0].length];
		Arrays.fill(row,1);
		Arrays.fill(col,1);
        /*
        int i, j;
        for (i = 0; i < row.length; i++)
            row[i] = 1;
        for (i = 0; i < col.length; i++)
            col[i] = 1;
         */
		/* Store the rows and columns to be marked as 0 in row[] and col[] arrays respectively */
		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < col.length; j++) {
				if (mat[i][j] == 0) {
					row[i] = 0;
					col[j] = 0;
				}
			}
		}
		/* Modify the input matrix mat[] using the above constructed row[] and col[] arrays */
		for (int i = 0; i < row.length; i++) {
			if ( row[i] == 0 ) {
				nullifyRow(mat,i);
			}
		}
		for (int j = 0; j < col.length; j++) {
			if ( col[j] == 0 ) {
				nullifyCol(mat,j);
			}
		}
		System.out.println("modifyMatrix complete");
	}

	private void nullifyCol(int[][] mat, int j) {
		for (int i= 0; i < mat.length; i++) {
			mat[i][j] = 0;
		}
	}

	private void nullifyRow(int[][] mat, int i) {
		for (int j = 0; j < mat[0].length; j++) {
			mat[i][j] = 0;
		}
	}

	public static void main(String args[]) {
		Set_Matrix_Zeroes_073 ob = new Set_Matrix_Zeroes_073();
		int[][] scores = new int[3][3];
		scores[0][0] = 1;
		scores[0][1] = 1;
		scores[1][0] = 1;
		scores[1][1] = 1;
		scores[2][2] = 1;
		scores[1][2] = 1;
		scores[2][1] = 1;
 	    ob.modifyMatrix(scores);
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
