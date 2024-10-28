package LeetCode;


import java.util.*;

public class Flatten_Binary_Tree_to_Linked_List_114 {
    /*
    Given the root of a binary tree, flatten the tree into a "linked list":
    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
    list and the left child pointer is always null.                                                        3
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.          9     20
    Input: root = [3,9,20,null,null,15,7]   Output: [[3],[9,20],[15,7]]                                     15   7
     */

    public static void main(String args[]) {
        Flatten_Binary_Tree_to_Linked_List_114 c4 = new Flatten_Binary_Tree_to_Linked_List_114();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        c4.preOrderTraverse(l2Head);
        c4.flatten(list);
        System.out.println(l2Head);
        Node l1Head = new Node(1);
        l1Head.left = new Node(2);
        l1Head.right = new Node(5);     //         1
        l1Head.left.left = new Node(3); //      2     5
        l1Head.left.right = new Node(4);//     3 4     6
        l1Head.right.right = new Node(6);
        c4.flattenAccepted(l1Head);
        System.out.println(l1Head);
    }

    static LinkedList<Node> list= new LinkedList<>();
    public void preOrderTraverse(Node node) {
        if (node!=null) {
            list.add(node);
            if (node.left != null) {
                preOrderTraverse(node.left);
            }
            if (node.right != null) {
                preOrderTraverse(node.right);
            }
        }
    }

    public void flatten(LinkedList<Node> list) {
        while(!list.isEmpty()){
            Node temp = list.removeFirst();
            temp.right = list.peek();
            temp.left = null;
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

    Queue<Node> queue = new LinkedList<>(); //Global Declaration of Queue
    public void addToQueue(Node root) {   //pre-order Traversal Of Queue
        if(root == null)
            return;
        //Storing the nodes in Queue according to pre-order
        queue.add(root);
        addToQueue(root.left);
        addToQueue(root.right);
    }
    public void flattenAccepted(Node root) {
        addToQueue(root); //call the addToQueue function
        //Arrange the nodes in the queue in form of a linkedList or Right-Skewed Tree
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            temp.right = queue.peek();
            temp.left = null;
        }
    }

}



