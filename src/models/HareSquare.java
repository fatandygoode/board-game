package models;

import java.util.Arrays;
import java.util.Collections;
 
public class HareSquare extends Square {
 
    //Instance Fields
    private int hareCard;
 
    //Constructor

    public HareSquare (String squareType)
    {
        super(squareType);
         
        String[] hareCardArray =  {"Free Ride", "Free Ride", "Draw 10 Carrots for each lettuce you still hold", 
                "Draw 10 Carrots for each lettuce you still hold", "Give 10 carrots to each player lying behind you in the race(if any)", 
                "Give 10 carrots to each player lying behind you in the race(if any)" , "Restore your carrot holding to exactly 65" , 
                "Restore your carrot holding to exactly 65" , "Lose half your carrots" , "Lose half your carrots" , "Show us your carrots!", 
                "Show us your carrots!", "If there are more players behind you than in front of you, miss a turn. If not, play again.", 
                "If there are more players behind you than in front of you, miss a turn. If not, play again.", 
                "Shuffle The heare Cards and receive from each player 1 carrot for doing so"};
        Collections.shuffle(Arrays.asList(hareCardArray));
    }
 
    private void hareCard()
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
