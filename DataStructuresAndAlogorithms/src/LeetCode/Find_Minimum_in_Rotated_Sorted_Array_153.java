package LeetCode;

public class Find_Minimum_in_Rotated_Sorted_Array_153 {
    /*
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
     * For example, the array nums = [0,1,2,4,5,6,7] might become:
     * [4,5,6,7,0,1,2] if it was rotated 4 times.
     * [0,1,2,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
     * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
     * You must write an algorithm that runs in O(log n) time.
     */
    private int rotatedMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int mid=0;
        //search condition is nums[n]>nums[n+1]
        //within sorted left<right
        //if rotated there exist a left bound where left>right
        while (left<right){
            mid=(left+right)/2;
            /*if (nums[left]<nums[mid] && nums[mid] <nums[right]) {//normal bst min on left
                right=mid-1;
            } else */if (nums[mid] >nums[right]){//min in right side
                /*if (nums[mid]>nums[mid+1]){
                    return nums[mid+1];
                }*/
                left=mid+1;
            } else if (nums[left]>nums[mid] ){//min in left side
                /*if (nums[mid-1]>nums[mid]){
                    return nums[mid];
                }*/
                right=mid-1;
            }
            System.out.println("mid idx: "+mid);
        }
        return nums[left];
    }

    public static void main(String args[]) {
        Find_Minimum_in_Rotated_Sorted_Array_153 ob = new Find_Minimum_in_Rotated_Sorted_Array_153();
        int[] input0 = new int[] {3,4,5,6,7,8,0,1,2};
        int[] input = new int[] {6,7,0,1,2,3,4,5};
        System.out.println(ob.rotatedMin(input0));
        System.out.println(ob.rotatedMin(input));
        System.out.println(ob.findMinAccepted(input));
    }

    public int findMinAccepted(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
