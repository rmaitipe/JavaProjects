package LeetCode;

import java.util.Arrays;

public class Rotate_Array_189 {
    /*
     * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
     * Input: nums = [1,2,3,4,5,6,7], k = 3    Output: [5,6,7,1,2,3,4]
     *
     * Follow Up:Find Solution with Time complexity: O(n) Space complexity: O(1)
     */

    public static void main(String args[]) {
        Rotate_Array_189 ob = new Rotate_Array_189();
        int[] arr = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(ob.rotateArray(arr, 3)));
    }

    private int[] rotateArray(int[] num,int rShift) {
        int[] newArr=new int[num.length];
        for (int i=num.length-rShift;i<num.length;i++){
            newArr[i-num.length+rShift]=num[i];//left
        }
        for (int j=0;j<=rShift;j++) {
            newArr[num.length - rShift + j-1] = num[j];
        }
        return newArr;
    }

    public void rotateAccepted(int[] nums, int k) {//
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
