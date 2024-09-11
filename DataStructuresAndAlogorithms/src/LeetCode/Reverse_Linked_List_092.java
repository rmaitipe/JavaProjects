package LeetCode;

public class Reverse_Linked_List_092 {

	/*
	 * Given the head of a singly linked list and two integers left and right where left <= right, reverse
	 * the nodes of the list from position left to position right, and return the reversed list.
	 * Input: head = [1,2,3,4,5], left = 2, right = 4
	 * Output: [1,4,3,2,5]
	 */
	public void reverseMethod(ListNode l1,int left,int right) {
		ListNode curr=l1;
		ListNode next=null;
		while (curr!=null){
			if (curr.next!=null &&curr.next.next!=null){
				next=curr.next.next;
				if (left==curr.val && right==next.val){
					int temp=curr.val;
					curr.val=next.val;
					next.val=temp;
					break;
				}
			}
			curr=curr.next;
		}
	}

	public static void main(String args[]) {
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(2);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(4);
		l2Head.next.next.next.next=new ListNode(5);

	    Reverse_Linked_List_092 ob = new Reverse_Linked_List_092();
		int left=2;int right=4;
 	    ob.reverseMethod(l2Head, left,right);
		System.out.println("reverseMethod");
    }
	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
