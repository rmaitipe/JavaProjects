package LeetCode;

public class Maximum_SubArray_053 {
    /* Given an integer array, find the subarray with the largest sum, and return its sum.
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4] Output:The subarray [4,-1,2,1] has the largest sum- 6.
     */
    public int findMaxSubArr(int[] nums) {
        int[] dpSum = new int [nums.length];
        dpSum[0]=nums[0];
        int max=nums[0];
        for (int i=1;i<nums.length;i++){
            if (nums[i]<nums[i]+dpSum[i-1]){
                dpSum[i]=nums[i]+dpSum[i-1];
            }else{
                dpSum[i]=nums[i];
                if (dpSum[i]>max){
                    max=dpSum[i];
                }
            }
        }
        return max;
    }

    public static void main(String args[])    {
        int[] prices = {-2,1,-3,4,-1,2,1,-5,4};
        Maximum_SubArray_053 ob = new Maximum_SubArray_053();
        int out=ob.findMaxSubArr(prices);
        System.out.println(String.valueOf(out));
    }

    private int dynamicProgrammingMaxSubArrAccepted(int[] nums){
        int[] sum = new int[nums.length];
        sum[0] = nums[0];//initial case
        int max = nums[0];
        for(int i=1; i<nums.length; i++){
            sum[i] = Math.max(sum[i-1]+nums[i], nums[i]);
            max = Math.max(max, sum[i]);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int currSum = nums[0];
        for(int i = 1;i<nums.length; i++){
            if(currSum<0){
                currSum = nums[i];//reset
            } else {
                currSum+=nums[i];
            }
            max = Math.max(max, currSum);
        }
        return max;
    }

}
