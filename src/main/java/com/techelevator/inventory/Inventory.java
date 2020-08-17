package com.techelevator.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.items.Appetizer;
import com.techelevator.items.Beverage;
import com.techelevator.items.Dessert;
import com.techelevator.items.Entree;
import com.techelevator.items.Item;

public class Inventory {

	private static final int STARTING_QUANTITY = 50;

	public Map<String, InventoryItem> loadInventory(String fileName) throws FileNotFoundException {

		Map<String, InventoryItem> inventory = new LinkedHashMap<String, InventoryItem>();
		
		List<String> lines =  loadLinesFromFile(fileName);
		
		for (String line : lines) {
			String[] parts = line.split("\\|");  
			Item item = buildItemFromParts(parts);
			
			InventoryItem inventoryItem = new InventoryItem(STARTING_QUANTITY, item);
			inventory.put(parts[0], inventoryItem);
		}
		return inventory;
	}
	
	private Item buildItemFromParts(String[] parts) {
		
		String type = parts[3];
		Item item = null;
		
		if (type.equalsIgnoreCase("B")) {
			item = new Beverage(parts[1], Double.parseDouble(parts[2]));
			
		} else if (type.equalsIgnoreCase("A")) {
			item = new Appetizer(parts[1], Double.parseDouble(parts[2]));
			
		} else if (type.equalsIgnoreCase("E")) {
			item = new Entree(parts[1], Double.parseDouble(parts[2]));
			
		} else if (type.equalsIgnoreCase("D")) {
			item = new Dessert(parts[1], Double.parseDouble(parts[2]));
		}
		
		return item;
	}

	private List<String> loadLinesFromFile(String fileName) throws FileNotFoundException {

		File inventoryFile = new File(fileName);
		List<String> lines = new ArrayList<String>();

		try (Scanner fileScanner = new Scanner(inventoryFile)) {
			while (fileScanner.hasNextLine()) {
				lines.add(fileScanner.nextLine());
			}
		}
		return lines;
	}
}
