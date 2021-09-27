package dataStructureStack;

import java.util.ArrayList;

public class Stack<T> implements StackInterface<T>{

	private ArrayList<T> stack;
	
	public Stack(){
		stack=new ArrayList<>();
	}
	
	public void add(T values) {
		stack.add(0, values);
	}
	
	public T get(int index) {
		return stack.get(index);
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public T pop() { // In case it stops working, I changed pop and peek
		if (isEmpty()) {
			return null;
		} else {			
			T temp=stack.get(0);
			stack.remove(0);
			return temp;
		}
	}
	public T peek() {
		if (isEmpty()) {
			return null;
		} else {			
			return stack.get(0);
		}
	}
	public int size() {
		return stack.size();
	}
	
	
		
}
	

