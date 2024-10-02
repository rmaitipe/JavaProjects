package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Reorder_List_143 {

	/*
	 * You are given the head of a singly linked-list. The list can be represented as:
	 * L0 → L1 → … → Ln - 1 → Ln
	 * Reorder the list to be on the following form:
	 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
	 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
	 * 12345 -> 15243
	 */

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(1);
	    l1Head.next=new ListNode(2);
	    l1Head.next.next =new ListNode(3);
	    l1Head.next.next.next =new ListNode(4);
		l1Head.next.next.next.next =new ListNode(5);
	    Reorder_List_143 ob = new Reorder_List_143();
 	    ListNode finalNode = ob.reorderList(l1Head);
		 while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		 }
    }


	//O(n) create list with index positions, initialize 2 pointers left=0, right =n
	// while left>right using a flip variable add node left++ else right--
	private ListNode reorderList(ListNode l1Head) {
		ListNode node=l1Head;
		List<ListNode> list= new LinkedList<>();
		int count=0;
		while (node!=null){
			list.add(node);
			count++;
			node=node.next;
		}
		ListNode retNode = null;
		int j=0;
		for (int i=0;i<count;i++){
			if (i%2==0){
				if (i==0){
					retNode=list.get(j);
				}else {
					retNode.next=list.get(j);
					retNode=retNode.next;
				}
				j++;
			}else{
				retNode.next=list.get(count-j);
				retNode=retNode.next;
			}
		}
		retNode.next=null;//close loop
		return l1Head;
	}

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}
	/*
	Approach: Find the middle of the linked list. 	Reverse the second half of the linked list.
    Merge the two halves of the linked list back together.
	 */

	public ListNode reorderListAccepted(ListNode head) {
		if(head==null || head.next==null){
			return null;
		}
		ListNode mid=findMid(head);
		ListNode head2=reverse(mid); //1->2->3<-4<-5
		mid.next=null;
		head=recordList(head,head2);
		return  head;
	}
	public static ListNode findMid(ListNode head){
		ListNode slow=head;
		ListNode fast=head;
		while(fast!=null && fast.next!=null){
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow;
	}
	public static ListNode reverse(ListNode head){
		ListNode prev=null;
		ListNode curr=head.next;
		ListNode next;
		while(curr!=null){
			next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
		}
		return prev;

	}
	public static ListNode recordList(ListNode head1 , ListNode head2){
		ListNode Original=head1;
		while(head1!=null && head2!=null){
			ListNode temp1=head1.next;
			ListNode temp2=head2.next;
			head1.next=head2;
			head2.next=temp1;
			head1=temp1;
			head2=temp2;
		}
		return Original;
	}
}
