package LeetCode;


import java.util.Objects;

public class Maximum_Depth_of_Binary_Tree_104 {
    /*
    Given the root of a binary tree, return its maximum depth.
    A binary tree's maximum depth is the number of nodes along the longest path from the root down to the farthest leaf.
     */

    public static void main(String args[]) {
        Maximum_Depth_of_Binary_Tree_104 c4 = new Maximum_Depth_of_Binary_Tree_104();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        System.out.println(c4.maxDepth(l2Head,0));
        System.out.println(c4.maxDepthAccepted(l2Head));
    }

    public int maxDepth(Node node,int height) {
        if (node==null) {
            return height;
        } else{
            return Math.max(maxDepth(node.left,height+1),maxDepth(node.right,height+1));
        }
    }

    static class Node {
        Integer val;
        Node left;
        Node right;

        public Node(int i) {
            this.val = i;
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

    public int maxDepthAccepted(Node root) {
        if(root == null) return 0;
        return Math.max(maxDepthAccepted(root.left), maxDepthAccepted(root.right)) + 1;
    }

}



