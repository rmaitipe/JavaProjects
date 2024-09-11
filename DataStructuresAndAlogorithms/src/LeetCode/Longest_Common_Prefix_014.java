package LeetCode;


public class Longest_Common_Prefix_014 {
	/*
	 * Write a function to find the longest common prefix string amongst an array of strings.
	 * If there is no common prefix, return an empty string "".
	 */
	public String longestCommonPrefix(String[] input) {
		StringBuilder sb=new StringBuilder(input[0]);
		for (String c:input){
			sb=duality(c,sb);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public StringBuilder duality(String longS,StringBuilder sb){
		for(int i=0;i<longS.length() && i<sb.length();i++) {
			if (longS.charAt(i) != sb.charAt(i)){
				sb= new StringBuilder(sb.substring(0,i));
				break;
			}
		}
		return sb;
	}

	public static void main(String args[]) {
		Longest_Common_Prefix_014 ob = new Longest_Common_Prefix_014();
		String[] input = new String[]{"flower", "flow", "flight"};
		ob.longestCommonPrefix(input);
	}
	/* If the array is sorted alphabetically then you can assume that the first element of the array
	and the last element of the array will have most different prefixes of all comparisons
	that could be made between strings in the array.
	If this is true, you only have to compare these two strings.*/
}
