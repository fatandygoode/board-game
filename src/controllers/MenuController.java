package controllers;

import static utils.ScannerInput.*;

import models.Player;
import models.Square;

import java.util.Collections;

/**
 * Create a game from the terminal and allow users to play in accordance with the rules
 *
 * @author John Madden, Brian O'Sullivan, Patrick Kiely, Sean Mundy.
 * @version 2017-03-21
 * https://github.com/fatandygoode/board-game
 *
 */
public class MenuController {
	private Board board;
	private int turnCounter, messageCounter, numberOfPlayersFinished, hareCardDeck;
	private boolean gameInProgress, turnComplete, showCarrots, firstLaunch;
	private String message;

	public static void main(String[] args) {
		new MenuController();
	}

	/**
	 * Constructor for objects of class MenuController
	 */
	private MenuController() {
		board = new Board();
		//firstLaunch = true;
		runMenu();
	}

	/**
	 * mainMenu() - This method displays the menu for the application,
	 * reads the menu option that the user entered and returns it.
	 *
	 * @return the users menu choice
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
			System.out.println("  5) Begin the game");
		}
		System.out.println("---------");
		System.out.println("  0) Exit");

		return validNextInt("==>> ");
	}

	/**
	 * This is the method that controls the loop.
	 */
	private void runMenu() {
		int mainMenuOption = mainMenu();//puts user's choice from the main menu through the switch loop
		boolean skipMainMenu = false;//keep displaying main menu until user starts the game

		while (mainMenuOption != 0) {// 0 to exit
			switch (mainMenuOption) {
				case 1:    //add player
					if (gameInProgress) {
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
				case 2: //list players
					if (board.numberOfPlayers() > 0) {
						System.out.println(board.listPlayers());
						System.out.println("Total: " + board.numberOfPlayers());
					} else {
						System.out.println("No players added");
					}
					break;
				case 3: //edit player name
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
				case 4: //remove player
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
		/*	commented out to speed up console, uncomment when finished
		 	if(firstLaunch) {//welcome message only on first launch
			System.out.println("<<=====>>");
			System.out.println("Welcome");
			System.out.println("<<=====>>");
			firstLaunch = false;
			pause();
		}*/

		//turn starts here
		Player playerToMove = board.getPlayer(turnCounter);

		if (playerToMove.isFinished()) {
			skipTurn();
		}
		else if(playerToMove.isSkipNextTurn()){
			playerToMove.setSkipNextTurn(false);
			skipTurn();
		}
		else{ //display status
			System.out.println(playerToMove.getPlayerName() + ", it's your turn");
			//carry-over moves
			if (playerToMove.getSquareCounter() > 0) {
				runSquare(playerToMove, board.getSquare(playerToMove.getPlayerBoardPosition()), 0, 0);
			}
		}
		Square currentSquare = board.getSquare(playerToMove.getPlayerBoardPosition());
		displayStatus(playerToMove, currentSquare);
		makeMove(playerToMove); //starts next method to continue
	}

	/**
	 * Method to display the options available to the current player
	 */
	private int gameMenu() {
		System.out.println("  In Game Menu");
		System.out.println("===============");
		System.out.println("  1) Move forward to a new square");
		System.out.println("  2) Draw or pay carrots");
		System.out.println("  3) Move backwards to a tortoise square");
		System.out.println("===============");
		System.out.println("  4) Display the full board");
		System.out.println("  5) Show available moves");//auto show if none are available (to do)
		System.out.println("===============");
		System.out.println("  0) Return to main menu");

		return validNextInt("===>>");
	}

	private void displayStatus(Player playerToMove, Square currentSquare) {
		System.out.println(playerToMove.toString());
		System.out.println("Current Square: " + currentSquare.getSquareType());
		System.out.println("Race Position: " + getRacePosition(playerToMove));
		if(messageCounter >= board.numberOfPlayers()){
			showCarrots = false;
		}
		if(showCarrots){
				System.out.println(message);
				messageCounter++;
			}
		pause();
	}

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
	 * Method to execute the move as directed by the player
	 */
	private void makeMove(Player playerToMove) {
		int gameMenuOption = gameMenu();//puts user's choice through switch loop

		switch (gameMenuOption) {
			case 1:
				validSquare(playerToMove);//series of helper methods
				break;
			case 2:
				chewCarrots(playerToMove);
				break;
			case 3:
				moveBack(playerToMove, false);
				break;
			case 4:
				System.out.println(board.listSquares());
				break;
			case 5:
				System.out.println("The following squares are available to move to: ");
				testMoves(playerToMove);
				pause();
				makeMove(playerToMove);
			case 0:
				runMenu();
				break;
			default:
				errorMessage("Invalid option entered: " + gameMenuOption, playerToMove);
				break;
		}
		nextPlayer();
	}

	private void chewCarrots(Player playerToMove) {
		Square currentSquare = board.getSquare(playerToMove.getPlayerBoardPosition());
		if (currentSquare.getSquareType().equals("---Carrot---")) {
			String input = (validNextString("Chew or draw carrots? (C/D)?"));
			input = input.toUpperCase();
			switch (input) {
				case "C":
					playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - 10);
					turnComplete = true;
					System.out.println(playerToMove.getPlayerName() + " chewed 10 carrots!");
					pause();
					break;
				case "D":
					playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + 10);
					turnComplete = true;
					System.out.println(playerToMove.getPlayerName() + " drew 10 carrots!");
					pause();
					break;
				default:
					errorMessage("Invalid option entered", playerToMove);
					break;
			}
		}
		else {
			errorMessage("Not on a carrot square!", playerToMove);
		}
	}

	private void validSquare(Player playerToMove) {
		int positionToMoveTo = validNextInt("Where do you want to move to? Enter square number: ");
		Square squareToMoveTo = board.getSquare(positionToMoveTo);
		if (positionToMoveTo < 0 || positionToMoveTo > 64) {
			errorMessage("Invalid square number!", playerToMove);
		}
		else if(positionToMoveTo < playerToMove.getPlayerBoardPosition()){
			errorMessage("Can only move forwards from this menu option!", playerToMove);
		}
		else{
			squareUnoccupied(playerToMove, squareToMoveTo, positionToMoveTo, false);
		}
	}

	private void testMoves(Player playerToMove){
		boolean testing = true;
		int currentPosition = playerToMove.getPlayerBoardPosition();
		for(int positionToMoveTo = currentPosition + 1; positionToMoveTo < 65; positionToMoveTo++){
			squareUnoccupied(playerToMove, board.getSquare(positionToMoveTo), positionToMoveTo, testing);
		}
		moveBack(playerToMove, testing);
	}

	private void moveBack(Player playerToMove, boolean testing){
		int i = 11;
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
			case "---Finish---":
				if (playerToMove.getNumberOfCarrots() - carrotCost <= 10 * numberOfPlayersFinished && playerToMove.getNumberOfLettuces() == 0) {
					playerToMove.setFinished();
					numberOfPlayersFinished++;
					completeMove(playerToMove, squareToMoveTo, positionToMoveTo, carrotCost, testing);
				} else {
					if(!testing){
						errorMessage("Invalid move! Lose any remaining lettuces and get you number of carrots to below " + 10 * (1 + numberOfPlayersFinished) + " to finish.", playerToMove);
					}
				}
				break;
			case "--Tortoise--":
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

	private void completeMove(Player playerToMove, Square squareToMoveTo, int positionToMoveTo, int carrotCost, boolean testing){
		if(!testing){
			String input;
			if(squareToMoveTo.getSquareType().equals("--Tortoise--")){
				input = validNextString("Do you want to move to square #" + positionToMoveTo + "? (Y/N)");
			}
			else{
				input = validNextString("This will use " + carrotCost + " carrots. Do you wish to continue? (Y/N)");
			}
			if (input.equals("Y") || input.equals("y")){
				Square squareToMoveFrom = board.getSquare(playerToMove.getPlayerBoardPosition());
				int spacesMoved = Math.abs(positionToMoveTo - playerToMove.getPlayerBoardPosition());
				squareToMoveFrom.setOccupied(false);
				if(squareToMoveTo != board.getSquare(64)){
					squareToMoveTo.setOccupied(true);
				}
				playerToMove.setPlayerBoardPosition(positionToMoveTo);
				playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - carrotCost);
				playerToMove.setSquareCounter(0);
				turnComplete = true;//invokes nextPlayer method
				if(playerToMove.isFinished()){
					System.out.println(playerToMove.getPlayerName() + "finished the game!");
				}
				else{
					System.out.println(playerToMove.getPlayerName() + " moved " + spacesMoved + " spaces to square #" + playerToMove.getPlayerBoardPosition());
					displayStatus(playerToMove, squareToMoveTo);
				}
				runSquare(playerToMove, squareToMoveTo, spacesMoved, carrotCost);
			}
			else {
				makeMove(playerToMove);
			}
		}
		else{
			System.out.println(positionToMoveTo);
		}
	}

	private void runSquare(Player playerToMove, Square squareToMoveTo, int spacesMoved, int carrotCost){
		switch(squareToMoveTo.getSquareType()){
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

	private void runNumber(int numberSquareType, Player playerToMove) {
		int squareCounter = playerToMove.getSquareCounter();
		if(squareCounter == 0){
			System.out.println("Crunching the numbers");
		}
		else{
			if(numberSquareType == getRacePosition(playerToMove)){
				int numberOfCarrots = playerToMove.getNumberOfCarrots() + 10 * numberSquareType;
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				System.out.println(playerToMove.getPlayerName() + " gained " + 10 * numberSquareType + " carrots!");
			}
		}
		squareCounter++;
		playerToMove.setSquareCounter(squareCounter);
	}

	private void runLettuce(Player playerToMove) {
		int squareCounter = playerToMove.getSquareCounter();
		squareCounter++;
		playerToMove.setSquareCounter(squareCounter);
		switch (squareCounter){
			case 1:
				System.out.println("Chewing a lettuce...");
				break;
			case 2:
				playerToMove.chewALettuce();
				System.out.println("Hares eat their shit, wait another turn");
				skipTurn();
				break;
			case 3:
				int numberOfCarrots = playerToMove.getNumberOfCarrots() + 10 * getRacePosition(playerToMove);
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				System.out.println(playerToMove.getPlayerName() + " gained " + 10 * getRacePosition(playerToMove) + " carrots! " + playerToMove.getNumberOfLettuces() + " lettuces left !");
				pause();
				break;
			default:
				break;
		}
	}

	private void runTortoise(Player playerToMove, int spacesMoved){
		playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + spacesMoved * 10);
		System.out.println(playerToMove.getPlayerName() + " has gained " + spacesMoved * 10 + " carrots!");
	}

	private void runHare(Player playerToMove, int carrotCost) {
		int numberOfCarrots = playerToMove.getNumberOfCarrots();
		int racePosition = getRacePosition(playerToMove);
		String nextHareCard = board.getHareCard(hareCardDeck);
		System.out.println("Draw a hare card!");
		pause();
		System.out.println(nextHareCard);
		this.hareCardDeck++;
		
		switch(nextHareCard) {
			case "Give10":
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
					if(i != turnCounter){
						Player playerToUpdate = board.getPlayer(i);
						String input = validNextString(playerToUpdate + ", do you want to accept " + carrotsToGive + " carrots? (Y/N)");
						if (input.equals("Y") || input.equals("y")) {
							playerToUpdate.setNumberOfCarrots(carrotsToGive + playerToUpdate.getNumberOfCarrots());
							System.out.println(playerToUpdate + " gained " + carrotsToGive + " carrots!");
							moveCounter++;
						}
					}
					i++;
				}
				playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots()-(moveCounter * carrotsToGive));
				System.out.println(playerToMove.getPlayerName() + " gave away" + moveCounter * carrotsToGive + "carrots!");
				break;
			case "MissTurn":
				if(board.numberOfPlayers() / racePosition >= 2){
					playerToMove.setSkipNextTurn(true);
					System.out.println("Miss the next turn!");
				}
				break;
			case "Restore65":
				playerToMove.setNumberOfCarrots(65);
				System.out.println(playerToMove + " restored their carrot count to 65!");
				break;
			case "Draw10":
				if(playerToMove.getNumberOfLettuces() == 0){
					playerToMove.setSkipNextTurn(true);
					System.out.println("Miss the next turn!");
				}
				else {
					numberOfCarrots = numberOfCarrots + 10 * racePosition;
					playerToMove.setNumberOfCarrots(numberOfCarrots);
					System.out.println(playerToMove.getPlayerName() + " gained " + 10 * racePosition + " carrots!");
				}
				break;
			case "FreeRide":
				playerToMove.setNumberOfCarrots(numberOfCarrots + carrotCost);
				System.out.println(playerToMove.getPlayerName() + " regained " + carrotCost + " carrots!");
				break;
			case "LoseHalf":
				numberOfCarrots = numberOfCarrots / 2 + numberOfCarrots % 2;
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				System.out.print(playerToMove.getPlayerName() + "lost half their carrots!");
				break;
			case "Show":
				this.message = playerToMove.getPlayerName() + "has " + numberOfCarrots + " carrots!";
				System.out.println(message);
				showCarrots = true;
				break;
			case "Shuffle":
				System.out.print("Shuffling the deck!");
				Collections.shuffle(board.getHareCards());
				this.hareCardDeck = 0;
				break;
			default:
				break;
		}
	}

	private void reset(Player playerToMove) {
		playerToMove.setNumberOfCarrots(65);
		playerToMove.setPlayerBoardPosition(0);
		errorMessage("No available moves! Returning to the start...", playerToMove);
	}

	/**
	 * Helper method to insert a pause in the program
	 */
	private void pause(){
		validNextString("\nPress any key to continue...\n");
	}

	private void errorMessage(String error, Player playerToMove) {
		System.out.println(error);
		pause();
		makeMove(playerToMove);
	}

	/**
	 * Method to rotate the player whose turn it is
	 */
	private void nextPlayer() {
		if (turnComplete) {
			turnCounter++; //increment turn counter by 1
			turnCounter = turnCounter % board.numberOfPlayers(); //reset to zero after all players have played
			turnComplete = false;//back to false for the next player
		}
		pause();
		runGame();
	}

	private void skipTurn() {
		turnComplete = true;
		nextPlayer();
	}
}



