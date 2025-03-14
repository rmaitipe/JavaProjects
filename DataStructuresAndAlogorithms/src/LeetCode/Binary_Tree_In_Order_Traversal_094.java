package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Binary_Tree_In_Order_Traversal_094 {
    /*
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.    1
     * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9] Output: [4,2,6,5,7,1,3,9,8]        2     3
     *                                                                                   4   5     8
     * inOrderTraverse doesn't need to return the stack                                     6 7   9
     */
    public static void main(String args[]) {
        Binary_Tree_In_Order_Traversal_094 c4 = new Binary_Tree_In_Order_Traversal_094();
        TreeNode l2Head = new TreeNode(3);
        l2Head.left = new TreeNode(9);
        l2Head.right = new TreeNode(20);
        l2Head.right.left = new TreeNode(15);
        l2Head.right.right = new TreeNode(7);
        c4.inTraverse(l2Head);
        System.out.println(c4.inorderTraversalAccepted(l2Head));
    }

    public void inTraverse(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack=inOrderTraverse(node,stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop().val);
        }
    }

    private Stack inOrderTraverse(TreeNode node, Stack<TreeNode> stack){
        if (node.right != null) {
            stack=inOrderTraverse(node.right,stack);
        }
        stack.push(node);
        if (node.left != null) {
            stack=inOrderTraverse(node.left,stack);
        }
        return stack;
    }

    public List<Integer> inorderTraversalAccepted(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderHelper(root, list);
        return list;
    }

    private void inorderHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderHelper(root.left, list);
        list.add(root.val);
        inorderHelper(root.right, list);
    }

    static class TreeNode {
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
    }
}






