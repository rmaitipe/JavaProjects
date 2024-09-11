package crackCodeInterview;

import java.util.HashSet;
import java.util.LinkedList;
import LinkedLists.Node;
import LinkedLists.SingleLinkedListHead;
import LinkedLists.SingleLinkedListRunner;

public class ChapterTwo_Linked_Lists {

    //Different from LinkedList structure with is an implementation of queue
    /*
    1. Remove Dupes! Write code to remove duplicates from an unsorted linked list.
    How would you solve this problem if a temporary buffer is not allowed?

    2. Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.

    3. Delete a Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
    the first and last node, not necessarily the exact middle) of a singly linked list, given only access to that node.
    Input:the node c from the linked list a->b->c->d->e->f
    Output: nothing is returned, but the new linked list looks like a->b->d->e- >f

    4. Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
    before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
    to be after the elements less than x (see below). The partition element x can appear anywhere in the
    "right partition"; it does not need to appear between the left and right partitions.
    Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]  Output:3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
    //Walmart interview

    5. Sum Lists: You have two numbers represented by a linked list, where each node contains a single
    digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
    function that adds the two numbers and returns the sum as a linked list.
    Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.  Output: 2 -> 1 -> 9. That is, 912.
    Suppose the digits are stored in forward order. Repeat the above problem.
    Input:(6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.  Output: 9 - > 1 -> 2. That is, 912.

    6. Palindrome: Implement a function to check if a linked list is a palindrome.

    7. Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
    node. Note that the intersection is defined based on reference, not value. That is, if the kth
    node of the first linked list is the exact same node (by reference) as the jth node of the second
    linked list, then they are intersecting.

    8. Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
    beginning of the loop.
    Input: A -> B -> C - > D -> E -> C [the same C as earlier] Output: C
    */

    public static void main(String args[]){
        ChapterTwo_Linked_Lists cc =new ChapterTwo_Linked_Lists();
        LinkedList<String> aList= new LinkedList<>();
        aList.add("abc"); aList.add("ammbc"); aList.add("bac");	aList.add("abc");
        removeDupe(aList);
        SingleLinkedListRunner<String> sl1 = new SingleLinkedListRunner<>();
        sl1.add("a"); sl1.add("b"); sl1.add("c"); sl1.add("d"); sl1.add("e");
        System.out.println("runnerK: "+sl1.runnerK(2));//same as returnKthLast
        SingleLinkedListHead<Integer> sl2 = new SingleLinkedListHead<>();
        sl2.add(2);sl2.add(4);sl2.add(1);sl2.add(8);sl2.add(2);sl2.add(6);sl2.add(5);
        cc.returnKthLast(sl2,3);
        cc.removeMid(sl1.getHead().next().next());//standalone as this messes up the size
        cc.partition (sl2.getHead(),3);
        SingleLinkedListHead<Integer> sl3 = new SingleLinkedListHead<>();
        sl3.add(2);sl3.add(5);sl3.add(7);sl3.add(1);sl3.add(2);
        SingleLinkedListHead<Integer> sl4 = sumList(sl2, sl3);
        System.out.println("sumList: " +sl4.toString());
        System.out.println("isPalindrome: "+cc.isPalindrome(sl3.getHead()));
        System.out.println("isIntersect: "+cc.isIntersect(sl2,sl3));
        Node<Integer> temp=new Node(5);
        temp.setNext(sl3.getHead());
        SingleLinkedListHead<Integer> sl5= new SingleLinkedListHead<>();
        sl5.setHead(temp);
        System.out.println("isIntersect: "+cc.isIntersect(sl5,sl3));
        System.out.println("isLoop: "+cc.isLoop(sl5));
    }

    private static  LinkedList<String> removeDupe( LinkedList<String> aList) {
        HashSet<String> set = new HashSet<>();
        for (String sVal:aList){
            boolean success = set.add(sVal);
            if (!success){
                aList.remove(sVal);
            }
        }
        return aList;
    }
	/*(Using two loops) This is the simple way where two loops are used.
    Outer loop is used to pick the elements one by one and inner loop compares the picked element with rest of the elements. */

    private Node<Integer> returnKthLast(SingleLinkedListHead<Integer> sl2, int index) {
        Node<Integer> inner =sl2.getHead();
        Node<Integer> outter = sl2.getHead();
        int curr=0;
        while (inner !=null){
            if(curr>=index){
                outter=outter.next();
            }
            curr++;
            inner=inner.next();
        }
        return outter;//add null checks
    }

    private void removeMid(Node<String> c) {//a->b->c->d->e ->abde
        Node<String> temp =c.next();
        c.setValue(temp.getValue());
        c.setNext(temp.next());
    }

    private Node<Integer> partition(Node<Integer> head, int x) {
        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        Node<Integer> before_head = new Node<Integer>();
        Node<Integer> before = before_head;
        Node<Integer> after_head = new Node<Integer>();
        Node<Integer> after = after_head;
        while (head != null) {
            // If the original list node is lesser than the given x, assign it to the before list.
            if ((int)head.getValue() < x) {
                before.setNext(head);
                before = before.next();
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.setNext(head);
                after = after.next();
            }
            // move ahead in the original list
            head = head.next();
        }
        // Last node of "after" list would also be ending node of the reformed list
        after.setNext(null);
        // Once all the nodes are correctly assigned to the two lists, combine them to form a single list which would be returned.
        before.setNext(after_head.next());
        return before_head.next();
    }

    //Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
    //Output: 2 -> 1 -> 9. That is, 912.
    private static SingleLinkedListHead<Integer> sumList(SingleLinkedListHead<Integer> sl1, SingleLinkedListHead<Integer> sl2) {
        Node<Integer> a = sl1.getHead();
        Node<Integer> b = sl2.getHead();
        SingleLinkedListHead<Integer> sl3 = new SingleLinkedListHead<>();
        if (a== null || b== null){return null;}
        int val=0;
        int carry= 0;

        while (a!= null && b!= null){
            val = a.getValue()+ b.getValue()+carry;
            carry= val/10;
            val = val%10;
            sl3.add(val);
            a = a.next();
            b = b.next();
        }
        if (a==null && b!=null){
            while (b!=null){
                val=b.getValue()+carry;
                sl3.add(val);
                carry=val/10;
                b=b.next();
            }
        }
        if (b==null && a!=null){
            while (a!=null){
                val=a.getValue()+carry;
                sl3.add(val);
                carry=val/10;
                a=a.next();
            }

        }if (carry==1){
            sl3.add(carry);
        }
        return sl3;
        //reverse order?
    }

    //reverse List and compare or use a Stack and iterate through half th elements comparing
    private boolean isPalindrome (Node<Integer> listHead){
        boolean retVal= true;
        Node<Integer> node= listHead;
        Node<Integer> sl2 = null;
        int count =0;
        while (node!=null){
            Node curr= new Node(node.getValue());
            if (sl2==null){
                sl2=curr;
            }
            else{
                Node temp=sl2;
                curr.setNext(temp);
                sl2=curr;
            }
            node=node.next();
            count++;
        }
        Node<Integer> n1=listHead;
        Node n2=sl2;
        for (int i=0;i<count/2;i++){
            if (n1.getValue()!=n2.getValue()){
                retVal=false; break;
            } else{
                n1=n1.next();
                n2=n2.next();
            }
        }
        /*while (sl1.peekFirst() !=null && sl1.peekLast()!=null){
            if (!sl1.removeFirst().equals(sl1.removeLast())){
                retVal=false;
                break;
            }
        }*/
        return retVal;
    }

    private boolean isIntersect(SingleLinkedListHead<Integer> sl1, SingleLinkedListHead<Integer> sl2) {
        Node<Integer> a = sl1.getHead();
        Node<Integer> b = sl2.getHead();
        HashSet<Node<Integer>> hashSet = new HashSet<>();
        boolean isUnique =false;
        while (a!= null){
            hashSet.add(a);
            a=a.next();
        }
        while (b!= null){
            isUnique = hashSet.add(b);
            b=b.next();
            if (isUnique ==false){
                break;
            }
        }
        return !isUnique;
    }

    private boolean isLoop(SingleLinkedListHead<Integer> sl1) {
        Node<Integer> a = sl1.getHead();
        HashSet<Node<Integer>> hashSet = new HashSet<>();
        boolean isUnique =false;
        while (a!= null){
            isUnique=hashSet.add(a);
            a=a.next();
            if (isUnique ==false){
                break;
            }
        }
        return !isUnique;
    }
}
