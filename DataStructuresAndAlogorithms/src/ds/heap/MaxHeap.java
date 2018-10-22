package ds.heap;

public class MaxHeap {

	private int[] heapArr;
    private int currSize;
    private int maxsize;
    private static final int FRONT = 1;//For ease of calculation root is taken as index 1
    
    public MaxHeap(int maxsize){
        this.maxsize = maxsize;
        this.currSize = 0;
        heapArr = new int[this.maxsize + 1];
        heapArr[0] = Integer.MAX_VALUE;
    }

    public static void main(String arg[]){
    	System.out.println("The MaxHeap initializing ");
    	MaxHeap maxHeap = new MaxHeap(15);
    	maxHeap.insert(17);
    	maxHeap.insert(22);
    	maxHeap.insert(9);
    	maxHeap.insert(10);
    	maxHeap.insert(8);
    	maxHeap.insert(19);
    	maxHeap.insert(16);
    	maxHeap.insert(5);
    	maxHeap.insert(30);  
    	maxHeap.insert(3);
    	maxHeap.minHeap();
    	maxHeap.print();
        System.out.println("Retrieve Maximum value: " + maxHeap.remove());
    }
    
    private int parent(int pos){
        return pos / 2;
    }

    private int leftNode(int pos){
        return (2 * pos);
    }

    private int rightNode(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos){
        if (pos >=  (currSize / 2)  &&  pos <= currSize){ 
            return true;
        }
        return false;
    }

    private void maxHeapify(int pos){
        if (!isLeaf(pos)){
            if ( heapArr[pos] < heapArr[leftNode(pos)] || heapArr[pos] < heapArr[rightNode(pos)]){
                if (heapArr[leftNode(pos)] > heapArr[rightNode(pos)]){
                    swap(pos, leftNode(pos));
                    maxHeapify(leftNode(pos));
                }
                else{
                    swap(pos, rightNode(pos));
                    maxHeapify(rightNode(pos));
                }
            }
        }
    }

    public void insert(int element) {
        heapArr[++currSize] = element;
        int current = currSize;
        while (heapArr[current] > heapArr[parent(current)]){
            swap(current,parent(current));
            current = parent(current);
        }	
    }

    private void swap(int start, int end){
        int temp;
        temp = heapArr[start];
        heapArr[start] = heapArr[end];
        heapArr[end] = temp;
    }
    
    public void print(){
        for (int i = 1; i <= currSize / 2; i++ ){
            System.out.println("     Root Node : " + heapArr[i]);
            System.out.println(" Left Node: " + heapArr[2*i] + " Right Node :" + heapArr[2*i + 1]);
        } 
    }

    public void minHeap(){
        for (int pos = (currSize / 2); pos >= 1 ; pos--){
            maxHeapify(pos);
        }
    }

    public int remove(){
        int popped = heapArr[FRONT];
        heapArr[FRONT] = heapArr[currSize--]; 
        maxHeapify(FRONT);// restructure heap
        return popped;
    }

}