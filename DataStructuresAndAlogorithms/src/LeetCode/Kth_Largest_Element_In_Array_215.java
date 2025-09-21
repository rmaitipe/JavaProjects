package LeetCode;

import java.util.PriorityQueue;
import java.util.Random;

public class Kth_Largest_Element_In_Array_215 {
	/*
	 * Given an integer array nums and an integer k, return the kth largest element in the array.
	 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
	 * Can you solve it without sorting?
	 *
	 * Input: nums = [3,2,1,5,6,4], k = 2	Output: 5 Explanation:6,5
	 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4	Output: 4 Explanation:6,5,5,4
	 *
	 * PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	 * PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	 */
	public int searchMethod(int[] arr,int k) {
		PriorityQueue<Integer> pq= new PriorityQueue<>();//This is same as sorting-> n.logk
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
		finalNode = ob.findKthLargestQSAccepted(nums,k);
		System.out.println("Result findKthLargestQS: "+finalNode);
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

/*
How Does Quickselect Work? Quickselect works identical to quicksort in that we:
Pick a pivot
Partition the data into two where:
	Numbers less than the pivot go to the left
	Numbers greater than the pivot go to the right
However, instead of recursing into both sides as in Quicksort, quickselect only recurs into one side; whichever one would have our kth largest element.
The main thing to note here is that our pivot at any given partition will always end up at the correct index. Therefore, we just need to check:
	If our pivot is at our "kth largest" index, return the number at that index.
	If our pivot comes before the "kth largest" index, perform quickselect on the right partition.
	If our pivot comes after the "kth largest" index, perform quickselect on the left partition.
Performing quickselect only on one partition reduces our average-case complexity from O(nlogn) to O(n).
n+n/2+n/4+... = 2n -> O(n)

function quickSelect(nums, left, right, k)
   if left = right return nums[left]   // base case

   pIndex = random element between left and right
   pIndex = partition(nums, left, right, pIndex)

   if k = pIndex
      return nums[k]
   else if k < pIndex
      return quickselect with: right = pIndex - 1
   else
      return quickselect with: left = pIndex + 1
*/

	public int findKthLargestQSAccepted(int[] nums, int k) {
		return quickSelect(nums, 0, nums.length-1, nums.length-k);
	}

	private int quickSelect(int[] nums, int left, int right, int k) {
		if (left == right) return nums[left];

		int pIndex = new Random().nextInt(right - left + 1) + left;
		pIndex = partition(nums, left, right, pIndex);

		if (pIndex == k) return nums[k];
		else if (pIndex < k) return quickSelect(nums, pIndex+1, right, k);
		return quickSelect(nums, left, pIndex-1, k);
	}

	private int partition(int[] nums, int left, int right, int pIndex) {
		int pivot = nums[pIndex];
		swap(nums, pIndex, right);
		pIndex = left;

		for (int i=left; i<=right; i++)
			if (nums[i] <= pivot)
				swap(nums, i, pIndex++);

		return pIndex - 1;
	}

	private void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
}
