package LeetCode;

import java.util.*;

public class Partition_Equals_Subset_Sum_416 {
	/*
	 * Given an integer array nums, return true if you can partition the array into two subsets such that the
	 * sum of the elements in both subsets is equal or false otherwise.
	 * Input: nums = [1,5,11,5]	Output: true	The array can be partitioned as [1, 5, 5] and [11].
	 *
	 * Integer[] what = Arrays.stream(nums).boxed().toArray( Integer[]::new);
	 */
	public static void main(String args[]) {
		Partition_Equals_Subset_Sum_416 ob = new Partition_Equals_Subset_Sum_416();
		int[] nums = {1, 5, 11, 5};
		Integer[] what = Arrays.stream(nums).boxed().toArray( Integer[]::new);
		System.out.println(ob.canPartition(what));
	}
/*
	if sum is odd return false;
	sort
	pick largest element see if can be made from rest of elements
		Subarray_Sum_Equals_K_560
	repeat untill list is empty
 */
	public boolean canPartition(Integer[] nums) {
		Arrays.sort(nums, Collections.reverseOrder());
		List<Integer> allList= (LinkedList<Integer>) Arrays.stream(nums).toList();//
		List<Integer> list = new ArrayList<>();    //
		boolean retVal=false;
		int i=0;
		while (!list.isEmpty()){
			list = subarraySum(Arrays.copyOfRange(nums,i,nums.length),list.getFirst());
			allList.removeAll(list);
			allList.remove(nums[i]);
			list=allList;
			i++;
		}
		return retVal;
	}

	public List<Integer> subarraySum(Integer[] nums, int k) {
		List<Integer> list = new ArrayList<>();
		//Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0,k);
		return list;
	}

	private void backtrack(List<Integer> list, List<Integer> tempList, Integer[] nums, int start, int remaining){
		if (remaining==0) {
			list=tempList;
			return;
		}
		for(int i = start; i < nums.length; i++){
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i+1,remaining-nums[i]); // not i because duplicates
			tempList.remove(tempList.size() - 1);
		}
	}

}
