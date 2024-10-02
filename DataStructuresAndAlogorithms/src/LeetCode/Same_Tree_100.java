package LeetCode;


import java.util.*;

public class Same_Tree_100 {
    /*
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
     */

    public static void main(String args[]) {
        Same_Tree_100 c4 = new Same_Tree_100();
        Node l1Head = new Node(3);
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(9);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        l2Head.left.left = new Node(7);
        l2Head.left.right = new Node(15);
        System.out.println(c4.isSameTree(l1Head,l2Head));
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



