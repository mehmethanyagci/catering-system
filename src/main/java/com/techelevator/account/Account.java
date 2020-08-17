package com.techelevator.account;

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
	
	public double makePurchase(double purchasePrice) {
		
		try {
			if (accountBalance - purchasePrice > 0) {
				accountBalance -= purchasePrice;
			} else {
				System.out.println("You do not have enough balance in your acoount.");
			}
		} catch (Exception e) {
			System.out.println("...");
		}
		
		return accountBalance;
	}
	
	
}
