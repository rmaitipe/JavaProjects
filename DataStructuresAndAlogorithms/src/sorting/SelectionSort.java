package sorting;

/*
 * Find minimum of unsorted array and move to sorted section - repeat O(n^2)
 * Similar to bubble sort
 */
public class SelectionSort {
	
	void selectionSort(int arr[]) {
        int n = arr.length;
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j;
            // Swap the found minimum element with the first element
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        } 
    } 

	public static void main(String args[]) {
		SelectionSort ob = new SelectionSort();
        int arr[] = {23, 34, 15, 12, 22, 10, 0};
        ob.selectionSort(arr);
        System.out.println("Sorted array");
        ob.printArray(arr);
    }
	
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i) {
        	System.out.print(arr[i] + " ");
        }
    }
}
