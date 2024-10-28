package LeetCode;

import java.util.Arrays;

public class Median_of_Two_Sorted_Arrays_004 {
    /*
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * The overall run time complexity should be O(log (m+n)).
     *
     * See Advanced
     */

    private int findMedianBasic(int[] arr1, int[] arr2) {
        int length=arr1.length+arr2.length;
        int[] arr3= Arrays.copyOf(arr1,length);//O(m+n)
        for (int i=0;i<arr2.length;i++){
            arr3[arr1.length+i]=arr2[i];
        }
        Arrays.sort(arr3);//log(m+n)
        if (length%2==0){
            return (arr3[(length/2)-1]+ arr3[length/2])/2;
        } else{
            return arr3[(length/2)];
        }
    }

    public static void main(String args[])    {
        int[] arr1 ={3, 10, 11, 13, 17, 19, 21};
        int[] arr2 = {12, 14, 15, 22, 25, 27};
        int[] arr3 ={1, 10, 11, 12, 14, 16};
        Median_of_Two_Sorted_Arrays_004 ob = new Median_of_Two_Sorted_Arrays_004();
        System.out.println(ob.findMedianBasic(arr1, arr2));
        System.out.println(ob.findMedianAdvanced(arr1, arr2));
        System.out.println(ob.findMedianBasic(arr1, arr3));
        System.out.println(ob.findMedianAdvanced(arr1, arr3));
    }

    /* figure out the split and use 2 pointers and keep moving until desired index O(m+n)*/
    private int findMedianAdvanced(int[] arr1, int[] arr2) {
        int length=arr1.length+arr2.length;//9->4  8->4
        int arr1Idx = 0, arr2Idx =0;
        int curr=0, prev=0;
        for (int i=0;i<=length/2;i++){//2 values base cases
            if (arr1[arr1Idx]<arr2[arr2Idx]){
                prev=curr;
                curr=arr1[arr1Idx];
                arr1Idx++;
            }else{
                prev=curr;
                curr=arr2[arr2Idx];
                arr2Idx++;
            }
        }
        if (length%2==0){
            return (curr+ prev)/2;
        } else{
            return curr;
        }
    }

    /* https://www.geeksforgeeks.org/median-of-two-sorted-arrays/
    * To find the median of the two sorted arrays, arr1[] and arr2[], we need the average of two middle
    * elements of merged sorted array. So, if we divide the merged array into two halves, then the median will be
    * (last element in first half + first element in second half) / 2.
    * The idea is to use Binary Search to find the valid partition in arr1[] say mid1, such that all elements of
    * arr1[0…mid1 – 1] will lie in the first half of the merged sorted array. Since, we know that first half of the
    * merged sorted array will have total n elements, the remaining mid2 = (n – mid1) elements will be from arr2[].
    * In other words, the first half of the merged sorted array will have all the elements in arr1[0…mid1 – 1] and arr2[0…mid2 – 1].
    *
    * How to check if the partition mid1 and mid2 is valid or not?
    * For mid1 and mid2 to be valid, we need to check for the following conditions:
    * All elements in arr1[0…mid1 – 1] should be less than or equal to all elements in arr2[mid2…n – 1]. Since both the subarrays are sorted individually,
    * we can check arr1[mid1 – 1] should be less than or equal to arr2[mid2].
    * All elements in arr2[0…mid2 – 1] should be less than or equal to all elements in arr1[mid1…n – 1]. Since both the subarrays are sorted individually,
    * we can check arr2[mid2 – 1] should be less than or equal to arr1[mid1].
    * For simplicity, take the element to the mid2 of partition mid1 as l1, so l1 = arr1[mid1 – 1] and element to the
    * right of partition mid1 as r1, so r1 = arr1[mid1]. Similarly, take the element to the mid2 of mid2 as l2, so
    * l2 = arr2[mid1 – 1] and element to the right of mid2 as r2, so r2 = arr2[mid2].
    * So, the above conditions can be simplified as l1 <= r2 and l2 <= r1.
    *
    * If the partition is not valid, we can have two cases:
    * If l1 > r2, this means that we have taken extra elements from arr1[], so take less elements by moving high = mid – 1.
    * If l2 > r1, this means that we have taken less elements from arr1[], so take more elements by moving low = mid + 1.
    * Time Complexity:The algorithm performs a binary search on the smaller array, leading to a time complexity of O(log(min(m,n))).
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length;//smaller
        int n = nums2.length;//larger
        int low = 0, high = m;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = (m + n + 1) / 2 - mid1;
            // Find elements to the left and right of partition in nums1
            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int r1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            // Find elements to the left and right of partition in nums2
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int r2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];
            if (l1 <= r2 && l2 <= r1) {//return condition
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

}