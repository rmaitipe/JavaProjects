package LeetCode;

import java.util.HashSet;

public class Intersection_Linked_List_160 {

	/*
	 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
	 * If the two linked lists have no intersection at all, return null.
	 */
	public ListNode intersectMethod(ListNode l1,ListNode l2) {
		ListNode retVal=null;
		HashSet<ListNode> mySet = new HashSet<>();
		while (l1!=null){
			mySet.add(l1);
			l1=l1.next;
		}
		while (l2!=null){
			boolean val= mySet.add(l2);
			if (val==false){
				retVal=l2;
				break;
			}
			l2=l2.next;
		}
		return retVal;
	}

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
	    Intersection_Linked_List_160 ob = new Intersection_Linked_List_160();
 	    ListNode finalNode = ob.intersectMethod(l1Head,l2Head);
		 while (finalNode!=null){
			 System.out.print(finalNode.val);
			 finalNode=finalNode.next;
		 }
    }
	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
