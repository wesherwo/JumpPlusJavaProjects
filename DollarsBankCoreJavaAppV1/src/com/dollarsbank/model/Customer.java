package com.dollarsbank.model;

import java.io.Serializable;

public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name, address;
	private long number;

	public Customer(String name, String address, long number) {
		this.name = name;
		this.address = address;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public long getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", number=" + number + "]";
	}
}
