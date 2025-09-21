package LeetCode;

public class Binary_Search_704 {

	/* Similar to 035, 278
	 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to
	 * search target in nums. If target exists, then return its index. Otherwise, return -1.
	 * You must write an algorithm with O(log n) runtime complexity.
	 */
	public int findIndexMethod(int arr[],int k){
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
		return -1;//edge cases
	}
	
	public static void main(String args[]) {
        int arr[] = {-1,0,3,5,9,12};
		Binary_Search_704 ob = new Binary_Search_704();
		System.out.println(ob.findIndexMethod(arr,9));
		System.out.println(ob.findIndexMethod(arr,2));
    }

}
