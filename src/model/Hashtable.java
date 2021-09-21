package model;

public class Hashtable {
	
	private int key;
	
	private double price;
	
	private int quantity;

	public Hashtable(int key, double price, int quantity) {
		this.key= key;
		this.price = price;
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String print() {
		return key+" "+ price+" "+quantity;
	}
	
	
}
