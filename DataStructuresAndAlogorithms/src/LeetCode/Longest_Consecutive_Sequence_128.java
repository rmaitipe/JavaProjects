package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Longest_Consecutive_Sequence_128 {
    /*
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     * You must write an algorithm that runs in O(n) time.
     * Input: nums = [100,4,200,1,3,2] Output: 4 (1,2,3,4 elements)
     * Input: nums = [100,4,101,1,3,2,6,103] Output: 4 (1,2,3,4 elements)
     */
    private int longestConsecutiveSequence(int [] nums){
        int max=-1;
        Map<Integer,Integer> map=new HashMap<>();
        for (int val: nums){
            if (!map.containsKey(val)) {
                if (map.containsKey(val-1) && map.containsKey(val+1)){
                    int a = map.get(val-1);
                    int b = map.get(val+1);
                    int idx=a+b+1;
                    max=Math.max(max,idx);
                    for (int i=1;i<=a;i++){
                        map.put(val-i,idx);
                    }
                    map.put(val,idx);
                    for (int i=1;i<=b;i++){
                        map.put(val+i,idx);
                    }
                } else if (map.containsKey(val-1)){
                    int idx=map.get(val-1)+1;
                    max=Math.max(max,idx);
                    for (int i=0;i<idx;i++){
                        map.put(val-i,idx);
                    }
                } else if (map.containsKey(val+1)){
                    int idx=map.get(val+1)+1;
                    max=Math.max(max,idx);
                    for (int i=0;i<idx;i++){
                        map.put(val+i,idx);
                    }
                } else {
                    map.put(val,1);
                }
            }
        }
        return max;
    }

    public static void main(String args[]) {
        Longest_Consecutive_Sequence_128 ob = new Longest_Consecutive_Sequence_128();
        int[] input = new int[] {100,4,101,1,3,2,6,103};
        System.out.println(ob.longestConsecutiveSequence(input));
        System.out.println("Accepted: "+ob.longestConsecutiveAccepted(input));
    }

    public int longestConsecutiveAccepted(int[] nums) {
        if (nums.length == 0) { // Step 1: Handle the base case when the array is empty.
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {  // Step 2: Insert all elements of 'nums' into the hash set 'set'.
            set.add(num);
        }
        int cnt = 1;        // Initialize a counter for the current consecutive sequence length.
        int longest = 0;    // Initialize a variable to store the maximum consecutive sequence length.

        for (int num : nums) {  // Step 3: Iterate through the elements of 'nums'.
            if (!set.contains(num - 1)) {// Step 4: If the current element 'num' is the start of a sequence (no 'num-1' in 'set'),
                int x = num; // Update 'x' to the current element 'num'.
                cnt = 1;
                while (set.contains(x + 1)) {// Step 5: While consecutive elements exist in 'set', increment 'cnt' and 'x'.
                    cnt++;
                    x = x + 1;
                }
            }
            longest = Math.max(longest, cnt); // Step 6: Update 'longest' with the maximum of 'longest' and 'cnt'.
        }
        return longest; // Step 7: Return 'longest' as the result.
    }

}
