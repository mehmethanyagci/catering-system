package com.techelevator.items;

public abstract class Item {

	private String name;
	private double price;
	private int quantity = 0;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Item(String name, double price,int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public abstract String getType(); 
	
}
