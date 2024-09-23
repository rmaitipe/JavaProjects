package LeetCode;


import java.util.*;

public class Flatten_Binary_Tree_to_Linked_List_114 {
    /*
    Given the root of a binary tree, flatten the tree into a "linked list":
    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
    list and the left child pointer is always null.
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.
    Input: root = [3,9,20,null,null,15,7]   Output: [[3],[9,20],[15,7]]
     */

    public static void main(String args[]) {
        Flatten_Binary_Tree_to_Linked_List_114 c4 = new Flatten_Binary_Tree_to_Linked_List_114();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        LinkedList<Integer>  list= new LinkedList<>();
        list = c4.preOrderTraverse(list,l2Head);
        System.out.println(list);
        Node l1Head = new Node(1);
        l1Head.left = new Node(2);
        l1Head.right = new Node(5);
        l1Head.left.left = new Node(3);
        l1Head.left.right = new Node(4);
        l1Head.right.right = new Node(6);            //         1
        LinkedList<Integer>  list2= new LinkedList<>();//      2     5
        list2 = c4.preOrderTraverse(list2,l1Head);     //     3 4     6
        System.out.println(list2);
    }

    public LinkedList<Integer> preOrderTraverse(LinkedList<Integer>  list, Node node) {
        if (node!=null) {
            list.add(node.val);
            if (node.left != null) {
                preOrderTraverse(list,node.left);
            }
            if (node.right != null) {
                preOrderTraverse(list,node.right);
            }
        }
        return list;
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



