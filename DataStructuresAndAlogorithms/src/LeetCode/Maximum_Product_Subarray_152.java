package LeetCode;

public class Maximum_Product_Subarray_152 {
    /*
     * Given an integer array nums, find a subarray that has the largest product, and return the product.
     * The test cases are generated so that the answer will fit in a 32-bit integer.
     * Input: nums = [2,3,-2,4] Output: 6   Explanation: [2,3] has the largest product 6.
     */
    private int maxProd(int[] nums) {
        int maxProd=nums[0];
        int[] result= new int[nums.length];
        result[0]=nums[0];
        for (int i=1;i< nums.length;i++){
            int prod= result[i-1]*nums[i];
            if (prod>nums[i]){
                result[i]=prod;
            } else{
                result[i]=nums[i];
            }
            if (result[i]>maxProd){
                maxProd=result[i];
            }
        }
        return maxProd;
    }

    public static void main(String args[]) {
        Maximum_Product_Subarray_152 ob = new Maximum_Product_Subarray_152();
        int[] input = new int[] {2,3,-2,4};
        System.out.println(ob.maxProd(input));
    }
}
