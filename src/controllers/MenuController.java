package controllers;

import static utils.ScannerInput.*;

import models.HareCard;
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
	private Board board; //creates new game
	private int turnCounter, hareCardDeck;
	private boolean gameInProgress, turnComplete, showCarrots; //firstLaunch;
	private Player playerToMove; //global variable, used in multiple methods, consider merge later and use local instead
	//private Square squareToMoveFrom, squareToMoveTo;//global variable, will be used in multiple methods, consider merge later and use local instead
	private String message;

	public static void main(String[] args) {
		new MenuController();
	}

	/**
	 * Constructor for objects of class MenuController
	 */
	private MenuController(){
		board = new Board();
		//firstLaunch = true;
		runMenu();
	}

	/**
	 * mainMenu() - This method displays the menu for the application,
	 * reads the menu option that the user entered and returns it.
	 *
	 * @return     the users menu choice
	 */
	private int mainMenu(){

		System.out.println("\nBoard Game Menu");
		System.out.println("---------");
		System.out.println("  1) Add a player");
		System.out.println("  2) List all players");
		System.out.println("  3) Edit a player name");
		System.out.println("  4) Remove a player");
		System.out.println("---------");
		if(gameInProgress){
			System.out.println("  5) Resume the game");
		}
		else{
			System.out.println("  5) Begin the game");
		}
		System.out.println("---------");
		System.out.println("  0) Exit");

		return validNextInt("==>> ");
	}

	/**
	 * This is the method that controls the loop.
	 */
	private void runMenu(){
		int mainMenuOption = mainMenu();//puts user's choice from the main menu through the switch loop
		boolean skipMainMenu = false;//keep displaying main menu until user starts the game

		while (mainMenuOption != 0){// 0 to exit
			switch (mainMenuOption){
			case 1:	//add player
				if(gameInProgress){
					System.out.println("Error, cannot perform this function after the game has started");
				}
				else{
					if(board.numberOfPlayers() < 6) { //can't add more than 6 players
						String playerName = validNextString("\nEnter the player name...\n");
						board.addPlayer(new Player(playerName));
						System.out.println("\n" + playerName + " added to the game\n");
					}
					else{
						System.out.println("\nMaximum 6 players, please remove a player first\n");
					}
				}
				break;
			case 2: //list players
				if (board.numberOfPlayers() > 0){
					System.out.println(board.listPlayers());
					System.out.println("\nTotal: " + board.numberOfPlayers() + "\n");
				}
				else{
					System.out.println("No players added");
				}
				break;

			case 3: //edit player name
					if(board.numberOfPlayers() > 0) {
						System.out.println(board.listPlayers());
						int index = validNextInt("\nPlease enter the index for the player you wish to edit: ");

						if(index >= 0 && index < board.players.size()){
							Player playerToUpdate = board.getPlayer(index);
							String newName = validNextString("\nEnter the new name for " + playerToUpdate.getPlayerName() + ": ");
							playerToUpdate.setPlayerName(newName);
						}
						else{
						System.out.println("Enter a valid index");
						}
					}
					else{
						System.out.println("No players added");
					}

				break;
			case 4: //remove player
				if(gameInProgress){
					System.out.println("Error, cannot perform this function after the game has started");
				}
				else{
					if(board.numberOfPlayers() > 0){
						System.out.println(board.listPlayers());

						int index = validNextInt("\nPlease enter the index of the player you wish to delete: ");
						if(index >= 0 && index < board.players.size()){
							System.out.println("\n" + board.getPlayer(index).getPlayerName() + " removed from the game");
							board.removePlayer(index);
						}
						else{
						System.out.println("Enter a valid index");
						}
					}
					else{
						System.out.println("No players added");
					}
				}
				break;
			case 5: //begin(resume) the game
				if(board.numberOfPlayers() > 1){//need at least 2 players

					gameInProgress = true;//change to main menu options

					skipMainMenu = true;//stop displaying main menu when running the game

					runGame();//starts the game
				}
				else{
					System.out.println("\nYou need to add between 2 and 6 players to begin the game");
				}
				break;
			default:
				System.out.println("\nInvalid option entered: " + mainMenuOption);
				break;
			}
			pause();

			if(!skipMainMenu){ //display the main menu again if not starting game
				mainMenuOption = mainMenu();
			}
		}
		System.out.println("\nExiting... bye");
		System.exit(0);//the user chose option 0, so exit the program
	}

	/**
	 * Method to run the game
	 */
	private void runGame(){
		/*	commented out to speed up console, uncomment when finished
		 	if(firstLaunch) {//welcome message only on first launch
			System.out.println("<<=====>>\n");
			System.out.println("Welcome\n");
			System.out.println("<<=====>>\n");
			firstLaunch = false;
			pause();
		}*/
		this.playerToMove = board.getPlayer(turnCounter);

		if(playerToMove.isFinished()){
			skipTurn();
		}
		else{ //display status
			System.out.println(playerToMove.getPlayerName() + ", it's your turn\n");
			//carry-over moves
			if(playerToMove.getSquareCounter() > 0) {
				runSquare(board.getSquare(playerToMove.getPlayerBoardPosition()),0);
			}
		}
		displayStatus();
		makeMove(); //starts next method to continue
	}

	private void displayStatus(){
		System.out.println(playerToMove.toString());
		System.out.println("Race Position: " + getRacePosition(playerToMove));
		}

	/**
	 * Method to display the options available to the current player
	 */
	private int gameMenu(){
		System.out.println("\nIn Game Menu");
		System.out.println("===============");
		System.out.println("  1) Move to a new square");
		System.out.println("  2) Draw or pay carrots");
		System.out.println("===============");
		System.out.println("  3) Display the full board");
		System.out.println("  4) Show available moves");//auto show if none are available (to do)
		System.out.println("===============");
		System.out.println("  0) Return to main menu");

		return validNextInt("===>>");
	}

	/**
	 * Method to execute the move as directed by the player
	 */
	private void makeMove(){
		int gameMenuOption = gameMenu();//puts user's choice through switch loop

		switch (gameMenuOption){
		case 1:
			moveSquare();
			break;
		case 2:
			chewCarrots();
			break;
		case 3:
			System.out.println(board.listSquares());
			break;
		case 0:
			runMenu();
			break;
		default:
			errorMessage("\nInvalid option entered: " + gameMenuOption);
			break;
		}
		nextPlayer();
	}

	private void chewCarrots(){
		Square currentSquare = board.getSquare(playerToMove.getPlayerBoardPosition());
		if(currentSquare.getSquareType().equals("Carrot")){
			String input = (validNextString("Chew or draw carrots? (C/D)?"));
			input = input.toUpperCase();
			switch (input){
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
					errorMessage("Invalid option entered");
					break;
			}
		}
		else{
			errorMessage("Not on a carrot square!");
		}
	}

	/**
	 * Method to rotate the player whose turn it is
	 */
	private void nextPlayer(){
		if(turnComplete){
			turnCounter++; //increment turn counter by 1
			turnCounter = turnCounter % board.players.size(); //reset to zero after all players have played
			turnComplete = false;//back to false for the next player
		}
		pause();
		runGame();
	}

	private void skipTurn(){
		turnComplete = true;
		nextPlayer();
	}

	private void moveSquare(){
		validSquare();
		//series of helper methods
	}

	/**
	 * Helper method to insert a pause in the program
	 */
	private void pause(){
		validNextString("\nPress any key to continue...\n");
	}

	private void errorMessage(String error){
		System.out.println(error);
		pause();
		makeMove();
	}

	private void validSquare(){
		int positionToMoveTo = validNextInt("\nWhere do you want to move to? Enter square number: \n");
		Square squareToMoveTo = board.getSquare(positionToMoveTo);
		String squareType = squareToMoveTo.getSquareType();
		if(positionToMoveTo < playerToMove.getPlayerBoardPosition() && squareType.equals("Tortoise")){
			moveBack();
		}
		else if(positionToMoveTo > playerToMove.getPlayerBoardPosition() && positionToMoveTo <65){
			squareUnoccupied(positionToMoveTo);
		}
		else{
			errorMessage("Invalid square number!");
		}
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

	private void moveBack() {
			int nearestTortoiseSquareNumber = 11;
			int[] tortoise = {11,15,19,24,30,37,43,50,56};
			for(int t : tortoise) {
				while(t < playerToMove.getPlayerBoardPosition()){
					nearestTortoiseSquareNumber = t;
				}
				String input = validNextString("Move back to square #" + nearestTortoiseSquareNumber + " and collect " + 10 + " carrots? (Y/N)?");
				input = input.toUpperCase();
				if(input.equals("Y")) {
					playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + 10 * (playerToMove.getPlayerBoardPosition() - nearestTortoiseSquareNumber));
					playerToMove.setPlayerBoardPosition(nearestTortoiseSquareNumber);
					displayStatus();
					turnComplete = true;
				}
				else{
					makeMove();
				}
			}
		}

	private void squareUnoccupied(int positionToMoveTo){
		Square squareToMoveTo = board.getSquare(positionToMoveTo);
		if(squareToMoveTo.isOccupied()){
			errorMessage("Invalid move! Square is occupied!");
		}
		else {
			enoughCarrots(positionToMoveTo);
		}
	}

	private void enoughCarrots(int positionToMoveTo) {
		int i = positionToMoveTo - playerToMove.getPlayerBoardPosition();
		int carrotCost = 0;
		while (i > 0) {
			carrotCost = carrotCost + i;
			i--;
		}
		if (carrotCost <= playerToMove.getNumberOfCarrots()) {
			String input = validNextString("This will use " + carrotCost + " carrots. Do you wish to continue? (Y/N)");
			if (input.equals("Y") || input.equals("y")){
				validMove(positionToMoveTo, carrotCost);
			}
		}
		else {
			errorMessage("You do not have enough carrots for this move");
		}
	}

	private void validMove(int positionToMoveTo, int carrotCost) {
		Square squareToMoveTo = board.getSquare(positionToMoveTo);
		switch (squareToMoveTo.getSquareType()) {
			case "Lettuce": //only if # of lettuces = 0
				if (playerToMove.getNumberOfLettuces() > 0) {
					completeMove(positionToMoveTo, carrotCost);
				} else {
					errorMessage("Can't move to a lettuce square unless number of lettuces is non-zero");
				}
				break;
			case "Finish":
				if (playerToMove.getNumberOfCarrots() - carrotCost <= 10 * board.numberOfPlayersFinished && playerToMove.getNumberOfLettuces() == 0) {
					playerToMove.setFinished();
					board.numberOfPlayersFinished++;
					completeMove(positionToMoveTo, carrotCost);
				} else {
					errorMessage("Invalid move! Lose any remaining lettuces and get you number of carrots to below " + 10 * (1 + board.numberOfPlayersFinished) + " to finish.");
				}
				break;
			case "Tortoise":
				errorMessage("Invalid move! Can't move forwards to a tortoise square");
				break;
			default:
				completeMove(positionToMoveTo, carrotCost);
				break;
		}
	}

	private void completeMove(int positionToMoveTo, int carrotCost){
			Square squareToMoveFrom = board.getSquare(playerToMove.getPlayerBoardPosition());
			Square squareToMoveTo = board.getSquare(positionToMoveTo);
			squareToMoveFrom.setOccupied(false);
			if(squareToMoveTo != board.getSquare(64)){
				squareToMoveTo.setOccupied(true);
			}
			playerToMove.setPlayerBoardPosition(positionToMoveTo);
			playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - carrotCost);
			playerToMove.setSquareCounter(0);
			turnComplete = true;//invokes nextPlayer method
			if(playerToMove.isFinished()){
				System.out.println(playerToMove.getPlayerName() + "finished the game!\n");
			}
			else {
				System.out.println(playerToMove.getPlayerName() + " moved to square #" + playerToMove.getPlayerBoardPosition() + "!\n");
				displayStatus();
			}
			runSquare(squareToMoveTo, carrotCost);
		}

	private void runSquare(Square squareToMoveTo, int carrotCost){
		switch(squareToMoveTo.getSquareType()){
			case "Hare":
				runHare(carrotCost);
				break;
			case "Lettuce":
				runLettuce();
				break;
			case "Number (1,5,6)":
				runNumber(1);
				runNumber(5);
				runNumber(6);
				break;
			case "Number (2)":
				runNumber(2);
				break;
			case "Number (3)":
				runNumber(3);
				break;
			case "Number (4)":
				runNumber(4);
				break;
			default:
				break;
		}
	}

	private void runNumber(int numberSquareType) {
		int squareCounter = playerToMove.getSquareCounter();
		if(squareCounter == 0){
			System.out.println("Crunching the numbers");
		}
		else{
			if(numberSquareType == getRacePosition(playerToMove)){
				int numberOfCarrots = playerToMove.getNumberOfCarrots() + 10 * numberSquareType;
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				System.out.println(playerToMove.getPlayerName() + " gained " + 10 * numberSquareType + " carrots!\n");
				pause();
			}
		}
		squareCounter++;
		playerToMove.setSquareCounter(squareCounter);
	}

	private void runLettuce() {
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
				System.out.println(playerToMove.getPlayerName() + " gained " + 10 * getRacePosition(playerToMove) + " carrots!" + playerToMove.getNumberOfLettuces() + "lettuces left !\n");
				pause();
				break;
			default:
				break;
		}
	}

	private void runHare(int carrotCost) {
		/*System.out.println("Draw a hare card!");
		HareCard nextHareCard = board.getHareCard(hareCardDeck);
		String hareCardType = nextHareCard.getHareCardType();
		this.hareCardDeck++;
		pause();
		switch(hareCardType) {
			case "Give10":
				int carrotsToGive = playerToMove.getNumberOfCarrots() / (board.players.size() - 1);
				if(carrotsToGive >= 10){
					carrotsToGive = 10;
				}
				if(carrotsToGive >= 5 && carrotsToGive < 10){
					carrotsToGive = 5;
				}
				if (carrotsToGive >= 1 && carrotsToGive < 5){
					carrotsToGive = 1;
				}
				else {
					reset();
				}
				int i = 1;
				while(i < board.players.size()) {
					Player playerToUpdate = board.getPlayer(i);
					String input = validNextString(playerToUpdate + ", do you want to accept " + carrotsToGive + " carrots? (Y/N)\n");
					if (input.equals("Y") || input.equals("y")) {
						playerToUpdate.setNumberOfCarrots(carrotsToGive + playerToUpdate.getNumberOfCarrots());
					}
					i++;
				}
				break;
			case "MissTurn":
				if(board.players.size() / getRacePosition(playerToMove) >= 2){
					skipTurn();
				}
				break;
			case "Restore65":
				playerToMove.setNumberOfCarrots(65);
				break;
			case "Draw10":
				if(playerToMove.getNumberOfLettuces() == 0){
					skipTurn();
				}
				else {
					int numberOfCarrots = playerToMove.getNumberOfCarrots() + 10 * getRacePosition(playerToMove);
					playerToMove.setNumberOfCarrots(numberOfCarrots);
				}
				break;
			case "FreeRide":
				playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() + carrotCost);
				break;
			case "LoseHalf":
				int numberOfCarrots = playerToMove.getNumberOfCarrots() / 2;
				playerToMove.setNumberOfCarrots(numberOfCarrots);
				break;
			case "Show":
				this.message = playerToMove.getPlayerName() + "has " +playerToMove.getNumberOfCarrots() + " carrots!\n";
				System.out.println(message);
				showCarrots = true;
				break;
			case "Shuffle":
				Collections.shuffle(board.getHareCards());
				this.hareCardDeck = 0;
				break;
			default:
				break;
		}*/
	}

	private void reset(){
		playerToMove.setNumberOfCarrots(65);
		playerToMove.setPlayerBoardPosition(0);
	}
}



