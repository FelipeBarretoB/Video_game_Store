package dataStructureQueue;

import java.util.ArrayList;

public class Queue<T> implements QueueInterface<T> {

	private ArrayList<T> queue;
	
	public Queue(){
		queue=new ArrayList<>();
	}
	
	public void add(T values) {
		queue.add(queue.size(), values);
	}
	
	public T get(int index) {
		return queue.get(index);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public T poll() {
		T temp=queue.get(0);
		queue.remove(0);
		return temp;
	}
	public T peek() {
		return queue.get(0);
	}
	public int size() {
		return queue.size();
	}
}
