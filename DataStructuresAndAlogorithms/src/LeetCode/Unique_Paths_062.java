package LeetCode;

public class Unique_Paths_062 {
	/*
	 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e. grid[0][0]).
	 * The robot tries to move to the bottom-right corner (i.e. grid[m - 1][n - 1]).
	 * The robot can only move either down or right at any point in time.
	 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach
	 * the bottom-right corner.
	 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
	 * Input: m = 3, n = 7		Output: 28
	 */

	public static void main(String args[]) {
		Unique_Paths_062 ob = new Unique_Paths_062();
		int[][] scores = new int[3][7];
		ob.uniquePathSumBasic(0,0,scores);
		System.out.println(ob.count);//recursion
		System.out.println(ob.uniquePathSumRecursion(0,0,scores));//recursion
		System.out.println(ob.uniquePathsDPAccepted(scores.length,scores[0].length));
    }
	int count=0;

	private void uniquePathSumBasic(int x,int y,int[][]scores) {
		if (x == scores.length-1 && y == scores[0].length-1) {
			count++;
		} else {
			if (x+1<scores.length && y+1<scores[0].length){
				uniquePathSumBasic(x,y+1,scores);
				uniquePathSumBasic(x+1,y,scores);
			} else if (x+1<scores.length){
				uniquePathSumBasic(x+1,y,scores);
			} else if (y+1<scores[0].length){
				uniquePathSumBasic(x,y+1,scores);
			}
		}
	}


	private int uniquePathSumRecursion(int x, int y, int[][]scores) {
		int retVal=0;
		if (x == scores.length-1 && y == scores[0].length-1) {
			return 1;
		} else {
			if (x+1<scores.length){
				retVal+= uniquePathSumRecursion(x+1,y,scores);
			}
			if (y+1<scores[0].length){
				retVal+= uniquePathSumRecursion(x,y+1,scores);
			}
		}
		return retVal;
	}

	/*
	 * The idea behind this approach is to use a 2D array to store the number of unique paths to each cell.
	 * A cell (i,j) can be reached either from (i−1,j) or (i,j−1), so unique paths to (i,j) is the sum of recursionthese two cells.
	 * Time Complexity: O(m×n) — We iterate through each cell once.	Space Complexity: O(m×n) — For the DP array.
	 */
	public int uniquePathsDPAccepted(int m, int n) {
		int [][]dp=new int [m][n];
		for(int i=0;i<dp[0].length;i++){
			dp[0][i]=1;
		}
		for(int j=0;j<dp.length;j++){
			dp[j][0]=1;
		}
		for(int i=1;i<dp.length;i++){
			for(int j=1;j<dp[0].length;j++){
				dp[i][j]=dp[i-1][j]+dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}

}
