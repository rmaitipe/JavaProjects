package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lowest_Common_Ancestor_of_a_Binary_Tree_236 {
    /*
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * Definition of LCA: The lowest common ancestor is defined between two nodes p and q
     * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself)
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1  Output:3   Explanation: The LCA of nodes 5 and 1 is 3
     */
    public static void main(String args[]) {
        Lowest_Common_Ancestor_of_a_Binary_Tree_236 c4 = new Lowest_Common_Ancestor_of_a_Binary_Tree_236();
        TreeNode l2Head = new TreeNode(3);
        l2Head.left = new TreeNode(9);
        l2Head.right = new TreeNode(20);
        TreeNode p=new TreeNode(15);
        l2Head.right.left = p;
        l2Head.right.right = new TreeNode(7);
        TreeNode q = new TreeNode(7);
        l2Head.right.right.left = q;                        //        3
        int node=c4.lowestCommonAncestor(l2Head,p,q);       //    9      20
        System.out.println(node);                           //         15   7
        System.out.println("Accepted: "+c4.lowestCommonAncestorAccepted(l2Head,p,q).val);
    }

    public int lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<Integer> pList=new ArrayList<>();
        List<Integer> qList=new ArrayList<>();
        backTrack(root,p,new ArrayList<>(),pList);
        backTrack(root,q,new ArrayList<>(),qList);
        //[160 Intersection of Two Linked Lists], pick smaller
        int retVal= pList.size()<qList.size()? findIntersectionOfLinkedList(pList,qList): findIntersectionOfLinkedList(qList,pList);//Ternary
        return retVal;
    }

    private int findIntersectionOfLinkedList(List<Integer> in, List<Integer> out) {//should be a List instead of a String
        int val=0;
        for (int i=0;i<in.size();i++){
            if (in.get(i)!=out.get(i)){
                val= in.get(i-1);
                break;
            }
        }
        return val;
    }

    private void backTrack(TreeNode node, TreeNode target,List<Integer> pathList, List<Integer> returnList){
        if (node== null) return;
        else{
            pathList.add(node.val);
            if (node.val==target.val){
                System.out.println("Found: "+node.val);
                returnList.addAll(pathList);
            } else {
                backTrack(node.left, target,pathList,returnList);
                backTrack(node.right, target,pathList,returnList);
                pathList.removeLast();
            }
        }
    }

    static class TreeNode{
        Integer val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int i) {
            this.val = i;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode node = (TreeNode) o;
            return Objects.equals(val, node.val) && Objects.equals(left, node.left) && Objects.equals(right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, left, right);
        }
    }

    public TreeNode backtrack(TreeNode root, TreeNode p, TreeNode q) {
        // the line below is the most hard to understand
        // for the first level recursion, if p(or q) is exactly the whole tree's root, return the root(because root is the lowest node).
        // for deeper(higher nodes) recursions, it indicates:
        // (1) we find p(or q), which is fine because this node will not be returned(to output) instantly since it belongs to a deeper recursion but will be used for determinations of shallower(lower nodes) recursions
        // (2) we reach the bottom of the tree, terminate the current track instantly and "null" will also be used for shallower recursions' determine statements.
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = backtrack(root.left, p, q);
        TreeNode right = backtrack(root.right, p, q);
        // determine statements
        if (left != null && right != null) // which means p,q exist below different subtrees;
            return root;
        return left != null ? left : right; // which means p,q exist below the same subtree;
    }

    /*
     * Here we are doing DFS search Algo
     * In the traversal where ever we get the p or q we return it,
     * while returning for a node from left there can be a null or p or q,
     * if both left and right are not null means one will be p and other will be q
     * if left is null then right can be p or q
     * if right is null then left can be p or q
     */
    public TreeNode lowestCommonAncestorAccepted(TreeNode root, TreeNode p, TreeNode q) {
        //In the traversal where ever we get the p or q we return it , if we reach null then simply return(Base cases)
        if(root == p) return p;
        if(root == q) return q;
        if(root == null) return null;
        TreeNode left = lowestCommonAncestorAccepted(root.left, p, q);
        TreeNode right = lowestCommonAncestorAccepted(root.right, p, q);
        if(left != null && right != null) return root;
        if(left == null){ // if left is null then right can be p or q
            return right;
        } else{
            return left;    // right == null ,  if right if null then left can be p or q
        }
    }

}



