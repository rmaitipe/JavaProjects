package LeetCode;

import java.util.Arrays;

public class Median_of_Two_Sorted_Arrays_004 {

    private int findMedianBasic(int[] arr1, int[] arr2) {
        int length=arr1.length+arr2.length;
        int[] arr3= Arrays.copyOf(arr1,length);
        for (int i=0;i<arr2.length;i++){
            arr3[arr1.length+i]=arr2[i];
        }
        Arrays.sort(arr3);
        if (length%2==0){
            return  (arr3[(length/2)-1]+ arr3[length/2])/2;
        } else{
            return arr3[(length/2)];
        }
    }
    /* figure out the split and use 2 pointers and keep moving until desired index*/

    public static void main(String args[])    {
        int[] arr1 ={3, 10, 11, 13, 17, 19, 21};
        int[] arr2 = {12, 14, 15, 22, 25, 27};
        int[] arr3 ={3, 10, 11, 12, 14, 16};
        Median_of_Two_Sorted_Arrays_004 ob = new Median_of_Two_Sorted_Arrays_004();
        System.out.println(ob.findMedianBasic(arr1, arr2));
        System.out.println(ob.findMedianAdvanced(arr1, arr2));
        System.out.println(ob.findMedianBasic(arr1, arr3));
        System.out.println(ob.findMedianAdvanced(arr1, arr3));
    }

    private int findMedianAdvanced(int[] arr1, int[] arr2) {
        int length=arr1.length+arr2.length;//9->4  8->4
        int left = 0, right =0;
        int curr=0, prevCurr=0;
        for (int i=0;i<=length/2;i++){//2 values base cases
            if (arr1[left]<arr2[right]){
                prevCurr=curr;
                curr=arr1[left];
                left++;
            }else{
                prevCurr=curr;
                curr=arr2[right];
                right++;
            }
        }
        if (length%2==0){
            return  (curr+ prevCurr)/2;
        } else{
            return curr;
        }
    }
}