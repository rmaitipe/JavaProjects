package LeetCode;

import java.util.Arrays;

public class Coin_Change_322 {
    /*
     * You are given an integer array coins representing coins of different denominations and an integer amount
     * representing a total amount of money. Return the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     * You may assume that you have an infinite number of each kind of coin.
     *
     * See 279
     */

    public static void main(String args[]) {
        Coin_Change_322 ob = new Coin_Change_322();
        int[] coins = {1,2,5};
        int amount=11;
        int[] coins2 = {1,4,9};
        int amount2=12;
        System.out.println(ob.coinChangeBasic(coins,amount));
        System.out.println(ob.coinChangeAcceptedDP(coins,amount));
        System.out.println(ob.coinChangeBasic(coins2,amount2));
        System.out.println(ob.coinChangeAcceptedDP(coins2,amount2));
    }

    private int coinChangeBasic(int[] num, int amount) {
        int count=0;
        for (int i=num.length-1;i>=0;i--){//for each coin type starting from the largest denomination
            int coinType=num[i];
            if (amount<coinType){
                //amount<coinType
            } else {
                int div = amount / coinType;
                amount = amount % coinType;
                if (div != 0) {
                    count = count + div;
                    if (amount == 0) {
                        break;
                    }
                }
            }
        }
        return amount==0?count:-1;
    }

    /*
     * Initialize min_coins array:
     *     Create an array min_coins of length amount + 1, initialized with each element set to amount + 1.
     *     Set min_coins[0] to 0, as it takes zero coins to make up an amount of zero.
     * Iterate over each amount:
     *     Start a loop from 1 to amount (inclusive) to represent each amount from 1 to amount.
     *     For each amount i, iterate over each coin denomination c in the coins list.
     * Inner loop Calculate the minimum number of coins:
     *     Check if the current amount i minus the coin denomination c is greater than or equal to 0.
     *     If it is, update min_coins[i] to the minimum of its current value and 1 + min_coins[i - c].
     *     1 + min_coins[i - c] represents taking one coin of denomination c and the minimum number of coins required
     *     to make up the remaining amount i - c.
     * Return the result:
     *     After updating min_coins for all amounts from 1 to amount, return min_coins[-1] if it's not equal to amount + 1.
     *     If min_coins[amount] is still amount + 1, it means the amount cannot be made up by any combination of coins, so return -1.

     */
    public int coinChangeAcceptedDP(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        /* We initialize each value with amount + 1. It can be a number greater than amount or negative number such as -1*/
        Arrays.fill(minCoins, amount + 1);
        minCoins[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i - coins[j]]);
                }
            }
        }
        return minCoins[amount] != amount + 1 ? minCoins[amount] : -1;
    }
}
