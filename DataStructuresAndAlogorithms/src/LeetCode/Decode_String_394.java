package LeetCode;

import java.util.Stack;

public class Decode_String_394 {
	/*
	 * Given an encoded string, return its decoded string.
	 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly
	 * k times. Note that k is guaranteed to be a positive integer.
	 * Assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
	 * Furthermore, you may assume that the original data does not contain any digits and that digits are only
	 * for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
	 * Input: s = "2[abc]3[cd]ef" 	Output: "abcabccdcdcdef"
	 * Input: s = "3[a2[c]]"		Output: "accaccacc"
	 */

	public static void main(String args[]) {
		Decode_String_394 ob = new Decode_String_394();
		String input ="2[abc]3[cd]ef";
		String input1 ="3[a2[c]]";
		System.out.print(ob.validateMethod(input1));
		System.out.print(" Accepted: "+ob.decodeStringAccepted(input1));
    }

	private String validateMethod(String input) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			//if [ iterate until corresponding closing ] is found
			// if another number+ bracket is found resolve it first
			if (input.charAt(i) == ']') {
				StringBuilder sb = new StringBuilder();
				while (!Character.isDigit(stack.peek())) {
					Character ch = stack.pop();
					if (ch != '[') {
						sb.insert(0, ch);
					}
				}
				int k = Integer.parseInt(String.valueOf(stack.pop()));
				String resolved = kMultiply(k, sb.toString());
				//if fully resolved return string else push to stack
				if (i == input.length() - 1)
					return resolved;
				else {
					for (int j = 0; j < resolved.length(); j++) {
						stack.push(resolved.charAt(j));
					}
				}
			} else {
				stack.push(input.charAt(i));
			}
		}
		StringBuilder sbOut = new StringBuilder();
		while (!stack.isEmpty()) {
			sbOut.append(stack.pop());
		}
		return sbOut.reverse().toString();
	}

	private String kMultiply (int k, String s){
		StringBuilder sb=new StringBuilder(s);
		for(int i=1;i<k;i++){
			sb.append(s);
		}
		return sb.toString();
	}

	/*
	 * The intuition behind this solution is to use two stacks—one to keep track of the number of times a string needs
	 * to be repeated (st), and another to store the intermediate results (st1). O(n) space & time complexity
	 */
	public String decodeStringAccepted(String s) {
		Stack<Integer> stac = new Stack<>();
		Stack<StringBuilder> stacSB = new Stack<>();
		StringBuilder str = new StringBuilder();
		int n = 0;
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				n = Integer.parseInt(String.valueOf(c));//n * 10 + (c - '0');
			} else if (c == '[') {
				stac.push(n);
				n = 0;
				stacSB.push(str);
				str = new StringBuilder();
			} else if (c == ']') {
				int k = stac.pop();
				StringBuilder temp = str;
				str = stacSB.pop();
				while (k-- > 0) {
					str.append(temp);
				}
			} else {
				str.append(c);
			}
		}
		return str.toString();
	}
}
