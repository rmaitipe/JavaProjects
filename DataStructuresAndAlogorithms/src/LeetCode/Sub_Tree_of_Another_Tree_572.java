package LeetCode;

import java.util.*;

public class Sub_Tree_of_Another_Tree_572 {
    /*
     * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same
     * structure and node values of subRoot and false otherwise.
     * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants.
     * The tree could also be considered as a subtree of itself.
     */
    public static void main(String args[]) {
        Sub_Tree_of_Another_Tree_572 c4 = new Sub_Tree_of_Another_Tree_572();
        Node l1Head = new Node(13);
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(9);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        l2Head.left.left = new Node(7);
        l2Head.left.right = new Node(15);
        System.out.println(c4.isSubTree(l1Head, l2Head));
    }

    public boolean isSubTree(Node head1,Node head2) {
        boolean retVal=true;
        Node node = findIntersect(head1,head2);
        if (node==null){
            retVal=false;
        }else{
            retVal = isSameTree(node,head2);
        }
        return retVal;
    }

    private Node findIntersect(Node node1, Node node2) {
        Node retVal=null;
        while (node1!=null){
            if (node1.val==node2.val) {
                retVal= node1;
            } else {
                retVal=findIntersect(node1.left, node2)==null? findIntersect(node1.right, node2): findIntersect( node1.left, node2);
            }
        }
        return retVal;
    }

    public boolean isSameTree(Node node1, Node node2) {
        boolean retVal=true;
        if (node1==null && node2==null){
            return retVal;
        } else if(node1!=null ^ node2!=null ){//XOR logic?
            retVal=false;
        } else{
            if (node1.val==node2.val){
                retVal= isSameTree(node1.left,node2.left) && isSameTree(node1.right,node2.right);
            }else{
                retVal=false;
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



