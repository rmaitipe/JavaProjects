package LeetCode;

import java.util.Arrays;

public class Jump_Game_055 {
    /*
     * You are given an integer array nums. You are initially positioned at the array's first index,
     * and each element in the array represents your maximum jump length at that position.
     * Return true if you can reach the last index, or false otherwise.
     * Input:nums=[2,3,1,1,4]       Output:true     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Input:nums =[3,2,1,0,4]      Output:false    You will always arrive at index 3 no matter what.
     * Its maximum jump length is 0, which makes it impossible to reach the last index.
     *
     * Solution without using extra space
     */
    private boolean jumpPath(int[] num) {
        int[] visited=new int [num.length];
        visited[0]=1;
        for (int i=0;i<num.length-1;i++){
            if (visited[i]==1){
                int idx= Math.min(i+num[i],num.length-1);
                Arrays.fill(visited,i,idx+1,1);
            }
        }
        return visited[num.length-1]!=0;
    }

    public static void main(String args[]) {
        Jump_Game_055 ob = new Jump_Game_055();
        int[] num = {2,3,1,1,4};
        System.out.println(ob.jumpPath(num));
        System.out.println(ob.canJumpAccepted(num));
    }

    /*
     * Intuition The basic idea is this: at each step, we keep track of the furthest reachable index.
     * The nature of the problem (eg. maximal jumps where you can hit a range of targets instead of singular target)
     * is that for an index to be reachable, each of the previous indices have to be reachable.
     */
    public boolean canJumpAccepted(int[] nums) {// only keeping track of rightmost boundary
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
}
