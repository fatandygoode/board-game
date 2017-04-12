package controllers;

import models.Player;
import models.Square;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Board is utilised by the menu controller to create and implement the objects required to run the game
 */
@SuppressWarnings({"WeakerAccess"})
public class Board {
    private final ArrayList<Square> squares;
    private final ArrayList<String> hareCards;
    @SuppressWarnings("CanBeFinal")
    private ArrayList<Player> players;
    private Integer turnCounter, numberOfPlayersFinished, hareCardDeck;

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
        turnCounter = 0;
        numberOfPlayersFinished = 0;
        hareCardDeck = 0;
    }

    /**
     * Getter method for the the hare card array list
     * @return the entire deck of hare cards
     */
    public ArrayList<String> getHareCards() {
        return hareCards;
    }

    /**
     * Getter method for a single hare card within the deck
     * @param index the index of the card to get
     * @return the String value (i.e. the instructions) of the hare card drawn
     */
    public String getHareCard(int index) {
        return hareCards.get(index);
    }

    /**
     * Method to return a particular square on the board
     * @param index the index of the square in the array list
     * @return the square corresponding the the index
     */
    public Square getSquare(int index) {
        return squares.get(index);
    }

    /**
     * To add players to the game
     * Pre-configured to four to speed up running the console
     * (commented out for final submission)
     */
    private void addPlayers(){
    //    players.add(new Player("John"));
    //    players.add(new Player("Brian"));
    //    players.add(new Player("Patrick"));
    //    players.add(new Player("Sean"));
    }

    /**
     *Populates the hareCards ArrayList with object of type String and gives them an initial random order
     */
    @SuppressWarnings("RedundantStringConstructorCall")
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

    /**
     *Method to create the board for the game
     *Iterates through a series of Squares and populates the squares ArrayList
     */
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

    /**
     * Method to return a human readable version of the board using a toString() method
     * @return A string version of the board
     */
    public String listSquares() {
        String list = "";
        for (int i = 1; i < squares.size()-1; i = i + 3) {
            //noinspection StringConcatenationInLoop
            list = list + i + squares.get(i).toString() + "\t\t\t" + (i+1) + squares.get(i+1) + "\t\t\t" + (i+2) + squares.get(i+2) + "\n";
        }
        return 0 + squares.get(0).toString() + "\n" + list + 64 + squares.get(64).toString();
    }

    /**
     *Method to add instances of class Player to the ArrayList players
     * @param player The name of the player to add to the game
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     *Method to remove instance of class Player from the ArrayList players
     * @param index of player to be removed
     */
    public void removePlayer(int index) {
        if ( (index >= 0) && (index < players.size() ) ) {
            players.remove(index);
        }
    }

    /**
     *Method which returns the number of players currently in the ArrayList
     * @return number of players  as an int
     */
    public int numberOfPlayers() {
        return players.size();
    }

    /**
     *Method which returns a human readable version of all players in the ArrayList players
     *@return A human readable string of the players currently in the ArrayList players
     */
    public String listPlayers() {
        StringBuilder list = new StringBuilder();
        for (int index = 0; index < players.size(); index++) {
            list.append(index).append(" - ").append(players.get(index).getPlayerName()).append("\n");
        }
        if (list.toString().equals("")) {
            return "No players";
        }
        else {
            return list.toString();
        }
    }

    /**
     *Method which return a player in the ArrayList players based upon a given index
     * @param index The index of the player to return
     * @return an instance of class Player in the ArrayList player
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * Method to return all players added to the game
     * @return All players that are added to the game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Method which increases the turnCounter variable at the end of the players turn
     * resets to 0 after the total number of players has been reached
     */
    public void increaseCounter(){
        turnCounter++; //increment turn counter by 1
        turnCounter = turnCounter % numberOfPlayers(); //reset to zero after all players have played
    }

    /**
     * Method which returns the turnCounter
     * @return turnCounter as an int
     */
    public int getTurnCounter(){
        return turnCounter;
    }

    /**
     * Method which increases the value of the hareCard ArrayList, changing which card is drawn next when
     * a hare square is landed on
     */
    public void drawNextCard() {
        hareCardDeck++;
    }

    /**
     *Method which shuffles the entire hareCard ArrayList after the "Shuffle" card has been drawn
     */
    public void shuffleDeck(){
        hareCardDeck = 0;
    }

    /**
     *Method which returns a first hare card in the ArrayList
     * @return String from the ArrayList hareCards at index number 0
     */
    public int topOfDeck(){
        return hareCardDeck;
    }

    /**
     *Method which increases the numberOfPlayersFinished whenever a player reaches the "Finished" square
     */
    public void newPlayerFinished() {
        numberOfPlayersFinished ++;
    }

    /**
     *Method which returns the number of players that have finished the game
     * @return the numberOfPlayersFinished as an int
     */
    public int getNumberOfPlayersFinished(){
        return numberOfPlayersFinished;
    }
}


