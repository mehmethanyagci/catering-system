package com.techelevator.items;

public class Dessert extends Item {

	public Dessert(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Dessert";
	}

}
