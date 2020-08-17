package com.techelevator.inventory;

import com.techelevator.items.Item;

public class InventoryItem {
	
	private int count;
	private Item item;
	
	public InventoryItem(int count, Item item) {
		this.count = count;
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public Item getItem() {
		return item;
	}
	
	public boolean removeItems(int numberToRemove) {
		
		if(count - numberToRemove < 0) {
			return false;
		} 
		count -= numberToRemove;
		return true;
	}

}
