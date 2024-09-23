package LeetCode;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lowest_Common_Ancestor_of_a_Binary_Tree_236 {
    /*
    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
    as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     */

    public static void main(String args[]) {
        Lowest_Common_Ancestor_of_a_Binary_Tree_236 c4 = new Lowest_Common_Ancestor_of_a_Binary_Tree_236();
        TreeNode l2Head = new TreeNode(3);
        l2Head.left = new TreeNode(9);
        l2Head.right = new TreeNode(20);
        TreeNode p=new TreeNode(15);
        l2Head.right.left = p;
        l2Head.right.right = new TreeNode(7);
        TreeNode q =  new TreeNode(7);
        l2Head.right.right.left = q;                        //        3
        int node=c4.lowestCommonAncestor(l2Head,p,q);       //    9      20
        System.out.println(node);                           //         15   7
    }

    public int lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List a =findPath (root,p);
        List b =findPath (root,q);
        System.out.println("a: " +a);System.out.println("b: "+b);
        int retVal=0;
        //find string intersection 3524 356, pick smaller
        retVal= a.size()<b.size()? findIntersection(a,b): findIntersection(b,a);//Ternary
        return retVal;
    }

    private int findIntersection(List<Integer> in, List<Integer> out) {//should be a List instead of a String
        int val=0;
        for (int i=0;i<in.size();i++){
            if (in.get(i)!=out.get(i)){
                val= in.get(i-1); break;
            }
        }
        return val;
    }

    private List findPath(TreeNode node, TreeNode target){
        if (node== null) return null;
        else{
            List<Integer> tmp=new ArrayList();
            if (node.val==target.val){
                System.out.println("Found: "+node.val);
                tmp.add(node.val);
                return tmp;//If int it sets size
            }else {
                tmp = findPath(node.left, target);
                if (tmp != null) {
                    tmp.addFirst(node.val);
                    System.out.println(tmp);
                } else{
                    tmp = findPath(node.right, target);
                    if (tmp != null) {
                        tmp.addFirst(node.val);
                        System.out.println(tmp);
                    }
                }
                return tmp;
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
}



