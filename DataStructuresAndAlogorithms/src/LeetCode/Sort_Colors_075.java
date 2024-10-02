package LeetCode;

import java.util.Arrays;

public class Sort_Colors_075 {
    /*
     * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same
     * color are adjacent, with the colors in the order red, white, and blue.
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     * You must solve this problem without using the library's sort function.
     * Input: nums = [2,0,2,1,1,0]  Output: [0,0,1,1,2,2]
     */

    public static void main(String args[]) {
        Sort_Colors_075 ob = new Sort_Colors_075();
        int[] colors = {2,0,2,1,1,0};
        System.out.println(Arrays.toString(ob.sortColors(colors)));
    }

    private int[] sortColors(int[] input) {
        int uB=0;//1st elem sorted
        int rB=-1; int wB=-1; int bB=-1;
        for (int i=1;i<input.length;i++){
            int curr=input[i];
            switch (curr){
                case 0:{
                    for (int j=uB;j>=rB;j--){
                        input[j+1]=input[j];
                    }
                    input[rB++]=curr;
                    if (wB != -1){wB++;}
                    if (wB != -1){bB++;}
                    uB++;
                    break;
                }
                case 1:
                    //
                    for (int j=uB;j>=wB;j--){
                        input[j+1]=input[j];
                    }
                    input[wB++]=curr;
                    bB++;uB++;
                    break;
                case 2: {//already in correct index
                    bB++;uB++;
                    break;
                }
                default:
                    break;
            }
        }
        return input;
    }

}
