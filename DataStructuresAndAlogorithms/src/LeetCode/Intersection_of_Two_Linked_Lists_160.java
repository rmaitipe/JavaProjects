package LeetCode;

import java.util.HashSet;

public class Intersection_of_Two_Linked_Lists_160 {

	/*
	 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
	 * If the two linked lists have no intersection at all, return null.
	 */

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(9);
	    l1Head.next=new ListNode(9);
	    l1Head.next.next =new ListNode(9);
	    l1Head.next.next.next =new ListNode(9);
	    ListNode l2Head =new ListNode(9);
		l2Head.next=new ListNode(9);
		l2Head.next.next =new ListNode(9);
		l2Head.next.next.next =new ListNode(9);
		l2Head.next.next.next.next=new ListNode(9);
		l2Head.next.next.next.next.next =new ListNode(9);
		l2Head.next.next.next.next.next.next =new ListNode(9);
	    Intersection_of_Two_Linked_Lists_160 ob = new Intersection_of_Two_Linked_Lists_160();
 	    ListNode finalNode = ob.findIntersection(l1Head, l2Head);
		 while (finalNode!=null){
			 System.out.print(finalNode.val);
			 finalNode=finalNode.next;
		 }
    }

	private ListNode findIntersection(ListNode l1Head, ListNode l2Head) {
		ListNode a = l1Head;
		ListNode b = l2Head;
		HashSet<ListNode> hashSet = new HashSet<>();
		boolean isUnique =false;
		while (a!= null){
			hashSet.add(a);
			a=a.next;
		}
		while (b!= null){
			isUnique = hashSet.add(b);
			b=b.next;
			if (!isUnique){
				break;
			}
		}
		if (isUnique) return null;
		else return b;
	}

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
