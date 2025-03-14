package LeetCode;

public class Maximum_SubArray_053 {
    /* Given an integer array, find the subarray with the largest sum, and return its sum.
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4] Output:The subarray [4,-1,2,1] has the largest sum- 6.
     *
     * If you have figured out the O(n) solution, try coding another solution using the divide & conquer approach.
     */
    public int findMaxSubArr(int[] nums) {
        int[] dpSum = new int [nums.length];
        dpSum[0]=nums[0];
        int max=nums[0];
        for (int i=1;i<nums.length;i++){
            if (nums[i]<nums[i]+dpSum[i-1]){
                dpSum[i]=nums[i]+dpSum[i-1];
            } else{
                dpSum[i]=nums[i];
            }
            if (dpSum[i]>max){
                max=dpSum[i];
            }
        }
        return max;
    }

    public static void main(String args[])    {
        int[] prices = {-2,1,-3,4,-1,2,1,-5,4};
        Maximum_SubArray_053 ob = new Maximum_SubArray_053();
        int out=ob.findMaxSubArr(prices);
        System.out.println(String.valueOf(out));
        System.out.println("Accepted: "+String.valueOf(ob.dynamicProgrammingMaxSubArrAccepted(prices)));
        System.out.println("Accepted2: "+String.valueOf(ob.maxSubArray(prices)));
    }

/*
Approach 1 : Divide and Conquer (Non-intuitive Approach -ignore)
1. calculate maxSum (maximum positive sum) from mid to left
2. calculate maxSum(maximum positive sum ) from mid+1 to right side
3. int midSum = leftMaxSUM+rightMaxSUM
 */
    public int maxSubArrayDivideConquer(int[] nums) {
        return helper(nums,0,nums.length-1);
    }

    public int helper(int nums[],int i,int j){
        if(i==j){
            return nums[i];
        }
        int mid  =  (i+j)/2;
        int sum = 0,leftMaxSUM = Integer.MIN_VALUE;
        for(int l =  mid;l>=i;l--){
            sum+=nums[l];
            if(sum>leftMaxSUM){
                leftMaxSUM =  sum;
            }
        }
        int rightMaxSUM = Integer.MIN_VALUE;
        sum = 0;    // reset sum to 0
        for (int l = mid + 1; l <=j; l++) {
            sum += nums[l];
            if (sum > rightMaxSUM ) {
                rightMaxSUM = sum;
            }
        }
        int maxLeftRight = Math.max(helper(nums, i, mid),
                helper(nums, mid + 1, j ));
        return Math.max(maxLeftRight, leftMaxSUM + rightMaxSUM );
    }
/*
Approach 2 : Dynamic Programming
Take one variable as a global maximum, say max (To keep track maximum value). dp[i] means max sum subarray ending
at index i If sum till i-1 is usefull, then take it, otherwise take current cell as sum till i
 */
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

/*
Approach 3 : Kadane Algorithm (Best Approach) - O(n) time Complexity
if overall sum become negative then it is better to reinitialize sum to zero, because a positive sum can increase later,
but if we move with negative sum then it can only decrease our positive sum.
 */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;

    }

}
