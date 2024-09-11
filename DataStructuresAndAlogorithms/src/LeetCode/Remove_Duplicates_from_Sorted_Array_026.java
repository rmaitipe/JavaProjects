package LeetCode;

import java.util.Arrays;

public class Remove_Duplicates_from_Sorted_Array_026 {


    /*
    Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that
    each unique element appears only once. The relative order of the elements should be kept the same.
    Then return the number of unique elements in nums.
    Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
    Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
    Return k.

    Input: nums = [0,0,1,1,1,2,2,3,3,4]
    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     */

    public static void main(String args[]) {
        Remove_Duplicates_from_Sorted_Array_026 ob=new Remove_Duplicates_from_Sorted_Array_026();
        int[] sortedNum  = {0,0,1,1,1,2,2,3,3,4};
        int[] sortedNum2  ={0,0,1,1,1,2,2,3,3,4};
        System.out.println(ob.removeDupes(sortedNum));
        System.out.println(ob.removeDuplicatesOptimal(sortedNum2));
    }

    private int removeDupes(int[] num) {
        if (num.length==1){
            return 1;
        } else{
            int left=0;
            int right=0;
            int shift=1;
            while (right<num.length && right+shift< num.length){
                if (num[right]==num[right+shift]){
                    shift++;
                } else{
                    left++;
                    num[left]=num[right+shift];
                    right=right+shift;
                    shift=1;
                }
            }
            //Arrays.fill(num,left+1,num.length,0);
            return num.length-left-1;
        }
    }

    public int removeDuplicatesOptimal(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}
