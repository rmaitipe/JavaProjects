package LeetCode;


import java.util.*;

public class Symmetric_Tree_101 {
    /*
    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     */

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
    }

    public boolean symTraverse(Node root) {
        boolean retVal=true;
        if (root==null){
            return retVal;
        } else{
            retVal= isSym(root.left,root.right);
        }
        return retVal;
    }

    private boolean isSym(Node left, Node right) {
        boolean retVal=false;
        if (left==null && right==null){
            retVal=true;
        }
        else if (left!=null && right!=null){
            if (left.val==right.val){
                retVal=isSym(left.left,right.right) && isSym(left.right,right.left);
            }
        }
        return retVal;
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

    public List<List<Integer>> levelOrderAccepted(Node root) {
        // Check if the root is null
        if (root == null) {
            return new ArrayList<>();  // Return an empty list if the tree is empty
        }
        Queue<Node> queue = new ArrayDeque<Node>();
        List<List<Integer>> traversal = new ArrayList<>();
        queue.add(root);  // Add the root node to the queue
        // Start level-order traversal
        while (!queue.isEmpty()) {
            List<Integer> currLevel = new ArrayList<>();
            int currSize = queue.size();  // Get the number of nodes at the current level
            // Process all nodes at the current level
            for (int i = 0; i < currSize; i++) {
                Node currNode = queue.poll();  // Get the next node
                // Add its value to the current level's list
                currLevel.add(currNode.val);
                // Add the children to the queue if they are not null
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            // Add the current level's list to the result
            traversal.add(currLevel);
        }
        return traversal;
    }

}



