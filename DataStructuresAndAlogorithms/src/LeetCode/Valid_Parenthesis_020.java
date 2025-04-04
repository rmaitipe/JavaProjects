package LeetCode;

import java.util.Stack;

public class Valid_Parenthesis_020 {
	/*
	 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string
	 * is valid. An input string is valid if:
	 * 1. Open brackets must be closed by the same type of brackets.
	 * 2. Open brackets must be closed in the correct order.
	 * 3. Every close bracket has a corresponding open bracket of the same type.
	 */

	public static void main(String args[]) {
		Valid_Parenthesis_020 ob = new Valid_Parenthesis_020();
		String input ="()[]{}";
		System.out.print(ob.validateMethod(input));
    }

	private boolean validateMethod(String input) {
		char[] array=input.toCharArray();
		Stack<Character> stack=new Stack<>();
		for (char c:array){
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			}
			else if (stack.isEmpty()) {return false;}
			else {
				if (c==')' && stack.peek()=='('){
					stack.pop();
				}
				else if (c=='}' && stack.peek()=='{'){
					stack.pop();
				}
				else if (c==']' && stack.peek()=='['){
					stack.pop();
				}
				else break;
			}
		}
		return stack.isEmpty();
	}

}
