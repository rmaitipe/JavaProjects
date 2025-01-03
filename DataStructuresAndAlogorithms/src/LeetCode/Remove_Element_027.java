package LeetCode;

public class Remove_Element_027 {

    /*
    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
    The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
    Input: nums = [0,1,2,2,3,0,4,2], val = 2  Output: 5, nums = [0,1,4,0,3,_,_,_]
     */
    public static void main(String args[])    {
        int[] prices ={0,1,2,2,3,0,4,2};
        int[] prices2 ={0,1,2,2,3,0,4,2};
        Remove_Element_027 ob = new Remove_Element_027();
        System.out.println(ob.removeDuplicates(prices,2));
        System.out.println(ob.removeElementAccepted(prices2,2));
    }

    private int removeDuplicates(int[] prices, int val) {
        int right= prices.length-1;
        int count=0;
        for (int i=0;i<=right;i++){
            if (prices[i]==val){
                while (prices[right]==val && right>i) {
                    prices[right]=0;
                    right--;
                }
                if (prices[right]!=val) {
                    prices[i] = prices[right];
                    prices[right] = val;
                    count++;
                }
            } else{
                count++;
            }
        }
        return count;
    }

    public int removeElementAccepted(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
