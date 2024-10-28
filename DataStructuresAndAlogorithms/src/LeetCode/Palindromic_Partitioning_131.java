package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Palindromic_Partitioning_131 {
    /*
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * Input: s = "aab"     Output: [["a","a","b"],["aa","b"]]
     */
    public static void main(String args[]) {
        Palindromic_Partitioning_131 ob = new Palindromic_Partitioning_131();
        String input ="abccdde";
        System.out.println(ob.partition("aab"));
        System.out.println(ob.partition(input));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length())
            list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < s.length(); i++){
                if(isPalindrome(s, start, i)){
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
}
