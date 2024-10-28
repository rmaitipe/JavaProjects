package LeetCode;

import java.util.PriorityQueue;

public class Kth_Largest_Element_In_Array_215 {
	/*
	 * Given an integer array nums and an integer k, return the kth largest element in the array.
	 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
	 * Can you solve it without sorting?
	 *
	 * Input: nums = [3,2,1,5,6,4], k = 2	Output: 5
	 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4	Output: 4
	 *
	 * PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	 * PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	 */
	public int searchMethod(int[] arr,int k) {
		PriorityQueue<Integer> pq= new PriorityQueue<>();//This is same as sorting
		for (int a:arr){
			pq.add(a);
		}
		for (int i=0;i<arr.length-k;i++){
			System.out.println(pq.poll());
		}
		return pq.poll();
	}

	public static void main(String args[]) {
		Kth_Largest_Element_In_Array_215 ob = new Kth_Largest_Element_In_Array_215();
		int [] arr={5,8,12,78,123,145,60,-45,4};
		int k=3;
		Integer finalNode = ob.searchMethod(arr,k);
		System.out.println("Result: "+finalNode);
		finalNode = ob.findKthLargestAccepted(arr,k);
		System.out.println("findKthLargestAccepted Result: "+finalNode);
		int [] nums = {3,2,3,1,2,4,5,5,6}; k = 4;//122334556
 	    finalNode = ob.searchMethod(nums,k);
		System.out.println("Result: "+finalNode);
		finalNode = ob.findKthLargestAccepted(nums,k);
		System.out.println("findKthLargestAccepted Result: "+finalNode);
    }

	public int findKthLargestAccepted(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			minHeap.offer(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			if (nums[i] > minHeap.peek()) {
				minHeap.poll();
				minHeap.offer(nums[i]);
			}
		}
		return minHeap.peek();
	}

}
