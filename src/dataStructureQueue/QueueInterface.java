package dataStructureQueue;


public interface QueueInterface<T> {
	
	public void add(T values);
	
	public T get(int index);
	
	public boolean isEmpty();
	
	public T poll();
	
	public T peek();
	
	public int size();
	
}
