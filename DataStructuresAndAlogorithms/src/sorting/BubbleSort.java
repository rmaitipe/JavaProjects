package sorting;

public class BubbleSort {

	/*
	 * O(n^2) because of two loops that iterate the collection. 
	 * Each turn the largest/smallest in unsorted section is bubbled to sorted section
	 */
	public void bubbleSort(int arr[]){
		int length = arr.length;
		int temp;
		for (int i=0;i<length-1;i++) {
			for (int j=0;j<length-i-1;j++) {
				if (arr[j]>arr[j+1]) {
					temp =arr[j];
					arr[j]=arr[j+1];
				arr[j+1]=temp;
				}
			}
		}
	}

	
	public static void main(String args[])
    {
        BubbleSort ob = new BubbleSort();
        int arr[] = {23, 34, 15, 12, 22, 10, 0};
        ob.bubbleSort(arr);
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
