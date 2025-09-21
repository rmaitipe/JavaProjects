package LeetCode;

import java.util.Arrays;

public class Number_of_Subarrays_That_Match_a_Pattern_I_3034 {
    /*
     * You are given a 0-indexed integer array nums of size n, and a 0-indexed integer array pattern of size m consisting of integers -1, 0, and 1.
    A subarray nums[i..j] of size m + 1 is said to match the pattern if the following conditions hold for each element pattern[k]:
    nums[i + k + 1] > nums[i + k] if pattern[k] == 1.
    nums[i + k + 1] == nums[i + k] if pattern[k] == 0.
    nums[i + k + 1] < nums[i + k] if pattern[k] == -1.
Return the count of subarrays in nums that match the pattern.
* Input: nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]   Output: 2
     */

    public static void main(String args[]) {
        Number_of_Subarrays_That_Match_a_Pattern_I_3034 ob = new Number_of_Subarrays_That_Match_a_Pattern_I_3034();
        int [] nums = new int[]{1,4,4,1,3,5,5,3};
        int [] pattern = new int[]{1,0,-1};
        System.out.println(ob.countSubArray(nums, pattern));
        System.out.println(ob.countMatchingSubarraysAccepted(nums, pattern));
        System.out.println(ob.countMatchingSubarraysHashAccepted(nums, pattern));
    }

    public int countSubArray(int[] nums, int[] pattern) {
        int finCount=0;
        int m=pattern.length;
        for (int  i=0; i<nums.length-m;i++) {
            finCount+=methodForSizeBlock(i, nums, pattern, finCount);
        }
        return finCount;
    }

    int methodForSizeBlock(int start, int[] nums, int[] pattern, int finCount) {
        int m = pattern.length;
        boolean setRetVal=true;
        for (int i=start;i<m+start;i++){
            switch(pattern[i-start]){
                case 1: {
                    if (nums[i+1]<=nums[i]){
                        setRetVal=false;
                    }
                    break;
                }
                case 0: {
                    if (nums[i + 1] != nums[i]) {
                        setRetVal = false;
                    }
                    break;
                }
                case -1: {
                    if (nums[i + 1] >= nums[i]) {
                        setRetVal = false;
                    }
                    break;
                }
                default: break;
            }
            if (!setRetVal){ break;}
        }
        if (setRetVal)  {
            return 1;
        }
        return 0;
    }


    public int countMatchingSubarraysAccepted(int[] nums, int[] pattern) {
        int count = 0;
        int n = nums.length;
        int m = pattern.length;
        int i = 0,j=0;
        int k=pattern.length+1;
        while (j < n) {
            //create a sliding window of size k and the check if its a valid window
            if((j-i+1)==k) {
                if (matchesPattern(nums, pattern,i,j)) {
                    count++;
                }
                i++;
            }
            j++;
        }
        return count;
    }

    private static boolean matchesPattern(int[] nums, int[] pattern, int i,int j) {
        boolean isValid=false;
        for (int x = 0; x < pattern.length; x++) {
            //if pattern[k]==1 --> a < b
            if(i<j&&pattern[x]==1&& nums[i]<nums[i+1]) {
                isValid=true;
            }
            //if pattern[k]=== --> both are equal
            else if(i<j&&pattern[x]==0&& nums[i]==nums[i+1]) {
                isValid=true;
            }
            //if pattern[k]=== --> b > c
            else if(i<j&&pattern[x]==-1&& nums[i]>nums[i+1]) {
                isValid=true;
            }
            //if a pattern is inValid then return false;
            else {
                return false;
            }
            i++;
        }
        return isValid;
    }

    /*
    O(n) solution.
    The key insight is that we can use a rolling hash algorithm to efficiently check for matching subarrays.
    Instead of comparing each subarray element by element, we can convert the pattern and subarrays into a hash value and
    compare those hashes. This is particularly efficient for large arrays.
     */
    public int countMatchingSubarraysHashAccepted(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length, ps[] = new int[n - 1], psum[] = new int[n], hash = 0, res = 0;
        for (int k = 0; k < m; k++) {
            hash += pattern[k];
        }
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                ps[i] = 1;
            } else if (nums[i] > nums[i + 1]) {
                ps[i] = -1;
            } else {
                ps[i] = 0;
            }
            // prefix sum for pattern, to guaranttee O(1) for calc hash of subarrays.
            psum[i + 1] = psum[i] + ps[i];
        }
        for (int i = 0; i < n - m; i++) {
            //count if hash of subarray matches
            if (psum[i + m] - psum[i] == hash) {
                if (valid(ps, pattern, i, m)) res++;
            }
        }
        return res;
    }

    private boolean valid(int[] ps, int[] pattern, int start, int len) {
        for (int i = 0; i < len; i++) {
            if (ps[start + i] != pattern[i]) return false;
        }
        return true;
    }
}
