package controllers;

import models.Player;
import models.Square;

import java.util.ArrayList;
import java.util.Collections;

public class Board
{
    private final ArrayList<Square> squares;
    private ArrayList<Player> players;
    private final ArrayList<String> hareCards;
    /**
     * Constructor for objects of class Board
     */
    public Board() {
        squares = new ArrayList<>();
        createBoard();
        players = new ArrayList<>();
        addPlayers();//delete when finished
        hareCards = new ArrayList<>();
        createDeck();
    }

    private void createDeck() {
        int i = 0;
        while(i < 2){
            hareCards.add(new String("Give 10 carrots to each player lying behind you in the race (if any).\nIf you haven't enough carrots, give them five each; if still not possible, one each.\nA player who doesn't want extra carrots may discard them"));
            hareCards.add(new String("Restore your carrot holding to exactly 65!"));
            hareCards.add(new String("Draw 10 Carrots for each lettuce you still hold. If you have none left, miss a turn."));
            hareCards.add(new String("Free Ride! Your Last turn costs nothing; retrieve the carrots you paid to reach this square"));
            hareCards.add(new String("Lose half your carrots! If an odd number, keep the odd one."));
            hareCards.add(new String("Show us your carrots! Show your carrots so everyone knows how many you have left"));
            hareCards.add(new String("If there are more players behind you than in front of you, miss a turn. If not, play again. If equal, of course play again."));
            i++;
        }
        hareCards.add(new String("Shuffle the hare cards and receive from each player 1 carrot for doing so"));
        Collections.shuffle(hareCards);
    }

    public ArrayList<String> getHareCards() {
        return hareCards;
    }

    public String getHareCard(int index) {
        return hareCards.get(index);
    }

    private void createBoard() {
    	int i = 0;
    	while(i < 65) {
    		squares.add(new Square());
    		i++;
    	}
        
    	squares.get(0).setSquareType("----Start---");
    	squares.get(64).setSquareType("---Finish---");
        
        int[] carrot = {2,5,13,21,26,33,38,40,49,55,59,61,63};
        for(int c : carrot){
        	squares.get(c).setSquareType("---Carrot---");
        }
        int[] hare = {1,3,6,14,25,31,34,39,46,51,58,62};
        for(int h : hare) {
        	squares.get(h).setSquareType("----Hare----");
        }
        int[] lettuce = {10,22,42,57};
        for(int l : lettuce){
        	squares.get(l).setSquareType("--Lettuce---");
        }	
        int[] tortoise = {11,15,19,24,30,37,43,50,56};
        for(int t : tortoise){
        	squares.get(t).setSquareType("--Tortoise--");
        }
        int[] number156 = {7,16,32,48,60};
        for(int n156 : number156){
        	squares.get(n156).setSquareType("--#(1,5,6)--");
        }
        int[] number2 = {8,17,23,29,35,41,47,53};
        for(int n2 : number2){
        	squares.get(n2).setSquareType("----#(2)----");
        }
        int[] number3 = {4,12,20,28,36,44,52};
        for(int n3 : number3){
        	squares.get(n3).setSquareType("----#(3)----");
        }
        int[] number4 = {9,18,27,45,54};
        for(int n4 : number4){
        	squares.get(n4).setSquareType("----#(4)----");
        }
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public Square getSquare(int index) {
        return squares.get(index);
    }

    public String listSquares() {
        String list = "";
        for (int i = 1; i < squares.size()-1; i = i + 3) {
                list = list + i + squares.get(i).toString() + "\t\t\t" + (i+1) + squares.get(i+1) + "\t\t\t" + (i+2) + squares.get(i+2) + "\n";
        }
        return 0 + squares.get(0).toString() + "\n" + list + 64 + squares.get(64).toString();
    }

    private void addPlayers(){ //to help run the console quicker. delete when finished
        players.add(new Player("John"));
        players.add(new Player("Brian"));
        players.add(new Player("Patrick"));
        players.add(new Player("Sean"));
    }

    /**
     * Method to add an object of type Player to the arraylist players
     * @param player - the name of the player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Method to remove a player from the game
     * @param index - the index of the player to be removed
     */
    public void removePlayer(int index) {
        if ( (index >= 0) && (index < players.size() ) ) {
            players.remove(index);
        }
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
            list = list + index + " - " + players.get(index).getPlayerName() + "\n";
        }
        if (list.equals("")) {
            return "No players";
        }
        else {
            return list;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }
}


