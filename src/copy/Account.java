package com.techelevator.account.copy;

import java.util.Scanner;

import com.techelevator.*;
import com.techelevator.view.*;

public class Account {
	
	private double accountBalance = 0.0;
	
	public Account() {  // what makes something an account. just make your declaration with no params.
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public double addMoney(int amountToAdd) {   // how to keep the balance under 5K?
		
			try {
				if (accountBalance + amountToAdd <= 5000) {
					accountBalance += amountToAdd;
				}  else {
					System.out.println("Your total balance cannot exceed $5000");
				}
			} catch (Exception e) {
				System.out.println("You can only add whole dollars!");
			}
		
		return accountBalance;
		
	}
	
	public void selectProducts(String productCode, int quantity) {
		
		try(Scanner readerFromItemCatalog = new Scanner("cateringsystem.csv")) {
			
			while(readerFromItemCatalog.hasNextLine()) {
				String line = readerFromItemCatalog.nextLine();
				System.out.println(line);
			}
			readerFromItemCatalog.nextLine();
			
			System.out.println("Please enter the product code");
			// this is the product code provided by the user
			String userProductCode = readerFromItemCatalog.nextLine();
			// Make sure userProductCode exists in cateringsystem.csv
			while(readerFromItemCatalog.hasNextLine()) {
				String line = readerFromItemCatalog.nextLine();
				if(line.contains(userProductCode)) {
					String[] stringArr = line.split("|");
					System.out.println(stringArr[0]);
					System.out.println(stringArr[1]);
					System.out.println(stringArr[2]);
					System.out.println(stringArr[3]);
				}
				
			}
			
			// Ask the user quantity of product code
			System.out.println("Please enter the quantity");
			
		} catch(Exception e) {
			System.out.println("Please enter a valid product code.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
