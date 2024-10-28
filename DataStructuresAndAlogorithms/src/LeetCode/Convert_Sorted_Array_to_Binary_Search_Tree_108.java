package LeetCode;

import java.util.Objects;

public class Convert_Sorted_Array_to_Binary_Search_Tree_108 {

	/*
	 * Given an integer array nums where the elements are sorted in ascending order, convert it to a
	 * height-balanced binary search tree.
	 * Input:nums =[-10,-3,0,5,9]	 Output:[0,-3,9,-10,null,5]	   Explanation:[0,-10,5,null,-3,null,9] is also accepted
	 * int mid = l + (r - l) / 2; // this is the formula to find mid value
	 */

	public TreeNode assignMidPartition(int[] arr, int left, int right) {
		TreeNode node=null;
		if (left<=right){
			int mid = (left + right) / 2;//See Above
			node = new TreeNode(arr[mid]);
			node.left=assignMidPartition(arr, left, mid - 1);
			node.right=assignMidPartition(arr, mid + 1, right);
		}
		return node;
	}

	public static void main(String args[]) {
		int [] arr={-5,1,2,7,12,14,20,25,34};
	    Convert_Sorted_Array_to_Binary_Search_Tree_108 ob = new Convert_Sorted_Array_to_Binary_Search_Tree_108();
 	    TreeNode rootNode = ob.assignMidPartition(arr,0, arr.length-1);
		TreeNode root= ob.createBSTAccepted(arr,0, arr.length-1);
		System.out.println(rootNode.val);
		System.out.println(root.val);
    }

	private TreeNode createBSTAccepted(int nums[], int l, int r) {
		if (l > r) { // Base Condition or Recursion Stopping Condition
			return null;
		}
		int mid = l + (r - l) / 2; // this is the formula to find mid value
		TreeNode root = new TreeNode(nums[mid]); // mid value or median
		root.left = createBSTAccepted(nums, l, mid - 1); // assign the value for left of subtree that is l to mid -1 for given
		root.right = createBSTAccepted(nums, mid + 1, r); // assign the value for right go subtree that is mid+1 to r for given
		return root;
	}

	static class TreeNode implements Comparable<TreeNode> {
		Integer val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int num) {
			this.val=num;
		}

		public TreeNode() {}

		//For PriorityQueue
		@Override
		public int compareTo(TreeNode t) {
			//if(this.val==t.val && this.left==t.left && this.right==t.right)
			return this.val-t.val;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			TreeNode node = (TreeNode) o;
			return Objects.equals(val, node.val) && Objects.equals(left, node.left) && Objects.equals(right, node.right);
		}

		@Override
		public int hashCode() {
			return Objects.hash(val, left, right);
		}
	}
}


