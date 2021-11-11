package com.dollarsbank.utility;

import java.io.*;
import java.util.ArrayList;
import com.dollarsbank.model.*;

public class FileStorageUtility {
	public static ArrayList<SavingsAccount> importData() {
		ArrayList<SavingsAccount> accounts = new ArrayList<SavingsAccount>();
		try {
			FileInputStream file = new FileInputStream(new File("Accounts.txt"));
			ObjectInputStream obj = new ObjectInputStream(file);
			
			while(file.available() > 0) {
				accounts.add((SavingsAccount)obj.readObject());
			}
			obj.close();
			file.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}
	
	public static void ExportData(ArrayList<SavingsAccount> accounts) {
		try {
			FileOutputStream file = new FileOutputStream(new File("Accounts.txt"));
			ObjectOutputStream obj = new ObjectOutputStream(file);
			
			for(SavingsAccount acc : accounts) {
				obj.writeObject(acc);
			}
			obj.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
