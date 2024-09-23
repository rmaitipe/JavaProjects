package LeetCode;


public class Palindrome_Linked_List_234 {
	/*
	 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
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
			}
			else{
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
		ListNode l1Head =new ListNode(9);
		l1Head.next=new ListNode(9);
		l1Head.next.next =new ListNode(9);
		l1Head.next.next.next =new ListNode(9);
		l1Head.next.next.next.next=new ListNode(9);
		l1Head.next.next.next.next.next =new ListNode(9);
		l1Head.next.next.next.next.next.next =new ListNode(9);
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(7);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(6);
		l2Head.next.next.next.next=new ListNode(9);
	    Palindrome_Linked_List_234 ob = new Palindrome_Linked_List_234();
		System.out.println(ob.validateMethod(l1Head));
 	    System.out.println(ob.validateMethod(l2Head));
    }
	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
