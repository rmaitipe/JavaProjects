package LeetCode;

public class Search_2D_Matrix_074 {
	/*
	 * You are given an m x n integer matrix with the following two properties:
	 * Each row is sorted in non-decreasing order.
	 * The first integer of each row is greater than the last integer of the previous row.
	 * Given an integer target, return true if target is in matrix or false otherwise.
	 * You must write a solution in O(log(m * n)) time complexity.
	 */
	public static void main(String args[]) {
		Search_2D_Matrix_074 ob = new Search_2D_Matrix_074();
		int[][] scores = new int[3][4];
		scores[0] = new int[]{1,3,5,7};
		scores[1] = new int[]{10,11,16,20};
		scores[2] = new int[]{23,30,34,60};
/*		scores[0][0] = 1;		scores[0][1] = 3;		scores[0][2] = 5;		scores[0][3] = 7;
		scores[1][0] = 10;		scores[1][1] = 11;		scores[1][2] = 16;		scores[1][3] = 20;
		scores[2][0] = 23;		scores[2][1] = 30;		scores[2][2] = 34;		scores[2][3] = 60;*/
		System.out.println(ob.search2D(scores,5));
    }

	private boolean search2D(int[][] scores, int target) {
		int left=0;
		int row=scores.length;
		int col=scores[0].length;
		int right= row*col;
		while (left<=right){
			int mid=(left+right)/2;//9=>30
			int dx=mid/col;
			int dy=(mid-(dx*col))%row;
			System.out.println("Mid: "+mid+" MidVal: "+scores[dx][dy]+" Dx: "+dx+" Dy: "+dy);
			if (scores[dx][dy]<target){
				left=mid+1;
			} else if (scores[dx][dy]>target){
				right=mid-1;
			}else{
				return true;
			}
		}
		return false;
	}

}
