package LeetCode;

import java.util.HashMap;

public class Design_Add_and_Search_Words_DS_211 {
    /*
     * Design a data structure that supports adding new words and finding if a string matches any previously added string.
     * Implement the WordDictionary class:
     * WordDictionary() Initializes the object.
     * void addWord(word) Adds word to the data structure, it can be matched later.
     * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
     * word may contain dots '.' where dots can be matched with any letter.
     *
     * WordDictionary wordDictionary = new WordDictionary(); addWord("bad"); addWord("dad"); addWord("mad");
     * search("pad");return False   search("bad");return True   search(".ad");return True   search("b..");return True
     */
    HashMap<Character,Nodes<Character>> headMap;

    public Design_Add_and_Search_Words_DS_211(){
        headMap=new HashMap<>();
    }

    public void addWord(String input){
        HashMap<Character,Nodes<Character>> map=headMap;
        for (char c:input.toCharArray()){
            if (!map.containsKey(c)){
                map.put(c, new Nodes(c));
            }
            map=map.get(c).getMap();
        }
    }

    public boolean search(String input){
        boolean retVal=false;
        HashMap<Character,Nodes<Character>> map=headMap;
        char[] ch= input.toCharArray();
        for (int i=0;i<ch.length;i++){
            if (ch[i]=='.'){
                for (Nodes<Character> cha:map.values()){
                    String modStr=input.substring(0,Math.max(0,i))+ cha.val +input.substring(Math.min(i+1,input.length()),input.length());
                    retVal=retVal || search(modStr);
                }
                return retVal;
            } else if (map.containsKey(ch[i])){
                map=map.get(ch[i]).getMap();
                if (i==ch.length-1){
                    retVal=true;
                }
            } else{
                retVal= false;break;
            }
        }
        return retVal;
    }

    public boolean startsWith(String input){
        HashMap<Character,Nodes<Character>> map=headMap;
        for (char c:input.toCharArray()){
            if (map.containsKey(c)){
                map=map.get(c).getMap();
            } else{
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
        Design_Add_and_Search_Words_DS_211 ob = new Design_Add_and_Search_Words_DS_211();
        ob.addWord("bad");
        ob.addWord("dad");
        ob.addWord("mad");
        System.out.println(ob.search("pad"));
        System.out.println(ob.search("bad"));
        System.out.println(ob.search(".ad"));
        System.out.println(ob.search("b.."));
    }
    /*
        public boolean search(String word) {
        WordDictionary curr = this;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(c == '.'){
                for(WordDictionary ch: curr.children)
                    if(ch != null && ch.search(word.substring(i+1))) return true;
                return false;
            }
            if(curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr != null && curr.isEndOfWord;
    }
     */
}
