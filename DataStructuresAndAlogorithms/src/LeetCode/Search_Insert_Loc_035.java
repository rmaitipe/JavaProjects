package LeetCode;

public class Search_Insert_Loc_035 {

	/* Incomplete
	 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not,
	 *  return the index where it would be if it were inserted in order.
	 * You must write an algorithm with O(log n) runtime complexity.
	 */
	public int findLocMethod(int arr[],int k){
		int right = arr.length-1;
	    int left=0;
		int mid=0;
		int retVal = 0;
		while (left<right){
			mid=(left+right)/2;//****
			if (arr[mid]==k){
				retVal=mid; break;
			} else if (arr[mid]>k){
				right=mid-1;
			} else{
				left=mid+1;
			}
			retVal=mid;
		}
		return retVal;
	}

	
	public static void main(String args[]) {
       //int arr[] = {3, 13, 15, 22, 25, 30, 45};
		int arr[] = {1, 3, 5, 6};
		Search_Insert_Loc_035 ob = new Search_Insert_Loc_035();
		System.out.println(ob.findLocMethod(arr,7));
    }
}
