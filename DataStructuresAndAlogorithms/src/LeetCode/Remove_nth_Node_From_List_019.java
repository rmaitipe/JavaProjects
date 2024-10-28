package LeetCode;

public class Remove_nth_Node_From_List_019 {

	/*
	 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
	 * Input: head = [1,2,3,4,5], n = 2		Output: [1,2,3,5]
	 */

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(5);
	    l1Head.next=new ListNode(9);
	    l1Head.next.next =new ListNode(8);
	    l1Head.next.next.next =new ListNode(7);
	    Remove_nth_Node_From_List_019 ob = new Remove_nth_Node_From_List_019();
 	    ListNode finalNode = ob.removeNth(l1Head,2);
		 while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		 }
    }

	private ListNode removeNth(ListNode l1Head, int target) {
		ListNode node=l1Head;
		ListNode prev=null;
		int count=1;
		while (node!=null){
			if (count==target){
				if (node.next!=null){
					ListNode temp=node.next;
					node.val=temp.val;
					node.next=temp.next;
				}else{
					if (prev!=null){
						prev.next=null;
					}
				}
				break;
			}else{
				count++;
				prev=node;
				node=node.next;
			}
		}
		return l1Head;
	}

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

}
