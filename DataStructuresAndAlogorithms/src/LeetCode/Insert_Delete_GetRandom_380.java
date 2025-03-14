package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Implement the RandomizedSet class:
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
public class Insert_Delete_GetRandom_380 {
/*
Use a combination of a HashMap and an ArrayList
HashMap stores the value and its corresponding index in the ArrayList. It allows O(1) insertions and deletions.
ArrayList stores the actual elements. It provides O(1) time complexity for accessing a random element, adding an element,
and removing an element from the end of the list (we do this by swapping the element to remove with the last element).
 */
    HashMap<Integer,Integer> hashMap;
    ArrayList<Integer> arrayList;

    boolean insert(int val){
        boolean retVal=false;
        if (!hashMap.containsKey(val)) {
            arrayList.add(val);
            int index = arrayList.size();
            hashMap.put(val, index);
            retVal=true;
        }
        return retVal;
    }

    boolean remove(int val){
        boolean retVal=false;
        if (hashMap.containsKey(val)) {
            int index = hashMap.get(val);
            hashMap.remove(val);
            int lastVal = arrayList.removeLast();
            hashMap.put(lastVal, index);
            arrayList.set(index, lastVal);
            retVal=true;
        }
        return retVal;
    }

    int getRandom(){
        int size=hashMap.size();
        return arrayList.get((int) (size*Math.random()));
    }

public static void main(String args[]) {
    Insert_Delete_GetRandom_380 lRUCache = new Insert_Delete_GetRandom_380();

    System.out.println(lRUCache);
}


}
