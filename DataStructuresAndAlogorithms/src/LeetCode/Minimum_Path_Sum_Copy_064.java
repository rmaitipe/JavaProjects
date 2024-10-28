package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimum_Path_Sum_Copy_064 {
	/*
	 * AMZN bulb question, if sum of bulbs before it is equal or greater than it the bulb is switched off.
	 * which combination gives the minimum number of bulbs witched off
	 * Q1 does switched off still count :(
	 */

	static int min=Integer.MAX_VALUE;

	public static void main(String args[]) {
		Minimum_Path_Sum_Copy_064 ob = new Minimum_Path_Sum_Copy_064();
		int[]scores = new int[] {1,3,4,2,3,1,2,1,2};
		ob.backTrack(new ArrayList<>(),scores,0,0);
		System.out.println(min);//recursion will have duplicated method calls
    }

	private void backTrack(List<Integer> temp, int[]scores, int sum,int count) {
		System.out.println("sum: "+sum+": length: "+ scores.length);
		if (temp.size()== scores.length) {
			//min=Math.min(count,min);
			if (count<min){
				min=count;
			}
		} else{
			for (int j=0;j<scores.length;j++) {
				if (!temp.contains(j)) {
					if (sum<scores[j]) {
						temp.add(j);
						backTrack(temp, scores, sum + scores[j],count);
						temp.removeLast();
					}else{
						temp.add(j);
						backTrack(temp, scores, sum,count+1);
						temp.removeLast();
					}
				}
			}
		}
	}

	private int jumpMaxPathSum(int[] num,int maxSteps) {
		int[] visited=new int [num.length];
		Arrays.fill(visited,1,num.length,Integer.MAX_VALUE);
		for (int i=0;i<num.length;i++){
			if (i+num[i]<num.length){
				int idx= Math.min(i+num[i],num.length-1);
				for (int j=i+1;j<=idx;j++){
					visited[j]=Math.min(visited[j],visited[i]+1);
				}
			}
		}
		return visited[num.length-1];
	}

}
