package LeetCode;

public class Remove_nth_Node_From_End_of_List_019 {
	/*
	 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
	 * Input: head = [1,2,3,4,5], n = 2		Output: [1,2,3,5]
	 * If the number of nodes in the list is sz. 1 <= n <= sz
	 */
	public static void main(String args[]) {
        ListNode l1Head =new ListNode(5);
	    l1Head.next=new ListNode(9);
	    l1Head.next.next =new ListNode(8);
	    l1Head.next.next.next =new ListNode(7);
	    Remove_nth_Node_From_End_of_List_019 ob = new Remove_nth_Node_From_End_of_List_019();
 	    ob.removeNth(l1Head,2);
		 while (l1Head!=null){
			 System.out.println(l1Head.val);
			 l1Head=l1Head.next;
		 }
		ListNode l2Head =new ListNode(5);
		l2Head.next=new ListNode(9);
		l2Head.next.next =new ListNode(8);
		l2Head.next.next.next =new ListNode(7);
		ListNode retNode=ob.removeNthFromEndAccepted(l2Head,2);
		while (retNode!=null){
			System.out.print(retNode.val);
			retNode=retNode.next;
		}
    }

	private void removeNth(ListNode l1Head, int target) {
		ListNode node=l1Head;
		ListNode runner=l1Head;
		int count=0;
		while (node!=null){
			if (count<=target){
				count++;
			}else{
				runner=runner.next;
			}
			node=node.next;
		}
		runner.next=runner.next.next;
	}

	/*
	 * We create a dummy node res with a value of 0 and set its next pointer to the head of the original list.
	 * This dummy node helps in handling edge cases when removing the first node.
	 */
	public ListNode removeNthFromEndAccepted(ListNode head, int n) {
		ListNode res = new ListNode(0, head);
		ListNode dummy = res;
		for (int i = 0; i < n; i++) {
			head = head.next;
		}
		while (head != null) {
			head = head.next;
			dummy = dummy.next;
		}
		dummy.next = dummy.next.next;
		return res.next;
	}


	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
