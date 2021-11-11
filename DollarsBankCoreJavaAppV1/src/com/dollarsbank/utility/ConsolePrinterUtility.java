package com.dollarsbank.utility;

public class ConsolePrinterUtility {
	public static String print(String s) {
		return ColorsUtility.ANSI_BLACK + s + ColorsUtility.ANSI_RESET;
	}
	public static String printRed(String s) {
		return ColorsUtility.ANSI_RED + s + ColorsUtility.ANSI_RESET;
	}
	public static String printGreen(String s) {
		return ColorsUtility.ANSI_GREEN + s + ColorsUtility.ANSI_RESET;
	}
	public static String printBlue(String s) {
		return ColorsUtility.ANSI_BLUE + s + ColorsUtility.ANSI_RESET;
	}
	public static String printWhite(String s) {
		return ColorsUtility.ANSI_WHITE + s + ColorsUtility.ANSI_RESET;
	}
}
