package LinkedLists;

public class SingleLinkedListRunner<T> {

    private Node<T> head;
    private Node<T> runner;
    private int size;

    public void add(T element){
        Node<T> nd = new Node<T>();
        nd.setValue(element);
        nd.setNext(null);
        System.out.println("Adding: "+element);
        /**
         * check if the list is empty
         */
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

    }
    public void deleteAfter(T after){

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
            tmp = refNode.next();
            refNode.setNext(tmp.next());
            System.out.println("Deleted: "+tmp.getValue());
            size--;
        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public Node<T> getHead() {
        return head;
    }
    public void setHead(Node<T> head) {
        this.head = head;
    }
    public void traverseMid(){
        Node<T> tmp = head;
        runner=head;
        boolean flag = false;
        while(tmp != null){
            System.out.println(tmp.getValue());
            System.out.println("runner"+runner.getValue());
            if (!flag){
                runner =tmp.next();
            }
            flag =!flag;
            tmp = tmp.next();
        }
    }
    public T runnerK(int k){
        Node<T> tmp = head;
        runner=head;
        int i=0;
        while(tmp != null){
            System.out.println(tmp.getValue());
            System.out.println("runner: "+runner.getValue());
            if (i>k ){
                runner =runner.next();
            }
            i++;
            tmp = tmp.next();
        }
        return runner.getValue();
    }

    public static void main(String a[]){
        SingleLinkedListRunner<Integer> sl = new SingleLinkedListRunner<Integer>();
        sl.add(3);
        sl.add(32);
        sl.add(54);
        sl.add(89);
        sl.addAfter(76, 54);
        sl.deleteFront();
        sl.deleteAfter(76);
        sl.add(50);
        sl.add(82);
        sl.add(20);
        sl.traverseMid();
        sl.runnerK(3);
    }
}