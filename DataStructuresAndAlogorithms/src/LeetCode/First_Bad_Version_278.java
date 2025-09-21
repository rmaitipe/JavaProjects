package LeetCode;

public class First_Bad_Version_278 {

	/* Similar to 035, 704
	 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version
	 * of your product fails the quality check. Since each version is developed based on the previous version, all the
	 * versions after a bad version are also bad. Suppose you have n versions [1, 2, ..., n] and you want to find out the
	 * first bad one, which causes all the following ones to be bad. You are given an API bool isBadVersion(version)
	 * which returns whether version is bad. Implement a function to find the first bad version.
	 * You should minimize the number of calls to the API.
	 * Input: n = 5, bad = 4	Output: 4
	 */

	public int firstBadVersionAccepted(int n) {
		int left = 1;
		int right = n;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			 /*If version mid is bad , that means the first bad version could be mid or even earlier.
			 So we move right = mid to keep searching in the left half.
					If version mid is good , the first bad version must be after it.
			 So we move left = mid + 1.*/
			if (isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public int findIndexMethod(int n) {
		int low = 0;
		int high = n;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (isBadVersion(mid) == true && isBadVersion(mid - 1) == false) return mid;
			else if (isBadVersion(mid) == false) low = mid + 1;
			else high = mid;
		}
		return -1;
	}
	
	public static void main(String args[]) {
        int arr[] = {-1,0,3,5,9,12};
		First_Bad_Version_278 ob = new First_Bad_Version_278();
		System.out.println(ob.findIndexMethod(9));
    }
 	boolean isBadVersion(int n){
		return true;
 	}
}
