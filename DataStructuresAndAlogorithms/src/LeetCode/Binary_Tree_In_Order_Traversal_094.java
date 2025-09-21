package LeetCode;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println(c4.inorderTraversalAccepted2(l2Head));
    }

    public void inTraverse(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        inOrderTraverse(node,stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop().val);
        }
    }

    //Using a stack reverses order, so goes to right first. Ignore and see inorderTraversalAccepted
    private void inOrderTraverse(TreeNode node, Stack<TreeNode> stack){
        if (node.right != null) {
            inOrderTraverse(node.right,stack);
        }
        stack.push(node);
        if (node.left != null) {
            inOrderTraverse(node.left,stack);
        }
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
    }

    public List<Integer> inorderTraversalAccepted2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }
}






