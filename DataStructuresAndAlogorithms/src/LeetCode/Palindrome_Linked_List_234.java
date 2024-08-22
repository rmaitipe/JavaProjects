package LeetCode;

public class Palindrome_Linked_List_234 {

	/*
	 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
	 */
	public boolean validateMethod(ListNode l1) {
		boolean retVal=false;
		while (l1!=null){
		//iterate full list attach to sb convert to string reverse
			// create a reverse the linkedList while traversing then iterate to length/2??
		}
		return retVal;
	}

	public static void main(String args[]) {
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(7);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(6);
		l2Head.next.next.next.next=new ListNode(9);
	    Palindrome_Linked_List_234 ob = new Palindrome_Linked_List_234();
 	    System.out.print(ob.validateMethod(l2Head));
    }
	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
