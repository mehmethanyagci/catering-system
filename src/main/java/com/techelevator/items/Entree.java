package com.techelevator.items;

public class Entree extends Item {

	public Entree(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Entree";
	}

}
