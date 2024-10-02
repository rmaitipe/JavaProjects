package LeetCode;


import java.util.*;

public class Path_Sum_III_437 {
    /*
     * Given the root of a binary tree and an integer targetSum, return the number of paths
     * where the sum of the values along the path equals targetSum.
     * The path does not need to start or end at the root or a leaf, but it must go downwards (parent nodes to child nodes).
     *
     * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8   Output: 3 Explanation: -3,11 || 5,3 || 5,2,1
     */

    public static void main(String args[]) {
        Path_Sum_III_437 c4 = new Path_Sum_III_437();
        Node l2Head = new Node(10);
        l2Head.left = new Node(5);
        l2Head.right = new Node(-3);
        l2Head.left.left = new Node(3);
        l2Head.left.right = new Node(2);
        l2Head.right.right = new Node(11);
        l2Head.left.left.left = new Node(3);
        l2Head.left.left.right = new Node(-2);
        l2Head.left.right.right = new Node(1);
        System.out.println(c4.pathSum(l2Head,8,8));
        System.out.println("Accepted: "+c4.pathSumAccepted(l2Head,8));
    }

    public int pathSum(Node node, Integer k,int target) {
        int retVal=0;
        if (node==null){
            return 0;
        } else{
            if (node.val==target){
                return 1;
            }else{
                retVal+=pathSum(node.left,k,target-node.val);
                retVal+=pathSum(node.right,k,target-node.val);
                retVal+=pathSum(node.left,k,k);
                retVal+=pathSum(node.right,k,k);
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

    public int pathSumAccepted(Node root, int targetSum) {
        if (root == null)
            return 0;
        return getSum(root, targetSum, 0) + pathSumAccepted(root.left, targetSum) + pathSumAccepted(root.right, targetSum);
    }

    public int getSum(Node root, int targetSum, long sum) {
        int res = 0;
        if (root == null)
            return res;
        sum += root.val;
        if (sum == targetSum)
            res++;
        res += getSum(root.left, targetSum, sum);
        res += getSum(root.right, targetSum, sum);
        sum -= root.val;
        return res;
    }
}



