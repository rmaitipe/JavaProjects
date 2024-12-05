package LeetCode;

import java.util.*;

/*
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
    Implement the AllOne class:
    AllOne() Initializes the object of the data structure.
    inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
    dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
    getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
    getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".

Note that each function must run in O(1) average time complexity.
 */
public class All_O_DataStructure_432 {
//Treemap is sorted on keys - You can not sort TreeMap on values. To provide sort on values you will need SortedSet.

    public All_O_DataStructure_432(){

    }

    public void inc(String key){

    }

    public void dec(String key){

    }

    public String getMinKey(){
        return "";
    }

    public String getMaxKey(){
        return "";
    }

    public static void main(String args[]) {
        All_O_DataStructure_432 allO = new All_O_DataStructure_432();
        /*allO.inc("hello");
        allO.inc("hello");
        allO.getMaxKey();
        allO.getMinKey();
        allO.inc("leet");
        allO.getMaxKey();
        allO.getMinKey();*/

        allO.inc("hello");
        allO.inc("goodbye");
        allO.inc("hello");
        allO.inc("hello");
        allO.getMaxKey();
        //allO.getMinKey();
        allO.inc("leet");
        allO.inc("code");
        allO.inc("leet");
        allO.dec("hello");
        allO.inc("leet");
        allO.inc("code");
        allO.inc("code");
        allO.getMaxKey();
    }

}
