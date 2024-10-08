package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_Sum_001 {
	/*
	 * Given an array of integers nums, return indices of the two numbers such that they add up to target.
	 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
	 * You can return the answer in any order.
	 * Input: nums = [2,7,11,15], target = 9	Output: [0,1]
	 */
	public int[] twoSumMethod(int arr[],int k){
		int length = arr.length;
		int [] retVal=null;
		Map<Integer,Integer> myMap =new HashMap<>();
		for (int i=0;i<length;i++) {
			if (myMap.containsKey(k-arr[i])){
				Integer j= myMap.get(k-arr[i]);
				retVal= new int[]{i,j};
				break;
			} else{
				myMap.put(arr[i],i);
			}
		}
		return retVal;
	}

	public static void main(String args[]){
        int target =25;
        int arr[] = {23, 34, 15, 12, 22, 10, 0};
		Two_Sum_001 ob = new Two_Sum_001();
        System.out.println(Arrays.toString(ob.twoSumMethod(arr, target)));
    }

}
