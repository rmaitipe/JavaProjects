package LeetCode;

import java.util.HashMap;

/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a
 dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:
    Trie() Initializes the trie object.
    void insert(String word) Inserts the string word into the trie.
    boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before),
    and false otherwise.
    boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the
    prefix prefix, and false otherwise.

     Alt accepted implementation - using an array - trie = new Trie[26];
 */
public class Implement_Trie_208 {

    HashMap<Character,Nodes<Character>> headMap;

    public Implement_Trie_208(){
        headMap=new HashMap<>();
    }

    private void insert(String input){
        HashMap<Character,Nodes<Character>> map=headMap;
        for (char c:input.toCharArray()){
            if (!map.containsKey(c)){
                map.put(c, new Nodes(c));
            }
            map=map.get(c).getMap();
        }
    }

    private boolean search(String input){
        HashMap<Character,Nodes<Character>> map=headMap;
        for (char c:input.toCharArray()){
            if (map.containsKey(c)){
                map=map.get(c).getMap();
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean startsWith(String input){
        HashMap<Character,Nodes<Character>> map=headMap;
        for (char c:input.toCharArray()){
            if (map.containsKey(c)){
                map=map.get(c).getMap();
            }else{
                return false;
            }
        }
        return true;
    }

    static class Nodes<Character> {
        HashMap<Character,Nodes<Character>> map;
        Character val;

        public Nodes(Character c) {
            this.val=c;
            this.map=new HashMap<>();
        }

        private HashMap<Character,Nodes<Character>> getMap(){
            return map;
        }
    }

    public static void main(String args[])    {
        Implement_Trie_208 ob = new Implement_Trie_208();
        ob.insert("Hello");
        ob.insert("World");
        System.out.println(ob.search("Hell"));
        System.out.println(ob.startsWith("Hell"));
        System.out.println(ob);
    }
}
