package LeetCode;

import java.util.HashMap;
import java.util.Map;

/*
Design a map that allows you to do the following:
    Maps a string key to a given value.
    Returns the sum of the values that have a key with a prefix equal to a given string.

Implement the MapSum class:
    MapSum() Initializes the MapSum object.
    void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
    int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
    At most 50 calls will be made to insert and sum.
*/
public class Map_Sum_Pairs_677 {

    TrieNode root;

    public Map_Sum_Pairs_677() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (curr.child[index] == null) {
                curr.child[index] = new TrieNode();
            }
            //curr.child[index].map.put(key, val);
            curr.value+=val;//BRM
            curr = curr.child[index];
        }
    }

    public int sum(String prefix) {
        int sum = 0;
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (curr.child[index] == null) {
                return sum;
            }
            curr = curr.child[index];
        }
        return curr.value;
        /*Map<String, Integer> map = curr.map;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sum += entry.getValue();
        }
        return sum;*/
    }

    class TrieNode {
        TrieNode[] child;
        //Map<String, Integer> map;
        Integer value;

        TrieNode() {
            child = new TrieNode[26];
            //map = new HashMap<>();
            value =0;
        }
    }

    public static void main(String args[])    {
        Map_Sum_Pairs_677 ob = new Map_Sum_Pairs_677();
        ob.insert("apple",3);
        System.out.println(ob.sum("ap"));
        ob.insert("app",2);
        System.out.println(ob.sum("ap"));
        System.out.println(ob.sum("appl"));
        System.out.println(ob.sum("pine"));
    }
}
