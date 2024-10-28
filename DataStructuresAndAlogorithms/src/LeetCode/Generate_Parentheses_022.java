package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Generate_Parentheses_022 {
	/*
	 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	 * Input: n = 3	Output: ["((()))","(()())","(())()","()(())","()()()"]
	 */
	public static void main(String args[]) {
		Generate_Parentheses_022 ob = new Generate_Parentheses_022();
		int length=3;
		System.out.println(ob.generate(length));
	}

	public List<String> generate(int length) {
		List<String> retList=new ArrayList<>();
		backtrack(retList,length*2,"()");
		return retList;
	}

	private void backtrack(List<String>retList, int index,String sb) {
		if (index == sb.length()) {
			retList.add(sb.toString());
		} else{
			String a=sb+"()";
			String b="()"+sb;
			backtrack(retList, index, a);
			backtrack(retList, index,"("+sb+")");
			if (!a.equals(b)) {
				backtrack(retList, index, b);
			}
		}
	}

	public List<String> generateParenthesisAccepted(int n) {
		List<String> result = new ArrayList<>();
		backtrack(result, "", 0, 0, n);
		return result;
	}

	private void backtrack(List<String> result, String current, int open, int close, int max) {
		// If the current string is a valid combination, add it to the result
		if (current.length() == max * 2) {
			result.add(current);
			return;
		}
		// If we can add an open parenthesis, do so
		if (open < max) {
			backtrack(result, current + "(", open + 1, close, max);
		}
		// If we can add a close parenthesis, do so
		if (close < open) {
			backtrack(result, current + ")", open, close + 1, max);
		}
	}
}
