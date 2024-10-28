package LeetCode;

import java.util.*;

public class Symmetric_Tree_101 {
    /*
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center). 1
     * Input: root = [1,2,2,3,4,4,3]    Output: true                                                              2   2
     * // both positive and negative can be coded, negative code can exit faster if in a loop                    3 4 4 3
     */
    public boolean symTraverse(Node root) {
        boolean retVal=true;
        if (root==null){
            return retVal;
        } else{
            retVal= isSym2(root.left,root.right);
        }
        return retVal;
    }

    private boolean isSym2(Node left, Node right) {
        boolean retVal=true;
        if (left!=null && right!=null){
            if (left.val==right.val){
                retVal=isSym2(left.left,right.right) && isSym2(left.right,right.left);
            } else{
                retVal=false;
            }
        } else if (left==null && right==null){
        } else{
            retVal=false;
        }
        return retVal;
    }

    public boolean symTraverse2(Node root) {
        boolean retVal=false;
        if (root!=null){
            retVal= isSym(root.left,root.right);
        }
        return retVal;
    }

    private boolean isSym(Node left, Node right) {
        boolean retVal=false;
        if (left==null && right==null){
            retVal=true;
        } else if (left!=null && right!=null){
            if (left.val==right.val){
                retVal=isSym(left.left,right.right) && isSym(left.right,right.left);
            }
        }
        return retVal;
    }

    public static void main(String args[]) {
        Symmetric_Tree_101 c4 = new Symmetric_Tree_101();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(9);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        l2Head.left.left = new Node(7);
        l2Head.left.right = new Node(15);
        System.out.println(c4.symTraverse(l2Head));
        System.out.println(c4.symTraverse2(l2Head));
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

}



