package LeetCode;

public class Diameter_of_Binary_Tree_543 {
    /*
     * Given the root of a binary tree, return the length of the diameter of the tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
     * This path may or may not pass through the root.
     * The length of a path between two nodes is represented by the number of edges between them.                  1
     * Input: root = [1,2,3,4,5]    Output: 3   Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3]. 2   3
     * See l2head for a Non root example                                                                        4 5
     */
    public int treeDiameter(Node node) {
        int length=0;
        if (node==null){
            return 0;
        } else {
            int a = maxDepth104Accepted(node.left)-1;
            int b = maxDepth104Accepted(node.right)-1;
            int c = a+b+2;
            length=Math.max(c,treeDiameter(node.left));
            length =Math.max(length,treeDiameter(node.right));
        }
        return length;
    }

    //from 104
    public int maxDepth104Accepted(Node root) {
        if(root == null) return 0;
        return Math.max(maxDepth104Accepted(root.left), maxDepth104Accepted(root.right)) + 1;
    }

    public static void main(String args[]) {
        Diameter_of_Binary_Tree_543 c4 = new Diameter_of_Binary_Tree_543();
        Node l1Head = new Node(1);
        l1Head.left = new Node(2);
        l1Head.right = new Node(3);
        l1Head.left.left = new Node(4);
        l1Head.left.right = new Node(5);
        System.out.println(c4.treeDiameter(l1Head));
        System.out.println(c4.diameterOfBinaryTreeAccepted(l1Head));
        Node l2Head = new Node(-10);                            //        -10
        l2Head.left = new Node(9);                              //    9          20
        l2Head.right = new Node(20);                            //           15     7
        l2Head.right.left = new Node(15);                       //         5            3
        l2Head.right.right = new Node(7);                       //            4            0
        l2Head.right.left.left = new Node(5);
        l2Head.right.right.right = new Node(3);
        l2Head.right.left.left.right = new Node(4);
        l2Head.right.right.right.right = new Node(0);
        System.out.println(c4.treeDiameter(l2Head));
        System.out.println(c4.diameterOfBinaryTreeAccepted(l2Head));
    }

    static class Node {
        Integer val;
        Node left;
        Node right;

        public Node(int i) {
            this.val = i;
        }

    }

    int max = 0;

    public int diameterOfBinaryTreeAccepted(Node root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(Node root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

}



