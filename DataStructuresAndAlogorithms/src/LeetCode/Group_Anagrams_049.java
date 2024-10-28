package LeetCode;

import java.util.*;

public class Group_Anagrams_049 {
    /*
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * Input: strs = ["eat","tea","tan","ate","nat","bat"] Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public static void main(String args[]) {
        Group_Anagrams_049 ob = new Group_Anagrams_049();
        String[] input = new String[] {"eat","tea","tan","ate","nat","bat"};
        System.out.println(ob.groupAnagrams(input));
    }

    private List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        List<List<String>> retVal=new ArrayList<>();
        for (String s:strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (map.containsKey(key)){
                map.get(key).add(s);
            } else{
                List<String> val= new ArrayList<>();
                val.add(s);
                map.put(key,val);
            }
        }
        for (Map.Entry<String,List<String>> ev: map.entrySet()){
            retVal.add(ev.getValue());
        }
        return retVal;
    }

}
