package LeetCode;

import java.util.Stack;

public class Longest_Valid_Parentheses_032 {
    /*
     * Given a string containing just the characters '(' and ')', return the length of the longest
     * valid (well-formed) parentheses substring
     * Input: s = ")()())"  Output: 4
     * Input: s = ")(()))"  Output: 4
     *
     * Not worth going DP route for logic clarity
     */

    private int longestParentheses(String input) {
        int maxCount = 0;
        int left = 0;
        while (left < input.length()) {
            if (input.charAt(left) == ')') {
                left++;
            } else {
                Stack<Character> stack = new Stack();
                stack.push('(');
                int count = 0;
                for (int j = left + 1; j < input.length(); j++) {
                    if (input.charAt(j) == ')') {
                        if (!stack.isEmpty() && stack.peek() == '(') {
                            stack.pop();
                            count += 2;
                        } else {
                            break;//mismatch
                        }
                    } else {
                        stack.push('(');
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                }
                left = left + count + stack.size();
            }
        }
        return maxCount;
    }

    public static void main(String args[]) {
        Longest_Valid_Parentheses_032 ob = new Longest_Valid_Parentheses_032();
        String input = ")()())";
        System.out.println(ob.longestParentheses(input));
        System.out.println("Accepted: "+ob.longestValidParenthesesAcceptedDP(input));
        String input2 = ")(()))";
        System.out.println(ob.longestParentheses(input2));
        System.out.println("Accepted: "+ob.longestValidParenthesesAcceptedDP(input2));
    }

    /*
     * Use a DP array dp where dp[i] represents the length of the longest valid parentheses substring ending at index i.
     * Initialize the DP array with zeros, since no valid substrings are found initially.
     * Filling the DP Array: Traverse the string s from left to right. For each character s[i]:
     * If s[i] is a closing parenthesis ')' and there is a matching opening parenthesis '(' before it, update array:
     *  If s[i-1] is an opening parenthesis '(' then dp[i] = dp[i-2] + 2.
     *  If s[i-1] is a closing parenthesis ')' and s[i-dp[i-1]-1] is an opening parenthesis '(' then dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2].
     */
    public int longestValidParenthesesAcceptedDP(String s) {
        int n=s.length();
        int[] dp=new int[n];
        int max=0;
        for(int i=1;i<n;i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i]=((i>=2)?dp[i-2]:0)+2;
                }
                else if(i-dp[i-1]>0 && s.charAt(i-dp[i-1]-1)=='('){
                    dp[i]=dp[i-1]+2+(i-dp[i-1]>1?dp[i-dp[i-1]-2]:0);
                }
                max=Math.max(max,dp[i]);
            }
        }
        return max;
    }
}
