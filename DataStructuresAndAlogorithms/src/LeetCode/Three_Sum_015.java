package LeetCode;

import java.util.*;

public class Three_Sum_015 {
	/*
	 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
	 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
	 * Notice that the solution set must not contain duplicate triplets.
	 * Input: nums = [-1,0,1,2,-1,-4]	Output: [[-1,-1,2],[-1,0,1]] Explanation: [0],[1],[2] = (-1) + 0 + 1 = 0.
	 * [1],[2],[4] = 0 + 1 + (-1) = 0.	[0],[3],[4] = (-1) + 2 + (-1) = 0. The distinct triplets are [-1,0,1] & [-1,-1,2].
	 *
	 * Can be reduced to -a= b + c,
	 * Incomplete - Sort Array first for better performing algorithms
	 */
	private void sumMethod(int[] arr) {//O(n2)
		for (int i=0;i< arr.length-2;i++){
			int[] val=twoSumExtend(arr,-arr[i],i);
			if (val!=null){
				System.out.println(Arrays.toString(val) +"," +arr[i]);
			}
		}
	}

	public int[] twoSumExtend(int arr[],int k,int offset){
		int[] retVal=null;
		Map<Integer,Integer> myMap =new HashMap<>();
		for (int j=offset+1;j<arr.length;j++) {
			if (myMap.containsKey(k-arr[j])){
				Integer idx=myMap.get(k-arr[j]);
				retVal= new int[]{arr[j],arr[idx]};
				break;
			} else{
				myMap.put(arr[j],j);
			}
		}
		return retVal;
	}

	public static void main(String args[]) {
		int arr[] = {23, 34, 15, 12, 22, -10, -5};
		Three_Sum_015 ob = new Three_Sum_015();
		ob.sumMethod(arr);
		System.out.println(ob.threeSumAccepted(arr));
		int arr2[] = {-1,0,1,2,-1,-4};
		ob.sumMethod(arr2);
		System.out.println(ob.threeSumAccepted(arr2));
	}

	/*
	The input array nums is sorted in ascending order. Sorting is essential for the two pointers approach.
	The code uses two pointers (j and k) to iterate through the array while checking for triplets that sum up to zero.
	The outer loop (i) iterates through each element as a potential first element of the triplet.
	The while loop continues until the two pointers meet. The sum of the current triplet is calculated at each iteration.

	If the sum is zero, the triplet is added to a HashSet to ensure uniqueness.
	If the sum is less than zero, the left pointer (j) is moved to the right.
	If the sum is greater than zero, the right pointer (k) is moved to the left.
    Time complexity: nlogn->Sorting  O(n log n + n^2) ≈ O(n^2)	Space complexity: O(n) due to HashMap
	*/
	public List<List<Integer>> threeSumAccepted(int[] nums) {
		int target = 0;
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		Set<List<Integer>> set = new HashSet<>();
		for(int i=0; i<nums.length; i++){
			int j = i+1;
			int k = nums.length - 1;
			while(j<k){
				int sum = nums[i]+nums[j]+nums[k];
				if(sum == target){
					set.add(Arrays.asList(nums[i],nums[j],nums[k]));
					/*
					// Skip duplicate elements for j
					while (j < k && nums[j] == nums[j + 1]) {
						j++;
					}
					// Skip duplicate elements for k
					while (j < k && nums[k] == nums[k - 1]) {
						k--;
					}
					*/
					j++;
					k--;
				}else if(sum < target){
					j++;
				}else{
					k--;
				}
			}
		}
		ans.addAll(set);
		return ans;
	}
}
