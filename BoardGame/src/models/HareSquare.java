package models;

import java.util.Random;

public class HareSquare extends Square 
{

	//Instance Fields
	private int hareCard;

	/**
     * Constructor for objects of class HareSquare
     */
	public HareSquare (String type, int position)
	{
		super(type, position);

		Random rand = new Random(); // Retrieved from http://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
		hareCard = rand.nextInt(8) + 1; //Must reference correctly
	}

	private void hareCard() //change to switch loop!! JM
	{
		if (hareCard == 1)
		{
			//"Free ride"
			//Return the ammount of carrots you paid to reach this square
		}

		if (hareCard == 2)
		{
			//Give 10 carrots to each player lying behind you in the race
			//If not possibly give 5
			//If not possible give 1
			//Receiving players may discard carrots if they wish
		}

		if (hareCard == 3)
		{
			//Show us your carrots
			//Players carrots are revealed to every other player
		}

		if (hareCard == 4)
		{
			//Shuffle the Hare Cards and receive 1 carrot from each player
			//Deduct one carrot from every other player and update current players total
		}

		if (hareCard == 5)
		{
			//Lose half your carrots
			//Iff odd result keep 1
		}

		if (hareCard == 6)
		{
			//Restore carrots to 65
			//Update players carrots
		}

		if (hareCard == 7)
		{
			//Draw 10 carrots for each lettuce you still hold
			//Check for ammount of lettuce, multiply n x 10
			//if n = 0 miss turn
		}

		if (hareCard == 8)
		{
			//If there are more players behind you than infront of you miss turn
			//if not play again
			//Get position of other players. if size behind > size ahead miss turn
			//if not play again
		}
	}

}
