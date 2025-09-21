package LeetCode;

import java.util.HashSet;

public class Path_Sum_III_437 {
    /*
     * Given the root of a binary tree and an integer targetSum, return the number of paths
     * where the sum of the values along the path equals targetSum.
     * The path does not need to start or end at the root or a leaf, but it must go downwards (parent nodes to child nodes).
     *
     * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8   Output: 3 Explanation: -3,11 || 5,3 || 5,2,1
     */

    static int count=0;
    HashSet<Node> hashSet=new HashSet<>();

    public static void main(String args[]) {
        Path_Sum_III_437 c4 = new Path_Sum_III_437();
        Node l2Head = new Node(10);                     //           10
        l2Head.left = new Node(5);                      //       5       -3
        l2Head.right = new Node(-3);                    //    3     2       11
        l2Head.left.left = new Node(3);                 //  3  -2    1
        l2Head.left.right = new Node(2);                //-3
        l2Head.right.right = new Node(11);
        l2Head.left.left.left = new Node(3);
        l2Head.left.left.right = new Node(-2);
        l2Head.left.right.right = new Node(1);
        l2Head.left.left.left.left = new Node(-3);
        System.out.println(c4.pathSumFail(l2Head,8,8));
        System.out.println("Accepted: "+c4.pathSumAccepted(l2Head,8));
        Node l1Head = new Node(1);                     //           10
        l1Head.right = new Node(2);                      //       5       -3         //-3
        l1Head.right.right = new Node(3);
        l1Head.right.right.right = new Node(4);
        l1Head.right.right.right.right = new Node(5);
        c4.backTrack(l1Head,3,0);
        System.out.println(count);
        System.out.println("Accepted: "+c4.pathSumAccepted(l1Head,3));
    }

    public int pathSumFail(Node node, Integer k,int target) {
        int retVal=0;
        if (node!=null){
            if (node.val==target){
                retVal= 1;// should traverse until end of node
            } else{
                retVal+=pathSumFail(node.left,k,target-node.val);
                retVal+=pathSumFail(node.right,k,target-node.val);
                retVal+=pathSumFail(node.left,k,k);
                retVal+=pathSumFail(node.right,k,k);
            }
        }
        return retVal;
    }

    static class Node {
        Integer val;
        Node left;
        Node right;
        public Node(int i) {
            this.val = i;
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

    public void backTrack(Node node, int target, int current){
        if (node!=null){
            current+=node.val;
            if (current==target)  {
                count++;
            }
            backTrack(node.left,target, current);
            backTrack(node.right,target, current);
            if (hashSet.add(node)){
                backTrack(node.left,target, 0);
                backTrack(node.right,target, 0);
            }
        }
    }
}



