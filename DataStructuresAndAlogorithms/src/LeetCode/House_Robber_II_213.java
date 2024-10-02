package LeetCode;

public class House_Robber_II_213 {
    /*
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
     * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
     * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if
     * two adjacent houses were broken into on the same night.
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of money
     * you can rob tonight without alerting the police.
     * IInput: nums = [1,2,3,1]   Output: 4
     */
    private int robberPath(int[] num) {
        if (num.length ==1){
            return num[0];
        } else if (num.length ==2){
            return Math.max(num[0],num[1]);
        } else {
            int[] visited = new int[num.length];
            visited[0] = num[0];
            visited[1] = num[1];
            for (int i = 2; i <= num.length - 1; i++) {
                int max = visited[i - 2];
                if (i - 3 > 0) {
                    max = Math.max(visited[i - 2], visited[i - 3]);
                }
                visited[i] = num[i] + max;
            }
            if (visited[0]==num[0]){
                return Math.max(visited[num.length - 2], visited[num.length - 3]);//last node not eligible
            }else {
                return Math.max(visited[num.length - 1], visited[num.length - 2]);
            }
        }
    }

    public int robAccepted(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    public static void main(String args[]) {
        House_Robber_II_213 ob = new House_Robber_II_213();
        int[] num = {1,2,3,1};
        System.out.println(ob.robberPath(num));
    }
}
