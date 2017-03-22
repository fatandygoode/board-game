package controllers;

import static utils.ScannerInput.*;
import models.Board;
import models.Player;

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
    private int turnCounter; //keeps track of whose turn it is
    private boolean gameInProgress; //changes main menu options if the game has begun
    private boolean turnComplete; //to facilitate nextPlayer method
    private boolean firstLaunch;
    private boolean skipMainMenu;
    
    public static void main(String[] args) {
		new MenuController();
	}
    
    /**
     * Constructor for objects of class MenuController
     */
    public MenuController() {
    	board = new Board();
    	turnCounter = 0;
    	firstLaunch = true;
    	runMenu();
    }
    
    /**
     * mainMenu() - This method displays the menu for the application, 
     * reads the menu option that the user entered and returns it.
     * 
     * @return     the users menu choice
     */
    private int mainMenu() {
    	
        System.out.println("\nBoard Game Menu");
        System.out.println("---------");     
        System.out.println("  1) Add a player");    
        System.out.println("  2) List all players");
        System.out.println("  3) Edit a player name");        
        System.out.println("  4) Remove a player");    
        System.out.println("---------");
        if(gameInProgress) {
    		System.out.println("  5) Resume the game");
    	}
    	else {
    		System.out.println("  5) Begin the game");
    	}
        System.out.println("---------");
        System.out.println("  0) Exit");
        
        int option = validNextInt("==>> ");
        
        return option;
    }
    
    /**
     * This is the method that controls the loop.
     */
    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {// 0 to exit  
            switch (option) {
            	case 1:	
                	if(gameInProgress) {
                		System.out.println("Error, cannot perform this function after the game has started");
                	}
                	else {
	                	if(board.numberOfPlayers() < 6) { //can't add more than 6 players
		                    String playerName = validNextString("\nEnter the player name...\n");
		                    board.add(new Player(playerName));
		                    System.out.println("\n" + playerName + " added to the game\n");
	                	}
	                	else {
	                		System.out.println("\nMaximum 6 players, please remove a player first\n");
	                	}
                	}
                	break;
	                    
                case 2:
                    if (board.numberOfPlayers() > 0) {
                    System.out.println(board.listPlayers());
                    System.out.println("\nTotal: " + board.numberOfPlayers() + "\n");
                    }
                    else {
                    	System.out.println("No players added");
                    }
                    break;
                    	
                case 3:
                    if (board.numberOfPlayers() > 0) {
                        System.out.println(board.listPlayers());

                        int index = validNextInt("\nPlease enter the index for the player you wish to edit: ");
            			Player playerToUpdate = board.get(index);

                        String newName = validNextString("\nEnter the new name for " + playerToUpdate.getPlayerName() + ": ");
                        playerToUpdate.setPlayerName(newName);
                    }
                    else {
                    	System.out.println("No players added");
                    }
                    break;
                    
                case 4:
                	if(gameInProgress) {
                		System.out.println("Error, cannot perform this function after the game has started");
                	}
                	else {
	                    if (board.numberOfPlayers() > 0) {
	                        System.out.println(board.listPlayers());
	
	                        int index = validNextInt("\nPlease enter the index of the player you wish to delete: ");
	                        System.out.println("\n" + board.get(index).getPlayerName() + " removed from the game");
	                        board.remove(index);
	                    }
	                    else {
	                    	System.out.println("No players added");
	                    }
                	}
                    break;
                    
                case 5:
                	if(board.numberOfPlayers() > 1) {//need at least 2 players
                		gameInProgress = true;
                		skipMainMenu = true;
                		runGame();
                	}
                	else {
                		System.out.println("\nYou need to add between 2 and 6 players to begin the game");
                	}
                	break;
                	
                default:   
                    System.out.println("\nInvalid option entered: " + option);
                    break;
            }
            
            pause();
            
			//display the main menu again if not starting game
            if(skipMainMenu == false) {
            	option = mainMenu();
			}
			
        }
        //the user chose option 0, so exit the program
        System.out.println("\nExiting... bye");
        System.exit(0);
	}
    
    /**
     * Method to run the game
     */
    private void runGame() {
    	skipMainMenu = false;
    	if(firstLaunch) {
    	System.out.println("<<=====>>\n");
    	System.out.println("Welcome\n");
    	System.out.println("<<=====>>\n");
    	firstLaunch = false;
    	pause();
    	}
    	
    	Player playerToMove = board.get(turnCounter);
        System.out.println("\n" + playerToMove.getPlayerName() + ", it's your turn\n");
        pause();
        
    	System.out.println(playerToMove.toString() + "\n");
    	pause();
    	
    	makeMove(); //starts next method to continue
    }
    
    /**
     * Method to display the options available to the current player
     */
    private int gameMenu() {
    	 System.out.println("\nIn Game Menu");
         System.out.println("===============");     
         System.out.println("  1) Move to a new square");   
         System.out.println("  2) Display the full board");
         System.out.println("  3) Show available moves");    
         System.out.println("===============");
         System.out.println("  0) Return to main menu");
    	
         int gameMenuOption = validNextInt("===>>");
     	
         return gameMenuOption;
    }
    
    /**
     * Method to execute the move as directed by the player
     */
    private void makeMove() {	
    	int gameMenuOption = gameMenu();	

    	switch (gameMenuOption) {
    	case 1:
    		moveSquare();
    		break;
    	case 2:
    		System.out.println("\nNothing found\n");
    		break;
    	case 3:
    		System.out.println("\nNothing found\n");
    		break;
    	case 0:
    		runMenu();
    		break;
    	default:
    		break;
    	}
		
    	nextPlayer();
    }
    
    /**
     * Method to rotate the player whose turn it is
     */
    private void nextPlayer() {
    	//======================================
    	//         TO DO
    	//======================================
    	//take into account if a player is finished
    	//======================================
    	if(turnComplete) {
    	turnCounter++; //increment turn counter by 1
    	turnCounter = turnCounter % board.players.size(); //reset to zero after all players have played
        turnComplete = false;
    	}
    	runGame();
    }
    
    private void moveSquare() {
    	
    	Player playerToMove = board.get(turnCounter);

    	System.out.println("\nWhere do you want to move to? Enter square number: \n");
		int newPosition = validNextInt("");
		playerToMove.setBoardPosition(newPosition);
		
		System.out.println("\n" + playerToMove.getPlayerName() + " moved to square #" + playerToMove.getBoardPosition() + "!\n");
		System.out.println("\n============================");
		turnComplete = true;
		pause();
    }
    
    /**
     * Helper method to insert a pause in the program
     * @return "Press any key to continue"
     */
    private String pause()
    {
        return validNextString("\nPress any key to continue...\n");
    }

}

