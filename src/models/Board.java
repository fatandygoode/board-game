package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import models.Player;
import models.Square;

public class Board
{

	public ArrayList<Player> players;
	public ArrayList<Square> squares;
	public int numberOfPlayersFinished;
	public ArrayList<String> hareCards;

	/**
	 * Constructor for objects of class Board
	 */
	public Board() {
		players = new ArrayList<Player>();
		squares = new ArrayList<Square>(64);
		hareCards = new ArrayList<String>();
		createBoard();
		createCards();
		addPlayers();//delete when finished
	}

	private void addPlayers(){ //to help run the console quicker. delete when finished
		players.add(new Player("John"));
		players.add(new Player("Brian"));
		//players.add(new Player("Patrick"));
		//players.add(new Player("Sean"));
	}

	private void createBoard() 
	{

		int i = 0;
		while(i < 65) {
			squares.add(new Square());
			i++;
		}

		squares.get(0).setSquareType("Start");
		squares.get(64).setSquareType("Finish");

		int[] carrot = {2,5,13,21,26,33,38,40,49,55,59,61,63};
		for(int c : carrot){
			squares.get(c).setSquareType("Carrot");
		}
		int[] hare = {1,6,14,25,31,34,39,46,51,58,62};
		for(int h : hare) {
			squares.get(h).setSquareType("Hare");
		}
		int[] lettuce = {10,22,42,57};
		for(int l : lettuce){
			squares.get(l).setSquareType("Lettuce");
		}	
		int[] tortoise = {3,11,15,19,24,30,37,43,50,56};// square 3 is a hare square. changed value to test moveBack() method. Must change back
		for(int t : tortoise){
			squares.get(t).setSquareType("Tortoise");
		}
		//====================================================
		//
		// Consider extending to a number class with additional
		//	numberSquareType variable
		// *********Low priority task***************************
		//
		//====================================================
		int[] number156 = {7,16,32,48,60};
		for(int n156 : number156){
			squares.get(n156).setSquareType("Number (1,5,6)");
		}
		int[] number2 = {8,17,23,29,35,41,47,53};
		for(int n2 : number2){
			squares.get(n2).setSquareType("Number (2)");
		}
		int[] number3 = {4,12,20,28,36,44,52};
		for(int n3 : number3){
			squares.get(n3).setSquareType("Number (3)");
		}
		int[] number4 = {9,18,27,45,54};
		for(int n4 : number4)
		{
			squares.get(n4).setSquareType("Number (4)");
		}
	}

	private void createCards()
	{
		for (int i = 0; i < 2; i++)
		{
			hareCards.add("Free Ride");
			hareCards.add("Draw 10");
			hareCards.add("Give 10");
			hareCards.add("Restore carrots");
			hareCards.add("Lose half");
			hareCards.add("Show");
			hareCards.add("More players behind");
		}
		hareCards.add("Shuffle");
		Collections.shuffle(Arrays.asList(hareCards));
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


	public String listSquares() {
		String list = "";
		for (int index = 0; index < squares.size(); index++) {
			//NumberSquare squareToDisplay = getSquare(index);
			list = "\n" + list + index + squares.get(index).toString() + "\n";
		}
		return list;        
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
	public Player getPlayer(int index) {
		return players.get(index);
	}

	public Square getSquare(int boardPosition) {
		return squares.get(boardPosition);
	}


}



