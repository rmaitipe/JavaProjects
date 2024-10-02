package LeetCode;

import java.util.LinkedHashMap;

    /*
     * Implement the LRUCache class:
     * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
     * int get(int key) Return the value of the key if the key exists, otherwise return -1.
     * void put(int key, int value) Update the value of the key if the key exists. Otherwise,
     * add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
     * evict the least recently used key.
     * The functions get and put must each run in O(1) average time complexity.
     */
public class LRU_Cache_146 {

    LinkedHashMap<Integer,Integer> headMap;
    int size;
    int currSize=0;

    public LRU_Cache_146(int size){
        headMap=new LinkedHashMap<>();
        this.size=size;
    }

    public void put(Integer key,Integer val){
        if (headMap.containsKey(key)) {//update order
            headMap.remove(key);
            headMap.put(key, val);
        }
        else{
            if (currSize==size) {
                headMap.remove(headMap.firstEntry().getKey());
            }else{
                currSize++;
            }
            headMap.put(key, val);
        }
    }

    public Integer get(Integer key){
        if (headMap.containsKey(key)) {
            int val = headMap.remove(key);
            headMap.put(key, val);
            return headMap.get(key);
        }else return -1;
    }

    public static void main(String args[])    {
        LRU_Cache_146 lRUCache = new LRU_Cache_146(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
        System.out.println(lRUCache);
    }
}
