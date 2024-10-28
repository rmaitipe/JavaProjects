package LeetCode;

import java.util.HashSet;

public class Intersection_of_Two_Linked_Lists_160 {
	/*
	 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
	 * If the two linked lists have no intersection at all, return null.
	 *
	 * Challenge - do with constant space complexity
	 */
	public static void main(String args[]) {
		ListNode intersect= new ListNode(3);
		ListNode l1Head =new ListNode(9);
		l1Head.next=new ListNode(8);
		l1Head.next.next =new ListNode(6);
		l1Head.next.next.next =intersect;
		ListNode l2Head =new ListNode(9);
		l2Head.next=new ListNode(4);
		l2Head.next.next =new ListNode(5);
		l2Head.next.next.next =new ListNode(6);
		l2Head.next.next.next.next=new ListNode(9);
		l2Head.next.next.next.next.next =intersect;
		l2Head.next.next.next.next.next.next =new ListNode(9);
	    Intersection_of_Two_Linked_Lists_160 ob = new Intersection_of_Two_Linked_Lists_160();
 	    ListNode finalNode = ob.findIntersection(l1Head, l2Head);
		 if  (finalNode!=null){
			 System.out.print(finalNode.val);
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
			if (!isUnique){
				break;
			}
			b=b.next;
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

	/*
	 * The first step in solving the problem is to understand that if two linked lists intersect, the nodes after the
	 * intersection point will be the same. Therefore, the lists can differ in length before the intersection point,
	 * but once they converge, they will remain the same until the end.
	 * One way to tackle this problem is to first calculate the length of both lists, then align the starting points of
	 * both lists to ensure that they traverse the same number of nodes before comparing them node by node.
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		// Get the lengths of both lists
		int count1 = 0;
		int count2 = 0;
		ListNode temp1 = headA;
		ListNode temp2 = headB;
		while (temp1 != null) {
			temp1 = temp1.next;
			count1++;
		}
		while (temp2 != null) {
			temp2 = temp2.next;
			count2++;
		}
		// Reset temp pointers
		temp1 = headA;
		temp2 = headB;
		// Adjust starting point of the longer list
		if (count1 > count2) {
			int diff = count1 - count2;
			for (int i = 0; i < diff; i++) {
				temp1 = temp1.next;
			}
		} else if (count2 > count1) {
			int diff = count2 - count1;
			for (int i = 0; i < diff; i++) {
				temp2 = temp2.next;
			}
		}
		// Move both pointers until they meet at the intersection
		while (temp1 != null && temp2 != null) {
			if (temp1 == temp2) {
				return temp1; // return the intersection node
			}
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		return null; // no intersection
	}
}
