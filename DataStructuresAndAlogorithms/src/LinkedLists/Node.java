package LinkedLists;

public class Node<T> implements Comparable<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value=value;
    }

    public Node() {
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public Node<T> next() {
        return next;
    }
    public void setNext(Node<T> ref) {
        this.next = ref;
    }
    @Override
    public int compareTo(T arg) {
        if(arg == this.value){
            return 0;
        } else {
            return 1;
        }
    }
}