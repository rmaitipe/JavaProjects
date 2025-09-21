package LeetCode;

import java.util.HashMap;

    /*
    A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a
    dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

    Implement the Trie class:
    Trie() Initializes the trie object.
    void insert(String word) Inserts the string word into the trie.
    boolean search(String word) Returns true if the string word is in the trie (i.e. inserted before), and false otherwise.
    boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix, and false otherwise.

     Alt accepted implementation - using an array - trie = new Trie[26];
 */
public class Implement_Trie_208 {

    HashMap<Character,Nodes<Character>> headMap;

    public Implement_Trie_208(){
        headMap=new HashMap<>();
    }

    public void insert(String input){
        HashMap<Character,Nodes<Character>> map=headMap;
        for (char c:input.toCharArray()){
            if (!map.containsKey(c)){
                map.put(c, new Nodes(c));
            }
            map=map.get(c).getMap();
        }
    }

    public boolean search(String input){
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

    public boolean startsWith(String input){
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

    class Trie {
        // Define the Node class, the building block of the Trie. 🧱
        private class Node {
            Node[] children = new Node[26]; // Array to store children nodes (a-z). 👶
            boolean isEndOfWord = false; // Flag to mark if this node is the end of a word. 🚩

            // Getter method for isEndOfWord
            public boolean isEndOfWord() {
                return isEndOfWord;
            }

            // Setter method for isEndOfWord
            public void setEndOfWord(boolean endOfWord) {
                this.isEndOfWord = endOfWord;
            }
        }

        private final Node root; // The root node of the Trie. 🌳

        // Constructor to initialize the Trie. 🏗️
        public Trie() {
            root = new Node(); // Create a new root node.
        }

        // Method to insert a word into the Trie. ✍️
        public void insert(String word) {
            Node current = root; // Start from the root node. 📍
            for (int i = 0; i < word.length(); i++) { // Iterate through each character of the word. 🚶
                char ch = word.charAt(i); // Get the character at the current index.
                int index = ch - 'a'; // Calculate the index (0-25) for the character (a-z). 🧮

                // If there's no node for this character, create one. 🆕
                if (current.children[index] == null) {
                    current.children[index] = new Node();
                }
                current = current.children[index]; // Move to the next node. ➡️
            }
            current.setEndOfWord(true); // Mark the last node as the end of the word. ✅
        }

        // Method to search for a word in the Trie. 🔍
        public boolean search(String word) {
            Node current = root; // Start from the root node. 📍
            for (int i = 0; i < word.length(); i++) { // Iterate through each character of the word. 🚶
                char ch = word.charAt(i); // Get the character at the current index.
                int index = ch - 'a'; // Calculate the index (0-25) for the character (a-z). 🧮

                // If there's no node for this character, the word doesn't exist. ❌
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index]; // Move to the next node. ➡️
            }
            return current != null && current.isEndOfWord(); // Check if the last node is the end of a word. 🕵️
        }

        // Method to check if any word starts with a given prefix in the Trie. ⭐️
        public boolean startsWith(String prefix) {
            Node current = root; // Start from the root node. 📍
            for (int i = 0; i < prefix.length(); i++) { // Iterate through each character of the prefix. 🚶
                char ch = prefix.charAt(i); // Get the character at the current index.
                int index = ch - 'a'; // Calculate the index (0-25) for the character (a-z). 🧮

                // If there's no node for this character, no word starts with this prefix. ❌
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index]; // Move to the next node. ➡️
            }
            return current != null; // If we reach the end of the prefix, it exists. 🎉
        }
    }
}
