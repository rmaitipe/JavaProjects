package LeetCode;

public class Ransom_Note_383 {
	/*
	 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from
	 * magazine and false otherwise. Each letter in magazine can only be used once in ransomNote.
	 * Input: ransomNote = "aa", magazine = "ab"	Output: false
	 *
	 */
	public boolean canConstruct(String ransomNote, String magazine) {
		// HashMap is the intuitive answer.
		// int[] charMag iterate ransom note & decrement if 0 break;
		boolean retVal=true;
		int[] charCount = new int[256];
		for (char m:magazine.toCharArray()){
			charCount[m]++;
		}
		for (char n:ransomNote.toCharArray()){
			if (charCount[n]==0){
				retVal=false;
				break;
			} else{
				charCount[n]--;
			}
		}
		return retVal;
	}

	public static void main(String args[]){
        String mag ="aab";
        String rans="aa";
		Ransom_Note_383 ob = new Ransom_Note_383();
        System.out.println(ob.canConstruct(rans, mag));
    }

	public boolean canConstructAccepted(String ransomNote, String magazine) {
		if (ransomNote.length() > magazine.length()) return false;
		int[] alphabets_counter = new int[26];

		for (char c : magazine.toCharArray())
			alphabets_counter[c-'a']++;

		for (char c : ransomNote.toCharArray()){
			if (alphabets_counter[c-'a'] == 0) return false;
			alphabets_counter[c-'a']--;
		}
		return true;
	}
}
