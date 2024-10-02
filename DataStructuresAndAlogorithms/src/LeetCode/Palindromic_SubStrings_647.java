package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Palindromic_SubStrings_647 {
    /*
     * Given a string s, return the number of palindromic substrings in it.
     * Input: s = "abc"    Output: 3   Explanation: Three palindromic strings: "a", "b", "c".
     * Input: s = "aaa"    Output: 6   Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     */

    public static void main(String args[]) {
        Palindromic_SubStrings_647 ob = new Palindromic_SubStrings_647();
        String input ="abc";
        System.out.print(ob.palindromeSubStrings(input));
        System.out.print(ob.palindromeSubStrings("aaa"));
        System.out.print(ob.longestPalindrome005AcceptedModified("aaa"));
    }

    private int palindromeSubStrings(String input) {
        int count=0;
        for (int i=0;i<input.length();i++){
            count+=expandCenter(input,i);
        }
        return count;
    }

    private int expandCenter(String input,int index) {
        int localCount=1;
        int i=1;
        while( index-i >0 && index+i<input.length()){
            if (input.charAt(index-i)==input.charAt(index+i)){
                localCount++;
                i++;
            }
        }
        return localCount;
    }

    public int longestPalindrome005AcceptedModified(String s) {
        List<String> list=new ArrayList<>();
        if (s == null || s.length() < 1) return 0; // edge case
        for (int i = 0; i < s.length(); i++) {
            list.addAll(expandAroundCenter(s, i, i));   // Check for odd length palindrome
            list.addAll(expandAroundCenter(s, i, i + 1));   // Check for even length palindrome
        }
        return list.size();  // Return the longest palindrome substring
    }

    // Expands around the center and returns the length of the palindrome
    private List<String> expandAroundCenter(String s, int left, int right) {
        List<String> retVal=new ArrayList<>();
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            retVal.add(s.substring(left, right));
            left--;
            right++;

        }
        return retVal;  // Return the list of palindrome
    }

}
