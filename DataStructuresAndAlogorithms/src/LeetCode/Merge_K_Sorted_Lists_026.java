package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Merge_K_Sorted_Lists_026 {
    /*
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     * Merge all the linked-lists into one sorted linked-list and return it.
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]   Output: [1,1,2,3,4,4,5,6]
     */

    public static void main(String args[]) {
        Merge_K_Sorted_Lists_026 ob = new Merge_K_Sorted_Lists_026();
        ListNode l1Head =new ListNode(2);
        l1Head.next= new ListNode(3);
        l1Head.next.next =new ListNode(5);
        ListNode l2Head =new ListNode(1);
        l2Head.next= new ListNode(7);
        l2Head.next.next =new ListNode(10);
        l2Head.next.next.next =new ListNode(6);
        l2Head.next.next.next.next=new ListNode(9);
        List<ListNode> list=new ArrayList<>();
        list.add(l2Head); list.add(l1Head);
        System.out.println(ob.mergeKListsAccepted(list));
    }

    /*
     * Employs a min-heap to efficiently identify the smallest element among all lists.
     * Creates a min-heap (PriorityQueue) to store the heads of the input lists.
     * The heap is sorted based on the node values, with the smallest value at the top.
     * Adds the head of each non-empty list to the min-heap.
     * Creates a dummy node (dummy) to serve as the starting point of the merged list.
     * Iterates as long as the min-heap is not empty:
     * Extracts the node with the smallest value from the min-heap (using poll).
     * Appends this smallest node to the end of the merged list, updating the current pointer.
     * If the extracted node has a next node in its original list, adds the next node to the min-heap for further processing.
     * Returns the head of the merged list (dummy.next), skipping the dummy node itself.
     */
    private ListNode mergeKListsAccepted(List<ListNode> lists){
    // Min-heap to store the smallest elements from each list
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {   // Add the head of each list to the heap
            if (list != null) {
                minHeap.add(list);
            }
        }
        // Dummy node to build the result list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll(); // Extract the smallest node from the heap
            current.next = smallest;
            current = current.next;
            if (smallest.next != null) { // If there is a next node in the list, add it to the heap
                minHeap.add(smallest.next);
            }
        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }

}
