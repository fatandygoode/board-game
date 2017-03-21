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
	
	@SuppressWarnings("resource")
	public static String validNextString(String prompt)
	{
		Scanner input = new Scanner(System.in);
		
		do
		{
			try
			{
				System.out.print(prompt);
				return input.nextLine();
			}
			
			catch (Exception e)
			{ 
				input.nextLine(); //swallows the buffer contents
				System.err.println("\tEnter a number please.");
			}
		}
		while (true);
	}
}