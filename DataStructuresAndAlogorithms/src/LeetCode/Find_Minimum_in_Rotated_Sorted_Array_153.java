package LeetCode;

public class Find_Minimum_in_Rotated_Sorted_Array_153 {
    /*
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
     * For example, the array nums = [0,1,2,4,5,6,7] might become:
     * [4,5,6,7,0,1,2] if it was rotated 4 times.   [7,0,1,2,4,5,6] if it was rotated 1 times.
     * [0,1,2,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
     * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
     * You must write an algorithm that runs in O(log n) time.
     */
    private int rotatedMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        //search condition is nums[n]>nums[n+1]
        //within sorted left<right  if rotated there exist a left bound where left>right
        while (left<right){
            int mid= left + (right - left) / 2;
            System.out.println("mid idx: "+mid);
            if (mid > 0 && nums[mid-1]>nums[mid] ) {
                return nums[mid];//edge case in else
            } else {
                if (nums[left] <= nums[mid] && nums[mid] < nums[right]) {//normal bst min on left
                    right = mid-1;
                }
                if (nums[mid] < nums[right]) {//within rotated portion
                    right = mid;
                }
                if (nums[left] < nums[mid]) {
                    left = mid + 1;
                }
            }
        }
        return nums[left];
    }

    public static void main(String args[]) {
        Find_Minimum_in_Rotated_Sorted_Array_153 ob = new Find_Minimum_in_Rotated_Sorted_Array_153();
        int[] input0 = new int[] {3,4,5,6,7,8,0,1,2};
        int[] input = new int[] {6,7,0,1,2,3,4,5};
        System.out.println(ob.rotatedMin(input0));
        System.out.println(ob.rotatedMin(input));
        System.out.println("Accepted: "+ob.findMinAccepted(input0));
        System.out.println("Accepted: "+ob.findMinAccepted(input));
    }

    /*
     * a[mid] > a[0]: This means mid is  in the first part of the graph. Implies pivot should be between mid + 1 to r
     * a[mid] < a[0]: This means mid is in the right part of the graph. Implies pivot should be between l and mid
     * Terminating conditions mid = 0 or a[mid] < a[mid - 1]
     * Special cases:  a[0] < a[a.length - 1]: Array is already sorted & a[0] is pivot
     * Array is of size two: Simply return min of two elements
     */
    public int findMinAccepted(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
            System.out.println("mid idx: "+mid);
        }
        return nums[left];
    }
}
