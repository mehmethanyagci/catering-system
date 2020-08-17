package com.techelevator.inventory;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.*;

public class InventoryTest {

	private final static String inventoryFileName = "cateringsystem.csv";
	private Inventory inventory;
	
	@Before
	public void setup() {
		inventory = new Inventory();
	}
	
	@Test
	public void load_inventory() throws FileNotFoundException {
		Map<String, InventoryItem> items = inventory.loadInventory(inventoryFileName);
	}
	
}
