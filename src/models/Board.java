package models;

import java.util.ArrayList;
import models.Player;

public class Board
{
	
    public ArrayList<Player> players;
    public ArrayList<Square> squares;

    /**
     * Constructor for objects of class Board
     */
    public Board() {
        players = new ArrayList<Player>();
        squares = new ArrayList<Square>();
        createBoard();
    }
    
    private void createBoard() {
    	
    	int i = 0;
    	while(i < 65) {
            squares.add(new Square(i));
            i++;
    	}
        
    	squares.get(0).setSquareType("Start");
    	squares.get(64).setSquareType("Finish");
        
        int[] carrot = {2,5,13,21,26,33,38,40,49,55,59,61,63};
        for(int c : carrot){
        	squares.get(c).setSquareType("Carrot");
        }
        int[] hare = {1,3,6,11,14,25,31,34,39,46,51,58,62};
        for(int h : hare) {
        	squares.get(h).setSquareType("Hare");
        }
        int[] lettuce = {10,22,42,57};
        for(int l : lettuce){
        	squares.get(l).setSquareType("Lettuce");
        }
        int[] number = {4,7,8,9,12,16,17,18,20,23,27,28,29,32,35,36,41,44,45,47,48,52,53,54,60};
        for(int n : number){
        	squares.get(n).setSquareType("Number");
        }
        int[] tortoise = {15,19,24,30,37,43,50,56};
        for(int t : tortoise){
        	squares.get(t).setSquareType("Tortoise");
        }
                
        //===========TO-DO======================
        //
        //Number square types
        //
        //=====================================
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
        	list = "\n" + list + squares.get(index).toString();
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
	
	public Square getSquare(int index) {
		return squares.get(index);
    }
}

