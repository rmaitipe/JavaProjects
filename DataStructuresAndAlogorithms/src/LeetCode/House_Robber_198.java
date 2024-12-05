package LeetCode;

public class House_Robber_198 {

    /*
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
     * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems
     * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of money
     * you can rob tonight without alerting the police.
     * Input: nums = [2,7,9,3,1]    Output: 12
     * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1). Total= 2 + 9 + 1 = 12.
     */

    public static void main(String args[]) {
        House_Robber_198 ob = new House_Robber_198();
        int[] num = {2,7,9,3,1};
        System.out.println(ob.robberPath(num));
        System.out.println(ob.robAccepted(num));
    }

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
            return Math.max(visited[num.length - 1], visited[num.length - 2]);
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
}
