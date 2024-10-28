package LeetCode;

import java.util.Arrays;

public class Minimum_Path_Sum_064 {
	/*
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
	 * which minimizes the sum of all numbers along its path.
	 * Note: You can only move either down or right at any point in time.
	 * Input:grid=[[1,3,1],[1,5,1],[4,2,1]]	 Output:7	Explanation:Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum
	 */
    static int min=Integer.MAX_VALUE;

	public static void main(String args[]) {
		Minimum_Path_Sum_064 ob = new Minimum_Path_Sum_064();
		int[][] scores = new int[3][3];
		scores[0] = new int[]{1,3,1};
		scores[1] = new int[]{1,5,1};
		scores[2] = new int[]{4,2,1};
		//scores[0][0] = 1;		scores[0][1] = 3;		scores[0][2] = 1;
		//scores[1][0] = 1;		scores[1][1] = 5;		scores[1][2] = 1;
		//scores[2][0] = 4;		scores[2][1] = 2;		scores[2][2] = 1;
		System.out.println(ob.minPathSumRecursive(0,0,scores));//recursion will have duplicated method calls
		System.out.println(ob.minPathSumDPAccepted(scores));
		ob.minPathBasic(0,0,scores,0);
		System.out.println(min);
    }

	private void minPathBasic(int x,int y,int[][]scores,int sum) {
		if (x == scores.length-1 && y == scores[0].length-1) {
			min= Math.min(sum+scores[x][y],min);
		} else {
			if (x+1<scores.length){
				minPathBasic(x+1,y,scores,sum+scores[x+1][y]);
			}  if (y+1<scores[0].length){
				minPathBasic(x,y+1,scores,sum+scores[x][y+1]);
			}
		}
	}

	private int minPathSumRecursive(int x,int y,int[][]scores) {
		if (x == scores.length-1 && y == scores[0].length-1) {
			return scores[x][y];
		} else{
			int right=Integer.MAX_VALUE;
			int down=Integer.MAX_VALUE;
			if (x+1<scores.length){
				right=minPathSumRecursive(x+1,y,scores);
			}
			if (y+1<scores[0].length){
				down=minPathSumRecursive(x,y+1,scores);
			}
			return Math.min(right,down)+scores[x][y];
		}
	}

	public int minPathSumDPAccepted(int[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		int[][] dp=new int[m][n];
		for(int i=0; i<m; i++){
			Arrays.fill(dp[i],-1);
		}
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				int one=Integer.MAX_VALUE;
				int two=Integer.MAX_VALUE;
				if(i==j && i==0){
					dp[0][0]=grid[0][0];
					continue;
				}
				if(i>0) one=dp[i-1][j]+grid[i][j];
				if(j>0) two=dp[i][j-1]+grid[i][j];
				dp[i][j]=Math.min(one,two);
			}
		}
		return dp[m-1][n-1];
	}

}
