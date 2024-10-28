package LeetCode;

import java.util.Arrays;

public class Jump_Game_II_045 {
    /*
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     * Each element nums[i] represents the maximum length of a forward jump from index i. In other words,
     * if you are at nums[i], you can jump to any nums[i+j] where: 0 <= j <= nums[i] and i+j < n
     * Return the minimum number of jumps to reach nums[n-1]. data is such a solution will exist
     * Input: nums = [2,3,1,1,4] Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1,
     * then 3 steps to the last index.
     *
     * See Unique Paths's DP solution
     */
    private int jumpPathDP(int[] num) {
        int[] visited=new int [num.length];
        Arrays.fill(visited,1,num.length,Integer.MAX_VALUE);
        for (int i=0;i<num.length;i++){
            if (i+num[i]<num.length){
                int idx= Math.min(i+num[i],num.length-1);
                for (int j=i+1;j<=idx;j++){
                    visited[j]=Math.min(visited[j],visited[i]+1);
                }
            }
        }
        return visited[num.length-1];
    }

    public static void main(String args[]) {
        Jump_Game_II_045 ob = new Jump_Game_II_045();
        int[] num = {2,3,1,1,4};
        System.out.println(ob.jumpPathDP(num));
        System.out.println(ob.jumpPathAccepted(num));
    }

    public int jumpPathAccepted(int[] nums) {// only keeping track of rightmost boundary
        int jumps = 0;       // Number of jumps needed to reach the end
        int currentEnd = 0;  // The end of the current jump range
        int farthest = 0;    // The farthest index we can reach at each step
        // Iterate through the array (but we don't need to process the last element)
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest point we can reach from the current index
            farthest = Math.max(farthest, i + nums[i]);
            // If we have reached the end of the current jump range
            if (i == currentEnd) {
                jumps++;               // We make a jump
                currentEnd = farthest;  // Update the current range to the farthest we can reach
                // If we can already reach the last index, break out of the loop
                if (currentEnd >= nums.length - 1) break;
            }
        }
        return jumps;
    }
}
