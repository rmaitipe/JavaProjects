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
		int[] nums1 = {1, 5, 11, 5,2,8,6}; // 11,8 || 5,5,1,2,6 [19]
		int[] nums2 = {1, 5, 11, 5,2,4,6};// 11,6 || 5,5,4,2,1 [17]
		Integer[] what = Arrays.stream(nums1).boxed().toArray( Integer[]::new);
		//System.out.println(ob.canPartition(what));
		System.out.println(ob.equalPartitionDPAccepted(nums2));
	}
/*
	if sum is odd return false;
	sort
	pick largest element see if can be made from rest of elements
		Subarray_Sum_Equals_K_560
	repeat until list is empty
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

	/*
	 We have to take care about only one subset because if one subset with weight sum/2 is possible then other
	 subset will surely have the weight sum/2. So now using subset sum problem code we have to just check if its
	 possible to have a subset having sum = totalSum/2;
	 */
	boolean equalPartitionDPAccepted(int[] nums) {
		int sum = 0;
		int n = nums.length;
		for (int i : nums) sum += i;
		if (sum % 2 != 0) return false;
		sum /= 2;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		for (int j : nums) {
			for (int i = sum; i > 0; i--) {
				if (i >= j) {
					dp[i] = dp[i] || dp[i - j];
				}
			}
		}
		return dp[sum];
	}
}
