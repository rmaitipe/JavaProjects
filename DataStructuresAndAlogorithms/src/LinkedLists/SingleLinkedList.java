package LinkedLists;

public class SingleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T element){
        Node<T> nd = new Node<T>();
        nd.setValue(element);
        System.out.println("Adding: "+element);
        /**
         * check if the list is empty
         */
        if(head == null){
            //since there is only one element, both head and tail points to the same object.
            head = nd;
            tail = nd;
        } else {
            //set current tail next link to new node
            tail.setNext(nd);
            //set tail as newly created node
            tail = nd;
        }
        size++;
    }
    public void add(T element, int pos){

    }
    public void addAfter(T element, T after){
        Node<T> tmp = head;
        Node<T> refNode = null;
        System.out.println("Traversing to all nodes..");
        /**
         * Traverse till given element
         */
        while(true){
            if(tmp == null){
                break;
            }
            if(tmp.compareTo(after) == 0){
                //found the target node, add after this node
                refNode = tmp;
                break;
            }
            tmp = tmp.next();
        }
        if(refNode != null){
            //add element after the target node
            Node<T> nd = new Node<T>();
            nd.setValue(element);
            nd.setNext(tmp.next());
            if(tmp == tail){
                tail = nd;
            }
            tmp.setNext(nd);
            size++;
        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void deleteFront(){
        if(head == null){
            System.out.println("Underflow...");
        }
        Node<T> tmp = head;
        head = tmp.next();
        if(head == null){
            tail = null;
        }
        System.out.println("Deleted: "+tmp.getValue());
    }

    public void delete(int pos){

    }
    public void deleteAfter(T after){

        Node<T> tmp = head;
        Node<T> refNode = null;
        System.out.println("Traversing to all nodes..");
        while(true){
            if(tmp == null){
                break;
            }
            if(tmp.compareTo(after) == 0){
                //found the target node, add after this node
                refNode = tmp;
                break;
            }
            tmp = tmp.next();
        }
        if(refNode != null){
            tmp = refNode.next();
            refNode.setNext(tmp.next());
            if(refNode.next() == null){
                tail = refNode;
            }
            System.out.println("Deleted: "+tmp.getValue());
            size--;
        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void traverse(){
        Node<T> tmp = head;
        while(true){
            if(tmp == null){
                break;
            }
            System.out.println(tmp.getValue());
            tmp = tmp.next();
        }
    }

    public static void main(String a[]){
        SingleLinkedList<Integer> sl = new SingleLinkedList<Integer>();
        sl.add(3);
        sl.add(32);
        sl.add(54);
        sl.add(89);
        sl.addAfter(76, 54);
        sl.deleteFront();
        sl.deleteAfter(76);
        sl.traverse();
    }

}
