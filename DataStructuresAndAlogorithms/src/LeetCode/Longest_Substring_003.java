package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Longest_Substring_003 {
	/*
	 * Given a string s, find the length of the longest substring without repeating characters.
	 *
	 * Use sliding window with hashset, use left and right pointers to move the window .
	 * If the set doesn't contain character then first add into the set and calculate the maxLength hand-in-hand.
	 * if character already present in the set that means you have to move your sliding window by 1 ,
	 * before that you have to remove all the characters that are in front of the character that is present already in window before.
	 * Now you have to remove that character also and move the left pointer and also add the new character into the set.
	 */
	public String detectLongestSubString(String input) {
		String longString="";
		StringBuilder sb=new StringBuilder();
		char[] arr=input.toCharArray();
		for (char c:arr){
			if (sb.isEmpty()){
				sb.append(c);
			}else{
				if (sb.toString().contains(String.valueOf(c))){
					if (longString.length()<sb.toString().length()){
						longString=sb.toString();
						sb.setLength(0);
						sb.append(c);
					}
				} else{
					sb.append(c);
				}
			}
		}
		if(longString.length()<sb.length()){
			longString= sb.toString();
		}
		return longString;
	}

	public static void main(String args[]) {
		Longest_Substring_003 ob = new Longest_Substring_003();
		String s = "abcaecdfbxwzer";
		System.out.println(ob.detectLongestSubString(s).length());
		System.out.println(ob.lengthOfLongestSubstring(s));
	}

	public int detectLongestSubStringAccepted(String str) {
		if (str.length() == 0)
			return 0;
		// if string length 1
		if (str.length() == 1)
			return 1;
		// if string length is more than 2
		int maxLength = 0;
		boolean[] visited = new boolean[256];
		int left = 0, right = 0;// left and right pointer of sliding window
		while (right < str.length()) {
			if (visited[str.charAt(right)]) {
				// The left pointer moves to the right while marking visited characters as false until
				// the repeating character is no longer part of the current window.
				while (visited[str.charAt(right)]) {
					visited[str.charAt(left)] = false;
					left++;
				}
			}
			visited[str.charAt(right)] = true;
			// The length of the current window (right -left + 1) is calculated and answer is updated accordingly.
			maxLength = Math.max(maxLength, (right - left + 1));
			right++;
		}
		return maxLength;
	}

	public int lengthOfLongestSubstring(String s) {
		int left = 0, right = 0, max = 0;
		Set<Character> set = new HashSet<>();
		while (right < s.length()) {
			if (!set.contains(s.charAt(right))) {
				set.add(s.charAt(right++));
				max = Math.max(max, set.size());
			} else {
				set.remove(s.charAt(left++));
			}
		}
		return max;
	}
}
