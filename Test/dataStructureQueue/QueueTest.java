package dataStructureQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class QueueTest {
	Queue<Object> queue;
	
	public void setupScenario1() {
		queue = new Queue<>();
	}
	
	public Integer[] setupScenario2() {
		Integer[] elements = {1,2,3,4,5};
		queue = new Queue<>();
		for (int i = 0; i < elements.length; i++) {
			queue.add(elements[i]);
		}
		return elements;
	}
	
	public void setupScenario3() {
		queue = new Queue<>();
		queue.add(10);
	}
	
	@Test
	public void testAdd() {
		setupScenario1();
		Integer toAdd = 90;
		queue.add(toAdd);
		assertEquals(toAdd, queue.peek());
		assertTrue(queue.size() > 0);;
	}
	
	@Test
	public void testGet() {
		Integer[] elements = setupScenario2();
		int size = elements.length;
		for (int i = 0; i < size; i++) {
			assertEquals(elements[i], queue.get(i));
		}
	}
	
	@Test
	public void testIsEmpty() {
		setupScenario3();
	}
	
	@Test
	public void testPoll() {
		Integer[] elements = setupScenario2();
		int size = elements.length;
		ArrayList<Integer> prov = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			prov.add(elements[i]);
		}
		for (int i = 0; i < size; i++) {
			assertTrue(prov.get(0) == queue.poll());
			prov.remove(0);
		}
	}
	
	@Test
	public void testPeek() {
		setupScenario1();
		Integer toAdd = 8;
		queue.add(toAdd);
		queue.add(12);
		queue.add(19);
		queue.add(32);
		assertEquals(toAdd, queue.peek());
	}
}
