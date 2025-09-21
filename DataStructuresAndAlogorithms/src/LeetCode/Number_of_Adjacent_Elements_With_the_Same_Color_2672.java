package LeetCode;

import java.util.Arrays;

public class Number_of_Adjacent_Elements_With_the_Same_Color_2672 {
    /*
     * You are given an integer n representing an array colors of length n where all elements are set to 0's meaning uncolored.
     * You are also given a 2D integer array queries where queries[i] = [indexi, colori]. For the ith query:
     * Set colors[indexi] to colori.
     * Count the number of adjacent pairs in colors which have the same color (regardless of colori).
     * Return an array answer of the same length as queries where answer[i] is the answer to the ith query.
     * Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]] Output: [0,1,1,0,2]
     */

    public static void main(String args[]) {
        Number_of_Adjacent_Elements_With_the_Same_Color_2672 ob = new Number_of_Adjacent_Elements_With_the_Same_Color_2672();
        int n=4;
        int[][] scores = new int[5][2];
        scores[0] = new int[]{0,2};
        scores[1] = new int[]{1,2};
        scores[2] = new int[]{3,1};
        scores[3] = new int[]{1,1};
        scores[4] = new int[]{2,1};
        System.out.println(Arrays.toString(ob.countInterval(n, scores)));
        System.out.println(Arrays.toString(ob.colorTheArrayAccepted(n, scores)));
    }

    public int[] countInterval(int n, int[][] query) {
        int[] queryRet = new int [query.length];
        int[] arr =new int[n];
        int counter=0;
        for (int i=0; i<query.length;i++) {
            int kIdx= query[i][0];
            //check both sides for delinking -> update count - 1
            if ((kIdx>=1 && arr[kIdx-1] !=query[i][1] && arr[kIdx-1]!=0)) {//incoming value has to be different
                counter--;
            }
            if (( kIdx<arr.length-1 && arr[kIdx+1]!=query[i][1] && arr[kIdx+1]!=0)) {//incoming value has to be different
                counter--;
            }
            arr[kIdx]=query[i][1];
            //check both sides for linking -> update count + 1
            if ((kIdx>=1 && arr[kIdx-1] ==query[i][1] && arr[kIdx-1]!=0)) {
                counter++;
            }
            if ((kIdx<arr.length-1 && arr[kIdx+1]==query[i][1] && arr[kIdx+1]!=0)) {
                counter++;
            }
            queryRet[i]=counter;
        }
        return queryRet;
    }

    public int[] colorTheArrayAccepted(int n, int[][] queries) {
        int[] nums = new int[n], result = new int[queries.length];
        int c = 0;
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0], color = queries[i][1];
            int pre = (index > 0) ? nums[index - 1] : 0;
            int nex = (index < n-1) ? nums[index + 1] : 0;

            if (nums[index] != 0 && nums[index] == pre) c--;
            if (nums[index] != 0 && nums[index] == nex) c--;
            nums[index] = color;
            if (nums[index] == pre) c++;
            if (nums[index] == nex) c++;
            result[i] = c;
        }
        return result;
    }
}
