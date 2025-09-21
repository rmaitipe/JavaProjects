package LeetCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
     * Implement the LRUCache class:
     * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
     * int get(int key) Return the value of the key if the key exists, otherwise return -1.
     * void put(int key,int value) Update the value of the key if it exists. Otherwise, add the key-value pair to the cache.
     * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
     * The functions get and put must each run in O(1) average time complexity.
     *
     * 1-3,3-2,5-2,7-1,5-0,6-4 size=4
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
        if (headMap.containsKey(key)) { //update order 1375
            headMap.remove(key);
            headMap.put(key, val);
        }
        else{                           //update order 3756
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
            return val;
        }else return -1;
    }

    public static void main(String args[]) {
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

        LRUCache lRUCacheA = new LRUCache(2);
        lRUCacheA.put(1, 1); // cache is {1=1}
        lRUCacheA.put(2, 2); // cache is {1=1, 2=2}
        lRUCacheA.get(1);    // return 1
        lRUCacheA.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCacheA.get(2);    // returns -1 (not found)
        lRUCacheA.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCacheA.get(1);    // return -1 (not found)
        lRUCacheA.get(3);    // return 3
        lRUCacheA.get(4);    // return 4
        System.out.println(lRUCacheA);
    }

    //Accepted alternate solution Map + DoubleLinkedList.

    public static class LRUCache {
    private class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0, 0);
        }
    }
    private int capacity, count;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if(null==n){
            return -1;
        }
        update(n);
        return n.value;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if(null==n){
            n = new Node(key, value);
            map.put(key, n);
            add(n);
            ++count;
        }
        else{
            n.value = value;
            update(n);
        }
        if(count>capacity){
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
            --count;
        }
    }

    private void update(Node node){
        remove(node);
        add(node);
    }
    private void add(Node node){
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    private void remove(Node node){
        Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev = before;
    }
}

}
