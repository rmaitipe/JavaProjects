package LeetCode;

import java.util.*;

public class Minimum_Window_Substring_076 {
	/*
	 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
	 * every character in t (including duplicates) is included in the window. If there is no such substring, return "".
	 * The testcases will be generated such that the answer is unique.
	 * Input: s = "ADOBECODEBANC", t = "ABC"	Output: "BANC"
	 */
	public static void main(String args[]) {
		Minimum_Window_Substring_076 ob = new Minimum_Window_Substring_076();
		String input="ADOBECODEBANC";
		String key="ABC";
		System.out.println(ob.findMinWindow(input,key));
		System.out.println(ob.minWindowAccepted(input,key));
    }

	public String findMinWindow(String  input, String key) {
		Map<Character, Integer> freqMap=new HashMap<>();
		for (char k:key.toCharArray()) {
			freqMap.put(k, freqMap.getOrDefault(k,0)+1);
		}
		Map<Character, Integer> checkMap=new LinkedHashMap<>();//order matters
		int right=0;
		int min=Integer.MAX_VALUE;
		int currentR=0; int currentL=-1;
		String minString = "";
		String leftString=input;
		while (right<input.length()){
			if (key.contains(String.valueOf(input.charAt(right)))) {
				//if map is empty set currentL=right;
				currentR=right;
				Character k=input.charAt(right);
				if (checkMap.size()<freqMap.size()) {
					checkMap.put(k, checkMap.getOrDefault(k, 0) + 1);
					if (checkMap.size()==1){
						currentL=right;
						leftString=leftString.substring(currentL);
					}
				}
				else if(checkMap.size()==freqMap.size()){
					while(checkMap.keySet().stream().findFirst().get()!=k){
						Character curr=checkMap.keySet().stream().findFirst().get();
						checkMap.remove(curr);
					}
					currentL=leftString.indexOf(k);
					leftString=leftString.substring(currentL+1);
					checkMap.remove(k);
					checkMap.put(k, checkMap.getOrDefault(k, 0) + 1);
				}
				if (checkMap.equals(freqMap)) {
					int eval=currentR-currentL;
					if (eval<min) {
						min = eval;
						minString = input.substring(currentL,currentR+1);
					}
				}
			}
			right++;
		}
		return minString;
	}

	/*
	If the length of s is smaller than the length of t, it's impossible for s to contain t. Therefore, return an empty string
Approach:

    Using HashMap: A HashMap is used to keep track of the frequency of each character, allowing efficient management of character counts.
    Target Character Management: Track the frequency of characters in T and calculate the minimum window size when all target characters are included in the window.
    Window Minimization: Expand the window to include more characters and contract it to find the smallest valid window.

Algorithm Details:
    Remaining Target Characters: Track the remaining count of characters from T. When all are zero, calculate the window size.
    Expanding and Contracting the Window: Expand the window by adding characters and contract it by removing unnecessary characters.
    Updating Minimum Window: Compute and update the smallest window size as needed.
	 */

	public String minWindowAccepted(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		Map<Character, Integer> charCount = new HashMap<>();
		for (char ch : t.toCharArray()) {
			charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
		}
		int targetCharsRemaining = t.length();
		int[] minWindow = {0, Integer.MAX_VALUE};
		int startIndex = 0;
		for (int endIndex = 0; endIndex < s.length(); endIndex++) {
			char ch = s.charAt(endIndex);
			if (charCount.containsKey(ch) && charCount.get(ch) > 0) {
				targetCharsRemaining--;
			}
			charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);
			if (targetCharsRemaining == 0) {
				while (true) {
					char charAtStart = s.charAt(startIndex);
					if (charCount.containsKey(charAtStart) && charCount.get(charAtStart) == 0) {
						break;
					}
					charCount.put(charAtStart, charCount.getOrDefault(charAtStart, 0) + 1);
					startIndex++;
				}
				if (endIndex - startIndex < minWindow[1] - minWindow[0]) {
					minWindow[0] = startIndex;
					minWindow[1] = endIndex;
				}
				charCount.put(s.charAt(startIndex), charCount.getOrDefault(s.charAt(startIndex), 0) + 1);
				targetCharsRemaining++;
				startIndex++;
			}
		}
		return minWindow[1] >= s.length() ? "" : s.substring(minWindow[0], minWindow[1] + 1);
	}

}
