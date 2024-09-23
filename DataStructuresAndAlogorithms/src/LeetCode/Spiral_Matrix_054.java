package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Spiral_Matrix_054 {

	/*
	 * Given an m x n matrix, return all elements of the matrix in spiral order.
	 */

	public static void main(String args[]) {
		Spiral_Matrix_054 ob = new Spiral_Matrix_054();
		int[][] scores = new int[3][3];
		scores[0][0] = 1;
		scores[0][1] = 4;
		scores[0][2] = 7;
		scores[1][0] = 2;
		scores[1][1] = 5;
		scores[1][2] = 8;
		scores[2][0] = 3;
		scores[2][1] = 6;
		scores[2][2] = 9;
		System.out.println(ob.spiralOrder(scores));
    }

	public List<Integer> spiralOrder(int[][] matrix) {
		return null;
	}

	public List<Integer> spiralOrderAccepted(int[][] mat) {
		List<Integer> ans = new ArrayList<>();
		int n = mat.length; // no. of rows
		int m = mat[0].length; // no. of columns
		// Initialize the pointers required for traversal.
		int top = 0, left = 0, bottom = n - 1, right = m - 1;
		// Loop until all elements are not traversed.
		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++) // For moving left to right
				ans.add(mat[top][i]);
			top++;
			for (int i = top; i <= bottom; i++)// For moving top to bottom.
				ans.add(mat[i][right]);
			right--;
			if (top <= bottom) {// For moving right to left.
				for (int i = right; i >= left; i--)
					ans.add(mat[bottom][i]);

				bottom--;
			}
			if (left <= right) {// For moving bottom to top.
				for (int i = bottom; i >= top; i--)
					ans.add(mat[i][left]);
				left++;
			}
		}
		return ans;
	}
}
