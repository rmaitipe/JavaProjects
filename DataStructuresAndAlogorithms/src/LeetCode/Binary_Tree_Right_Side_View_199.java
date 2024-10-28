package LeetCode;


import java.util.*;
import java.util.stream.Collectors;

public class Binary_Tree_Right_Side_View_199 {
    /*
     * Given the root of a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     * Input: root = [1,2,3,null,5,null,4]  Output: [1,3,4]
     */
    public LinkedHashMap<Integer,Node> transposedTree(Node root) {
        LinkedList<Node> pq=new LinkedList<>();
        LinkedHashMap<Node,Integer> map =new LinkedHashMap<>();
        pq.add(root);
        map.put(root,1);
        while (!pq.isEmpty()) {
            Node node=pq.poll();
            if (node == null) {
                return null;
            } else {
                int level = map.get(node);
                if (node.right != null) {
                    pq.add(node.right);
                    map.put(node.right, level+1);
                }
                if (node.left != null) {
                    pq.add(node.left);
                    map.put(node.left, level+1);
                }
            }
        }
        LinkedHashMap<Integer,Node>map2=map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));//better way for this?
        return map2;
    }

    public static void main(String args[]) {
        Binary_Tree_Right_Side_View_199 c4 = new Binary_Tree_Right_Side_View_199();
        Node l2Head = new Node(3);
        l2Head.left = new Node(9);
        l2Head.right = new Node(20);
        l2Head.right.left = new Node(15);
        l2Head.right.right = new Node(7);
        l2Head.left.left = new Node(17);
        l2Head.left.left.left = new Node(6);
        LinkedHashMap<Integer,Node> map=c4.transposedTree(l2Head);
        System.out.println(map);
        System.out.println(c4.rightSideViewAccepted(l2Head));
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
    /*
     * In BFS/level order conventionally left to right is traversed, for this case we can reverse the order
     */
    public List<Integer> rightSideViewAccepted(Node root) {
        List<Integer>list=new ArrayList<>();
        Queue<Node>q=new LinkedList<>();
        if(root==null){
            return list;
        }
        q.offer(root);
        while(!q.isEmpty()){
            int lsize=q.size();
            for(int i=0;i<lsize;i++){
                Node curr=q.poll();
                if(i==0) list.add(curr.val);
                if(curr.right!=null) q.offer(curr.right);
                if(curr.left!=null) q.offer(curr.left);
            }
        }
        return list;
    }

}



