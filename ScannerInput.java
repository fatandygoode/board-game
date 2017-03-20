package utils;

import java.text.ParseException;
import java.util.Scanner;

public class ScannerInput
{
	@SuppressWarnings("resource")
	public static int validNextInt(String prompt)
	{
		Scanner input = new Scanner(System.in);
		
		do
		{
			try 
			{
				System.out.print(prompt);
				return Integer.parseInt( input.next() );
			}
			catch (NumberFormatException e) 
			{ 
				System.err.println("\tEnter a number please.");
			}
		}
		while (true);
	}
}