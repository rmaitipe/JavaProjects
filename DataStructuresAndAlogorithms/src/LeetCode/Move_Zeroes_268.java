package LeetCode;

import java.util.Arrays;

public class Move_Zeroes_268 {
    /*
     * Given an integer array, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     * Input: nums = [0,1,0,3,12] Output: [1,3,12,0,0]
     *
     * O(n2) solution vs accepted O(n)
     */

    public static void main(String args[]) {
        Move_Zeroes_268 ob = new Move_Zeroes_268();
        int [] arr={0,1,0,3,12};
        ob.moveZeroMethod(arr);
        int [] nums = {0};
        ob.moveZeroMethod(nums);
        for (int a:arr){System.out.println(a);};
        int [] arr1={0,0,8,0,7,0,5};
        System.out.println(Arrays.toString(ob.moveZeroesAccepted(arr1)));
        int [] arr2={0,0,8,0,7,0,5};
        System.out.println(Arrays.toString(ob.moveZeroesAccepted2(arr2)));
    }

    private int[] moveZeroMethod(int[] arr) {
        int indexNonZeroL = -1;
        int indexZeroL = -1;
        for (int i=0;i< arr.length;i++){
            if (arr[i]==0){
                indexZeroL=i;
                for (int j=i+1;j<arr.length;j++){
                    if (arr[j]!=0){
                        indexNonZeroL=j;
                        break;
                    }
                }
            }
            if (indexNonZeroL!=-1 && indexZeroL!=-1) {
                int tmp = arr[indexZeroL];
                arr[indexZeroL] = arr[indexNonZeroL];
                arr[indexNonZeroL] = tmp;
                indexNonZeroL=-1;indexZeroL=-1;
            }
        }
        return arr;
    }

    /*
    Initialize two pointers, i and j, both pointing to the 0 index. Iterate through the array with the pointer j.
    If nums[j] is not zero, swap nums[i] and nums[j] and increment both i and j.
    At the end of the iteration, all non-zero elements will be moved to the front of the array, and the remaining
    elements from i to the end will be zeros.
    [i=0,j=0]80705      //swap   80705
    [i=1,j=1]80705*
    [i=1,j=2]87705      //swap   87005
    [i=2,j=3]87705*
    [i=2,j=4]87505      //swap	 87500
    k=3 -> 4
     */

// Shift non-zero values as far forward as possible
// Fill remaining space with zeros
    public int[] moveZeroesAccepted(int[] nums) {
        int i =0;
        for (int j =0; j<nums.length; j++){
            if(nums[j] != 0){
                nums[i] = nums[j];
                i++;
            }
        }
        for(int k = i; k<nums.length; k++){
            nums[k] = 0;
        }
        return nums;
    }


    public int[] moveZeroesAccepted2(int[] nums) {
        int i = 0; int temp=0;
        for (int j =0; j<nums.length; j++){//88300[7820]
            if(nums[j] != 0){
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        return nums;
    }
}
