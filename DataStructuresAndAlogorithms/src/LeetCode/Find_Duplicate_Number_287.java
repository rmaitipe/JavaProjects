package LeetCode;

import java.util.HashMap;

public class Find_Duplicate_Number_287 {
    /*
     * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
     * There is only one repeated number in nums, return this repeated number.
     * You must solve the problem without modifying the array nums and using only constant extra space.
     * Input: nums = [1,3,4,2,2]   Output: 2
     * Input: nums = [3,3,3,3,3]   Output: 3     Input: nums = [6,3,1,3,4,2,8,9,5]   Output: 3
     */

    public static void main(String args[]) {
        Find_Duplicate_Number_287 ob = new Find_Duplicate_Number_287();
        int[] input= {6,3,1,3,4,2,8,9,5};
        System.out.println(ob.findDupe(input));
        System.out.println("Accepted: "+ob.findDuplicateAccepted(input));
    }

    private int findDupe(int[] input) { //O(n2)
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
    /*
    Marking in Array Approach - Modifies the original array, which is disallowed by the problem constraints.
        Traverse the array nums.
        For each element num, take its absolute value as an index (idx = abs(num)).
        If nums[idx] is negative, then idx is the duplicate number.
        Otherwise, negate nums[idx]
     */

    /*
    Treat the array as a linked list to find a cycle. Only possible because values are in range [1, n] inclusive.

    After identifying a meeting point inside the cycle, we reinitialize the slow pointer back to nums[0].
    The fast pointer stays at the last meeting point. Now, we enter another while loop where both pointers move one step
    at a time. The reason behind this is mathematical: according to Floyd's cycle detection algorithm, when both
    pointers move at the same speed, they will eventually meet at the starting point of the cycle.
    This is the duplicate number we are looking for.
     */
    public int findDuplicateAccepted(int[] nums) {//Hare & Tortoise Method
        int slow = nums[0];
        int fast = nums[0];
        do { // we are sure that at least one duplicate is there
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        // find the head of loop
        slow = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
