package LeetCode;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sum_Root_to_Leaf_Numbers_129 {
	/*
You are given the root of a binary tree containing digits from 0 to 9 only. Each root-to-leaf path in the tree represents a number.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
Example:
    1
   / \
  2   3
/ \
4   5
For the above tree, the sum of all numbers formed by concatenating the root-to-leaf paths would be 124 + 125 + 13 = 262.
 */

	public static void main(String args[]) {
		Node root = new Node(1);
		root.left=new Node(2);
		root.right=new Node(3);
		root.left.left=new Node(4);
		root.left.right=new Node(5);
		Sum_Root_to_Leaf_Numbers_129 ob = new Sum_Root_to_Leaf_Numbers_129();
		int val= ob.dfsSum(root,"",0);
		System.out.println(val);
    }

	private int dfsSum(Node node,String sb,int sum) {
		if (node.left ==null && node.right==null){
			return Integer.valueOf(sb+node.val);
		} else{
			if(node.left ==null) {
				sum+=dfsSum(node.right, sb+node.val, sum);
			}
			else if(node.right ==null) {
				sum+=dfsSum(node.left, sb+node.val, sum);
			} else{
				sum+=dfsSum(node.left, sb+node.val, sum);
				sum+=dfsSum(node.right, sb+node.val, sum);
			}
		}
		return sum;
	}

	static class Node implements Comparable<Node> {
		Integer val;
		Node left;
		Node right;

		public Node(int i) {
			this.val = i;
		}

		//For PriorityQueue
		@Override
		public int compareTo(Node t) {
			return this.val-t.val;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return Objects.equals(val, node.val) && Objects.equals(left, node.left) && Objects.equals(right, node.right);
		}

		@Override
		public int hashCode() {
			return Objects.hash(val, left, right);
		}
	}

	int total;

	public int sumNumbersAccepted(Node root) {
		total = 0;
		helper(root, 0);
		return total;
	}

	void helper(Node root, int sum) {
		if (root == null) return;
		sum = sum * 10 + root.val;
		if (root.left == null && root.right == null) {
			total += sum;
			return;
		}
		helper(root.left, sum);
		helper(root.right, sum);
	}

}
