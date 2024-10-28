package sorting;

public class InsertionSort {

	/*
	 * Divides to sorted and unsorted section at start - loops iterate on opposite directions. O(n^2)
	 * Selection sort finds the smallest element in the unsorted portion of the array and swaps it to the front,
	 * while insertion sort iteratively takes an element from the unsorted portion and places it in its correct position
	 * within the already sorted part of the array
	 */
	public void insertionSort(int arr[]){
		int length = arr.length;
		int temp;
	    for (int i = 1; i < length; i++) {
	        for (int j = i; j > 0; j--) {
	            if (arr[j-1] > arr[j]) {
	                temp = arr[j];
	                arr[j] = arr[j-1];
	                arr[j-1] = temp;
	            }
	        }
	    }
	}
	
	public static void main(String args[])
    {
		InsertionSort ob = new InsertionSort();
        int arr[] = {23, 34, 15, 12, 22, 10, 0};
        ob.insertionSort(arr);
        System.out.println("Sorted array");
        ob.printArray(arr);
    }
	
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i) {
        	System.out.print(arr[i] + " ");
        }
    }
}
