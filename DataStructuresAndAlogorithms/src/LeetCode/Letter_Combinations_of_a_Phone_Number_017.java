package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Letter_Combinations_of_a_Phone_Number_017 {
	/*
	 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
	 * could represent. Return the answer in any order.
	 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
	 * 2-abc 3-def 4-ghi 5-jkl 6-mno 7-pqrs 8-tuv 9wxyz
	 * Input: digits = "23"	Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
	 */
	public static void main(String args[]) {
		Letter_Combinations_of_a_Phone_Number_017 ob = new Letter_Combinations_of_a_Phone_Number_017();
		String numsStr = "23";
		System.out.println(ob.letterCombinations(numsStr));
	}

	public List<String> letterCombinations (String nums) {
		List<String> list = new ArrayList<>();
		//Arrays.sort(nums);
		Map<Integer,List<Character>> lookupMap=new HashMap<>();
		lookupMap.put(2,List.of('a','b','c'));
		lookupMap.put(3,List.of('d','e','f'));
		lookupMap.put(4,List.of('g','h','i'));
		lookupMap.put(5,List.of('j','k','l'));
		lookupMap.put(6,List.of('m','n','o'));
		lookupMap.put(7,List.of('p','q','r','s'));
		lookupMap.put(8,List.of('t','u','v'));
		lookupMap.put(9,List.of('w','x','y','z'));
		backtrack(list, new ArrayList<>(), nums,lookupMap,0);
		return list;
	}

	private void backtrack(List<String> list, List<Character> tempList, String nums, Map<Integer,List<Character>> lookupMap,int start){
		if(tempList.size() == nums.length()){
			list.add(String.valueOf(tempList));
		} else{
			int key=Character.getNumericValue(nums.charAt(start));
			List<Character> kv = lookupMap.get(key);
			for(Character ch:kv){
				tempList.add(ch);
				backtrack(list, tempList, nums, lookupMap,start+1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
