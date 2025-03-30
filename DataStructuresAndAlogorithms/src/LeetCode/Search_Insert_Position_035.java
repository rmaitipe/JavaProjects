package LeetCode;

public class Search_Insert_Position_035 {

	/* Similar to 704
	 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
	 * If not, return the index where it would be if it were inserted in order.
	 * You must write an algorithm with O(log n) runtime complexity.
	 */
	public int findLocMethod(int arr[],int k){
		int left=0;
		int right = arr.length-1;
		while (left<=right){//while (left<right){
			int mid=left+(right-left)/2;
			if (arr[mid]==k){
				return mid;
			} else if (arr[mid]>k){
				right=mid-1;
			} else{
				left=mid+1;
			}
		}
		return left;//edge cases
	}
	
	public static void main(String args[]) {
        int arr[] = {3, 13, 15, 22, 25, 30, 45};
		int arr2[] = {1, 3, 5, 6};
		Search_Insert_Position_035 ob = new Search_Insert_Position_035();
		System.out.println(ob.findLocMethod(arr,7));
		System.out.println("Accepted: "+ob.searchInsertAccepted(arr,7));
		System.out.println(ob.findLocMethod(arr2,7));
		System.out.println("Accepted: "+ob.searchInsertAccepted(arr2,7));
    }

	public int searchInsertAccepted(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
