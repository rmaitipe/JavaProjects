package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Combination_Sum_039 {
    /*
     * Given an array of distinct integers candidates and a target integer target, return a list of all unique
     * combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
     * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
     * frequency of at least one of the chosen numbers is different. 2 <= candidates[i] <= 40
     * Input: candidates = [2,3,5], target = 8  Output: [[2,2,2,2],[2,3,3],[3,5]]
     */

    public static void main(String args[]) {
        Combination_Sum_039 ob = new Combination_Sum_039();
        int[] nums = {2,3,5};
        System.out.println(ob.combinationSumAccepted(nums,8));
        System.out.println(ob.combinationSumAccepted(nums,10));
    }

    public List<List<Integer>> combinationSumAccepted(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        //Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements, this also ensures combination 2,5,3 does not occur
                backtrack(list, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}
