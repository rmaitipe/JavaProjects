package LeetCode;

import java.util.Arrays;

public class Find_First_and_Last_Position_of_Element_in_Sorted_Array_034 {

	/*
	 * Given an array of integers nums sorted in non-decreasing order,find the starting and ending index of a target.
	 * If target is not found in the array, return [-1, -1].
	 * You must write an algorithm with O(log n) runtime complexity.
	 */

	private int findOccurrence(int nums[], int l, int r, int k) {
		if (l > r) { // Base Condition or Recursion Stopping Condition
			return -1;
		}
		int mid = l + (r - l) / 2; // this is the formula to find mid value
		if (nums[mid]==k){
			return mid;
		} else if (nums[mid]<k){
			l=mid+1;
		} else{
			r=mid-1;
		}
		return findOccurrence(nums,k,l,r);
	}

	private int[] findIndices(int nums[], int k) { //This actually has same time complexity as solution below.
		int found=findOccurrence(nums,0,nums.length,k);
		if (found==-1) { // Base Condition or Recursion Stopping Condition
			return new int[]{-1, -1};
		} else{
			int left = found,right=found;
			while (left-1>0 && nums[left-1]==k){
				left--;
			}
			while (right+1<nums.length-1 && nums[right+1]==k){
				right++;
			}
			return new int[] {left,right};
		}
	}

	public static void main(String args[]) {
		int [] arr={-5,1,2,7,12,12,14,20,25,25,34}; int target=12;
		Find_First_and_Last_Position_of_Element_in_Sorted_Array_034 ob = new Find_First_and_Last_Position_of_Element_in_Sorted_Array_034();
 	    int[] indices = ob.findIndices(arr,target);
		System.out.println(Arrays.toString(indices));
		indices = ob.searchRangeAccepted(arr,target);
		System.out.println(Arrays.toString(indices));
    }

	public int[] searchRangeAccepted(int[] nums, int target) {
		int[] result = {-1, -1};
		int left = binarySearch(nums, target, true);
		int right = binarySearch(nums, target, false);
		result[0] = left;
		result[1] = right;
		return result;
	}

	private int binarySearch(int[] nums, int target, boolean isSearchingLeft) {
		int left = 0;
		int right = nums.length - 1;
		int idx = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				idx = mid;
				if (isSearchingLeft) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		return idx;
	}
}
