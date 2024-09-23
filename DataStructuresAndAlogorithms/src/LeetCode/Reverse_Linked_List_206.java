package LeetCode;

public class Reverse_Linked_List_206 {

	/*
	 * Given the head of a singly linked list, reverse the list, and return the reversed list.
	 * Not Optimal. It can further be reduced.
	 */
	public ListNode reverseMethod(ListNode l1) {
		ListNode pre=null;
		ListNode curr=null;
		ListNode retVal=null;
		while (l1!=null){
			if (pre==null){
				pre= new ListNode(l1.val);
				retVal=pre;
			} else{
				curr = new ListNode(l1.val);
				curr.next = pre;
				pre=curr;
				retVal=curr;
			}
			l1=l1.next;
		}
		return retVal;
	}

	public static void main(String args[]) {
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(7);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(6);
		l2Head.next.next.next.next=new ListNode(9);

	    Reverse_Linked_List_206 ob = new Reverse_Linked_List_206();
 	    ListNode finalNode = ob.reverseMethod(l2Head);
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

	    public ListNode reverseListAccepted(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while(current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
       return prev;
    }

}
