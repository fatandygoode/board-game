package models;

import java.util.ArrayList;
import models.Player;

public class Board
{
	
    public ArrayList<Player> players;
    public ArrayList<Square> squares;
   
    private Square square0;
    private Square square1;

    /**
     * Constructor for objects of class Board
     */
    public Board() {
        players = new ArrayList<Player>();
        squares = new ArrayList<Square>();
        createBoard();
    }
    
    private void createBoard() {
    	square0 = new Square(0, "start");
    	squares.add(square0);
    	square1 = new Square(1, "number");
    	squares.add(square1);
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
}

