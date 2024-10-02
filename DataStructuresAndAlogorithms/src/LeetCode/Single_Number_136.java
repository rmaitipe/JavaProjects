package LeetCode;

public class Single_Number_136 {
    /*
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * Input: nums = [2,2,1]        Output: 1
     * Input: nums = [4,1,2,1,2]    Output: 4
     */

    public static void main(String args[]) {
        Single_Number_136 ob = new Single_Number_136();
        int[] input= {64,1,2,1,2};
        System.out.println(ob.findSingular(input));
    }

    private int findSingular(int[] input) {
        int retVal=input[0];
        for (int i=1;i<input.length;i++){
            retVal=retVal^input[i];
        }
        return retVal;
    }

}
