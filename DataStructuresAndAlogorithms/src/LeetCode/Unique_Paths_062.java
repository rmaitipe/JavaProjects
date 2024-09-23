package LeetCode;

import java.util.Arrays;

public class Unique_Paths_062 {

	/*
	 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
	 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
	 * The robot can only move either down or right at any point in time.
	 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach
	 * the bottom-right corner.
	 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
	 *
	 *
	 * 	111			111
	 *  1			123
	 *  1			1
	 */

	public static void main(String args[]) {
		Unique_Paths_062 ob = new Unique_Paths_062();
		int[][] scores = new int[3][3];
		ob.uniquePathSum(0,0,scores);
		System.out.println(ob.count);//recursion
		System.out.println(ob.uniquePathsDPAccepted(scores.length,scores[0].length));

    }
	int count=0;

	private void uniquePathSum(int x,int y,int[][]scores) {
		if (x == scores.length-1 && y == scores[0].length-1) {
			count++;
		} else {
			if (x+1<scores.length && y+1<scores[0].length){
				uniquePathSum(x,y+1,scores);
				uniquePathSum(x+1,y,scores);
			} else if (x+1<scores.length){
				uniquePathSum(x+1,y,scores);
			} else if (y+1<scores[0].length){
				uniquePathSum(x,y+1,scores);
			}
		}
	}


	/*
	Overall Functionality:
    The code breaks down the problem into smaller subproblems (finding paths to each cell) and utilizes memoization
    to store solutions for previously calculated subproblems.
    By building solutions for smaller subproblems and combining them, the code efficiently calculates the total number
    of unique paths for the entire grid.
    This approach avoids redundant calculations and improves the time complexity of the solution.
	 */
	public int uniquePathsDPAccepted(int m, int n) {
		if(m==1 && n==1){
			return 1;
		}
		int [][] dp=new int [m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				dp[i][j]=-1;
			}
		}
		solve(m - 1,n - 1,dp);
		return dp[m - 1][n - 1];
	}
	public int solve(int row , int col,int [][] dp){
		if(row==0 && col==0){
			return 1;
		}
		if(row<0 || col<0){
			return 0;
		}
		if(dp[row][col]>-1){//if value already calculated
			return dp[row][col];
		}
		int up=solve(row-1,col,dp);
		int left=solve(row,col-1,dp);
		return dp[row][col]=up+left;
	}

}
