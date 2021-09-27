package dataStructureStack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StackTest {
	Stack<Object> stack;
	
	public void setupScenario1() {
		stack = new Stack<>();
	}
	
	public void setupScenario2() {
		stack = new Stack<>();
		Integer[] elements = {1, 2, 3, 4};
		for (int i = 0; i < elements.length; i++) {			
			stack.add(elements[i]);
		}
	}
	
	public void setupScenario3() {
		stack = new Stack<>();
		stack.add(3);
	}
	
	@Test
	public void testAdd() {
		setupScenario1();
		int toAdd = 5;
		stack.add(toAdd);
		assertEquals(toAdd, stack.peek());
	}
	
	@Test
	public void testGet() {
		setupScenario1();
		int toAdd = 3;
		stack.add(toAdd);
		assertEquals(toAdd, stack.get(0));
	}
	
	@Test
	public void testPop() {
		setupScenario3();
		stack.pop();
		assertTrue(stack.peek() == null);
	}
	
	@Test
	public void testPeek() {
		setupScenario1();
		int toAdd = 10;
		stack.add(toAdd);
		stack.add(toAdd + 5);
		assertEquals(toAdd + 5, stack.peek());
	}
	
	@Test
	public void testSize() {
		setupScenario2();
		int size = stack.size();
		int count = 0;
		while (!stack.isEmpty()) {
			stack.pop();
			count++;
			assertTrue(stack.size() + count == size);
		}
	}
}
