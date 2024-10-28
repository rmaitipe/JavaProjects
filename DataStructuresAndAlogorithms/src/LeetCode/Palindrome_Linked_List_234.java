package LeetCode;


public class Palindrome_Linked_List_234 {
	/*
	 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
	 * Input: head = [1,2,2,1]		Output: true
	 *
	 * Optimal: solve without new nodes
	 * get count+2 pointers, then Reverse_Linked_List_206
	 */
	public boolean validateMethod(ListNode l1) {
		boolean retVal= true;
		ListNode node= l1;
		ListNode sl2 = null;
		int count =0;
		while (node!=null){
			ListNode curr= new ListNode(node.val);
			if (sl2==null){
				sl2=curr;
			} else{
				ListNode temp=sl2;
				curr.next=temp;
				sl2=curr;
			}
			node=node.next;
			count++;
		}
		ListNode n1=l1;
		ListNode n2=sl2;
		for (int i=0;i<count/2;i++){
			if (n1.val!=n2.val){
				retVal=false; break;
			} else{
				n1=n1.next;
				n2=n2.next;
			}
		}
		return retVal;
	}

	public static void main(String args[]) {
		ListNode l1EvenHead =new ListNode(4);
		l1EvenHead.next=new ListNode(3);
		l1EvenHead.next.next =new ListNode(9);
		l1EvenHead.next.next.next =new ListNode(9);
		l1EvenHead.next.next.next.next=new ListNode(3);
		l1EvenHead.next.next.next.next.next =new ListNode(4);
	    ListNode l2OddHead =new ListNode(1);
		l2OddHead.next=new ListNode(7);
		l2OddHead.next.next =new ListNode(3);
		l2OddHead.next.next.next =new ListNode(7);
		l2OddHead.next.next.next.next=new ListNode(1);
	    Palindrome_Linked_List_234 ob = new Palindrome_Linked_List_234();
		System.out.println(ob.validateMethod(l1EvenHead));
 	    System.out.println(ob.isPalindromeAccepted(l2OddHead));
    }

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while(curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public boolean isPalindromeAccepted(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode temp = reverse(slow.next); //12321   12123
		//slow.next = temp;
		ListNode p1 = head;
		ListNode p2 = temp;
		while(p2 != null) {
			if(p1.val != p2.val) return false;
			p1 = p1.next;
			p2 = p2.next;
		}
		return true;
	}
}
