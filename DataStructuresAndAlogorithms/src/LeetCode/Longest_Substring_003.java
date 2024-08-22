package LeetCode;


public class Longest_Substring_003 {

	/*
	 * Given a string s, find the length of the longest substring without repeating characters.
	 *
	 * Use sliding window with hashset, use left and right pointers to move the window .
	 * If the set doesn't contain character then first add into the set and calculate the maxLength hand-in-hand.
	 * if character already present in the set that means you have to move your sliding window by 1 ,
	 * before that you have to remove all the characters that are infront of the character that is present already in window before.
	 * Now you have to remove that character also and move the left pointer and also add the new character into the set.
	 */
	public String detectSubStringMethod(String input) {
		return null;
	}



	public static void main(String args[]) {
		Longest_Substring_003 ob = new Longest_Substring_003();
		String s = "abcabcbb";
		ob.detectSubStringMethod(s);
	}
}
