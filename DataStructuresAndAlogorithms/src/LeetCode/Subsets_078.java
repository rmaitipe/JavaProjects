package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Subsets_078 {
	/*
	 * Given an integer array nums of unique elements, return all possible subsets (the power set).
	 * The solution set must not contain duplicate subsets. Return the solution in any order.
	 * Input: nums = [1,2,3]	Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
	 */
	public static void main(String args[]) {
		Subsets_078 ob = new Subsets_078();
		int[] nums = {1,2,3};
		System.out.println(ob.combinationSumAccepted(nums));
	}

	public List<List<Integer>> combinationSumAccepted(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		//Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i+1); // not i because duplicates
			tempList.remove(tempList.size() - 1);
		}
	}

}
