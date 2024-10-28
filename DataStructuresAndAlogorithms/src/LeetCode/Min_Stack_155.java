package LeetCode;

import java.util.EmptyStackException;
import java.util.Stack;

public class Min_Stack_155 {

	/*
	 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	 * Implement the MinStack class:
	 * MinStack() initializes the stack object.
	 * void push(int val) pushes the element val onto the stack.
	 * void pop() removes the element on the top of the stack.
	 * int top() gets the top element of the stack.
	 * int getMin() retrieves the minimum element in the stack.
	 * You must implement a solution with O(1) time complexity for each function.
	 */

	public static void main(String args[]) throws Exception {
		Min_Stack_155 ob = new Min_Stack_155();
		ob.push(12);
		ob.push(22);
		ob.push(2);
		System.out.println(ob.getMin());
		ob.push(-7);
		ob.push(2);
		System.out.println(ob.getMin());
    }

	Stack<Integer> s= new Stack<>();
	Stack<Integer> minElemStack =new Stack<>();

	Min_Stack_155() {
	}
	int getMin() throws EmptyStackException {
		return minElemStack.peek();
	}

	int peek() throws EmptyStackException{
		return s.peek();
	}

	void pop() throws Exception {
		if (s.isEmpty()) {
			System.out.println("Stack is empty");
			return;
		}
		System.out.print("Top Most Element Removed: ");
		Integer t = s.pop();
		if (t == minElemStack.peek()) {
			System.out.println("minElement removed");
			minElemStack.pop();
		} else {
			System.out.println(t);
		}
	}

	void push(Integer x) throws Exception {
		if (minElemStack.isEmpty() || x <= minElemStack.peek()) {
			s.push(x);
			minElemStack.push(x);
		} else {
			s.push(x);
		}
		System.out.println("Number Inserted: " + x);
	}
}
