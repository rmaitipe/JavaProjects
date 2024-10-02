package LeetCode;

import java.util.*;

public class Contains_Duplicate_217 {
    /*
     * Given an integer array nums, return true if any value appears at least twice in the array,
     * and return false if every element is distinct.
     */
    private boolean findDuplicates(int[] prices) {
        boolean retVal=true;
        Arrays.sort(prices);
        for (int i=1;i<prices.length;i++){
            if (prices[i]==prices[i-1]){
                retVal=false;
                break;
            }
        }
        return retVal;
        //Use a HashSet
        //Use XOR
    }

    public static void main(String args[])    {
        int[] prices ={-10, 7, 10, 11, 12, -40, 16, 19};
        Contains_Duplicate_217 ob = new Contains_Duplicate_217();
        System.out.println(ob.findDuplicates(prices));
    }
}
