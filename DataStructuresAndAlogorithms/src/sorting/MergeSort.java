package sorting;

import java.util.Arrays;

public class MergeSort {

	/*
	 * Divide & Conquer algorithm. Sort left side of array, sort right side of array, then merge the now sorted arrays.
	 * uses sub arrays O(n log n)
	 */
	public void mergeSort(int[] array, int left, int right) {
	    if (left >= right) return;
	    int mid = (left+right)/2;
	    mergeSort(array, left, mid);
	    mergeSort(array, mid+1, right);
	    merge(array, left, mid, right);
	}
	
	public static void main(String args[]) {
		MergeSort ob = new MergeSort();
        int arr[] = {23, 34, 15, 12, 22, 10, 0};
        ob.mergeSort(arr,0,arr.length-1);
        System.out.println("Sorted array"+ Arrays.toString(arr));
    }
    
    public void merge(int[] array, int left, int mid, int right) {
        // Creating temporary subarrays
        int[] leftArray = new int[mid - left + 1];
        int[] rightArray = new int[right - mid];

        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[left + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Copying from leftArray and rightArray back into array
        for (int i = left; i < right + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                   array[i] = leftArray[leftIndex];
                   leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}
