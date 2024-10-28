package LeetCode;

public class Search_in_Rotated_Sorted_Array_033 {
    /*
     * There is an integer array nums sorted in ascending order (with distinct values).
     * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1<=k<nums.length)
     * such that the resulting array is [nums[k], nums[k+1], .., nums[n-1], nums[0], nums[1], .., nums[k-1]] (0-indexed).
     * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
     * or -1 if it is not in nums.
     * You must write an algorithm with O(log n) runtime complexity.
     * Input: nums = [4,5,6,7,0,1,2], target = 0    Output: 4
     * [7,0,1,2,4,5,6] if it was rotated 1 times. [0,1,2,4,5,6,7] if it was rotated 7 times.
     */

    public static void main(String args[]) {
        Search_in_Rotated_Sorted_Array_033 ob = new Search_in_Rotated_Sorted_Array_033();
        int[] arr = {4,5,6,7,0,1,2};
        System.out.println(ob.searchRotatedArray(arr, 0));
        System.out.println("Accepted "+ob.searchAccepted(arr, 0));
    }

    private int searchRotatedArray(int[] num,int target) {
        int left=0;
        int right=num.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            System.out.println("mid idx: "+mid);
            if (num[mid]==target) return mid;
            else if (target<num[mid] && num[left]<num[mid]){//bs
                right=mid-1;
            }
            else if (num[mid]<target && num[right]>num[mid]){//bs
                left=mid+1;
            }
            else if (target<num[mid] && num[left]>num[mid]){
                left=mid+1;
            }
            else if (num[mid]<target && num[right]<num[mid]){
                right=mid-1;
            }
        }
        return -1;
    }

    public int searchAccepted(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println("mid idx: "+mid);
            if (nums[mid] == target){
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return nums[low] == target ? low : -1;
    }
}
