package LeetCode;

public class Longest_Palindrome_Substring_005 {
    /*
     * Given a string s, return the longest palindromic substring in s.
     * Input: s = "babad"    Output: "bab" "aba" is also a valid answer.
     *
     * Find O(n2) solution
     */

    private String longestPalindromeBasic(String input) {
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

    private String isPalindromeCentricIncorrect(int i,String input){
        int leftMax=0;
        int rightMax=input.length()-1;
        int shift=1;
        StringBuilder sb= new StringBuilder();
        sb.append(input.charAt(i));
        while (i+shift<=rightMax && i-shift>=leftMax && input.charAt(i+shift)==input.charAt(i-shift)){
            sb.append(input.charAt(i+shift)) ;
            sb.insert(0,input.charAt(i-shift));
            shift++;
        }
        return sb.toString();
    }
}
