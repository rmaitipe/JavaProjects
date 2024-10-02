package LeetCode;

public class Search_2D_Matrix_II_240 {
	/*
	 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
	 * This matrix has the following properties:Integers in each row are sorted in ascending from left to right.
	 * Integers in each column are sorted in ascending from top to bottom.
	 * Input:matrix=[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target=5 Output:true
	 */
	private boolean search2D(int matrix [][], int target){
		int row=matrix.length;
		int col = matrix[0].length;
		int downRow=0; int upRow=row-1;
		int leftCol=0; int rightCol=col-1;
		while (matrix[upRow][leftCol]>target && matrix[upRow][rightCol]>target){
			upRow--;
		}
		while (matrix[downRow][leftCol]<target && matrix[downRow][rightCol]<target){
			downRow++;
		}
		while (matrix[downRow][leftCol]>target && matrix[upRow][leftCol]>target){
			leftCol++;
		}
		while (matrix[downRow][rightCol]>target && matrix[upRow][rightCol]>target){
			rightCol--;
		}
		for (int i=downRow;i<=upRow;i++){
			for (int j=leftCol;j<=rightCol;j++){
				if (matrix[i][j]==target){
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String args[]) {
		Search_2D_Matrix_II_240 ob = new Search_2D_Matrix_II_240();
		int[][] scores = new int[5][5];
		scores[0] = new int[]{1,4,7,11,15};
		scores[1] = new int[]{2,5,8,12,19};
		scores[2] = new int[]{3,6,9,16,22};
		scores[3] = new int[]{10,13,14,17,24};
		scores[4] = new int[]{18,21,23,26,30};
		System.out.println(ob.search2D(scores,5));
	}
}
