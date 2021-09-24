package dataStructureStack;

public interface StackInterface<T> {

	public void add(T values);
	
	public T get(int index);
	
	public boolean isEmpty();
	
	public T pop();
	
	public T peek();
	
	public int size();
}
