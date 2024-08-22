package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Three_Sum_015 {

	/*
	 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
	 * Notice that the solution set must not contain duplicate triplets.
	 * Can be reduced to -a= b + c,
	 * Incomplete - Sort Array first for better performing algorithms and avoid duplicate condition
	 */
	public int[] threeSumMethod(int arr[],int k){
		int length = arr.length;
		int [] retVal=null;
		Map<Integer,Integer> myMap =new HashMap<Integer,Integer>();
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

	
	public static void main(String args[]) {
        int arr[] = {23, 34, 15, 12, 22, -10, -5};
		Three_Sum_015 ob = new Three_Sum_015();
		ob.sumMethod(arr);
        System.out.println();
    }

	private void sumMethod(int[] arr) {
		int[] val;
		for (int i=0;i< arr.length-2;i++){
			val=threeSumMethod(Arrays.copyOfRange(arr,i+1, arr.length),-arr[i]);
			if (val!=null){
				System.out.println(val.toString() +i);
			}
		}
	}

}
