package LeetCode;

import java.util.Arrays;

public class Maximum_Profit_121 {

	/*
	 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
	 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
	 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
	 */
	public int calcMethod(int[] arr, int day){
		/*create arrays while iterating the 1st fill it with max future-> 1->5->6
		int[] trackMax= Arrays.copyOf(arr,arr.length);
		Arrays.fill(trackMax,Integer.MIN_VALUE);
		for (int i: arr){
			for (int j=0; j<i;j++) {
				if (trackMax[j] < arr[i]){
					trackMax[j]=arr[i];
				}
			}
		}*/
		int lowestPurchase=Integer.MAX_VALUE;
		int profit;
		int maxProfit = 0;
		for (int i=0;i<arr.length; i++){
			if (arr[i]>lowestPurchase){
				lowestPurchase=arr[i];
			}
			profit= arr[i]-lowestPurchase;
			if (maxProfit<profit){
				maxProfit=profit;
			}
		}
		return maxProfit;
	}

	
	public static void main(String args[])
    {
        int[] input ={7,1,5,3,6,4};
		int day =5;
		Maximum_Profit_121 ob = new Maximum_Profit_121();
        System.out.println(ob.calcMethod(input,day));
    }

}
