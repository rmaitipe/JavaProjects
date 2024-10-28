package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subarray_Sum_Equals_K_560 {
	/*
	 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
	 * A subarray is a contiguous non-empty sequence of elements within an array.
	 * Input: nums = [1,2,3], k = 3	Output: 2 [1,2] & [3]
	 * Input: nums = [1,1,1], k = 2	Output: 2 [1,1] & [1,1]
	 */
	public static void main(String args[]) {
		Subarray_Sum_Equals_K_560 ob = new Subarray_Sum_Equals_K_560();
		int[] nums = {1,2,3};
		System.out.println(ob.subarraySum(nums,3));
		System.out.println(ob.subarraySumAccepted(nums,3));
	}

	public List<List<Integer>> subarraySum(int[] nums,int k) {
		List<List<Integer>> list = new ArrayList<>();
		//Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0,k);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start,int remaining){
		if (remaining==0) {
			list.add(new ArrayList<>(tempList));
		}
		if (tempList.size()!=nums.length) {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, i + 1, remaining - nums[i]); // not i because duplicates
				tempList.remove(tempList.size() - 1);
			}
		}
	}
/*

The approach used in this solution is to use a HashMap, map, to store the cumulative sum of the elements in the array
and the number of times that cumulative sum has been seen so far. The cumulative sum of the elements in a subarray
can be represented as the difference between the cumulative sum of the elements in the array up to the end of the
subarray and the cumulative sum of the elements in the array up to the start of the subarray.

The function starts by initializing a variable sum to keep track of the cumulative sum of the elements in the array,
and a variable ans to keep track of the number of subarrays that add up to k. The function also adds an entry to map
with key 0 and value 1, which represents the cumulative sum of an empty subarray, which is 0.

The function then iterates over each element in the array, adding the current element to the cumulative sum, and
checking if the cumulative sum minus k is in map. If it is, the function adds the value of that key in map to the
count of subarrays that add up to k, as that represents the number of times that cumulative sum has been seen so far,
and therefore the number of subarrays that end at the current element and add up to k.

Finally, the function adds the current cumulative sum to map, with a value of 1 if it is the first time that cumulative
sum has been seen, or increments the value of that key by 1 if it has been seen before.
 */
	public int subarraySumAccepted(int[] nums, int k) {
		int sum = 0;
		int ans = 0;
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(0,1);
		for(int j=0;j<nums.length;j++){
			sum += nums[j];
			if(map.containsKey(sum -k)){
				ans += map.get(sum-k);
			}
			map.put(sum,map.getOrDefault(sum,0)+1);
		}
		return ans;
	}

}
