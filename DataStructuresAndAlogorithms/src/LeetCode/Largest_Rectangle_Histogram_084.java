package LeetCode;


public class Largest_Rectangle_Histogram_084 {
	/* See 739
	 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
	 * return the area of the largest rectangle in the histogram.
	 *
	 * Input: heights = [2,1,5,6,2,3]	Output: 10
	 */
	public int largestRectHist(int[] input) {
		int[] minHeightTracker=new int [input.length];
		int maxCalc=0;
		minHeightTracker[0]=input[0];// length, height, area
		for (int i=1;i<input.length;i++){
			minHeightTracker[i]=input[i];
			int a=0;
			for (int j=i-1;j>=0;j--) {
				 a = Math.max(a,(i-j+1) * Math.min(minHeightTracker[j], input[i]));
				 // if height < j update j
				 if (minHeightTracker[j]> input[i]){
					 minHeightTracker[j]=input[i];
				 }
			}
			int b = minHeightTracker[i];//current height
			int area = Math.max(b, a);
			maxCalc = Math.max(maxCalc, area);
		}
		return maxCalc;
	}

	public static void main(String args[]) {
		Largest_Rectangle_Histogram_084 ob = new Largest_Rectangle_Histogram_084();
		int[] input = new int[]{2,1,5,6,2,3};
		System.out.println(ob.largestRectHist(input));
		System.out.println(ob.largestRectangleAreaAccepted(input));
		System.out.println(ob.largestRectangleAreaAcceptedAlt(input));
	}

	/*
	For any i coordinate we know utmost higher (or of the same height) neighbors to the right and to the left, we can easily find the largest rectangle:
	int maxArea = 0;
	for (int i = 0; i < height.length; i++) {
        lessFromLeft = i;
        lessFromRight = i;
        while (lessFromLeft >0 && height[lessFromLeft] >= height[i]) {
		  lessFromLeft--;
	    }
	    while (lessFromRight < height.length && height[lessFromRight] >= height[i]) {
		  lessFromRight++;
	    }
		maxArea = Math.max(maxArea, height[i] * (lessFromRight - lessFromLeft - 1));
	}
	The only line change shifts this algorithm from O(n^2) to O(n) complexity: we don't need to rescan each item to the
	left - we can reuse results of previous calculations and "jump" through indices in quick manner:
	while (p >= 0 && height[p] >= height[i]) {
		  p = lessFromLeft[p];
	}
	 */
	public int largestRectangleAreaAccepted(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
		int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
		lessFromRight[height.length - 1] = height.length;
		lessFromLeft[0] = -1;

		for (int i = 1; i < height.length; i++) {
			int p = i - 1;
			while (p >= 0 && height[p] >= height[i]) {
				p = lessFromLeft[p];
			}
			lessFromLeft[i] = p;
		}

		for (int i = height.length - 2; i >= 0; i--) {
			int p = i + 1;
			while (p < height.length && height[p] >= height[i]) {
				p = lessFromRight[p];
			}
			lessFromRight[i] = p;
		}
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
		}
		return maxArea;
	}

	public int largestRectangleAreaAcceptedAlt(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int global=-1;
		int[] lessFromLeft = new int[height.length];
		int[] lessFromRight = new int[height.length];
		for (int i=0; i< height.length;i++){
			int left =i;
			while (left>0 && height[left]>=height[i]){
				left--;
			}
			lessFromLeft[i]=i-left;
		}
		for (int i=0; i< height.length;i++){
			int right =i;
			while (right<height.length && height[right]>=height[i]){
				right++;
			}
			lessFromRight[i]=right-i;
		}
		for (int i=0; i< height.length;i++){
			int val= height[i];
			int area= (lessFromRight[i]-lessFromLeft[i]+1)*val;
			global = Math.max(area, global);
		}
		return global;
	}
}
