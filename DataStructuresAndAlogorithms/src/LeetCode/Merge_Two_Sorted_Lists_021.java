package LeetCode;

public class Merge_Two_Sorted_Lists_021 {
	/*
	 * You are given the heads of two sorted linked lists list1 and list2.
	 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
	 * Return the head of the merged linked list.
	 * Final solution notes:New Nodes not needed
	 */
	private ListNode mergeSorted(ListNode leftP,ListNode rightP) {
		//ListNode leftP = sl1;//nn
		//ListNode rightP = sl2;//nn
		ListNode retVal=null;
		ListNode curr = null;
		while (leftP.next!=null && rightP.next!=null){
			if (leftP.val< rightP.val){
				if (retVal==null){
					retVal=new ListNode(leftP.val);
					curr=retVal;
				} else{
					//curr.next=new ListNode(leftP.val);
					curr.next=leftP;
					curr=curr.next;
				}
				leftP=leftP.next;

			}else{
				if (retVal==null){
					retVal=new ListNode(rightP.val);
					curr=retVal;
				}else{
					//curr.next=new ListNode(rightP.val);
					curr.next=rightP;
					curr=curr.next;
				}
				rightP=rightP.next;
			}
		}
		if (leftP.next!=null || rightP.next!=null){
			curr.next = (leftP.next != null) ? leftP : rightP;
		}
		/*while (leftP.next!=null){
			curr.next=new ListNode(leftP.val);
			curr=curr.next;
			leftP=leftP.next;
		}
		while (rightP.next!=null){
			curr.next=new ListNode(rightP.val);
			curr=curr.next;
			rightP=rightP.next;
		}*/
		return retVal;
	}

	public static void main(String args[]) {
		ListNode l1Head =new ListNode(2);
		l1Head.next=new ListNode(5);
		l1Head.next.next =new ListNode(6);
		l1Head.next.next.next  =new ListNode(21);
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(7);
		l2Head.next.next =new ListNode(14);
		l2Head.next.next.next =new ListNode(16);
		l2Head.next.next.next.next=new ListNode(19);
	    Merge_Two_Sorted_Lists_021 ob = new Merge_Two_Sorted_Lists_021();
		ListNode finalNode=ob.mergeSorted(l1Head,l2Head);
		while (finalNode!=null){
			System.out.println(finalNode.val);
			finalNode=finalNode.next;
		}
		ListNode finNode=ob.mergeTwoListsAccepted(l1Head,l2Head);
		while (finNode!=null){
			System.out.println(finNode.val);
			finNode=finNode.next;
		}
    }
	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

	public ListNode mergeTwoListsAccepted(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode();
		ListNode cur = dummy;
		while (list1 != null && list2 != null) {
			if (list1.val > list2.val) {
				cur.next = list2;
				list2 = list2.next;
			} else {
				cur.next = list1;
				list1 = list1.next;
			}
			cur = cur.next;
		}
		cur.next = (list1 != null) ? list1 : list2;
		return dummy.next;
	}

}
