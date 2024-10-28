package LeetCode;

import java.util.*;

public class Partition_Labels_763 {
    /*
     * You are given a string s. We want to partition the string into as many parts as possible so that each
     * letter appears in at most one part. Note that the partition is done so that after concatenating all the parts
     * in order, the resultant string should be s.
     * Return a list of integers representing the size of these parts.
     * Input:s="ababcbacadefegdehijhklij"    Output:[9,7,8] Explanation:The partition is "ababcbaca","defegde","hijhklij"
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
     */
    private List<Integer> partitionPath(String input) {
        Map<Character,Integer> rightIdxBoundryMap=new HashMap<>();
        List<Integer> retVal=new ArrayList<>();
        for (int i=0;i<input.length();i++){
            rightIdxBoundryMap.put(input.charAt(i),i);
        }
        int left =0;
        int right=0;
        while(left<input.length()){
            right = rightIdxBoundryMap.get(input.charAt(left));
            for (int j=left+1;j<right;j++) {
                if (rightIdxBoundryMap.get(input.charAt(j)) > right) {
                    right = rightIdxBoundryMap.get(input.charAt(j));
                }
            }
            retVal.add(right-left+1);
            left=right+1;
        }
        return retVal;
    }

    public static void main(String args[]) {
        Partition_Labels_763 ob = new Partition_Labels_763();
        String input= "ababcbacadefegdehijhklij";
        System.out.println(ob.partitionPath(input));
        System.out.println(ob.partitionLabelsAccepted(input));
    }

    public List<Integer> partitionLabelsAccepted(String s) {
        List<Integer> al = new ArrayList<>();
        char[] str = s.toCharArray();
        Map<Character, Integer> hmap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            hmap.put(str[i], i);
        }
        int temp = hmap.get(str[0]),
                prev = 0;
        for (int i = 0; i < str.length; i++) {
            if (hmap.get(str[i]) > temp) {
                temp = hmap.get(str[i]);
            }
            if (i == temp) {
                al.add(temp - prev + 1);
                prev = temp + 1;
            }
        }
        return al;
    }

}
