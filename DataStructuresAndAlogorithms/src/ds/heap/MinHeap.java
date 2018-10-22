package ds.heap;

/*A Binary heapArr is a Binary Tree with following properties.
 * 1. Itfs a complete tree 
 * 2. A Binary heapArr is either Min heapArr or Max heapArr
 * 
 * For an array backed implementation the pseudo code can be thought of as 
 * while not end of array, 
	if heapArr is empty, 
		place item at root; 
	else, 
		place item at bottom of heapArr; 
		while (child > parent) 
			swap(parent, child); 
	go to next array element; 
   end
    [1]
  [2] [3]
[4][5][6][7]
 * */
public class MinHeap

{
    private int[] heapArr;
    private int currSize;
    private int maxsize;
    private static final int FRONT = 1;//For ease of calculation root is taken as index 1
    
    public MinHeap(int maxsize){
        this.maxsize = maxsize;
        this.currSize = 0;
        heapArr = new int[this.maxsize + 1];
        heapArr[0] = Integer.MIN_VALUE;
    }

    public static void main(String arg[]){
    	System.out.println("The MinHeap initializing ");
    	MinHeap minHeap = new MinHeap(15);
        minHeap.insert(17);
        minHeap.insert(22);
        minHeap.insert(9);
        minHeap.insert(10);
        minHeap.insert(8);
        minHeap.insert(19);
        minHeap.insert(16);
        minHeap.insert(5);
        minHeap.insert(30);  
        minHeap.insert(3);
        minHeap.minHeap();
        minHeap.print();
        System.out.println("Retrieve Minimum value: " + minHeap.remove());
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

    private void minHeapify(int pos){
        if (!isLeaf(pos)){
            if ( heapArr[pos] > heapArr[leftNode(pos)] || heapArr[pos] > heapArr[rightNode(pos)]){
                if (heapArr[leftNode(pos)] < heapArr[rightNode(pos)]){
                    swap(pos, leftNode(pos));
                    minHeapify(leftNode(pos));
                }
                else{
                    swap(pos, rightNode(pos));
                    minHeapify(rightNode(pos));
                }
            }
        }
    }

    public void insert(int element) {
        heapArr[++currSize] = element;
        int current = currSize;
        while (heapArr[current] < heapArr[parent(current)]){
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
            System.out.println(" Left Node: " + heapArr[2*i] + " Right Node :" + heapArr[2 * i  + 1]);
        } 
    }

    public void minHeap(){
        for (int pos = (currSize / 2); pos >= 1 ; pos--){
            minHeapify(pos);
        }
    }

    public int remove(){
        int popped = heapArr[FRONT];
        heapArr[FRONT] = heapArr[currSize--]; 
        minHeapify(FRONT);// restructure heap
        return popped;
    }

}