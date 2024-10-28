package LeetCode;


import java.util.*;

public class Invert_Binary_Tree_226 {
    /*
     * Given the root of a binary tree, invert the tree, and return its root.   4                 4
     * Input: root = [4,2,7,1,3,6,9]    Output: [4,7,2,9,6,3,1]               2   7             7   2
     *                                                                       1 3  6 9          9 6 3 1
     */
    public static void main(String args[]) {
        Invert_Binary_Tree_226 c4 = new Invert_Binary_Tree_226();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        Node node=c4.invertTree(l2Head);
        System.out.println(node);
    }

    public Node invertTree(Node node) {
        if (node==null){
            return node;
        } else{
            Node temp= node.left;
            node.left=invertTree(node.right);
            node.right=invertTree(temp);
            return node;
        }
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



