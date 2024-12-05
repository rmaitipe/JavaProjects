package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Longest_Common_Subsequence_1143 {
    /*
    Given two strings text1 and text2, return the length of their longest common subsequence.
    If there is no common subsequence, return 0.

    A subsequence of a string is a new string generated from the original string with some characters (can be none)
    deleted without changing the relative order of the remaining characters.
    Input: text1 = "abcde", text2 = "ace"  Output: 3
    Input: text1 = "abcdfgh", text2 = "ace"  Output: 2
    Input: text1 = "bcdfghe", text2 = "ace"  Output: 2
     */
    public static void main(String args[]) {
        Longest_Common_Subsequence_1143 ob = new Longest_Common_Subsequence_1143();
        System.out.println(ob.longestCommonSubsequenceBasic("kabcde","ace"));
        System.out.println(ob.longestCommonSubsequenceDPAccepted("kabcde","ace"));
        System.out.println(ob.longestCommonSubsequenceBasic("kabcde","dace"));
        System.out.println(ob.longestCommonSubsequenceDPTry("kabcde","dace"));
    }

    public int longestCommonSubsequenceBasic(String text1, String text2) {
        //text1<text2 //swap if not
        List<String> start = new ArrayList<>();
        int max=0;
        int indexOf = text1.indexOf(text2.charAt(0));
        while (indexOf != -1){//what if char not found??
            start.add(text1.substring(indexOf));
            indexOf=text1.indexOf(text2,indexOf+1);
         }
        for (String s : start) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(text2.charAt(0)));
            max=Math.max(max,backTrack(list, s.substring(1), text2, 1,1));
        }
        return max;//
    }

    private int backTrack(List<String>list,String text1, String text2, int indexT2,int seqLength){
        int max=0;
        if (indexT2==text2.length()) return seqLength;
        else{
            //String temp=text2.substring(indexT2);
            List<String> idx = new ArrayList<>();
            String key= String.valueOf(text2.charAt(indexT2));
            int indexOf = text1.indexOf(key);
            while (indexOf != -1){
                idx.add(text1.substring(indexOf));
                indexOf=text1.indexOf(key,indexOf+1);
            }
            for (String s : idx) {
                list.add(String.valueOf(text2.charAt(indexT2)));
                max=Math.max(max,backTrack(list, s.substring(1), text2, indexT2+1,seqLength+1));
            }
        }
        return max;
    }

    /*
    Dynamic Programming (DP):
    We create a 2D array dp to store the lengths of the longest common subsequences for all subproblems.
    The array dp has dimensions (length1 + 1) x (length2 + 1) to accommodate empty strings as substrings.
    The cell dp[i][j] represents the length of the longest common subsequence of substrings text1[0...i-1] and text2[0...j-1].
    We fill in the dp array from the bottom up using the following recurrence relation:
        If characters at indices i-1 and j-1 match, dp[i][j] = dp[i-1][j-1] + 1.
        If characters do not match, dp[i][j] = max(dp[i-1][j], dp[i][j-1]).
    Result:
        The bottom-right cell dp[length1][length2] contains the length of the longest common subsequence of the entire strings text1 and text2.
     */

    public int longestCommonSubsequenceDPAccepted(String text1, String text2) {
        // Lengths of the input strings
        int length1 = text1.length();
        int length2 = text2.length();
        // Create a 2D array to store the lengths of longest common subsequences
        // for all subproblems, initialized with zero
        int[][] dp = new int[length1 + 1][length2 + 1];
        // Build the dp array from the bottom up
        for (int i = 1; i <= length1; ++i) {
            for (int j = 1; j <= length2; ++j) {
                // If characters match, take diagonal value and add 1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // If characters do not match, take the maximum value from
                // the left (dp[i][j-1]) or above (dp[i-1][j])
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // The bottom-right cell contains the length of the longest
        // common subsequence of text1 and text2
        return dp[length1][length2];
    }

    public int longestCommonSubsequenceDPTry(String text1, String text2) {
        // Lengths of the input strings
        int length1 = text1.length();
        int length2 = text2.length();
        if(length2>length1) {
            String temp=text1;
            text1 = text2;
            text2 = temp;
        }
        int[] dp = new int[length1];
        for (int i = 0; i < length2; i++) {
            String str= text2.substring(i,i+1);
            int idxOf=text1.lastIndexOf(str);
            while (idxOf!=-1){
                int max=0;
                for (int k=idxOf-1;k>0;k--){//scan left -can this be improved?
                    max=Math.max(max,dp[k]);
                }
                if (max==0){
                    dp[i]=1;
                } else{
                    dp[i]=max+1;
                }
                idxOf=text1.substring(0,idxOf-1).lastIndexOf(str);
            }
        }
        // The bottom-right cell contains the length of the longest
        // common subsequence of text1 and text2
        return Arrays.stream(dp).max().getAsInt();
    }

}
