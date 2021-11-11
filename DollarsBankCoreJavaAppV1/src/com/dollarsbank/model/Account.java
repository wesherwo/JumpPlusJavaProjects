package com.dollarsbank.model;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private String UID, pass;
	
	public Account(String UID, String pass) {
		this.UID = UID;
		this.pass = pass;
	}
	
	public String getUID() {
		return UID;
	}
	
	public String getPass() {
		return pass;
	}

	@Override
	public String toString() {
		return "Account [UID=" + UID + ", pass=" + pass + "]";
	}
}
