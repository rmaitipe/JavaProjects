package crackCodeInterview;


import java.util.Stack;

public class ChapterThree_StacksQueues {
    /*
     * 1.Three in One: Describe how you could use a single array to implement three stacks.
     *
     * 2. Stack Min: How would you design a stack which, in addition to push and pop, has a function min
     * which returns the minimum element? Push, pop and min should all operate in O(1) time.
     *
     * 3. Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
     * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
     * threshold. Implement a data structure SetOfStacks that mimics this. SetO-fStacks should be
     * composed of several stacks and should create a new stack once the previous one exceeds capacity.
     * SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack
     * (that is, pop () should return the same values as it would if there were just a single stack).
     * FOLLOW UP
     * Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
     *
     * 4. Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
     *
     * 5. Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
     * an additional temporary stack, but you may not copy the elements into any other data structure
     * (such as an array). The stack supports the following operations: push, pop, peek, and is Empty.
     *
     * 6. Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
     * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
     * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
     * that type). They cannot select which specific animal they would like. Create the data structures to
     * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
     * and dequeueCat. You may use the built-in Linked list data structure.
     */

    public static void main(String args[]){
        ThreeStack stack= new ThreeStack(8);
        stack.pushStack1(4); stack.pushStack2(3); stack.pushStack3(6);
    }

    /* Alternate Method 2 for a 2 Stack is described
    This method efficiently utilizes the available space. It doesn't cause an overflow if there is space available in arr[].
    The idea is to start two stacks from two extreme corners of arr[]. stack1 starts from the leftmost element,
    the first element in stack1 is pushed at index 0. The stack2 starts from the rightmost corner, the first element in stack2 is
    pushed at index (n-1). Both stacks grow (or shrink) in opposite direction.
    To check for overflow, all we need to check is for space between top elements of both stacks. This check is highlighted in the below code.*/
    static class ThreeStack {
        int array[];
        int index1;	int index2;	int index3;
        int tempIndex1; int tempIndex2;	int tempIndex3;

        ThreeStack(int size){
            array = new int [size];
            index1=size/3;
            index2=size*2/3;
            index2=size;
            tempIndex1=0;
            tempIndex2=(size/3)+1;
            tempIndex3=(size*2/3)+1;
        }
        public void pushStack1(int val){
            if (tempIndex1==index1) { System.out.println("Stack1 is full");}
            else{ array[tempIndex1] = val;  tempIndex1++; }
        }
        public void pushStack2(int val){
            if (tempIndex1==index2) { System.out.println("Stack2 is full");}
            else{ array[tempIndex2] = val;  tempIndex2++; }
        }
        public void pushStack3(int val){
            if (tempIndex1==index3) { System.out.println("Stack3 is full");}
            else{ array[tempIndex3] = val;  tempIndex3++; }
        }
        public void popStack1(){
            if (tempIndex1 !=0){tempIndex1--;}
        }
        public void popStack2(){
            if (tempIndex2 !=(index1+1)){tempIndex2--;}
        }
        public void popStack3(){
            if (tempIndex3 !=(index2+1)){tempIndex3--;}
        }
    }
    /* Push(x) : Inserts x at the top of stack.
     * If stack is empty, insert x into the stack and make minEle equal to x.
     * If stack is not empty, compare x with minEle. Two cases arise:
     * 	If x is greater than or equal to minEle, simply insert x.
     *  If x is less than minEle, insert (2*x - minEle) into the stack and make minEle equal to x.
     * For example, let previous minEle was 3. Now we want to insert 2. We update minEle as 2 and insert 2*2 - 3 = 1 into the stack.
     *
     * Pop() : Removes an element from top of stack.
     * Remove element from top. Let the removed element be y. Two cases arise:
     *   If y is greater than or equal to minEle, the minimum element in the stack is still minEle.
     *   If y is less than minEle, the minimum element now becomes (2*minEle - y), so update (minEle = 2*minEle - y).
     * This is where we retrieve previous minimum from current minimum and its value in stack.
     * For example, let the element to be removed be 1 and minEle be 2. We remove 1 and update minEle as 2*2 - 1 = 3.
     */
    static class StackMin{
        Stack<Integer> s;
        Integer minEle;

        StackMin() { s = new Stack<Integer>(); }
        void getMin()
        {
            if (s.isEmpty())
                System.out.println("Stack is empty");
            else
                System.out.println("Minimum Element in the " + " stack is: " + minEle);
        }
        void peek()
        {
            if (s.isEmpty()) {
                System.out.println("Stack is empty ");
                return;
            }
            Integer t = s.peek(); // Top element.
            System.out.print("Top Most Element is: ");
            if (t < minEle)
                System.out.println(minEle);
            else
                System.out.println(t);
        }
        void pop() throws Exception {
            if (s.isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            System.out.print("Top Most Element Removed: ");
            Integer t = s.pop();
            if (t < minEle) {
                System.out.println(minEle);
                minEle = 2*minEle - t;
            }
            else
                System.out.println(t);
        }

        void push(Integer x) throws Exception
        {
            if (s.isEmpty()) {
                minEle = x;
                s.push(x);
                System.out.println("Number Inserted: " + x);
                return;
            }
            if (x < minEle) {
                s.push(2*x - minEle);
                minEle = x;
            }
            else
                s.push(x);
            System.out.println("Number Inserted: " + x);
        }
    }

    /*
     * 1) While stack1 is not empty, push everything from stack1 to stack2.
     * 2) Push x to stack1 (assuming size of stacks is unlimited).
     * 3) Push everything back to stack1.
     */
    static class MyQueue {
        Stack s1;
        Stack s2;

        MyQueue(int size){
            s1 = new Stack();
            s2 = new Stack();
        }
        void enqueue(int val) throws Exception{
            while (!s1.isEmpty())  {
                s2.push(s1.pop());
            }
            s1.push(val);
            while (!s2.isEmpty())  {
                s1.push(s2.pop());
            }
        }
        int dequeue() throws Exception{
            if (s1.isEmpty())  {
                System.out.println("Q is Empty");
                System.exit(0);
            }
            int x = (int) s1.peek();
            s1.pop();
            return x;
        }
    }

    Stack sortStack(Stack<Integer> input) throws Exception {
        Stack<Integer> tmpStack = new Stack();
        while (!input.isEmpty()) {
            int tmp = input.pop();
            while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
                input.push(tmpStack.pop());
            }
            tmpStack.push(tmp);
        }
        return tmpStack;
    }
}