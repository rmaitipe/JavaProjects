package LeetCode;

import java.util.*;

/*
 * Implement the AllOne class to store the strings' count with the ability to return the strings with minimum and maximum counts.
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 */
public class All_O_DataStructure_432 {
    /*
    Node Representation: Each node represents a string, its frequency, and its position in the list.
    Increment (inc):If the string already exists, we increment its frequency and adjust its position in the list by swapping
        it upward as needed to maintain order based on frequency. If it doesn't exist, we create a new node with frequency 1 and append it to the list.
    Decrement (dec):If the string's frequency is reduced, we adjust its position by swapping it downward in the list. If the frequency becomes zero, we remove the node.
    Max and Min Key Retrieval:The list is sorted in decreasing order of frequency, so the first element gives the maximum key, and the last element gives the minimum key.
    Also: HashMap+LinkedList
     */
    List<Node> list =new ArrayList<>();
    Map<String, Node> map=new HashMap<>();

    public static class Node{
        String name="";
        int val=0;
        int pos=0;

        Node(String s,int key,int pos) {
            name=s;
            val=key;
            this.pos=pos;
        }
    }

    public void inc(String key) {
        if(map.containsKey(key)) {
            Node temp=map.get(key);
            temp.val++;
            int pos=temp.pos;
            while(--pos>=0 && list.get(pos).val<temp.val){
                list.get(pos).pos=pos+1;
                Collections.swap(list,pos+1,pos);
                temp.pos=pos;
            }
        }
        else {
            Node temp= new Node(key, 1, list.size());// Relative size + because frequency of 1
            list.add(temp);
            map.put(key,temp);
        }
    }

    public void dec(String key) {
        Node temp=map.get(key);
        temp.val--;
        int pos=temp.pos;
        while(++pos< list.size() && list.get(pos).val>temp.val) {
            list.get(pos).pos=pos-1;
            Collections.swap(list,pos,pos-1);
            list.get(pos).pos=pos;
        }
        if(temp.val==0) {
            list.remove(pos-1);
            map.remove(key);
        }
    }

    public String getMaxKey() {
        return list.size()==0?"": list.get(0).name;
    }

    public String getMinKey() {
        return list.size()==0?"": list.get(list.size()-1).name;
    }

    public static void main(String[] args) {
        All_O_DataStructure_432 allO = new All_O_DataStructure_432();
        allO.inc("hello");
        allO.inc("hello");
        System.out.println(allO.getMaxKey());
        System.out.println(allO.getMinKey());
        allO.inc("leet");
        System.out.println(allO.getMaxKey());
        System.out.println(allO.getMinKey());
        All_O_DataStructure_432 allO2 = new All_O_DataStructure_432();
        allO2.inc("hello");
        allO2.inc("goodbye");
        allO2.inc("hello");
        allO2.inc("hello");
        System.out.println(allO2.getMaxKey());
        System.out.println(allO2.getMinKey());
        allO2.inc("leet");
        allO2.inc("code");
        allO2.inc("leet");
        allO2.dec("hello");
        allO2.inc("leet");
        allO2.inc("code");
        allO2.inc("code");
        System.out.println(allO2.getMaxKey());
        System.out.println(allO2.getMinKey());
    }

}
