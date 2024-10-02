package LeetCode;

import java.util.HashMap;

public class Copy_List_With_Random_Pointer_138 {
	/*
	 * A linked list of length n is given such that each node contains an additional random pointer,
	 * which could point to any node in the list, or null.Construct a deep copy of the list.
	 * The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of
	 * its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in
	 * the copied list such that the pointers in the original list and copied list represent the same list state.
	 * None of the pointers in the new list should point to nodes in the original list.
	 *
	 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
	 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
	 * Return the head of the copied linked list.
	 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]	Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
	 */
	public ListNode copyList(ListNode head) {
		ListNode node=head;
		ListNode dummy= new ListNode();
		ListNode nextIter = dummy;
		HashMap<ListNode,Integer> oldMap=new HashMap<>();
		HashMap<Integer,ListNode> newMap=new HashMap<>();
		int i=0;
        while (node!=null){
			nextIter.next=new ListNode(node.val);
			oldMap.put(node.rand,i);
			newMap.put(i,nextIter);//correlation maps
			i++;
            node=node.next;
			nextIter=nextIter.next;
		}
		ListNode secondIter= dummy.next;
		ListNode pre=head;
		while (secondIter!=null){
			int nodeIdx=oldMap.get(pre.rand);
			secondIter.rand=newMap.get(nodeIdx);
			secondIter=secondIter.next;
			pre=pre.next;
		}
		return dummy.next;
	}

	public static void main(String args[]) {
        ListNode l1Head =new ListNode(7,null);
		ListNode l4 =new ListNode(1,l1Head);
		ListNode l2 =new ListNode(11,l4);
	    l1Head.next=new ListNode(13,l1Head);
	    l1Head.next.next =new ListNode(11,l4);
	    l1Head.next.next.next =new ListNode(10,l2);
		l1Head.next.next.next.next =l4;
	    Copy_List_With_Random_Pointer_138 ob = new Copy_List_With_Random_Pointer_138();
 	    ListNode finalNode = ob.copyList(l1Head);
		while (finalNode!=null){
			 System.out.println(finalNode.val);
			 finalNode=finalNode.next;
		}
		ListNode finalNode2 = ob.copyRandomListAccepted(l1Head);
		while (finalNode2!=null){
			System.out.println("Accepted :"+finalNode2.val);
			finalNode2=finalNode2.next;
		}
    }

	public static class ListNode {
    	int val;
  		ListNode next;
		ListNode rand;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode rand) { this.val = val; this.rand = rand;}
	}

	public ListNode copyRandomListAccepted(ListNode head) {
		if(head==null){
			return null;
		}
		ListNode curr = head;
		HashMap<ListNode, ListNode> map = new HashMap<>();
		while (curr != null) {
			map.put(curr, new ListNode(curr.val));
			curr = curr.next;
		}
		curr=head;
		while(curr!=null){
			map.get(curr).next=map.get(curr.next);
			map.get(curr).rand=map.get(curr.rand);
			curr=curr.next;
		}
		return map.get(head);
	}
}
