package LeetCode;


import java.util.*;

public class Binary_Tree_Level_Order_Traversal_102 {
    /*
    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
    Input: root = [3,9,20,null,null,15,7]   Output: [[3],[9,20],[15,7]]
     */

    public static void main(String args[]) {
        Binary_Tree_Level_Order_Traversal_102 c4 = new Binary_Tree_Level_Order_Traversal_102();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        c4.levelTraverse(l2Head);
        List<List<Integer>>  list = c4.levelOrderAccepted(l2Head);
        System.out.println(list);
    }

    public void levelTraverse(Node node) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            System.out.println(node.val);
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



