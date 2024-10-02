package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Word_Break_139 {
    /*
     * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
     * sequence of one or more dictionary words. Note that the same word in the dictionary may be reused multiple
     * times in the segmentation.
     */

    public static void main(String args[]) {
        Word_Break_139 ob = new Word_Break_139();
        Implement_Trie_208_Modified dict=new Implement_Trie_208_Modified();
        dict.insert("apple");
        dict.insert("pen");
        List<String> wordDict = Arrays.asList("apple", "pen");
        String input="applepenapple";
        System.out.println(ob.evalWord(dict,input));
        System.out.println(ob.wordBreakAcceptedDP(input,wordDict));
    }

    private boolean evalWord(Implement_Trie_208_Modified wordDict,String s) {
        boolean retVal=false;
        for (int i=1;i<=s.length();i++){
            String inner=s.substring(0,i);
            boolean isStart=wordDict.startsWith(inner);
            if (isStart) {
                boolean isWord = wordDict.search(inner);
                if (isWord) {
                    if (wordDict.search(s)) {
                        return true;
                    } else {
                        retVal = evalWord(wordDict, s.substring(i, s.length()));
                    }
                }
            }else{
                break;
            }
        }
        return retVal;
    }

    public boolean wordBreakAcceptedDP(String s,List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String w : wordDict) {
                int start = i - w.length();
                if (start >= 0 && dp[start] && s.substring(start, i).equals(w)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

class Implement_Trie_208_Modified {

    HashMap<Character, Nodes<Character>> headMap;

    public Implement_Trie_208_Modified() {
        headMap = new HashMap<>();
    }

    public void insert(String input) {
        HashMap<Character, Nodes<Character>> map = headMap;
        for (int i=0;i<input.length();i++) {
            char ch= input.charAt(i);
            if (!map.containsKey(ch)) {
                Nodes<Character> node=new Nodes<>(ch);
                //if (i==input.length()-1) {
                //    node.isWord = true;
                //}
                map.put(ch, node);
            }
            map = map.get(ch).getMap();
        }
    }

    public boolean search(String input) {
        boolean retVal=false;
        HashMap<Character, Nodes<Character>> map = headMap;
        for (int i=0;i<input.length();i++) {
            char ch= input.charAt(i);
            if (map.containsKey(ch)) {
                map = map.get(ch).getMap();
                if (i==input.length()-1 && map.isEmpty()) {
                    retVal= true;
                }
            } else {
                return false;
            }
        }
        return retVal;
    }

    public boolean startsWith(String input) {
        HashMap<Character, Nodes<Character>> map = headMap;
        for (char c : input.toCharArray()) {
            if (map.containsKey(c)) {
                map = map.get(c).getMap();
            } else {
                return false;
            }
        }
        return true;
    }

    static class Nodes<Character> {
        HashMap<Character, Nodes<Character>> map;
        Character val;
        //boolean isWord;

        public Nodes(Character c) {
            this.val = c;
            this.map = new HashMap<>();
        }

        private HashMap<Character, Nodes<Character>> getMap() {
            return map;
        }
    }
}