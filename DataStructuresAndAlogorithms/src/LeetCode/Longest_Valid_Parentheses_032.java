package LeetCode;

import java.util.Stack;

public class Longest_Valid_Parentheses_032 {
    /*
     * Given a string containing just the characters '(' and ')', return the length of the longest
     * valid (well-formed) parentheses substring
     * Input: s = ")()())"  Output: 4
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
    }

}
