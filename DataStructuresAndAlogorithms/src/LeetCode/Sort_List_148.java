package LeetCode;

public class Sort_List_148 {
	/*
	 * Given the head of a linked list, return the list after sorting it in ascending order.
	 * Input: head = [-1,5,3,4,0]	Output: [-1,0,3,4,5] -1 3 5
	 *
	 * Follow up: Can you sort the linked list in O(n log(n)) time and O(1) memory (i.e. constant space)?
	 */
	public ListNode sortList(ListNode head) { //Insert Sort O(n2)
		ListNode node=head;
		ListNode sortedBound=node;
		while (node!=null){
			if (node.val<sortedBound.val){
				ListNode temp=node.next;//set the next cycle
				//shift left sortedBound.next
				insertNode(head,node);
				node=temp;
			}else{
				sortedBound=node;
				node=node.next;
			}
		}
		sortedBound.next=null;
		return head;
	}

	private void insertNode(ListNode node, ListNode insertNode) {
		while (node!=null){
			if (node.next==null){//edge case 1 elem
				if (node.val<insertNode.val) {
					node.next = insertNode;
					insertNode.next = null;
				} else{
					ListNode temp=node;
					node=insertNode;
					node.next=temp;
				}
			} else if (node.val<insertNode.val && node.next.val>insertNode.val){
				ListNode temp=node.next;
				node.next=insertNode;
				insertNode.next=temp;
				break;
			} else{
				node=node.next;
			}
		}
	}

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(-1);
	    l1Head.next=new ListNode(5);
	    l1Head.next.next =new ListNode(3);
	    l1Head.next.next.next =new ListNode(4);
		l1Head.next.next.next.next =new ListNode(0);
	    Sort_List_148 ob = new Sort_List_148();
 	    ListNode finalNode = ob.sortList(l1Head);
		while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		}
		ListNode finalNode2 = ob.sortListAccepted(l1Head);
		while (finalNode2!=null){
			System.out.println("Accepted: "+finalNode2.val);
			finalNode2=finalNode2.next;
		}
    }

	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode rand) { this.val = val;}
	}

	/*Algo Used: Merge Sort
	If the list is empty or has only one element (head==null or head.next==null), it's already sorted, return the head.
	Find the middle node of the list. Separates the list into left (head to mid) and right (mid.next to the end).
	The link between the halves is broken by setting mid.next = null.
	Recursively calls sortList on both left and right to sort them independently.
	finally merge to combine the sorted left and right halves into a single sorted list. (See Merge_Two_Sorted_Lists_021)
	 */
	public ListNode sortListAccepted(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = getMid(head);	// Split the list into two halves
		ListNode left = head;
		ListNode right = mid.next;
		mid.next = null; // Break the link between the two halves
		// Recursively sort the left and right halves
		left = sortListAccepted(left);
		right = sortListAccepted(right);
		return merge(left, right);		// Merge the two sorted halves
	}

	// Function to get the middle of the linked list
	private ListNode getMid(ListNode head) {
		ListNode slow = head, fast = head;
		// Use two pointers: slow and fast to find the middle
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// Function to merge two sorted linked lists i.e 5,7 & 2,6
	private ListNode merge(ListNode left, ListNode right) {
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		// Merge the two sorted lists
		while (left != null && right != null) {
			if (left.val < right.val) {
				current.next = left;
				left = left.next;
			} else {
				current.next = right;
				right = right.next;
			}
			current = current.next;
		}
		// Append the remaining elements
		if (left != null) {
			current.next = left;
		}
		if (right != null) {
			current.next = right;
		}
		return dummy.next;
	}
}
