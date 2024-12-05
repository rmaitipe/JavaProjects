package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Word_Break_139 {
    /*
     * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
     * sequence of one or more dictionary words. Note that the same word in the dictionary may be reused multiple
     * times in the segmentation.
     */
    public static void main(String args[]) {
        Word_Break_139 ob = new Word_Break_139();
        List<String> wordDict = Arrays.asList("apple", "pen");
        String input="applepenapple";
        System.out.println(ob.evalWord(input,wordDict));
        System.out.println(ob.wordBreakAcceptedDP(input,wordDict));
    }

    private boolean evalWord(String input,List<String> wordDict) {
        boolean retVal=false;
        for( int j=0; j<wordDict.size();j++){
            for (int i=0;i<input.length();i++){
                if (wordDict.get(j).charAt(i)!=input.charAt(i)){
                    break;
                }
                else{
                    if (i== input.length()-1 && i==wordDict.get(j).length()-1){
                        return true;
                    }
                    if (i==wordDict.get(j).length()-1){
                        //end of word found
                        return evalWord(input.substring(i+1), wordDict);
                    }
                }
            }
        }
        return retVal;
    }
/*
Non DP solution, same as above both are inefficient
    public boolean wordBreak(String s, List<String> wordDict) {
        // if we found word break for whole string, return true
        if(s.isEmpty()) return true;
        for(String word: wordDict){
            int n = word.length();
            if(s.length() >= n){
                // if word is prefix of s, then break s and look for word break in remaining string
                if (s.startsWith(word)) {
                    boolean b = wordBreak(s.substring(n), wordDict);
                    if(b) return b;
                }
            }
        }
        return false;
    }

    The DP solution does not have the recursive call
 */
    public boolean wordBreakAcceptedDP(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreakAcceptedDP2(String s,List<String> wordDict) {
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