package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.techelevator.CateringSystemCLI;
import com.techelevator.account.Account;
import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.InventoryItem;

public class Menu {

	private Menu menu;
	CateringSystemCLI cli = new CateringSystemCLI(menu);
	private Inventory inventory = new Inventory();
	Map<String, InventoryItem> items;
	
	
	public Menu getMenu() {
		return menu;
	}
	
	public void mainMenu()  {
		
		System.out.println("----------");
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
		System.out.println("----------");
		
		Scanner input = new Scanner(System.in);

		String userChoice = input.nextLine();

		if(userChoice.equals("1")) {
			menu.displayItems(items);
		} else if (userChoice.equals("2")) {
			menu.order();
		} else if (userChoice.equals("3")) {
			menu.quit();
		} else {
			System.out.println("Please enter a valid selection");
		}
		
	}

	public void displayItems(Map<String, InventoryItem> items) {

		for (Entry<String, InventoryItem> entry : items.entrySet()) {
			System.out.print(entry.getKey() + " - ");
			System.out.print(entry.getValue().getItem().getName() + " - ");
			System.out.print(entry.getValue().getItem().getPrice() + " - ");
			System.out.print(entry.getValue().getCount());
			System.out.println();
		}
	}

	public void order() { // double account

		System.out.println("------------------------");
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
//		System.out.println("Current Account Balance: $");
//		System.out.println("------------------------");
	}

	public void quit() {
		// set the keepRunning to false and stop the program.
		System.out.println("See you soon");
		 // = false;
	}
	

}



