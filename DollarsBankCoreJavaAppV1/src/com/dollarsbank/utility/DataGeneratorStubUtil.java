package com.dollarsbank.utility;

public class DataGeneratorStubUtil {
	public static String titleBox(String s) {
		StringBuilder title = new StringBuilder();
		title.append("+");
		for(int i = 0; i < s.length() + 4; i++) {
			title.append("-");
		}
		title.append("+\n");
		title.append("|  " + s + "  |\n+");
		for(int i = 0; i < s.length() + 4; i++) {
			title.append("-");
		}
		title.append("+\n");
		return title.toString();
	}
}
