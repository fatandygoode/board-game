package utils;

import java.util.ArrayList;
import java.util.Scanner;

public class ScannerInput {
	@SuppressWarnings("resource")
	public static int validNextInt(String prompt) {
		
		Scanner input = new Scanner(System.in);	
		
		do {
			try {
				System.out.print(prompt);
				return Integer.parseInt( input.next() );
			}
			catch (NumberFormatException | IndexOutOfBoundsException e) {
				System.err.println("\tEnter a valid number please.");
			}
		}
		while (true);
	}

	public static boolean validNextIndex(ArrayList<Object> arrayList, int index) {
		return index < arrayList.size();
	}
	
	@SuppressWarnings("resource")
	public static String validNextString(String prompt)
	{
		Scanner input = new Scanner(System.in);
		System.out.print(prompt);
		return input.nextLine();			
	}
	
}