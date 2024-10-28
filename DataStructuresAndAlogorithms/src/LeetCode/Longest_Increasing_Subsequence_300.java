package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Longest_Increasing_Subsequence_300 {
    /*
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     * Input: nums = [10,9,2,5,3,7,101,18]  Output: 4   Explanation:The longest increasing subsequence is [2,3,7,101],
     * other subsequences of similar length exist
     *
     * Find O(n log(n)) time complexity solution- Mancher's
     */

    public static void main(String args[]) {
        Longest_Increasing_Subsequence_300 ob = new Longest_Increasing_Subsequence_300();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(ob.longestIncreasingSubSeq(nums));
        System.out.println(ob.lengthOfLIS(nums));
    }

    public List<List<Integer>> longestIncreasingSubSeq(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        //Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums,  0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        if(tempList.size()== nums.length) {
            return;
        } else{
            for(int i = start; i < nums.length; i++){
                if(!tempList.isEmpty() && nums[i]>tempList.getLast()) {
                    tempList.add(nums[i]);
                    list.add(new ArrayList<>(tempList));
                    backtrack(list, tempList, nums, i + 1); // not i + 1 because we can reuse same elements
                    tempList.remove(tempList.size() - 1);
                }else if (tempList.isEmpty()){
                    tempList.add(nums[i]);
                    backtrack(list, tempList, nums, i + 1); // not i + 1 because we can reuse same elements
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

/*
    Initialize an array dp with the same length as nums and fill it with 1s.
    Iterate through each element in nums.
        For each element at index i, iterate through all previous elements at indices j (where j < i).
        If nums[j] < nums[i], update dp[i] to the maximum between its current value and dp[j] + 1. This means we've found a longer subsequence ending at index i.
    Find the maximum value in the dp array, which represents the length of the longest increasing subsequence.
    Return the maximum value.
    public int lengthOfLIS(int[] nums) {
        if(nums.length==1) return 1;
        int[] dp= new int[nums.length];
        int max_element=0;
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; ++i){
            for (int j = 0; j < i; ++j){
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;//use a localMax instead
                }
            }
            max_element=Math.max(dp[i],max_element);
        }
        return max_element;
     }
    {10,9,2,5,3,7,101,18}           dp[0]=1
    //i=1(9),j=1->0(9-10)           dp[1]=1
    //i=2(2),j=2->0(2-9-10)         dp[2]=1
    //i=3(5),j=3->0(5-2-9-10)       dp[3]=2
    //i=4(3),j=4->0(3-5-2-9-10)     dp[4]=2
    //i=5(3),j=5->0(7-3-5-2-9-10)   dp[5]=3
 */
    public int lengthOfLIS(int[] nums) { //O(n2) 2,6,4,5  [1,2,1,1]i=1 [1,2,2,1]i=2
        int[] dp = new int[nums.length];
        int ans = 1;
        for(int i=0; i<nums.length; i++){
            int max = 1;
            for(int j=i; j>=0; j--){
                if(nums[j] < nums[i]){
                    max = Math.max(dp[j]+1, max);
                }
            }
            dp[i] = max;
            ans = Math.max(ans, max);
        }
        return ans;
    }

    /*
    Initialize List li: We create a list li initialized with Integer.MAX_VALUE, which helps in keeping track of the
    smallest end element of increasing subsequences of different lengths.
    Update the List: For each number in the array nums, update the list li:
        For each number, find the first position in li where li[j] >= nums[i] and update li[j] with nums[i].
        This process helps maintain the smallest possible end elements for subsequences of increasing lengths.
    Determine the Length of the LIS: After processing all numbers, find the length of the longest increasing subsequence
    by counting how many elements in li are not Integer.MAX_VALUE.
    O(log(n))
     */
    public int increasingTriplet(int[] nums) {
        int n = nums.length;
        List<Integer> li = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            li.add(Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (li.get(mid) >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            li.set(left, nums[i]);
        }
        int i = n - 1;
        while (i >= 0 && li.get(i) == Integer.MAX_VALUE) {
            i--;
        }
        return i + 1;
    }

}
