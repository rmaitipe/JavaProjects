package LeetCode;

public class Reverse_Linked_List_II_092 {

	/*
	 * Given the head of a singly linked list and two integers left and right where left <= right, reverse
	 * the nodes of the list from position left to position right, and return the reversed list.
	 * Input: head = [1,2,3,4,5], left = 2, right = 4
	 * Output: [1,4,3,2,5]
	 */
	public ListNode reverseMethod(ListNode l1,int left,int right) {
		ListNode curr=l1;
		ListNode prev=null;
		while (curr.val != left) {
			prev = curr;
			curr = curr.next;
		}
		//reverse LL
		ListNode l2Head=null;
		ListNode l2Tail=curr;
		while (curr.val!=right) {
			if (l2Head ==null){
				l2Head=new ListNode(curr.val);
			}else {
				ListNode temp = new ListNode(curr.val);
				temp.next=l2Head;
				l2Head = temp;
			}
			curr=curr.next;
		}
		prev.next=l2Head;
		l2Tail.next=curr.next;
		return l1;
	}

	public static void main(String args[]) {
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(2);
		l2Head.next.next =new ListNode(3);
		l2Head.next.next.next =new ListNode(4);
		l2Head.next.next.next.next=new ListNode(5);

		Reverse_Linked_List_II_092 ob = new Reverse_Linked_List_II_092();
		int left=2;int right=4;
		ListNode finalNode=ob.reverseMethod(l2Head, left,right);
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
