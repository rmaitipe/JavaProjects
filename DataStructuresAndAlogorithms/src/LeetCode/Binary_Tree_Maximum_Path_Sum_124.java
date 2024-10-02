package LeetCode;

import java.util.*;

public class Binary_Tree_Maximum_Path_Sum_124 {
    /*
     * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
     * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
     * The path sum of a path is the sum of the node's values in the path.
     * Given the root of a binary tree, return the maximum path sum of any non-empty path.
     * Input: root = [1,2,3]    Output: 6   Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
     */
    public int maxPathSum(Node node) {
        int sum=0;
        if (node==null){
            return 0;
        } else if (node.left!=null && node.right!=null){
            sum= Math.max(node.val+node.left.val+node.right.val,Math.max(maxPathSum(node.left),maxPathSum(node.right)));
        } else if(node.left!=null){
            sum= maxPathSum(node.left);
        }else if(node.right!=null){
            sum= maxPathSum(node.right);
        }
        return sum;
    }

    public static void main(String args[]) {
        Binary_Tree_Maximum_Path_Sum_124 c4 = new Binary_Tree_Maximum_Path_Sum_124();
        Node l2Head = new Node(-10);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        System.out.println(c4.maxPathSum(l2Head));
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

    public int maxPathSumAccepted(Node root) {
        int res[]=new int[1];
        res[0]=Integer.MIN_VALUE;
        helper(root,res);
        return res[0];
    }

    int helper(Node root,int res[]) {
        if(root==null)
            return 0;
        int left=helper(root.left,res);
        int right=helper(root.right,res);
        int tmp=Math.max(root.val,root.val+Math.max(left,right));
        int max=Math.max(tmp,root.val+left+right);
        res[0]=Math.max(max,res[0]);
        return tmp;
    }
}



