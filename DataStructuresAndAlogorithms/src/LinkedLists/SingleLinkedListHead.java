package LinkedLists;

public class SingleLinkedListHead<T> {

    private Node<T> head;
    private int size;

    public void add(T element){
        Node<T> nd = new Node<T>();
        nd.setValue(element);
        nd.setNext(null);
        System.out.println("Adding: "+element);
        if(head == null){
            //since there is only one element
            head=nd;
        }
        else {
            Node<T> temp =head;
            while (temp.next()!=null){
                temp=temp.next();
            }
            //set current tail next link to new node
            temp.setNext(nd);
        }
        //set tail as newly created node
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
        System.out.println("Deleted: "+tmp.getValue());
    }

    public void delete(int pos){
        Node<T> tmp = head;
        int traverse=0;
        System.out.println("Traversing to all nodes..");
        while(traverse<pos-1){
            if(tmp == null){
                break;
            }
            else{
                tmp = tmp.next();
                traverse++;
            }
        }
        if(tmp !=null && tmp.next() != null){
            Node<T> refNode = tmp.next();
            tmp.setNext(refNode.next());
            System.out.println("Deleted: "+refNode.getValue());
            size--;
        } else {
            System.out.println("Unable to find the given poisition...");
        }
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
        SingleLinkedListHead<Integer> sl = new SingleLinkedListHead<Integer>();
        sl.add(3);
        sl.add(32);
        sl.add(54);
        sl.add(89);
        sl.addAfter(76, 54);
        sl.deleteFront();
        sl.deleteAfter(76);
        sl.traverse();
    }

    public Node<T> getHead() {
        return head;
    }
    public void setHead(Node<T> head) {
        this.head = head;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString(){
        Node<T> tmp = head;
        StringBuilder sb = new StringBuilder();
        if (tmp ==null){
            return null;
        }
        else{
            while (tmp != null){
                sb.append(tmp.getValue()+ " ");
                tmp= tmp.next();
            }
        }
        return sb.toString();
    }

}
