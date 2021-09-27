package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HashtableTest {
	Hashtable table;
	
	public void setupScenario1() {
		
	}
	
	public void setupScenario2() {
		table = new Hashtable(153, 25000, 9);
	}
	
	@Test
	public void testHashtable() {
		setupScenario1();
		int key = 0;
		double price = 0;
		int quantity = 0;
		table = new Hashtable(key,price,quantity);
		assertEquals(key, table.getKey());
		assertEquals(price, table.getPrice());
		assertEquals(quantity, table.getQuantity());
	}
	
	@Test
	public void testSetPrice() {
		setupScenario2();
		double priceToChange = 15000;
		//double oldPrice = table.getPrice();
		table.setPrice(priceToChange);
		assertEquals(priceToChange, table.getPrice());
	}
	
	@Test
	public void testSetQuantity() {
		setupScenario2();
		int quantity = 12;
		table.setQuantity(quantity);
		assertEquals(quantity, table.getQuantity());
	}
	
	@Test
	public void testSetKey() {
		setupScenario2();
		int key = 908;
		table.setKey(key);
		assertEquals(key, table.getKey());
	}
	
	@Test
	public void testPrint() {
		setupScenario2();
		String text = "153 25000.0 9";
		assertEquals(text, table.print());
	}
}
