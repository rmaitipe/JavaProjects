package LeetCode;

import java.util.*;

public class Kth_Smallest_Element_in_a_BST_230 {
    /*
     * Given the root of a binary search tree, and an integer k, return the kth smallest value
     * of all the values of the nodes in the tree.
     * Input: root = [5,3,6,2,4,null,null,1], k = 3     Output: 3
     */
    public static void main(String args[]) {
        Kth_Smallest_Element_in_a_BST_230 c4 = new Kth_Smallest_Element_in_a_BST_230();
        TreeNode l2Head = new TreeNode(3);
        l2Head.left = new TreeNode(9);
        l2Head.right = new TreeNode(20);
        l2Head.right.left = new TreeNode(15);
        l2Head.right.right = new TreeNode(7);
        int k=3;
        System.out.println(c4.findKthSmall(l2Head,k));
        System.out.println(c4.kthSmallestAccepted(l2Head,k));
    }

    public int findKthSmall(TreeNode node,int k) {//heap not needed - see Accepted
        PriorityQueue<TreeNode> minHeap = inorderTraversal(node);
        for (int i = 0; i < k; i++) {
            minHeap.poll();
        }
        return minHeap.peek().val;

    }
    public PriorityQueue<TreeNode> inorderTraversal(TreeNode root) {
        PriorityQueue<TreeNode> minHeap =new PriorityQueue<>();
        inorderHelper(root, minHeap);
        return minHeap;
    }

    private void inorderHelper(TreeNode root, PriorityQueue<TreeNode> minHeap) {
        if (root == null) {
            return;
        }
        inorderHelper(root.left, minHeap);
        minHeap.add(root);
        inorderHelper(root.right, minHeap);
    }

    static class TreeNode implements Comparable<TreeNode> {
        Integer val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int i) {
            this.val = i;
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

        @Override
        public int compareTo(TreeNode o) {
            return this.val-o.val;
        }
    }
    //inorder
    int i=0;
    int ans=0;

    public int kthSmallestAccepted(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    public void inorder(TreeNode node, int k){
        if(node==null){
            return;
        }
        inorder(node.left, k);
        i++;
        if(i==k){
            ans=node.val;
            return;
        }
        inorder(node.right, k);
    }

}






