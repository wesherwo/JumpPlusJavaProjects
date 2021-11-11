package com.dollarsbank.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SavingsAccount implements Serializable{
	private static final long serialVersionUID = 1L;
	private double savings;
	private ArrayList<String> transactions;
	private Customer customer;
	private Account account;
	
	public SavingsAccount(double savings, ArrayList<String> transactions, String name, String address, long number, String UID, String pass) {
		this.savings = savings;
		this.transactions = transactions;
		this.customer = new Customer(name, address, number);
		this.account = new Account(UID, pass);
	}
	
	public double getSavings() {
		return savings;
	}
	
	public ArrayList<String> getTransactions() {
		return transactions;
	}
	
	public String getName() {
		return customer.getName();
	}
	
	public String getAddress() {
		return customer.getAddress();
	}
	
	public Long getNumber() {
		return customer.getNumber();
	}
	
	public String getUID() {
		return account.getUID();
	}
	
	public String getPass() {
		return account.getPass();
	}

	@Override
	public String toString() {
		return "SavingsAccount [savings=" + savings + ", transactions=" + transactions.toString() + ", customer="
				+ customer + ", account=" + account + "]";
	}
	
	public void addFunds(double d) {
		savings += d;
		transactions.add(0, "Savings " + d);
	}
}
