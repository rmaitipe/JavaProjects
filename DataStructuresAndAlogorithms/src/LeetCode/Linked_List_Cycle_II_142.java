package LeetCode;

import java.util.HashMap;

public class Linked_List_Cycle_II_142 {
	/*
	 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
	 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
	 * connected to  (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
	 *
	 * Accepted Solution- Floyd's Cycle Detection Algorithm (Tortoise and Hare)
	 */
	private Integer findLoopStart(ListNode sl1) {
		ListNode node = sl1;
		HashMap<ListNode,Integer> hashMap = new HashMap<>();
		int index=0;
		while (node!= null){
			if (hashMap.containsKey(node)){
				return hashMap.get(node);
			} else {
				hashMap.put(node, index);
				node = node.next;
				index++;
			}
		}
		return null;
	}

	public static void main(String args[]) {
	    ListNode l2Head =new ListNode(1);
		l2Head.next=new ListNode(7);
		ListNode loop=new ListNode(10);
		l2Head.next.next =loop;
		l2Head.next.next.next =new ListNode(6);
		l2Head.next.next.next.next=new ListNode(9);
		l2Head.next.next.next.next.next=loop;
	    Linked_List_Cycle_II_142 ob = new Linked_List_Cycle_II_142();
		System.out.println(ob.findLoopStart(l2Head));
		System.out.println(ob.detectCycleAccepted(l2Head));
    }
	public static class ListNode {
    	int val;
  		ListNode next;
     	ListNode() {}
    	ListNode(int val) { this.val = val; }
     	ListNode(int val, ListNode next) { this.val = val; this.next = next;}
	}

	public ListNode detectCycleAccepted(ListNode head) {//Floyd's Cycle Detection Algorithm (Tortoise and Hare)
		if(head == null || head.next==null  ){
			System.out.print("no cycle");
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast!=null && fast.next!=null){
			fast = fast.next.next;
			slow= slow.next;
			if(fast==slow){
				break;
			}
		}
		if(fast!=slow){
			return null;
		}
		/*
		Finding the Cycle Start: If fast and slow meet, it confirms the presence of a cycle.
		To find the starting node of the cycle, reset one of the pointers to the head of the list (slow = head).
		Move both slow and fast one step at a time. The point at which they meet again is the start of the cycle.
		Explanation: if distance before start of Node is x and then y distance was travelled before 2 pointers met
		slow node travelled x+y, fast node travelled x+y+(full circle length*n) then
		since fast moves twice as fast as slow, it has traveled double the distance of slow at ANY point in time
		2(x+y)=x+y+nc -> x+y=nc ->x=nc-y
		you can see faster pointer is nc-y points to go to entry node. after reset if slow pointer travels x,
		faster point will meet it there.
		*/
		slow = head;
		while(slow!=fast){
			fast= fast.next;
			slow= slow.next;
		}
		return slow;
	}

}
