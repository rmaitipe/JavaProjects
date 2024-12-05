package LeetCode;

import java.util.Arrays;

public class Valid_Anagram_242 {
    /*
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise
     */
    private boolean validate(String a,String b) {
        char[] aa=a.toCharArray();
        char[] bb=b.toCharArray();
        Arrays.sort(aa);
        Arrays.sort(bb);
        return Arrays.equals(aa,bb);
    }

    public static void main(String args[]) {
        Valid_Anagram_242 ob = new Valid_Anagram_242();
        String input1 ="anagram";
        String input2 ="nagaram";
        System.out.print(ob.validate(input1,input2));
    }

}
