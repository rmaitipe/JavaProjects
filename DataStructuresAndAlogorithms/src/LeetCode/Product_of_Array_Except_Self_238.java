package LeetCode;

import java.util.Arrays;

public class Product_of_Array_Except_Self_238 {
    /*
     * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the
     * elements of nums except nums[i]. The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
     * You must write an algorithm that runs in O(n) time and without using the division operation.
     * Input: nums = [1,2,3,4]  Output: [24,12,8,6]
     * Input: nums = [-1,1,0,-3,3]  Output: [0,0,9,0,0]
     * Yahoo :]
     */
    private int[] findProducts(int[] input) {
        int[] output=new int [input.length];
        int[] outputL=new int [input.length];
        outputL[0]=input[0];
        int[] outputR=new int [input.length];
        outputR[input.length-1]=input[input.length-1];
        for (int i=1;i<input.length;i++){
           outputL[i]=input[i]*outputL[i-1];
        }
        for (int j=input.length-2;j>=0;j--){
            outputR[j]=input[j]*outputR[j+1];
        }
        for (int k=0;k<input.length;k++){
            if (k == 0) {
                output[k]=outputR[k+1];
            }else if (k==input.length-1){
                output[k]=outputL[k-1];
            } else {
                output[k] = outputL[k - 1] * outputR[k + 1];
            }
        }
        return output;
    }

    public static void main(String args[]) {
        Product_of_Array_Except_Self_238 ob = new Product_of_Array_Except_Self_238();
        int[] input= {-1,1,0,-3,3};
        System.out.println(Arrays.toString(ob.findProducts(input)));
    }

    /*
     * The logic is, we don't actually need separate array to store prefix product and suffix products,
     * we can do all the approach discussed directly onto our final answer array.
     */
    public int[] productExceptSelfAccepted(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        Arrays.fill(ans, 1);
        int curr = 1;
        for(int i = 0; i < n; i++) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for(int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        return ans;
    }

}
