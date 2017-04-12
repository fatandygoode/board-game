package controllers;

import static utils.ScannerInput.*;
import models.Player;
import models.Square;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Create a game from the terminal and allow users to play in accordance with the rules
 * @author John Madden, Brian O'Sullivan, Patrick Kiely, Sean Mundy.
 * @version 2017-03-21
 * https://github.com/fatandygoode/board-game
 */
class MenuController {
	private Board board;
	@SuppressWarnings("CanBeFinal")
	private ArrayList<Integer> availableMoves;
	private boolean gameInProgress, turnComplete, firstLaunch;

	/**
	 * Main method to run the program
	 * @param args As required by main
	 */
	public static void main(String[] args) {
		new MenuController();
	}

	/**
	 * Constructor for objects of class MenuController
	 */
	private MenuController() {
		board = new Board();
		availableMoves = new ArrayList<>();
		firstLaunch = true;
		runMenu();
	}

	/**
	 * This method displays the pre-game menu for the application
	 * @return The user's option that will be fed through the runMenu() method
	 */
	private int mainMenu() {

		System.out.println("  Board Game Menu");
		System.out.println("---------");
		System.out.println("  1) Add a player");
		System.out.println("  2) List all players");
		System.out.println("  3) Edit a player name");
		System.out.println("  4) Remove a player");
		System.out.println("---------");
		if (gameInProgress) {
			System.out.println("  5) Resume the game");
		} else {
			System.out.println("  5) Start a new game");
		}
		System.out.println("---------");
		System.out.println("  6) Save Game (XML)");
		System.out.println("  7) Load Game (XML)");
		System.out.println("---------");
		System.out.println("  8) Display game rules");
		System.out.println("---------");
		System.out.println("  0) Exit");
		return validNextInt("==>> ");
	}

	/**
	 * This method controls the loop for the 'out of game' functions
	 */
	private void runMenu() {
		int mainMenuOption = mainMenu();//puts user's choice from the main menu through the switch loop
		boolean skipMainMenu = false;//keep displaying main menu until user starts the game
		while (mainMenuOption != 0) {// 0 to exit and terminate the program
			switch (mainMenuOption) {
				case 1: //add  a player to the game
					if (gameInProgress) { //can't be done after the game has begun
						System.out.println("Error, cannot perform this function after the game has started");
					} else {
						if (board.numberOfPlayers() < 6) { //can't add more than 6 players
							String playerName = validNextString("Enter the player name...");
							board.addPlayer(new Player(playerName));
							System.out.println(playerName + " added to the game");
						}
						else {
							System.out.println("Maximum 6 players, please remove a player first");
						}
					}
					break;
				case 2: //list all players added to the game
					if (board.numberOfPlayers() > 0) {
						System.out.println(board.listPlayers());
						System.out.println("Total: " + board.numberOfPlayers());
					} else {
						System.out.println("No players added");
					}
					break;
				case 3: //edit a player's name
					if (board.numberOfPlayers() > 0) {
						System.out.println(board.listPlayers());
						int index = validNextInt("Please enter the index for the player you wish to edit: ");
						if (index >= 0 && index < board.numberOfPlayers()) {
							Player playerToUpdate = board.getPlayer(index);
							String newName = validNextString("Enter the new name for " + playerToUpdate.getPlayerName() + ": ");
							playerToUpdate.setPlayerName(newName);
						} else {
							System.out.println("Enter a valid index");
						}
					} else {
						System.out.println("No players added");
					}
					break;
				case 4: //remove a player from the game
					if (gameInProgress) {
						System.out.println("Error, cannot perform this function after the game has started");
					} else {
						if (board.numberOfPlayers() > 0) {
							System.out.println(board.listPlayers());
							int index = validNextInt("Please enter the index of the player you wish to delete: ");
							if (index >= 0 && index < board.numberOfPlayers()) {
								System.out.println(board.getPlayer(index).getPlayerName() + " removed from the game");
								board.removePlayer(index);
							} else {
								System.out.println("Enter a valid index");
							}
						} else {
							System.out.println("No players added");
						}
					}
					break;
				case 5: //begin(resume) the game
					if (board.numberOfPlayers() > 1) {//need at least 2 players
						gameInProgress = true;//change to main menu options
						skipMainMenu = true;//stop displaying main menu when running the game
						runGame();//starts the game
					} else {
						System.out.println("You need to add between 2 and 6 players to begin the game");
					}
					break;
				case 6: //save the current game
					if(gameInProgress){
						try {
							save();
							System.out.println("The game has been saved!");
						}
						catch (Exception e) {
							System.err.println("Error writing to file: " + e);
						}
					}
					else {
					System.out.println("Cannot save before starting the game");
					}
					break;
				case 7: //load a previously saved game
					try {
						load();
						System.out.println("Loading game...");
						firstLaunch = false;
						gameInProgress = true;
					}
					catch (Exception e) {
						System.err.println("Error reading from file: " + e);
					}
					break;
				case 8:
					gameRules();
					break;
				default:
					System.out.println("Invalid option entered: " + mainMenuOption);
					break;
			}
			pause();
			if (!skipMainMenu) { //display the main menu again if not starting game
				mainMenuOption = mainMenu();
			}
		}
		System.out.println("Exiting... bye");
		System.exit(0);//the user chose option 0, so exit the program
	}

	/**
	 * Method to run the game
	 */
	private void runGame() {
		 	if(firstLaunch) {//welcome message only on first launch (ref: http://www.ascii-code.com/ascii-art)
			   System.out.println(" _____________________________________________________________________\n");
			   System.out.println("          ,\\                                                   ");
			   System.out.println("          \\\\\\,_        <<<<<<<<<<>>>>>>>>>>>                         ");
			   System.out.println("           \\` ,\\       #####################                    __   ");
			   System.out.println("      __,.-\" =__)      =====================         .,-;-;-,. /'_\\  ");
			   System.out.println("    .\"        )	       ***   WELCOME!    ***       _/_/_/_|_\\_\\) /   ");
			   System.out.println(" ,_/   ,    \\/\\_       =====================    '-<_><_><_><_>=/\'\\   ");
			   System.out.println(" \\_|    )_-\\ \\_-`      #####################        `/_/====/_/-'\\_\\ ");
			   System.out.println("    `-----` `--`       <<<<<<<<<<>>>>>>>>>>>    	 \"\"     \"\"    \"\" ");
			   System.out.println(" _____________________________________________________________________");
			   firstLaunch = false;
			   Collections.shuffle(board.getPlayers());//randomise player order at the start
			   pause();
		 	}
		Player playerToMove = board.getPlayer(board.getTurnCounter());//turn starts here
		if (playerToMove.isFinished()) {//skips a players turn if they are finished
			skipTurn();
		}
		else if(playerToMove.isSkipNextTurn()){//skips a players turn if instructed to do so as per game rules
			playerToMove.setSkipNextTurn(false);
			skipTurn();
		}
		else{//message to display player turn
			System.out.println(playerToMove.getPlayerName() + ", it's your turn");
			//runs "carry-over" moves
			if (playerToMove.getSquareCounter() > 0) {
				runSquare(playerToMove, board.getSquare(playerToMove.getPlayerBoardPosition()), 0, 0);
			}
		}
		String message = "";//create a 'show your carrots' message when applicable
		for(Player player : board.getPlayers()){
			if(player.getMessageCounter() > 1){
				//noinspection StringConcatenationInLoop
				message = message + player.showMessage();
				player.setMessageCounter(player.getMessageCounter() - 1);
			}
		}
		Square currentSquare = board.getSquare(playerToMove.getPlayerBoardPosition());
		displayStatus(playerToMove, currentSquare, message);//helper method to display status message
		makeMove(playerToMove); //starts next method to continue
	}

	/**
	 * Method to display the in game menu options
	 * @return the option entered by the player to be fed to the runGame() method
	 */
	private int gameMenu() {
		System.out.println("  In Game Menu");
		System.out.println("===============");
		System.out.println("  1) Move forward to a new square");
		System.out.println("  2) Draw or pay carrots");
		System.out.println("  3) Move backwards to a tortoise square");
		System.out.println("===============");
		System.out.println("  4) Display the full board");
		System.out.println("  5) Check available moves");
		System.out.println("  6) Display player status");
		System.out.println("===============");
		System.out.println("  0) Return to main menu");
		return validNextInt("===>>");
	}

	/**
	 * Method to display useful information for the player to move
	 * @param playerToMove The player whose turn it is
	 * @param currentSquare The square the player is starting from
	 * @param message The 'show your carrots' message as per the hare card
	 */
	private void displayStatus(Player playerToMove, Square currentSquare, String message) {
		System.out.println(playerToMove.toString());
		System.out.println("Current Square: " + currentSquare.getSquareType());
		System.out.println("Race Position: " + getRacePosition(playerToMove));
		System.out.println(message);
		pause();
	}

	/**
	 * Method to calculate a player's position in the race
	 * @param playerToMove The player whose turn it is
	 * @return The player's position in the race
	 */
	private int getRacePosition(Player playerToMove){
		int racePosition = board.numberOfPlayers();
		for(Player player : board.getPlayers()){
			if(playerToMove.getPlayerBoardPosition() > player.getPlayerBoardPosition()){
				racePosition--;
			}
		}
		return racePosition;
	}

	/**
	 * Method to execute the move as per the player's input
	 * @param playerToMove The player whose turn it is
	 */
	private void makeMove(Player playerToMove) {
		int gameMenuOption = gameMenu();//puts user's choice through switch loop

		switch (gameMenuOption) {
			case 1:
				validSquare(playerToMove);//series of helper methods to move forwards
				break;
			case 2:
				chewCarrots(playerToMove, false);//stay on carrot square and pay or draw carrots
				break;
			case 3:
				moveBack(playerToMove, false);//move back to nearest tortoise square
				break;
			case 4:
				System.out.println(board.listSquares());//list the full board
				pause();
				makeMove(playerToMove);//back to this method when complete
				break;
			case 5:
				testMoves(playerToMove);//check which moves are available, if none - reset
				if(availableMoves.size() > 0){
				System.out.println("The following squares are available to move to: ");
					for(Integer i : availableMoves){
					System.out.println(i + "\t" + board.getSquare(i).getSquareType());
					}
				}
				else {
					reset(playerToMove);
				}
				availableMoves.clear();//clear the array list for the next player
				pause();
				makeMove(playerToMove);//back to this method when complete
			case 6:
				displayStatus(playerToMove, board.getSquare(playerToMove.getPlayerBoardPosition()), "");//re-display a player's status
				makeMove(playerToMove);//back to this method when complete
				break;
			case 0:
				runMenu();//back to main menu
				break;
			default:
				errorMessage("Invalid option entered: " + gameMenuOption, playerToMove);
				break;
		}
		nextPlayer();//method to cycle between players
	}

	/**
	 * Method to execute menu option 2. If a player is on a carrot square they can pay or draw 10 carrots in lieu of moving to a new square.
	 * @param playerToMove The player whose turn it is
	 * @param testing Tells the method that the player is only checking if the move is available, and not to execute the move
	 */
	private void chewCarrots(Player playerToMove, boolean testing) {
		int currentPosition = playerToMove.getPlayerBoardPosition();
		Square currentSquare = board.getSquare(currentPosition);
		if (currentSquare.getSquareType().equals("---Carrot---")){//only allow move on carrot square
			if(!testing){//only present options if not testing
				String input = (validNextString("Chew or draw 10 carrots? (C/D)?"));
				input = input.toUpperCase();
				switch (input) {
					case "C":
						if (playerToMove.getNumberOfCarrots() >= 10){
						playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - 10);
						System.out.println(playerToMove.getPlayerName() + " chewed 10 carrots!");
						pause();
						completeMove(playerToMove, currentSquare, currentPosition, 0, false);
						}
						else {
							errorMessage("Invalid option, 10 or more carrots required.", playerToMove);
						}
						break;
					case "D":
						playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + 10);
						System.out.println(playerToMove.getPlayerName() + " drew 10 carrots!");
						pause();
						completeMove(playerToMove, currentSquare, currentPosition, 0, false);
						break;
					default:
						errorMessage("Invalid option entered", playerToMove);
						break;
				}
			}
			else {
				completeMove(playerToMove, currentSquare, currentPosition, 0, true);
			}
		}
		else {
			if(!testing){
				errorMessage("Not on a carrot square!", playerToMove);
			}
		}
	}

	/**
	 * Method to check that the square a user has input 1) exists and 2) is ahead of them on the board
	 * @param playerToMove The player whose turn it is
	 */
	private void validSquare(Player playerToMove) {
		int positionToMoveTo = validNextInt("Where do you want to move to? Enter square number: ");
		if (positionToMoveTo < 1 || positionToMoveTo > 64) {
			errorMessage("Invalid square number!", playerToMove);
		}
		else if(positionToMoveTo < playerToMove.getPlayerBoardPosition()){
			errorMessage("Can only move forwards from this menu option!", playerToMove);
		}
		else{
			Square squareToMoveTo = board.getSquare(positionToMoveTo);
			squareUnoccupied(playerToMove, squareToMoveTo, positionToMoveTo, false);
		}
	}

	/**
	 * Method to check which moves are available to the player to move (menu option 5)
	 * Runs menu options 1, 2, and 3 with the testing variable set to true
	 * If a move is valid it passes through the helper methods, and gets added to the list in the completeMove() method
	 * @param playerToMove The player whose turn it is
	 */
	private void testMoves(Player playerToMove){
		int currentPosition = playerToMove.getPlayerBoardPosition();
		for(int positionToMoveTo = currentPosition + 1; positionToMoveTo < 65; positionToMoveTo++){//generate all squares ahead of the player and test if they can be moved to
			squareUnoccupied(playerToMove, board.getSquare(positionToMoveTo), positionToMoveTo, true);
		}
		moveBack(playerToMove, true);//test the tortoise square move
		chewCarrots(playerToMove, true);//test the carrot square move
	}

	/**
	 * Method to move back to the nearest tortoise square
	 * @param playerToMove The player whose turn it is
	 * @param testing Tells the method that the player is only checking if the move is available, and not to execute the move
	 */
	private void moveBack(Player playerToMove, boolean testing){
		int i = 11;//11 is the first tortoise square on the board
		int positionToMoveTo = 11;
		if(playerToMove.getPlayerBoardPosition() > 11){
			while(i < playerToMove.getPlayerBoardPosition()){
				if(board.getSquare(i).getSquareType().equals("--Tortoise--")){
					positionToMoveTo = i;
				}
				i++;
			}
			Square squareToMoveTo = board.getSquare(positionToMoveTo);
			squareUnoccupied(playerToMove, squareToMoveTo, positionToMoveTo, testing);
		}
		else{
			if(!testing){
				errorMessage("Invalid option. No tortoise squares behind you!", playerToMove);
			}
		}
	}

	/**
	 * Method to check whether or the square to move to as input by the player is occupied
	 * @param playerToMove The player whose turn it is
	 * @param squareToMoveTo The square the player wants to move to as per their input
	 * @param positionToMoveTo The position the player wants to move to as per their input
	 * @param testing Tells the method that the player is only checking if the move is available (if set to true), and not to execute the move
	 */
	private void squareUnoccupied(Player playerToMove, Square squareToMoveTo, int positionToMoveTo, boolean testing){
		if(squareToMoveTo.isOccupied()){
			if(!testing){
				errorMessage("Invalid move! Square is occupied!", playerToMove);
			}
		}
		else {
			enoughCarrots(playerToMove, squareToMoveTo, positionToMoveTo, testing);
		}
	}

	/**
	 * Method to check whether or not a player has enough carrots to move to the square they've input
	 * @param playerToMove The player whose turn it is
	 * @param squareToMoveTo The square the player wants to move to as per their input
	 * @param positionToMoveTo The position the player wants to move to as per their input
	 * @param testing Tells the method that the player is only checking if the move is available, and not to execute the move
	 */
	private void enoughCarrots(Player playerToMove, Square squareToMoveTo, int positionToMoveTo, boolean testing) {
		int i = positionToMoveTo - playerToMove.getPlayerBoardPosition();
		int carrotCost = 0;
		while (i > 0) {
			carrotCost = carrotCost + i;
			i--;
		}
		if (carrotCost <= playerToMove.getNumberOfCarrots()) {
			validMove(playerToMove, squareToMoveTo, positionToMoveTo, carrotCost, testing);
		}
		else {
			if(!testing){
				errorMessage("You do not have enough carrots for this move", playerToMove);
			}
		}
	}

	/**
	 * Method to check if the move a player has input is in accordance with game rules
	 * @param playerToMove The player whose turn it is
	 * @param squareToMoveTo The square the player wants to move to as per their input
	 * @param positionToMoveTo The position the player wants to move to as per their input
	 * @param carrotCost The number of carrots the move as input by the player will require
	 * @param testing Tells the method that the player is only checking if the move is available, and not to execute the move
	 */
	private void validMove(Player playerToMove, Square squareToMoveTo, int positionToMoveTo, int carrotCost, boolean testing) {
		switch (squareToMoveTo.getSquareType()) {
			case "--Lettuce---": //only if # of lettuces = 0
				if (playerToMove.getNumberOfLettuces() > 0) {
					completeMove(playerToMove, squareToMoveTo, positionToMoveTo, carrotCost, testing);
				} else {
					if(!testing){
						errorMessage("Can't move to a lettuce square unless number of lettuces is non-zero", playerToMove);
					}
				}
				break;
			case "---Finish---"://only if # of lettuces = 0 & number of carrots is sufficiently low as per game rules
				if (playerToMove.getNumberOfCarrots() - carrotCost <= 10 * (1 + board.getNumberOfPlayersFinished()) && playerToMove.getNumberOfLettuces() == 0) {
					playerToMove.setFinished();//player set to finished so that their turn will be skipped
					board.newPlayerFinished();//increment # of players finished, changes finish requirement for the next player
					completeMove(playerToMove, squareToMoveTo, positionToMoveTo, carrotCost, testing);
				} else {
					if(!testing){
						errorMessage("Invalid move! Lose any remaining lettuces and get you number of carrots to below " + 10 * (1 + board.getNumberOfPlayersFinished()) + " to finish.", playerToMove);
					}
				}
				break;
			case "--Tortoise--"://doesn't allow a player to move forward to a tortoise square
				if(positionToMoveTo < playerToMove.getPlayerBoardPosition()){
					completeMove(playerToMove, squareToMoveTo, positionToMoveTo, carrotCost, testing);
				}
				else{
					if(!testing){
						errorMessage("Invalid move! Can only move backwards to a tortoise square!", playerToMove);
					}
				}
				break;
			default:
				completeMove(playerToMove, squareToMoveTo, positionToMoveTo, carrotCost, testing);
				break;
		}
	}

	/**
	 * Method to execute a player's move.
	 * Updates the attributes of the relevant player and squares, invokes methods to run any post-move instructions
	 * If testing, will return the move to the list of available moves (menu option 5)
	 * @param playerToMove The player whose turn it is
	 * @param squareToMoveTo The square the player wants to move to as per their input
	 * @param positionToMoveTo The position the player wants to move to as per their input
	 * @param carrotCost The number of carrots the move as input by the player will require
	 * @param testing Tells the method that the player is only checking if the move is available, and not to execute the move
	 */
	private void completeMove(Player playerToMove, Square squareToMoveTo, int positionToMoveTo, int carrotCost, boolean testing) {
		if (!testing) {//execute if not testing
			String input;
			if (squareToMoveTo.getSquareType().equals("--Tortoise--")) {
				input = validNextString("Do you want to move to square #" + positionToMoveTo + "? (Y/N)");
			} else if (squareToMoveTo.getSquareType().equals("---Carrot---") && carrotCost == 0) {
				input = "y";
			} else {
				input = validNextString("This will use " + carrotCost + " carrots. Do you wish to continue? (Y/N)\n");
			}
			if (input.equals("Y") || input.equals("y")) {
				Square squareToMoveFrom = board.getSquare(playerToMove.getPlayerBoardPosition());
				int spacesMoved = Math.abs(positionToMoveTo - playerToMove.getPlayerBoardPosition());
				squareToMoveFrom.setOccupied(false);//old square now unoccupied
				if (squareToMoveTo != board.getSquare(64)) {
					squareToMoveTo.setOccupied(true);//new square now occupied (disabled for finish square)
				}
				playerToMove.setPlayerBoardPosition(positionToMoveTo);//update board position
				playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - carrotCost);//update number of carrots
				playerToMove.setSquareCounter(0);//number of turns a player has been at current square
				turnComplete = true;//invokes nextPlayer method
				if (playerToMove.isFinished()) {
					System.out.println(playerToMove.getPlayerName() + " finished the game!");
				} else {
					System.out.println(playerToMove.getPlayerName() + " moved " + spacesMoved + " spaces to square #" + playerToMove.getPlayerBoardPosition());
					displayStatus(playerToMove, squareToMoveTo, "");
				}
				runSquare(playerToMove, squareToMoveTo, spacesMoved, carrotCost);//run post-move instructions
			}
			else {
				makeMove(playerToMove);//back to in game menu if move is not confirmed
			}
		}
		else {
			availableMoves.add(positionToMoveTo);//populates the list returned in menu option 5
		}
	}

	/**
	 * Method to invoke post-move instructions as per game rules
	 * @param playerToMove The player whose turn it is
	 * @param squareToMoveTo The square the player wants to move to as per their input
	 * @param spacesMoved The number of spaces between the player's old square and their new one
	 * @param carrotCost The number of carrots the move as input by the player will require
	 */
	private void runSquare(Player playerToMove, Square squareToMoveTo, int spacesMoved, int carrotCost){
		switch(squareToMoveTo.getSquareType()){
			case "---Carrot---":
				System.out.println("All carrot, no stick");
				break;
			case "----Hare----":
				runHare(playerToMove, carrotCost);
				break;
			case "--Lettuce---":
				runLettuce(playerToMove);
				break;
			case "--#(1,5,6)--":
				runNumber(1, playerToMove);
				runNumber(5, playerToMove);
				runNumber(6, playerToMove);
				break;
			case "----#(2)----":
				runNumber(2, playerToMove);
				break;
			case "----#(3)----":
				runNumber(3, playerToMove);
				break;
			case "----#(4)----":
				runNumber(4, playerToMove);
				break;
			case "--Tortoise--":
				runTortoise(playerToMove, spacesMoved);
				break;
			default:
				break;
		}
	}

	/**
	 * Run the actions to be taken after landing on a number square
	 * @param numberSquareType The type of number square from 1-6
	 * @param playerToMove The player whose turn it is
	 */
	private void runNumber(int numberSquareType, Player playerToMove) {
		int squareCounter = playerToMove.getSquareCounter();
		if(squareCounter == 0){//display message on first landing
			System.out.println("Crunching the numbers");
		}
		else{//gift carrots to the player on their next turn in accordance with game rules
			if(numberSquareType == getRacePosition(playerToMove)){
				int numberOfCarrots = playerToMove.getNumberOfCarrots() + 10 * numberSquareType;
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				System.out.println(playerToMove.getPlayerName() + " gained " + 10 * numberSquareType + " carrots!");
			}
		}
		squareCounter++;
		playerToMove.setSquareCounter(squareCounter);
	}

	/**
	 * Run the actions to be taken after landing on a lettuce square
	 * @param playerToMove The player whose turn it is
	 */
	private void runLettuce(Player playerToMove) {
		int squareCounter = playerToMove.getSquareCounter();
		squareCounter++;
		playerToMove.setSquareCounter(squareCounter);
		switch (squareCounter){
			case 1: //message on first landing
				System.out.println("Chewing a lettuce...");
				break;
			case 2: //message on next turn, player can't move, moves to next player
				playerToMove.chewALettuce();
				System.out.println("Hares eat their shit, wait another turn");
				skipTurn();
				break;
			case 3: //finishes the move as per game rules
				int numberOfCarrots = playerToMove.getNumberOfCarrots() + 10 * getRacePosition(playerToMove);
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				System.out.println(playerToMove.getPlayerName() + " gained " + 10 * getRacePosition(playerToMove) + " carrots! " + playerToMove.getNumberOfLettuces() + " lettuces left !");
				pause();
				break;
			default:
				break;
		}
	}

	/**
	 * Run the actions to be taken after moving to a tortoise square
	 * @param playerToMove The player whose turn it is
	 * @param spacesMoved The number of spaces between the player's old square and their new one
	 */
	private void runTortoise(Player playerToMove, int spacesMoved){
		playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + spacesMoved * 10);
		System.out.println(playerToMove.getPlayerName() + " has gained " + spacesMoved * 10 + " carrots!");
	}

	/**
	 * Run the actions to be taken after moving to a hare square
	 * @param playerToMove The player whose turn it is
	 * @param carrotCost The number of carrots the move as input by the player will require
	 */
	private void runHare(Player playerToMove, int carrotCost) {
		int numberOfCarrots = playerToMove.getNumberOfCarrots();
		int racePosition = getRacePosition(playerToMove);
		String nextHareCard = board.getHareCard(board.topOfDeck());//draws top card on deck
		System.out.println("Draw a hare card!");
		pause();
		System.out.println(nextHareCard);
		pause();
		board.drawNextCard();//moves to next card in deck for next time
		
		switch(nextHareCard) {
			case "Give 10 carrots to each player lying behind you in the race (if any).\nIf you haven't enough carrots, give them five each; if still not possible, one each.\nA player who doesn't want extra carrots may discard them":
				int carrotsToGive = numberOfCarrots / (board.numberOfPlayers() - 1);
				if(carrotsToGive >= 10){
					carrotsToGive = 10;
				}
				else if(carrotsToGive >= 5 && carrotsToGive < 10){
					carrotsToGive = 5;
				}
				else if (carrotsToGive >= 1 && carrotsToGive < 5){
					carrotsToGive = 1;
				}
				else {
					reset(playerToMove);
				}
				int i = 0;
				int moveCounter = 0;
				while(i < board.numberOfPlayers()) {
					if(i != board.getTurnCounter()){
						Player playerToUpdate = board.getPlayer(i);
						if(getRacePosition(playerToUpdate) > getRacePosition(playerToMove)){
							String input = validNextString(playerToUpdate.getPlayerName() + ", do you want to accept " + carrotsToGive + " carrots? (Y/N)");
							if (input.equals("Y") || input.equals("y")) {
								playerToUpdate.setNumberOfCarrots(carrotsToGive + playerToUpdate.getNumberOfCarrots());
								System.out.println(playerToUpdate.getPlayerName() + " gained " + carrotsToGive + " carrots!\n");
								moveCounter++;
							}
						}
					}
					i++;
				}
				playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - (moveCounter * carrotsToGive));
				System.out.println(playerToMove.getPlayerName() + " gave away " + (moveCounter * carrotsToGive) + " carrots!");
				break;
			case "If there are more players behind you than in front of you, miss a turn. If not, play again. If equal, of course play again.":
				if(board.numberOfPlayers() / racePosition >= 2){
					playerToMove.setSkipNextTurn(true);
					System.out.println("Miss the next turn!");
				}
				else {
					System.out.println("Playing again...\n");
					turnComplete = false;
					makeMove(playerToMove);
				}
				break;
			case "Restore your carrot holding to exactly 65!":
				playerToMove.setNumberOfCarrots(65);
				System.out.println(playerToMove.getPlayerName() + " restored their carrot holding to 65!");
				break;
			case "Draw 10 Carrots for each lettuce you still hold. If you have none left, miss a turn.":
				if(playerToMove.getNumberOfLettuces() == 0){
					playerToMove.setSkipNextTurn(true);
					System.out.println("Miss the next turn!");
				}
				else {
					numberOfCarrots = numberOfCarrots + 10 * playerToMove.getNumberOfLettuces();
					playerToMove.setNumberOfCarrots(numberOfCarrots);
					System.out.println(playerToMove.getPlayerName() + " gained " + 10 * playerToMove.getNumberOfLettuces() + " carrots!");
				}
				break;
			case "Free Ride! Your Last turn costs nothing; retrieve the carrots you paid to reach this square":
				playerToMove.setNumberOfCarrots(numberOfCarrots + carrotCost);
				System.out.println(playerToMove.getPlayerName() + " regained " + carrotCost + " carrots!");
				break;
			case "Lose half your carrots! If an odd number, keep the odd one.":
				System.out.print(playerToMove.getPlayerName() + " lost " + (numberOfCarrots / 2) + " carrots!\n");
				numberOfCarrots = numberOfCarrots / 2 + numberOfCarrots % 2;
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				break;
			case "Show us your carrots! Show your carrots so everyone knows how many you have left":
				System.out.println(playerToMove.showMessage());
				playerToMove.setMessageCounter(board.numberOfPlayers());
				break;
			case "Shuffle the hare cards and receive from each player 1 carrot for doing so":
				System.out.print("Shuffling the deck!\n");
				Collections.shuffle(board.getHareCards());
				board.shuffleDeck();
				int j = 0;
				int moveCounter2 = 0;
				while(j < board.numberOfPlayers()) {
					if(j != board.getTurnCounter()){
						Player playerToUpdate = board.getPlayer(j);
						if(playerToUpdate.getNumberOfCarrots() > 0){
							playerToUpdate.setNumberOfCarrots(playerToUpdate.getNumberOfCarrots() - 1);
							System.out.println(playerToUpdate.getPlayerName() + " gave 1 carrot!");
							moveCounter2++;
						}
					}
					j++;
				}
				playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + board.numberOfPlayers() - 1);
				System.out.println(playerToMove.getPlayerName() + " gained " + moveCounter2 + " carrots!");
				break;
			default:
				break;
		}
	}

	/**
	 * Resets a player's position and carrot holding
	 * @param playerToMove The player whose turn it is
	 */
	private void reset(Player playerToMove) {
		playerToMove.setNumberOfCarrots(65);
		playerToMove.setPlayerBoardPosition(0);
		errorMessage("No available moves! Returning to the start...", playerToMove);
	}

	/**
	 * Inserts a pause in the program
	 */
	private void pause(){
		validNextString("Press any key to continue...\n");
	}

	/**
	 * Method to display error messages, and return to in-game menu
	 * @param error The error message to display
	 * @param playerToMove The player whose turn it is
	 */
	private void errorMessage(String error, Player playerToMove) {
		System.out.println(error);
		pause();
		makeMove(playerToMove);
	}

	/**
	 * Method to move on to the next player after a turn has been finished
	 */
	private void nextPlayer() {
		if (turnComplete) {
			board.increaseCounter();
			turnComplete = false;//back to false for the next player
		}
		pause();
		runGame();
	}

	/**
	 * Skips a player's turn
	 */
	private void skipTurn() {
		turnComplete = true;
		nextPlayer();
	}

	/**
	 * Method to save the current game
	 * @throws Exception To throw any exception
	 */
	private void save() throws Exception
	{
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("boardgame.xml"));
		out.writeObject(board);
		out.close();
	}

	/**
	 * Method to load a previously saved game
	 * @throws Exception To throw any exception
	 */
	private void load() throws Exception
	{
		XStream xstream = new XStream(new DomDriver());
		ObjectInputStream is = xstream.createObjectInputStream(new FileReader("boardgame.xml"));
		board = (Board) is.readObject();
		is.close();
	}

	private void gameRules(){
		System.out.println("------------------");
		System.out.println(" A game of skill based on a classic theme");
		System.out.println("------------------");
		System.out.println(" Number of players : 2 to 6\n");
		System.out.println(" Playing time  : 45-60 minutes");
		System.out.println(" Types of card  : 6 race cards, 15 hare cares, 18 lettuce cards, and carrot cards in denominations of 1, 5, 10, 15, 30, 60. ");

		System.out.println("------------------");
		System.out.println(" Introduction");
		System.out.println("------------------");
		System.out.println(" Hare & Tortoise is a race game with a difference.\n");
		System.out.println(" You don't move round the track by rolling dice and obeying instructions," );
		System.out.println(" but by spending carrots and spending them wisely . In this game you can always move as far as you like");
		System.out.println(" - so long as you have enough carrots to pay for it.\n");
		System.out.println(" (You start with 65 and earn more by landing on certain square.)\n");
		System.out.println("------------------");
		System.out.println(" The catch is the further you move the more you pay!");
		System.out.println("------------------");
		System.out.println(" So players who hare ahead too fast, run out of carrots first and the lose valuable time to get them back \n");
		System.out.println(" Meanwhile, those who plod along like tortoises have so many carrots left that they have a good chance");
		System.out.println(" of overtaking the hares.");
		System.out.println("------------------");
		System.out.println(" The skill of the game lies in choosing the right square to land on ");
		System.out.println(" and in playing hare or tortoise depending on your position\n");
		System.out.println(" The fun of it lies in changing other runner's positions by overtaking them.");
		System.out.println("  - or even moving backwards!\n");
		System.out.println(" Sometimes it's right to leap ahead. Sometime it's best to lag behind\n");
		System.out.println(" But all the time it's down to YOU to make the right decision\n");
		System.out.println("------------------");
		System.out.println(" Off you go. May the best animal win! ");
		System.out.println("------------------");
		System.out.println(" Getting Ready");
		System.out.println("------------------");
		System.out.println(" Choose a runner each and place it on the board at START.");
		System.out.println(" Deal carrot cards to each player as follows : one 30, one 15, one 10, two 5's - total 65 carrots.\n");
		System.out.println(" During play, hold you carrot cards so that no-one knows exactly how many you have left.");
		System.out.println(" Each player also takes three lettuce cards and a race card. The race card contains useful information");
		System.out.println(" and is for reference only.");
		System.out.println(" Separate the undealt carrot cards and place them on the board in the numbered spaces - the 'carrot patch'.");
		System.out.println(" Throughout play, all carrot payments are made into this patch.");
		System.out.println("------------------");
		System.out.println(" Object of the game");
		System.out.println("------------------");
		System.out.println(" To get your runner home first. But you must;");
		System.out.println(" Get rid of all your lettuces on the way round ( This is done by landing on lettuce squares");
		System.out.println(" Not have too many carrot left over when you reach home.");
		System.out.println(" The first home is allowed up to 10 unused carrots, second 20, the third 30, and so on) ");
		System.out.println("------------------");
		System.out.println(" How to move");
		System.out.println("------------------");
		System.out.println(" You may move your runner forwards to any unoccupied square except the tortoise square.\n");
		System.out.println(" It can be any distance as long as you have enough carrots to pay for the move");
		System.out.println(" The cost is shown on the race.");
		System.out.println(" Later in the game you may move backwards instead of forwards, only to a tortoise square.\n");
		System.out.println(" Moving backwards costs nothing - instead, it is a way of earning carrots.");
	}
}