package LeetCode;

import java.util.HashSet;

public class Linked_List_Cycle_141 {
	/*
	 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
	 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
	 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
	 *  connected to. Note that pos is not passed as a parameter.
	 * Return true if there is a cycle in the linked list. Otherwise, return false.
	 *
	 * Optimal Solution: O(n) time & O(1) space 	Floyd’s Cycle Detection Algorithm
	 */
	private boolean isLoop(ListNode sl1) {
		ListNode a = sl1;
		HashSet<ListNode> hashSet = new HashSet<>();
		boolean isUnique =false;
		while (a!= null){
			isUnique=hashSet.add(a);
			a=a.next;
			if (!isUnique){
				break;
			}
		}
		return !isUnique;
	}

	public static void main(String args[]) {
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(7);
		ListNode loop=new ListNode(10);
		l2Head.next.next =loop;
		l2Head.next.next.next =new ListNode(6);
		l2Head.next.next.next.next=new ListNode(9);
		l2Head.next.next.next.next.next=loop;
	    Linked_List_Cycle_141 ob = new Linked_List_Cycle_141();
		System.out.println(ob.isLoop(l2Head));
		System.out.println(ob.isLoopAccepted(l2Head));
    }

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

	public boolean isLoopAccepted(ListNode head) {
		if(head == null) return false;
		ListNode slow = head;
		ListNode fast = head;
		while(fast!=null && fast.next!=null){
			fast= fast.next.next;
			slow= slow.next;
			if(fast==slow){
				return true;
			}
		}
		return false;
	}

}
