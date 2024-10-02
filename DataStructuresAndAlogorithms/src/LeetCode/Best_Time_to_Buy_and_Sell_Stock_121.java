package LeetCode;

public class Best_Time_to_Buy_and_Sell_Stock_121 {
	/*
	 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
	 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
	 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
	 * Input: prices = [7,1,5,3,6,4]	Output: 5
	 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
	 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
	 */
	public int calcMethod(int[] arr){
		/*create arrays while iterating the 1st fill it with max future-> 1->5->6
		public int findMaxProfit(int[] prices) {
        int min[]=new int [prices.length];//min array is not needed
        int maxProfit=Integer.MIN_VALUE;
        Arrays.fill(min,prices[0]);
        for (int i=0;i<prices.length;i++){
            if (min[i]>prices[i]){
                Arrays.fill(min,i,min.length,prices[i]);
            }
            if (prices[i]-min[i]>maxProfit){
                maxProfit=prices[i]-min[i];
            }
        }
        return maxProfit;
    	}
    	*/
		int lowestPurchase=Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i=0;i<arr.length; i++){
			if (arr[i]<lowestPurchase){
				lowestPurchase=arr[i];
			}
			int profit= arr[i]-lowestPurchase;
			if (maxProfit<profit){
				maxProfit=profit;
			}
		}
		return maxProfit;
	}
	
	public static void main(String args[]) {
        int[] input ={3, 10, 11, 12, 14, 16, 19};
		Best_Time_to_Buy_and_Sell_Stock_121 ob = new Best_Time_to_Buy_and_Sell_Stock_121();
        System.out.println(ob.calcMethod(input));
    }

}
