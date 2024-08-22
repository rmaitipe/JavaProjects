package LeetCode;

import java.util.PriorityQueue;

public class KthLargest_In_Array_215 {

	/*
	 * Given an integer array nums and an integer k, return the kth largest element in the array.
	 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
	 * Can you solve it without sorting?
	 */
	public int searchMethod(int[] arr,int k) {
		PriorityQueue<Integer> pq= new PriorityQueue<>();
		for (int a:arr){
			pq.add(a);
		}
		for (int i=0;i<k;i++){
			pq.poll();
		}
		return pq.poll();
	}

	public static void main(String args[]) {
		KthLargest_In_Array_215 ob = new KthLargest_In_Array_215();
		int [] arr={5,8,12,78,123,145,60,-45,4};
		int k=3;
 	    Integer finalNode = ob.searchMethod(arr,k);
		 System.out.print(finalNode);
    }

}
