package LeetCode;

import java.util.Arrays;

public class Perfect_Squares_279 {

    /*
     * Given an integer n, return the least number of perfect square numbers that sum to n.
     * A perfect square is an integer that is the square of an integer; in other words,
     * it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
     */

    public static void main(String args[]) {
        Perfect_Squares_279 ob = new Perfect_Squares_279();
        System.out.println(ob.numSquares(12));
        System.out.println(ob.numSquaresAcceptedDP(12));
    }

    private int numSquares(int n) {
        int rep= (int) Math.sqrt(n);
        if (rep*rep==n) {
            return 1;
        }
        else if (n<4) {
            return n;
        } else {
            int a= (int) Math.pow(rep-1,2);
            int b= (int) Math.pow(rep,2);
            if (b>0) {
                return Math.min(numSquares(n - a) + 1, numSquares(n - b) + 1); //12->3 //8
            } else{
                return numSquares(n - a) + 1;
            }
        }
    }

    /*
    Utilizes dynamic programming to find the minimum number of perfect squares that sum up to the given number n.
    Initializes an array dp of size n + 1 where each element is initialized to INT_MAX,
    except dp[0] which is set to 0 since it doesn't require any perfect squares to represent.
    Iterates from 1 to n to calculate the minimum number of perfect squares required for each number.
    For each number i, iterates over all perfect squares less than or equal to i (from 1 to sqrt(i)) to
    find the minimum number of perfect squares required.
    Updates dp[i] with the minimum value found by comparing it with the current value of dp[i - j * j] + 1,
    where j * j represents the perfect square being considered.
    0,1,2,3,1,2,3,4,2,1,3,3....
     */
    public int numSquaresAcceptedDP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int min_val = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                System.out.println("i: "+i+" j: "+j+" dp: "+(i - (j * j))+" dpIndex+1: "+(dp[i - j * j]+1));
                min_val = Math.min(min_val, dp[i - j * j] + 1);//i=12, j=1 dp11+1=4 j=2 dp7+1=5 j=3 dp3+1=4
            }
            dp[i] = min_val ;
        }
        return dp[n];
    }


}
