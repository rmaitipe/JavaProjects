package LeetCode;

import java.util.Objects;
import java.util.Stack;

public class Validate_Binary_Search_Tree_098 {
    /*
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     * A valid BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     */
    private boolean isBST(Node node) {
        boolean isValid =true;
        if (node!=null){
            if (node.left!=null && node.right!=null){
                if (node.val<node.right.val && node.val>node.left.val){
                    isValid=isBST(node.left) && isBST(node.right);
                    /*isValid=isBST(node.left);
                    if (!isValid)
                        return false;
                    isValid=isBST(node.right);
                    if (!isValid)
                        return false;*/
                } else{
                    isValid= false;
                }
            } else if(node.left!=null){
                if (node.val>node.left.val) {
                    isValid = isBST(node.left);
                }else{
                    isValid= false;
                }
            } else if(node.right!=null){
                if(node.val<node.right.val) {
                    isValid = isBST(node.right);
                }else{
                    isValid= false;
                }
            }
        }
        return isValid;
    }

    public static void main(String args[]) {
        Validate_Binary_Search_Tree_098 c4 = new Validate_Binary_Search_Tree_098();
        Node l1Head = new Node(4);
        l1Head.left = new Node(1);
        l1Head.right = new Node(6);
        l1Head.right.left = new Node(5);
        l1Head.right.right = new Node(8);
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        l2Head.left.left = new Node(65);
        l2Head.left.right = new Node(27);
        System.out.println(c4.isBST(l2Head));
        System.out.println(c4.isValidBSTAccepted(l2Head));
        System.out.println(c4.isBST(l1Head));
        System.out.println(c4.isValidBSTAccepted(l1Head));
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

    public boolean isValidBSTAccepted(Node root) {
        if (root == null) return true;
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

}
