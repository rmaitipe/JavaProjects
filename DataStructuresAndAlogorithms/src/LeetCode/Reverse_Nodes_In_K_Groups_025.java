package LeetCode;

public class Reverse_Nodes_In_K_Groups_025 {
	/*
	 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
	 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is
	 * not a multiple of k then left-out nodes, in the end, should remain as it is.
	 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
	 * Input: head = [1,2,3,4,5] k=2		Output: [2,1,4,3,5]
	 */
	public ListNode reverseKGroups(ListNode head,int k) {
		ListNode node=head;
		int count=0;
		while (node!=null) {
			ListNode nextIter=null;
			ListNode seqStart=node;
			int i=0;
			while (i < k && node!=null) {//reverse LikedList k at Time
				nextIter = node.next;
				node = node.next;
				i++;
			}
			if (node!=null) {
				ListNode retVal = reverseList206Accepted(seqStart, k, nextIter);
				if (count ==0){//If i=0 assign to head
					head=retVal;
				}
			}
			count++;
			node=nextIter;
		}
		return head;
	}

	public ListNode reverseList206Accepted(ListNode head,int k, ListNode tail) {
		ListNode prev = tail;
		ListNode current = head;
		int i=0;
		while(current != null && i<k) {
			ListNode next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			i++;
		}
		return prev;
	}

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(1);
	    l1Head.next=new ListNode(2);
	    l1Head.next.next =new ListNode(3);
	    l1Head.next.next.next =new ListNode(4);
		l1Head.next.next.next.next =new ListNode(5);
	    Reverse_Nodes_In_K_Groups_025 ob = new Reverse_Nodes_In_K_Groups_025();
 	    ListNode finalNode = ob.reverseKGroups(l1Head,2);
		while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		}
		ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(2);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(4);
		l2Head.next.next.next.next =new ListNode(5);
		ListNode finalNode2 = ob.reverseKGroupAccepted(l2Head,2);
		while (finalNode2!=null){
			System.out.println("Accepted: "+finalNode2.val);
			finalNode2=finalNode2.next;
		}

    }

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

	public ListNode reverseKGroupAccepted(ListNode head, int k) {
		if( head.next==null) return head;
		ListNode temp=head;
		int size=1;
		ListNode dummy=new ListNode(0);
		ListNode t=dummy;
		ListNode Next=null;
		while(temp!=null){
			if(size==k){
				Next=temp.next;

				temp.next=null;
				ListNode newHeads=reverse(head);
				t.next=newHeads;
				t=head;
				head=Next;
				temp=Next;
				size=1;
			}else{
				size++;
				temp=temp.next;
			}
		}
		if(Next!=null){
			t.next=Next;
		}
		return dummy.next;
	}

	ListNode reverse(ListNode head){
		ListNode prev=null;
		ListNode curr=head;
		ListNode Next=head;
		while(curr!=null){
			Next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=Next;
		}
		return prev;
	}
}
