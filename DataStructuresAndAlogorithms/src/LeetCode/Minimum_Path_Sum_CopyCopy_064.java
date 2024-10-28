package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimum_Path_Sum_CopyCopy_064 {
	/*
	 * Find maximum sum, move by maxSteps
	 */
	public static void main(String args[]) {
		Minimum_Path_Sum_CopyCopy_064 ob = new Minimum_Path_Sum_CopyCopy_064();
		int[]scores = new int[] {1,3,11,4,7,9,1,2};
		int maxSteps=3;
		int sum=0;
		List<List<Integer>> list=new ArrayList<>();
		List<Integer> temp=new ArrayList<>();
		temp.add(scores[0]);
		ob.backTrack(list,temp,scores,maxSteps,sum,0);
		System.out.println(sum);//recursion will have duplicated method calls
    }

	private void backTrack(List<List<Integer>> list,List<Integer> temp, int[]scores,int maxSteps, int sum,int idx) {
		System.out.println("idx: "+idx+": length: "+ scores.length);
		if (idx>= scores.length-1) {
			list.add(temp);
			System.out.println("EXIT");
		} else{
			for (int j=idx+1;j<=idx+1+maxSteps;j++) {
				if (j<scores.length) {
					temp.add(scores[j]);
					backTrack(list,temp,scores,maxSteps,sum+scores[j],j);
					temp.remove(temp.size()-1);
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
