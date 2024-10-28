package LeetCode;

public class Swap_Node_In_Pairs_024 {
	/*
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * You must solve the problem without modifying the values in the list's nodes
	 * (i.e., only nodes themselves may be changed.)
	 * Input: head = [1,2,3,4]		Output: [2,1,4,3]
	 */
	public ListNode swapPairs(ListNode head) {
		ListNode node=head;
		ListNode prev=null;
		ListNode headN=head.next;//add single node check for initialization
        while (node!=null && node.next!=null){
			ListNode temp=node.next;
			node.next=node.next.next;
			temp.next=node;
			if (prev!=null){
				prev.next=temp;
			}
			//else{
			//	headN=temp;
			//}
			prev=node;
			node=node.next;
			if (node==null){
				prev.next=null;
			}
		}
		return headN;
	}

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(1);
	    l1Head.next=new ListNode(2);
	    l1Head.next.next =new ListNode(3);
	    l1Head.next.next.next =new ListNode(4);
		//l1Head.next.next.next.next =new ListNode(5);
	    Swap_Node_In_Pairs_024 ob = new Swap_Node_In_Pairs_024();
 	    ListNode finalNode = ob.swapPairs(l1Head);
		while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		}
		ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(2);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(4);
		//21Head.next.next.next.next =new ListNode(5);
		ListNode finalNode2 = ob.swapPairsAccepted(l2Head);
		while (finalNode2!=null){
			System.out.println("Accepted:"+ finalNode2.val);
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
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = dummy;
		while (current.next != null && current.next.next != null) {
			ListNode first = current.next;
			ListNode second = current.next.next;
			first.next = second.next;
			current.next = second;
			current.next.next = first;
			current = current.next.next;
		}
		return dummy.next;
	}

}
