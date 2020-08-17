package com.techelevator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Map.Entry;

import com.techelevator.account.Account;
import com.techelevator.cart.CustomerCart;
import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.InventoryItem;
import com.techelevator.items.Beverage;
import com.techelevator.view.Menu;

public class CateringSystemCLI {

	private String pathName = "cateringsystem.csv";
	private Menu menu;
	private Inventory inventory = new Inventory();
	Account account = new Account();
	double totalForProduct;
	CustomerCart cart = new CustomerCart();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	
	public void run() throws FileNotFoundException {
		
		PrintWriter writer = new PrintWriter("log.txt"); //add money, purchase, complete transaction

		Map<String, InventoryItem> items = inventory.loadInventory(pathName);

		while (true) {

			System.out.println("----------");
			System.out.println("(1) Display Catering Items");
			System.out.println("(2) Order");
			System.out.println("(3) Quit");
			System.out.println("----------");

			Scanner input = new Scanner(System.in);

			String userChoice = input.nextLine();

			if (userChoice.equals("1")) {
				menu.displayItems(items);
				// break;
			} else if (userChoice.equals("2")) {
				menu.order(); // account.getAccountBalance()
				break;
			} else if (userChoice.equals("3")) {
				menu.quit();
				break;
			} else {
				System.out.println("Please enter a valid selection");
			}
		}

		while (true) {

			Scanner input = new Scanner(System.in);

			String userInput = input.nextLine();

			if (userInput.equals("1")) {
				double currentAccountBalance = account.getAccountBalance();
     			System.out.println("How much would you like to add?");
				String amountToAdd = input.nextLine(); 
				account.addMoney(Integer.parseInt(amountToAdd));
				System.out.println("Your balance: " + account.getAccountBalance());
				
				Date date = new Date();
				writer.write( dateFormat.format(date) + "   Add Money   " +  currentAccountBalance + "     " + account.getAccountBalance());
				writer.write("\n");
				menu.order(); // account.getAccountBalance()

			} else if (userInput.equals("2")) {
				// user should be able to select products from cateringsystem.csv

				System.out.println("What would you like to have? Please enter the product code");
				String userProductChoice = input.nextLine().toUpperCase();
				System.out.println(
						"How many " + items.get(userProductChoice).getItem().getName() + " would you like to have?");
				String quantity = input.nextLine();

				System.out.println("You would like to have " + Integer.parseInt(quantity) + " "
						+ items.get(userProductChoice).getItem().getName() + "(s)");
				System.out.println("Quantity: " + quantity);

				for (Entry<String, InventoryItem> entry : items.entrySet()) {

					String key = entry.getKey();
					InventoryItem value = entry.getValue();

					try {
						if (key.equals(userProductChoice)) {
							System.out.println("Valid product code");

						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter a valid product code.");
					}

					try {
						if (key.equals(userProductChoice) && Integer.parseInt(quantity) <= items.get(key).getCount()) {
							System.out.println("We have enough in stock.");
						}
					} catch (Exception e) {
						System.out.println("...");
					}

					try {
						if (key.equals(userProductChoice) && Integer.parseInt(quantity) <= items.get(key).getCount()
								&& account.getAccountBalance() >= totalForProduct) {
							System.out.println("You have enough money");
							
							totalForProduct = Integer.parseInt(quantity) * items.get(key).getItem().getPrice();
							
							System.out.println(
									"Total for " + items.get(key).getItem().getName() + "(s) $" + totalForProduct);
							
							double currentAccountBalance = account.getAccountBalance();
							
							account.makePurchase(totalForProduct);
							
							items.get(key).getItem().setQuantity(Integer.parseInt(quantity));
							
							cart.addItem(key, items.get(key).getItem());
							
							Date date = new Date();
							writer.write( dateFormat.format(date) + "  "  + quantity + "   "+  items.get(key).getItem().getName() + "  " + currentAccountBalance + "     " + account.getAccountBalance());
							writer.write("\n");
							System.out.println(items.get(key).getItem().getName() + "  ==>  " + items.get(key).getItem().getPrice());
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
					}

					// update the balance after transacation

				}
				// System.out.println("Current Account Balance: $" +
				// account.getAccountBalance());
				
				
				menu.order();
				System.out.println("Current Account Balance: $" + account.getAccountBalance());
				System.out.println("------------------------");

			} else if (userInput.equals("3")) {
				// call a method to print the receipt
				System.out.println("Your transaction is complete. This is your reciept");
				System.out.println("Quantity     Type      Name      Unit Price      Total");
						
				double change = account.getAccountBalance();
				
				double currentTotal = 0;
				
				for (String key : cart.getCart().keySet() ) { // soda 1.50 -- wine 3.05
					
					System.out.print( cart.getCart().get(key).getQuantity() + "     ");
					System.out.print( cart.getCart().get(key).getType() + "     ");
					System.out.print( cart.getCart().get(key).getName() + "     ");
					System.out.print( cart.getCart().get(key).getPrice() + "     ");
					System.out.print( cart.getCart().get(key).getQuantity() * cart.getCart().get(key).getPrice() );
					System.out.println();
					
					currentTotal += cart.getCart().get(key).getQuantity() * cart.getCart().get(key).getPrice();
					
				}
				
				System.out.println("Total: " + currentTotal);
				
				Date date = new Date();
				writer.write( dateFormat.format(date) + " Give Change " + account.getAccountBalance() + " $0.00");
				writer.write("\n");
				writer.flush();	
			} else {

				System.out.println("Please enter a valid selection.");
			}
		}
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Menu getMenu() {
		return menu;
	}

	public String getPathName() {
		return pathName;
	}

	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}
}

/*
 * Display the Starting Menu and get the users choice
 * 
 * IF the User Choice is Display Vending Machine Items, THEN display vending
 * machine items ELSE IF the User's Choice is Purchase, THEN go to the purchase
 * menu
 */
