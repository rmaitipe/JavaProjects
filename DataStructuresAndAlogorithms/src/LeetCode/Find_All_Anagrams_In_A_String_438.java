package LeetCode;

import java.util.*;

public class Find_All_Anagrams_In_A_String_438 {
    /*
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
     * You may return the answer in any order.
     * Input: s = "cbaebabacd", p = "abc"   Output: [0,6]
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     */
    public static void main(String args[]) {
        Find_All_Anagrams_In_A_String_438 ob = new Find_All_Anagrams_In_A_String_438();
        String input1 = "cbaebabacd";
        String input2 = "abc";
        System.out.println(ob.findAnagrams(input1,input2));
        System.out.println(ob.findAnagramsAccepted(input1,input2));
    }

    private List<Integer> findAnagrams(String str1, String str2) {
        //O(n) scan - if char present, scan ahead
        List<Integer> list= new ArrayList<>();
        int left=0;
        while (left < str1.length()-str2.length()) {
            if (!str2.contains(str1.substring(left,left+1))) {
                left++;
            }
            else {
                boolean isAnagram= checkAnagram(str1.substring(left,left+str2.length()),str2);
                if (isAnagram) {
                    list.add(left);
                }
                left++;//can we improve this
            }
        }
        return list;
    }

    private boolean checkAnagram(String substring, String str2) {
        char[] a= substring.toCharArray();
        Arrays.sort(a);
        char[] b= str2.toCharArray();
        Arrays.sort(b);
        return Arrays.equals(a,b);
    }

    public List<Integer> findAnagramsAccepted(String s, String p) {
        Map<Character,Integer>keyMap=new HashMap<>();// This can be HashMaps
        Map<Character,Integer>temp=new HashMap<>();
        List<Integer>ans=new ArrayList<>();
        for(int i=0;i<p.length();i++){
            keyMap.put(p.charAt(i),keyMap.getOrDefault(p.charAt(i),0)+1);
        }
        int i=0;
        int j=0;
        while(j<s.length()){
            temp.put(s.charAt(j),temp.getOrDefault(s.charAt(j),0)+1);
            if(j-i+1<p.length()){
                j++;
            } else if(j-i+1==p.length()){
                //System.out.println(temp);
                if(temp.equals(keyMap)){
                    ans.add(i);
                }
                temp.put(s.charAt(i),temp.getOrDefault(s.charAt(i),0)-1);
                if(temp.get(s.charAt(i))==0){
                    temp.remove(s.charAt(i));
                }
                i++;
                j++;
            }
        }
        return ans;
    }
}
