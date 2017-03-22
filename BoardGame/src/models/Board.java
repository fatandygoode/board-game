package models;

import java.util.ArrayList;
import models.Player;

public class Board
{

	public Square square;
	public ArrayList<Player> players;
	public ArrayList<Square> squares;
	// private Square square0;
	// private Square square1;

	/**
	 * Constructor for objects of class Board
	 */
	public Board() {
		players = new ArrayList<Player>();
		squares = new ArrayList<Square>();
		createBoard();
	}

	public void createBoard() {

		int i = 0;
		String squareType = "";
		for(int squareNumber = 0; squareNumber < 66; squareNumber++)
		{
			i = squareNumber;
			if(squareNumber == 0)
			{
				squareType = "Start";
			}
			else if(squareNumber == 65)
			{
				squareType = "Finish";
			}
			else if(squareNumber == 1 || squareNumber == 3 || squareNumber == 6 || squareNumber == 14 ||
					squareNumber == 25 || squareNumber == 31 || squareNumber == 34 || squareNumber == 39
					|| squareNumber == 46 || squareNumber == 51 || squareNumber == 58 || squareNumber == 62)
			{
				squareType = "Hare";
			}
			else if(squareNumber == 2 || squareNumber == 5 || squareNumber == 13 || squareNumber == 21|| 
					squareNumber == 26 || squareNumber == 33 || squareNumber == 38 || squareNumber == 40 || 
					squareNumber == 49 || squareNumber == 55 || squareNumber == 59 || squareNumber == 61 ||
					squareNumber == 63)
			{
				squareType = "Carrot";
			}
			else if (squareNumber == 10 || squareNumber == 22 || squareNumber == 42 || squareNumber == 57)
			{
				squareType = "Lettuce";
			}
			else if(squareNumber == 11 || squareNumber == 15 || squareNumber == 19 || squareNumber == 24 || 
					squareNumber == 30 || squareNumber == 37 || squareNumber == 43 || squareNumber == 50 ||
					squareNumber == 56)
			{
				squareType = "Tortoise";
			}
			else
			{
				squareType = "Number";

			}
			squares.add(new Square(i, squareType));
		}

	}

	/**
	 * Method to add an object of type Player to the arraylist players	
	 * @param player - the name of the player to be added
	 */
	public void add(Player player) {
		players.add(player);  
	}

	/**
	 * Method to get the number of players playing the game
	 * @return the size of the array list players
	 */
	public int numberOfPlayers() {
		return players.size();
	}

	/**
	 * Method to return an indexed list of the players added to the game
	 * @return an indexed list of the players added to the game
	 */
	public String listPlayers() {
		String list = "";
		for (int index = 0; index < players.size(); index++) {
			list = "\n" + list + index + " - " + players.get(index).getPlayerName() + "\n";
		}
		if (list.equals("")) {
			return "No players";
		}
		else {
			return list;
		}        
	}

	/**
	 * Method to remove a player from the game
	 * @param index - the index of the player to be removed
	 */
	public void remove(int index) {
		if ( (index >= 0) && (index < players.size() ) ) {
			players.remove(index);
		}	
	}

	/**
	 * Method to get a player from the array list
	 * @param index - the index of the player to be returned
	 * @return the object of type Player corresponding to the given index
	 */
	public Player get(int index) {
		return players.get(index);
	}
	
    public String listSquares()
    {
        String listOfSquares = "";
        for (int index = 0; index < squares.size(); index++)
        {
            listOfSquares = listOfSquares + squares.get(index).toString() + "\n";
        }
        if (listOfSquares.equals(""))
        {
            return "No products";
        }
        else
        {
            return listOfSquares;
        }    
    }
}

