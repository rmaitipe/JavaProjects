package crackCodeInterview;


import java.util.*;

public class ChapterFour_TreesGraphs {
    /*
    1. Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
    2. Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create
        a binary search tree with minimal height.
    3. List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
        at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).

    4. Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
        this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
        node never differ by more than one.

    5. Validate BST: Implement a function to check if a binary tree is a binary search tree.
    6. Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
        binary search tree. You may assume that each node has a link to its parent.
    7. Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
        projects, where the second project is dependent on the first project). All of a project's dependencies
        must be built before the project is. Find a build order that will allow the projects to be built. If there
        is no valid build order, return an error.
        EXAMPLE Input:projects: a, b, c, d, e, f    dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
        Output: f, e, a, b, d, c

    8. First Common Ancestor: Design an algorithm and write code to find the first common ancestor
        of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
        necessarily a binary search tree.

    9. BST Sequences: A binary search tree was created by traversing through an array from left to right
        and inserting each element. Given a binary search tree with distinct elements, print all possible
        arrays that could have led to this tree.
        EXAMPLE Input:   Output: {2, 1, 3}, {2, 3, 1}

    1O. Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
        algorithm to determine if T2 is a subtree of Tl.
        A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2.
        That is, if you cut off the tree at node n, the two trees would be identical.

    11. Random Node: You are implementing a binary tree class from scratch which, in addition to
        insert, find, and delete, has a method getRandomNode() which returns a random node
        from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
        for getRandomNode, and explain how you would implement the rest of the methods.

    12. Paths with Sum: You are given a binary tree in which each node contains an integer value (which
        might be positive or negative). Design an algorithm to count the number of paths that sum to a
        given value. The path does not need to start or end at the root or a leaf, but it must go downwards
        (traveling only from parent nodes to child nodes).
     */

    public static void main(String args[]) {
        ChapterFour_TreesGraphs c4=new ChapterFour_TreesGraphs();
        int[] arr={1,2,7,9,12,15,20,22};
        int[] arr2={2,7,9,12,15,20,22};
        Node head= c4.arrayToNodeMinimalTree(arr);
        Node head2=c4.arrayToNodeMinimalTree(arr2);
        List<ArrayList<Node>> list = c4.levelDepthList(head);
        List<ArrayList<Node>> list2 = c4.levelDepthList(head2);
        System.out.println("isBalanced: "+ c4.isBalanced(head2));
        System.out.println("isBalanced: "+ c4.isBalanced(head));
        System.out.println("findCommonParent: "+ c4.findCommonParent(head,head.left,head.right.right));
        System.out.println("isBST: "+ c4.isBST(head));
        System.out.println("isSubTree: "+ c4.isSubTree(head,head2));
    }

    //2 Minimal tree
    public Node arrayToNodeMinimalTree(int[] arr){
        Node head=assignMidPartition (arr,0,arr.length-1);
        return head;
        /*Node head=new Node();
        assignMidPartition (arr,0,arr.length,head);
        return head;*/
    }

    /*public void assignMidPartition(int[] arr,int left, int right, Node node) {
        if (left<right){
            if (node==null){
                node=new Node();
            }
            int mid = (left + right) / 2;
            node.val = (arr[mid]);
            assignMidPartition(arr, left, mid - 1, node.left);
            assignMidPartition(arr, mid + 1, arr.length, right);
        }
    }*/
    public Node assignMidPartition(int[] arr,int left, int right) {
        Node node;
        if (left>right){
            return null;
        }else{
            node=new Node();
            int mid = (left + right) / 2;
            node.val = (arr[mid]);
            node.left=assignMidPartition(arr, left, mid - 1);
            node.right=assignMidPartition(arr, mid + 1, right);
        }
        return node;
    }

    public List<ArrayList<Node>> levelDepthList(Node node){
        Queue<Node> queue =new PriorityQueue<>();
        int nodeCount=0;
        int levelCount=0;
        queue.add(node);
        List<ArrayList<Node>> list= new ArrayList<>();
        list.add(new ArrayList<>());
        while (!queue.isEmpty()) {
            node = queue.poll();
            list.get(levelCount).add(node);
            nodeCount++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (!queue.isEmpty() && isLevel(nodeCount,levelCount)){
                levelCount++;
                list.add(new ArrayList<>());
                nodeCount=0;
            }
        }
        return list;
    }
    /*
    ArrayList<LinkedList<TreeNode>> createLevelLinkedlistAccepted(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<Linkedlist<TreeNode>>();
        LinkedList<TreeNode> current= new LinkedList<TreeNode>();
        if (root != null) {
            current.add(root);        // "Visit" the root
        }
        while (current.size() > 0) {
            result.add(current);// Add previous level
            Linkedlist<TreeNode> parents = current;//Go to next level
            current = new LinkedList<TreeNode>();
            for (TreeNode parent : parents) {
                // Visit the children
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
       }
     */

    //4 level traversal+nodeCount - find min & find max see diff
    public boolean isBalanced(Node n) {
        int nodeCount=0;
        int levelCount=0;
        int minLevel=Integer.MAX_VALUE;
        int maxLevel=Integer.MIN_VALUE;
        Queue<Node> queue =new PriorityQueue<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            nodeCount++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (!queue.isEmpty()) {
                if (isLevel(nodeCount, levelCount)) {
                    levelCount++;
                    nodeCount = 0;
                }
                if (node.left == null && node.right == null) {
                    if (levelCount < minLevel) {
                        minLevel = levelCount;
                    }
                    if (levelCount > maxLevel) {
                        maxLevel = levelCount;
                    }
                }
            }
        }
        return maxLevel-minLevel==0;
    }

    private boolean isLevel(int nodeCount, int levelCount) {
        boolean retVal=false;
        if (Math.pow(2, levelCount) == nodeCount){
            retVal=true;
        }
        return retVal;
    }

    private boolean isBST(Node node) {
        boolean isValid = true;
        if (node != null) {
            if (node.left != null && node.val > node.left.val) {
                isValid = isBST(node.left);
                if (!isValid)
                    return false;
                isValid = isBST(node.right);
                if (!isValid)
                    return false;
            } else {
                return false;
            }
            if (node.right != null && node.val < node.right.val) {
                isValid = isBST(node.left);
                if (!isValid)
                    return false;
            } else {
                return false;
            }
        }
        return isValid;
    }

    /*private boolean isBST(Node node) {
        boolean isValid =true;
        if (node!=null){
            if (node.left!=null && node.right!=null){
                if (node.val<node.right.val && node.val>node.left.val){
                    isValid=isBST(node.left);
                    if (!isValid)
                        return false;
                    isValid=isBST(node.right);
                    if (!isValid)
                        return false;
                } else{
                    return false;
                }
            }
            else if(node.left!=null){
                if (node.val>node.left.val){
                    isValid=isBST(node.left);
                    if (!isValid)
                        return false;
                } else{
                    return false;
                }
            }
            else if(node.right!=null){
                if (node.val<node.right.val){
                    isValid=isBST(node.right);
                    if (!isValid)
                        return false;;
                } else{
                    return false;
                }
            }
            else{
            }
        }
        return isValid;
    }*/

    //8 head,n1,n2   left,|,right vs |,left,right
    private Node findCommonParent(Node head,Node n1,Node n2){
        LinkedList <Node>n1List = findPath(head,n1);
        LinkedList <Node>n2List = findPath(head,n2);
        Node retVal = null;
        for (Node elem: n1List){
            if (n2List.contains(elem)){
                retVal=elem;
                break;
            }
        }
        return retVal;
    }

    private LinkedList<Node> findPath(Node head, Node n2) {
        LinkedList<Node> list = new LinkedList<>();
        //
        return list;
    }

    //10 traverse T2 and add to Set
    public boolean isSubTree(Node t1,Node t2){
        //dfs
        HashSet<Node> set =new HashSet<>();
        set = populateSet(set,t1);
        return  set.contains(t2);
    }
    public HashSet<Node> populateSet(HashSet set, Node t1){
        if (t1!=null) {
            set.add(t1);
            populateSet(set,t1.left);
            populateSet(set,t1.right);
        }
        return set;
    }
}

class Node implements Comparable<Node> {
    Integer val;
    Node left;
    Node right;

    //For PriorityQueue
    @Override
    public int compareTo(Node t) {
        //if(this.val==t.val && this.left==t.left && this.right==t.right)
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



