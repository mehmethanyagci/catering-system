package com.techelevator.items;

public class Beverage extends Item {

	public Beverage(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Beverage";
	}

	
}
