package com.dollarsbank.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.*;

import com.dollarsbank.model.*;
import com.dollarsbank.utility.*;

public class DollarsBankController {
	
	private static ArrayList<SavingsAccount> accounts;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void Run() {
		importData();
		MainMenu();
	}
	
	private static void MainMenu() {
	    String in;
	    do {
	    	System.out.println(ConsolePrinterUtility.printBlue(DataGeneratorStubUtil.titleBox("DOLLARSBANK Welcomes You!")));
			System.out.println("1. Create New Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
		    System.out.println(ConsolePrinterUtility.printGreen("\nEnter Choice (1, 2, or 3): "));
	    	in = scanner.nextLine();
	    	if(in.equals("1")) {
	    		newAccount();
	    	} else if(in.equals("2")) {
	    		Login();
	    	} 
	    } while(!in.equals("3"));
	    System.out.println("Goodbye!");
	    signOut();
	}
	
	private static void newAccount() {
		System.out.println(ConsolePrinterUtility.printBlue(DataGeneratorStubUtil.titleBox("Enter Details For New Account")));
		System.out.println("Customer Name: ");
		String name = scanner.nextLine();
		System.out.println("Customer Address: ");
		String address = scanner.nextLine();
		System.out.println("Customer Contact Number: ");
		Long number = Long.parseLong(scanner.nextLine());
		System.out.println("User ID: ");
		String UID = scanner.nextLine();
		
		String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m;
		String pass = "";
		do {
			System.out.println("Password (8 Characters with one lower, upper, & special): ");
			pass = scanner.nextLine();
			m = p.matcher(pass);
			if(!m.matches())
				System.out.println(ConsolePrinterUtility.printRed("Invalid Password.  Try Again!"));
		} while(!m.matches());
		
		System.out.println("Inital Deposit Amount: ");
		Double amount = Double.parseDouble(scanner.nextLine());
		ArrayList<String> transaction = new ArrayList<String>();
		transaction.add("Inital Deposit Amount in account [" + UID + "]");
		accounts.add(new SavingsAccount(amount, transaction, name, address, number, UID, pass));
		System.out.println("Account Created");
	}
	
	private static void Login() {
		int loc = -1;
		System.out.println(ConsolePrinterUtility.printBlue(DataGeneratorStubUtil.titleBox("Enter Login Details")));
		System.out.println("User ID: ");
		String UID = scanner.nextLine();
		System.out.println("Password: ");
		String pass = scanner.nextLine();
		
		boolean found = false;
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getUID().equals(UID)) {
				if(accounts.get(i).getPass().equals(pass)) {
					loc = i;
				} else {
					found = true;
					System.out.println(ConsolePrinterUtility.printRed("Invalid Credentials.  Try Again!"));
				}
			}
		}
		if(loc != -1) {
			userAccount(loc);
		} else {
			if(!found)
				System.out.println(ConsolePrinterUtility.printRed("User not found."));
		}
	}
	
	private static void userAccount(int i) {
		String in;
	    do {
	    	System.out.println(ConsolePrinterUtility.printBlue(DataGeneratorStubUtil.titleBox("Welcome " + accounts.get(i).getName())));
			System.out.println("1. Deposit Amount");
			System.out.println("2. Withdraw Amount");
			System.out.println("3. Funds Transfer");
			System.out.println("4. View Last 5 Transactions");
			System.out.println("5. Display Customer Information");
			System.out.println("6. Sign Out");
		    System.out.println(ConsolePrinterUtility.printGreen("\nEnter Choice (1, 2, 3, 4, 5, or 6): "));
	    	in = scanner.nextLine();
	    	if(in.equals("1")) {
	    		deposit(i);
	    	} 
	    	else if(in.equals("2")) {
	    		withdraw(i);
	    	} 
	    	else if(in.equals("3")) {
	    		transfer(i);
	    	}
	    	else if(in.equals("4")) {
	    		lastFive(i);
	    	}
	    	else if(in.equals("5")) {
	    		info(i);
	    	}
	    } while(!in.equals("6"));
	}
	
	private static void deposit(int i) {
		System.out.println(ConsolePrinterUtility.printGreen("\nEnter Deposit Amount: "));
		Double amount = (double) -1;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch(Exception e) {
			System.out.println(ConsolePrinterUtility.printRed("Must be a positive amount."));
			return;
		}
		if(amount < 0) {
			System.out.println(ConsolePrinterUtility.printRed("Must be a positive amount."));
		} else {
			accounts.get(i).addFunds(amount);
		}
	}
	
	private static void withdraw(int i) {
		System.out.println(ConsolePrinterUtility.printGreen("\nEnter Withdrawl Amount: "));
		Double amount = (double) -1;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch(Exception e) {
			System.out.println(ConsolePrinterUtility.printRed("Must be a positive amount."));
			return;
		}
		if(amount < 0) {
			System.out.println(ConsolePrinterUtility.printRed("Must be a positive amount."));
		} else {
			if(accounts.get(i).getSavings() < amount) {
				System.out.println(ConsolePrinterUtility.printRed("Not enough funds in account."));
			} else {
				accounts.get(i).addFunds(-amount);
			}
		}
	}
	
	private static void transfer(int i) {
		System.out.println(ConsolePrinterUtility.printGreen("\nEnter Account to Tranfer Funds To: "));
		System.out.println("User ID: ");
		String UID = scanner.nextLine();
		boolean found = false;
		int loc = -1;
		for(int j = 0; j < accounts.size(); j++) {
			if(accounts.get(j).getUID().equals(UID)) {
				loc = j;
				found = true;
			}
		}
		if(!found) {
			System.out.println(ConsolePrinterUtility.printRed("User not found."));
			return;
		}
		System.out.println(ConsolePrinterUtility.printGreen("\nEnter Transfer Amount: "));
		Double amount = (double) -1;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch(Exception e) {
			System.out.println(ConsolePrinterUtility.printRed("Must be a positive amount."));
			return;
		}
		if(amount < 0) {
			System.out.println(ConsolePrinterUtility.printRed("Must be a positive amount."));
		} else {
			if(accounts.get(i).getSavings() < amount) {
				System.out.println(ConsolePrinterUtility.printRed("Not enough funds in account."));
			} else {
				accounts.get(i).addFunds(-amount);
				accounts.get(loc).addFunds(amount);
			}
		}
	}
	
	private static void lastFive(int i) {
		for(int j = 0; j < accounts.get(i).getTransactions().size() && j < 5; j++) {
			System.out.println(accounts.get(i).getTransactions().get(j));
		}
		System.out.println("Balance - " + accounts.get(i).getSavings() + " as of " + new Date());
	}
	
	private static void info(int i) {
		System.out.println("Name: " + accounts.get(i).getName());
		System.out.println("Address: " + accounts.get(i).getAddress());
		System.out.println("Number: " + accounts.get(i).getNumber());
		System.out.println("Account Number: " + accounts.get(i).getUID());
		System.out.println("Savings: " + accounts.get(i).getSavings());
	}
	
	private static void importData() {
		accounts = FileStorageUtility.importData();
		for(SavingsAccount acc : accounts) {
			System.out.println(acc.getUID());
		}
	}
	
	private static void signOut() {
		FileStorageUtility.ExportData(accounts);
	}

}
