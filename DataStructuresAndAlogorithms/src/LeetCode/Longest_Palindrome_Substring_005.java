package LeetCode;

public class Longest_Palindrome_Substring_005 {
    /*
     * Given a string s, return the longest palindromic substring in s.
     * Input: s = "babad"    Output: "bab" "aba" is also a valid answer.
     *
     * Find O(n2) solution
     * Manacher's Algorithm O(n)-not expected
     */

    private String longestPalindromeBasic(String input) { //O(n^3)
        String output="";
        for (int i=0;i<input.length();i++){
            for (int j=i;j<input.length();j++){
                String inner =input.substring(i, j);
                if (isPalindrome(inner)){
                    if (output.length()<inner.length()){
                        output=inner;
                    }
                }
            }
        }
        return output;
    }

    private boolean isPalindrome(String substring) {
        boolean retVal=true;
        for (int i=0;i<substring.length()/2;i++){
            if (substring.charAt(i)!=substring.charAt(substring.length()-i-1)){
                retVal=false;
                break;
            }
        }
        return retVal;
    }

    public static void main(String args[]) {
        Longest_Palindrome_Substring_005 ob = new Longest_Palindrome_Substring_005();
        String input = "babbad";//babad
        System.out.println(ob.longestPalindromeBasic(input));
        System.out.println(ob.longestPalindromeAccepted(input));
        System.out.println(ob.longestPalindromeAcceptedDP(input));
    }

    /*
     * We initialize start and end indices to keep track of the longest palindrome found.
     * For each index i, we consider two cases:
     * A palindrome with i as the center (odd length).
     * A palindrome with i and i+1 as the center (even length).
     * Update start and end if the length of the palindrome centered at i is larger than the previously found palindrome.
     */
    public String expand(String s,int left, int right) {
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }

    public String longestPalindromeAccepted(String s) {
        if (s == null || s.length() < 1) return ""; // edge case
        int start = 0, end = 0;//keeps track of current subString
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);   // Check for odd length palindrome
            int len2 = expandAroundCenter(s, i, i + 1);   // Check for even length palindrome
            int len = Math.max(len1, len2);   // Get the max length
            if (len > end - start) {            // If we found a longer palindrome
                start = i - len / 2;      // Update the start index   i - (len - 1) / 2;
                end = i + len / 2;              // Update the end index
            }
        }
        return s.substring(start, end + 1);  // Return the longest palindrome substring
    }

    // Expands around the center and returns the length of the palindrome
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;  // Return the length of the palindrome
    }

/*
To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating palindromes.
Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome
since the two left and right end letters are the same.
Algorithm :
    We initialize a boolean table dp and mark all the values as false.
    We will use a variable max_len to keep track of the maximum length of the palindrome.
    We will iterate over the string and mark the diagonal elements as true as every single character is a palindrome.
    Now, we will iterate over the string and for every character we will expand around its center.
    For odd length palindrome, we will consider the current character as the center and expand around it.
    For even length palindrome, we will consider the current character and the next character as the center and expand around it.
    We will keep track of the maximum length and the maximum substring.
 */
    public String longestPalindromeAcceptedDP(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); ++i) {
            dp[i][i] = true;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
