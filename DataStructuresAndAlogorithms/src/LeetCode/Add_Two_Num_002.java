package LeetCode;

public class Add_Two_Num_002 {

	/*
	 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
	 * order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 *
	 * Pass by Value pitfall in addRemainingNode method
	 */
	public ListNode addTwoNumMethod(ListNode l1, ListNode l2) {
		ListNode retVal=null;
		ListNode curr =null;
		int carry=0;
		int sum=0;
		while (l1 !=null && l2!=null){
			sum=l1.val+l2.val+carry;
			carry=sum/10;
			if (retVal==null){
				retVal=new ListNode(sum%10);//1st
				curr=retVal;
			}else{
				curr.next= new ListNode(sum%10);
				curr=curr.next;
			}
			l1=l1.next;
			l2=l2.next;
		}
		if (l1==null){
			//curr= addRemainingNode(l2, curr, carry);
			while (l2!=null){
				sum=l2.val+carry;
				if (carry==0){
					curr.next=l2;
				}
				else{
					curr.next= new ListNode(sum%10);
				}
				carry=sum/10;
				curr=curr.next;
				l2=l2.next;
			}
		} else if (l2==null){
			while (l1!=null){
				sum=l1.val+carry;
				if (carry==0){
					curr.next=l1;
				}
				else{
					curr.next= new ListNode(sum%10);
				}
				carry=sum/10;
				curr=curr.next;
				l1=l1.next;
			}
		}
		if (carry==1){
			curr.next= new ListNode(1);
		}
		return retVal;
	}

	/*public int addRemainingNode(ListNode l3, ListNode node, int carry){
		int sum=0;
		while (l3!=null){
			sum=l3.val+carry;
			carry=sum/10;
			node.next= new ListNode(sum%10);
			node=node.next;
			l3=l3.next;
		}
		return carry;
	}*/

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(9);
	    l1Head.next=new ListNode(9);
	    l1Head.next.next =new ListNode(9);
	    l1Head.next.next.next =new ListNode(9);
	    ListNode l2Head =new ListNode(9);
		l2Head.next=new ListNode(9);
		l2Head.next.next =new ListNode(9);
		l2Head.next.next.next =new ListNode(9);
		l2Head.next.next.next.next=new ListNode(9);
		l2Head.next.next.next.next.next =new ListNode(9);
		l2Head.next.next.next.next.next.next =new ListNode(9);
	    Add_Two_Num_002 ob = new Add_Two_Num_002();
 	    ListNode finalNode = ob.addTwoNumMethod(l1Head, l2Head);
		 while (finalNode!=null){
			 System.out.print(finalNode.val);//9999+9999999 expected 89990001
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

	public ListNode addTwoNumbersAccepted(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode res = dummy;
		int total = 0, carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			total = carry;
			if (l1 != null) {
				total += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				total += l2.val;
				l2 = l2.next;
			}
			int num = total % 10;
			carry = total / 10;
			dummy.next = new ListNode(num);
			dummy = dummy.next;
		}
		return res.next;
	}
}
