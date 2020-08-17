package com.techelevator.cart;

import java.util.HashMap;
import java.util.Map;

import com.techelevator.inventory.InventoryItem;
import com.techelevator.items.Item;

public class CustomerCart {
	
	private Map<String, Item> cart = new HashMap<String, Item>();

	public Map<String, Item> getCart() {
		return cart;
	}

	public void setCart(Map<String, Item> cart) {
		this.cart = cart;
	}
	
	public void addItem(String key, Item item) {
		
		if (cart.containsKey(key)) {
			int newQuantity = cart.get(key).getQuantity() + item.getQuantity();
			item.setQuantity(newQuantity);
		} 
		cart.put(key, item);
		
	}
	
	
}
