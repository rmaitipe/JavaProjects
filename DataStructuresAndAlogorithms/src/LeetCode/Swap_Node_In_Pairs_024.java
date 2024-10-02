package LeetCode;

public class Swap_Node_In_Pairs_024 {
	/*
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * You must solve the problem without modifying the values in the list's nodes
	 * (i.e., only nodes themselves may be changed.)
	 * Input: head = [1,2,3,4]		Output: [2,1,4,3]
	 *
	 * Not working
	 */
	public ListNode swapPairs(ListNode head) {
		ListNode node=head;
		ListNode headN=head.next;
        while (node!=null && node.next!=null){
			ListNode nextIter=node.next.next;
			ListNode temp=node.next;
			node.next=node;
			node=temp;
			node=nextIter;
		}
		return headN;
	}


	public static void main(String args[]) {
        ListNode l1Head =new ListNode(1);
	    l1Head.next=new ListNode(2);
	    l1Head.next.next =new ListNode(3);
	    l1Head.next.next.next =new ListNode(4);
	    Swap_Node_In_Pairs_024 ob = new Swap_Node_In_Pairs_024();
 	    ListNode finalNode = ob.swapPairs(l1Head);
		while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		}
		ListNode finalNode2 = ob.swapPairsAccepted(l1Head);
		while (finalNode2!=null){
			System.out.println(finalNode2.val);
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

	public ListNode swapPairsAccepted(ListNode head) {
		ListNode dummy = new ListNode(0, head);
		ListNode prev = dummy, cur = head;
		while (cur != null && cur.next != null) {
			ListNode npn = cur.next.next;
			ListNode second = cur.next;
			//swap
			second.next = cur;
			cur.next = npn;
			prev.next = second;
			//setup for next loop
			prev = cur;
			cur = npn;
		}
		return dummy.next;
	}

}
