package utils;

import java.util.Scanner;

/**
 *
 */
public class ScannerInput {

	/**
	 *
	 * @param prompt
	 * @return
	 */
	@SuppressWarnings("resource")
	public static int validNextInt(String prompt) {
		Scanner input = new Scanner(System.in);
		do {
			try {
				System.out.print(prompt);
				return Integer.parseInt( input.next() );
			}
			catch (NumberFormatException e) {
				System.err.println("\tEnter a valid number please.");
			}
		}
		while (true);
	}

	/**
	 *
	 * @param prompt
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String validNextString(String prompt) {
		Scanner input = new Scanner(System.in);
		System.out.print(prompt);
		return input.nextLine();			
	}
}