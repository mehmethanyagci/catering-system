package com.techelevator.items;

public class Appetizer extends Item{

	public Appetizer(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Appetizer";
	}

}
