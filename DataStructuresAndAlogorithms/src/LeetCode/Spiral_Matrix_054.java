package LeetCode;

import java.util.*;

public class Spiral_Matrix_054 {
	/*
	 * Given an m x n matrix, return all elements of the matrix in spiral order.
	 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]	Output: [1,2,3,6,9,8,7,4,5]
	 */
	public void spiralOrder(int[][] matrix) {
		int rowD=0;
		int colL=0;
		int rowU=matrix.length-1;
		int colR=matrix[0].length-1;
		int count =0;
		while (count<matrix.length*matrix[0].length){
			for (int i=colL;i<=colR-1;i++){
				System.out.print(matrix[rowD][i]);
				count++;
			}
			for (int i=rowD;i<=rowU-1;i++){
				System.out.print(matrix[i][colR]);
				count++;
			}
			for (int i=colR;i>=colL+1;i--){
				System.out.print(matrix[rowU][i]);
				count++;
			}
			for (int i=rowU;i>=rowD+1;i--){
				System.out.print(matrix[i][colL]);
				count++;
			}
			if(rowD==rowU && colR==colL){
				System.out.print(matrix[rowD][colL]);
				count++;
			}
			rowD++;
			colR--;
			colL++;
			rowU--;
		}
	}

	public static void main(String args[]) {
		Spiral_Matrix_054 ob = new Spiral_Matrix_054();
		int[][] scores = new int[3][3];
		scores[0] = new int[]{1,2,3};
		scores[1] = new int[]{4,5,6};
		scores[2] = new int[]{7,8,9};
		ob.spiralOrder(scores);
		int[][] scores2 = new int[3][4];
		scores2[0] = new int[]{1,2,3,4};
		scores2[1] = new int[]{5,6,7,8};
		scores2[2] = new int[]{9,10,11,12};
		System.out.println("2nd set of data");
		ob.spiralOrder(scores2);
	}

	public List<Integer> spiralOrderAccepted(int[][] mat) {
		// Define ans list to store the result.
		List<Integer> ans = new ArrayList<>();
		int n = mat.length; // no. of rows
		int m = mat[0].length; // no. of columns
		// Initialize the pointers required for traversal.
		int top = 0, left = 0, bottom = n - 1, right = m - 1;
		// Loop until all elements are not traversed.
		while (top <= bottom && left <= right) {
			// For moving left to right
			for (int i = left; i <= right; i++)
				ans.add(mat[top][i]);
			top++;
			// For moving top to bottom.
			for (int i = top; i <= bottom; i++)
				ans.add(mat[i][right]);
			right--;
			// For moving right to left.
			if (top <= bottom) {
				for (int i = right; i >= left; i--)
					ans.add(mat[bottom][i]);
				bottom--;
			}
			// For moving bottom to top.
			if (left <= right) {
				for (int i = bottom; i >= top; i--)
					ans.add(mat[i][left]);
				left++;
			}
		}
		return ans;
	}
}
