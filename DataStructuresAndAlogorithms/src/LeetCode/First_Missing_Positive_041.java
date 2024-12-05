package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class First_Missing_Positive_041 {
    /*
     * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
     * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
     * Input: nums = [1,2,0] Output: 3
     * Input: nums = [3,4,-1,1] Output: 2
     */

    public static void main(String args[]) {
        First_Missing_Positive_041 ob = new First_Missing_Positive_041();
        int[] nums = {7,8,9,11,12};
        int out=ob.firstMissingPositive(nums);
        System.out.println(out);
    }

    private int firstMissingPositive(int[] nums) {
        //iterate and place in heap
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for (int val:nums){
            if (val>0){
                pq.add(val);
            }
        }
        int size=pq.size();
        int retVal=size+1;
        for (int j=1;j<=size;j++){
            if (j==pq.poll()){
                continue;
            } else{
                retVal=j;
                break;
            }
        }
        return retVal;
    }

}
