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
//Treemap is sorted on keys - You can not sort TreeMap on values. To provide sort on values you will need SortedSet.

    private Map<String, Integer> keyCnt;
    private Map<Integer, Node> cntNodeMap;
    private Node head, tail;
    public All_O_DataStructure_432() {
        keyCnt = new HashMap<>();
        cntNodeMap = new HashMap<>();
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }
    private Node addNodeAfter(Node node, int c) {
        Node newNode = new Node(c);
        newNode.next = node.next;
        newNode.prev = node;
        node.next.prev = newNode;
        node.next = newNode;
        cntNodeMap.put(c, newNode);
        return newNode;
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        cntNodeMap.remove(node.cnt);
    }
    public void inc(String key) {
        int c = keyCnt.getOrDefault(key, 0);
        keyCnt.put(key, c + 1);
        Node curr = cntNodeMap.get(c);
        Node next = cntNodeMap.get(c + 1);
        if (next == null) next = addNodeAfter(curr == null ? head : curr, c + 1);
        next.keys.add(key);
        if (curr != null) {
            curr.keys.remove(key);
            if (curr.keys.isEmpty()) removeNode(curr);
        }
    }
    public void dec(String key) {
        int c = keyCnt.get(key);
        if (c == 1) keyCnt.remove(key);
        else keyCnt.put(key, c - 1);

        Node curr = cntNodeMap.get(c);
        Node prev = cntNodeMap.get(c - 1);

        if (c > 1 && prev == null) prev = addNodeAfter(curr.prev, c - 1);
        if (c > 1) prev.keys.add(key);

        curr.keys.remove(key);
        if (curr.keys.isEmpty()) removeNode(curr);
    }
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
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

    class Node {
        int cnt;
        Set<String> keys;
        Node prev, next;
        public Node(int c) {
            cnt = c;
            keys = new HashSet<>();
            prev = next = null;
        }
    }
}
