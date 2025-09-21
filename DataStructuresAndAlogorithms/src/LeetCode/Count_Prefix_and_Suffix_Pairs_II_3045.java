package LeetCode;

import java.util.*;

public class Count_Prefix_and_Suffix_Pairs_II_3045 {
	/*
	You are given a 0-indexed string array words.
	Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:
    isPrefixAndSuffix(str1, str2) returns true if str1 is both a prefix and a suffix of str2, and false otherwise.

	For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix,
	but isPrefixAndSuffix("abc", "abcd") is false.
	Return an integer denoting the number of index pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j]) is true.

	Input: words = ["a","aba","ababa","aa"] Output: 4
	Integer overflow!!!!
	 */


	public static void main(String args[]) {
		Count_Prefix_and_Suffix_Pairs_II_3045 ob = new Count_Prefix_and_Suffix_Pairs_II_3045();
		String[] words = {"a","aba","ababa","aa"};

		System.out.println(ob.eval(words));
		System.out.println("\"a\",\"aba\",\"ababa\",\"aa\": "+ ob.countPrefixSuffixPairsIntOverFlow(words));
		String[] words2 = {"a","cbc","bcb","b","aca"};
		System.out.println("\"a\",\"cbc\",\"bcb\",\"b\",\"aca\": "+ ob.countPrefixSuffixPairsIntOverFlow(words2));
		String[] words3 = {"a","aa","aa","b","ab"};
		System.out.println("\"a\",\"aa\",\"aa\",\"b\",\"ab\": "+ob.countPrefixSuffixPairsIntOverFlow(words3));
		String[] words4 = {"pa","papa","ma","mama"};
		System.out.println("\"pa\",\"papa\",\"ma\",\"mama\": "+ob.countPrefixSuffixPairsIntOverFlow(words4));
		String[] words5 = {"b","a","b","a","b"};
		System.out.println("\"b\",\"a\",\"b\",\"a\",\"b\": "+ob.countPrefixSuffixPairsIntOverFlow(words5));


	}

	int eval(String[] words) {
		DictionaryH forward=new DictionaryH();
		DictionaryH backward=new DictionaryH();
		int count=0;
		for (String str:words){
			boolean up = forward.insert(str);
			boolean down = backward.insert(str);
			if (up && down){
				count++;
			}
		}
		return 0;//TODO
	}

	 class DictionaryH{
		 Nodes<Character> topLevel;

		 public boolean insert(String str) {
			 Nodes<Character> currLevel=topLevel;
			 for (int i=0;i<str.length();i++){
				 Character character=str.charAt(i);

			 }
			 return false;//TODO
		 }
	 }

	 // from 211
	 class Nodes<Character> {
		 HashMap<Character, Design_Add_and_Search_Words_DS_211.Nodes<Character>> map;
		 Character val;

		 public Nodes(Character c) {
			 this.val=c;
			 this.map=new HashMap<>();
		 }

		 private HashMap<Character, Design_Add_and_Search_Words_DS_211.Nodes<Character>> getMap(){
			 return map;
		 }
	 }

/*
Attempt 2
 */
	public long countPrefixSuffixPairsIntOverFlow(String[] words) {
		TrieNode ascTrieRoot = new TrieNode();
		TrieNode descTrieRoot = new TrieNode();
		int count=0;
		HashMap<String,Long> mapCount=new HashMap<>();
		for (String str:words){
			count+=trieInsert(str,ascTrieRoot, descTrieRoot,mapCount);
			mapCount.put(str,mapCount.getOrDefault(str,0L)+1);
		}

		return count;
	}

	private long trieInsert(String str,TrieNode ascTrieRoot, TrieNode descTrieRoot, HashMap<String,Long> mapCount){
		String reverseStr= new StringBuilder(str).reverse().toString();
		char[] asc=str.toCharArray();
		char[] desc=reverseStr.toCharArray();
		TrieNode forNode=ascTrieRoot;
		TrieNode toNode=descTrieRoot;
		int localCount=0;
		for (int i=0;i<str.length();i++){
			boolean a = ascTrieRoot.insert(asc[i], forNode);
			boolean b = descTrieRoot.insert(desc[i], toNode);
			forNode= forNode.node[asc[i]-'a'];
			toNode = toNode.node[desc[i]-'a'];
			if (i==str.length()-1){
				forNode.isWord=true;
				toNode.isWord=true;
			}
			if (a && b && str.substring(0,i+1).contentEquals(new StringBuilder(reverseStr.substring(0,i+1)).reverse())) {
				long matchCount= mapCount.getOrDefault(str.substring(0,i+1),0L);
				localCount+=matchCount;
			}
		}
		return localCount;
	}

	private int trieInsert2(String str, TrieNode ascTrieRoot, TrieNode descTrieRoot){
		int localCount=0;
		char[] asc=str.toCharArray();
		String rev= new StringBuilder(str).reverse().toString();
		char[] desc=rev.toCharArray();
		TrieNode forNode=ascTrieRoot;
		TrieNode toNode =descTrieRoot;
		for (int i=0;i<str.length();i++){
			String key = ascTrieRoot.insert2(asc[i], str, forNode, i);
			String key2 = descTrieRoot.insert2(desc[i], rev, toNode, i);
			if (i==str.length()-1){
				forNode.node[asc[i]-'a'].isWord=true;
				toNode.node[desc[i]-'a'].isWord=true;
			}
			if (key!=null && key2!=null && key.equals(new StringBuilder(key2).reverse().toString())) {
				localCount++;
			}
			forNode= forNode.node[asc[i]-'a'];
			toNode= toNode.node[desc[i]-'a'];
		}
		return localCount;
	}


	class TrieNode {
		TrieNode[] node;
		boolean isWord;

		TrieNode(){
			node = new TrieNode [26];
		}

		private boolean insert(char c, TrieNode curr){
			boolean retVal = false;
			if (curr.node[c-'a']==null) {
				curr.node[c - 'a'] = new TrieNode();
			}
			else if (curr.node[c - 'a'].isWord){
				retVal= true;
			}
			return retVal;
		}

		private String insert2(Character c, String str, TrieNode curr, int idx){
			String retVal = null;
			if (curr.node[c-'a']!=null){
				if (curr.isWord){
					retVal=str.substring(0,idx+1);
				}
			}
			else{
				curr.node[c - 'a'] = new TrieNode();
			}
			return retVal;
		}
	}


}
