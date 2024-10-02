package LeetCode;

import java.util.*;

public class Find_Duplicate_Number_287 {

    /*
     * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
     * There is only one repeated number in nums, return this repeated number.
     * You must solve the problem without modifying the array nums and using only constant extra space.
     * Input: nums = [1,3,4,2,2]   Output: 2
     * Input: nums = [3,3,3,3,3]   Output: 3
     */

    public static void main(String args[]) {
        Find_Duplicate_Number_287 ob = new Find_Duplicate_Number_287();
        int[] input= {6,3,1,3,4,2,8,9,5};
        System.out.println(ob.findDupe(input));
    }

    private int findDupe(int[] input) {
        for (int i=0;i<input.length;i++){
            for (int j=i+1;j<input.length;j++){
                /*int res=input[i]*input[j];
                if (Math.sqrt(res)==input[i]){
                    return input[i];
                }*/
                int res=input[i]^input[j];
                if (res==0){
                    return input[i];
                }
            }
        }
        return -1;
    }

    public int findDuplicateAccepted(int[] nums) {//Hare & Tortoise Method
        int slow = nums[0];
        int fast = nums[0];
        do { // we are sure that at least one duplicate is there
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        // find the head of loop
        fast = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
