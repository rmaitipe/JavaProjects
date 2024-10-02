package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Palindromic_Partitioning_131 {
    /*
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * Incomplete
     */

    public static void main(String args[]) {
        Palindromic_Partitioning_131 ob = new Palindromic_Partitioning_131();
        String input ="abc";
        System.out.print(ob.palindromePartition(input));
    }

    public List<String> palindromePartition(String s) {
        List<String> list=new ArrayList<>();
        if (s == null || s.length() < 1) return list; // edge case
        return iterString(s,list); // Return the longest palindrome substring
    }

    public List<String> iterString(String s, List<String> longestPali){
        if (s.length() == 1) {longestPali.add(s);}
        else {
            int[] arr = longestPalindromeAccepted(s);
            longestPali.add(s.substring(arr[0], arr[1]));
            if (arr[0]>0) {
                iterString(s.substring(0, arr[0]), longestPali);
            }
            if (arr[1]<s.length() - 1) {
                iterString(s.substring(arr[1], s.length() - 1), longestPali);
            }
        }
        return longestPali;
    }

    // Expands around the center and returns the length of the palindrome
    public int[] longestPalindromeAccepted(String s) {
        if (s == null || s.length() < 1) return null; // edge case
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);   // Check for odd length palindrome
            int len2 = expandAroundCenter(s, i, i + 1);   // Check for even length palindrome
            int len = Math.max(len1, len2);   // Get the max length

            if (len > end - start) {    // If we found a longer palindrome
                start = i - (len - 1) / 2;    // Update the start index
                end = i + len / 2;    // Update the end index
            }
        }
        return new int[] {start, end + 1};  // Return the longest palindrome substring
    }

    // Expands around the center and returns the length of the palindrome
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;  // Return the length of the palindrome
    }

}
