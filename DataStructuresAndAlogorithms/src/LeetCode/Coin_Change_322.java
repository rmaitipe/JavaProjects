package LeetCode;

public class Coin_Change_322 {
    /*
     * You are given an integer array coins representing coins of different denominations and an integer amount
     * representing a total amount of money. Return the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     * You may assume that you have an infinite number of each kind of coin.
     */

    public static void main(String args[]) {
        Coin_Change_322 ob = new Coin_Change_322();
        int[] coins = {1,2,5};
        int amount=11;
        System.out.println(ob.coinChange(coins,amount));
    }

    private int coinChange(int[] num,int amount) {
        int count=0;
        for (int i=num.length-1;i>=0;i--){//for each cointype starting from largest denom
            int coinType=num[i];
            int div=amount/coinType;
            amount=amount%coinType;
            if (div!=0){
                count=count+div;
                if (amount==0){
                    break;
                }
            }
        }
        return amount==0?count:-1;
    }

    public int coinChangeAcceptedDP(int[] coins, int amount) {
        if (amount<1)
            return 0;
        int dp[]=new int[amount+1];
        for(int i=1;i<=amount;i++){
            dp[i]=Integer.MAX_VALUE;
            for(int coin: coins){
                if(coin<=i && dp[i-coin]!=Integer.MAX_VALUE){
                    dp[i]=Math.min(dp[i],1+dp[i-coin]);
                }
            }
        }
        if(dp[amount]==Integer.MAX_VALUE)
            return -1;
        return dp[amount];
    }
}
