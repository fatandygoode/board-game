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
public class MenuController
{
    private Board board; //creates new game
    private int turnCounter; //keeps track of whose turn it is
    private boolean gameHasStarted;
    
    public static void main(String[] args)
    {
		new MenuController();
	}
    
    /**
     * Constructor for objects of class MenuController
     */
    public MenuController()
    {
    	board = new Board();
    	turnCounter = 0;
    	gameHasStarted = false;
    	runMenu();
    }
    
    /**
     * mainMenu() - This method displays the menu for the application, 
     * reads the menu option that the user entered and returns it.
     * 
     * @return     the users menu choice
     */
    private int mainMenu()
    { 
        System.out.println("\nBoard Game Menu");
        System.out.println("---------");     
        System.out.println("  1) Add a player");    
        System.out.println("  2) List all players");
        System.out.println("  3) Edit a player name");        
        System.out.println("  4) Remove a player");    
        System.out.println("---------");
        System.out.println("  5) Begin the game");
        System.out.println("---------");
        System.out.println("  0) Exit");
        int option = validNextInt("==>> "); //ScannerInput class
        return option;
    }
    
    /**
     * This is the method that controls the loop.
     */
    private void runMenu()
    {
        int option = mainMenu();
        while (option != 0)
        {  
        	
            switch (option)
            {
                case 1:
                	if(board.numberOfPlayers() < 6) //can't add more than 6 players
                	{
	                    String playerName = validNextString("\nEnter the player name...\n");
	                    board.add(new Player(playerName));
	                    System.out.println("\n" + playerName + " added to the game\n");
                	}
                	else
                	{
                		System.out.println("\nMaximum 6 players, please remove a player first\n");
                	}
	                    break;
	                    
                case 2:
                    System.out.println(board.listPlayers());
                    System.out.println("\nTotal: " + board.numberOfPlayers() + "\n");

                    	break;
                    	
                case 3:    
                    System.out.println(board.listPlayers());
                    if (board.numberOfPlayers() > 0)
                    {
                        int index = validNextInt("\nPlease enter the index for the player you wish to edit: ");
            			Player playerToUpdate = board.get(index);

                        String newName = validNextString("\nEnter the new name for " + playerToUpdate.getPlayerName() + ": ");
                        playerToUpdate.setPlayerName(newName);
                    }
                    break;
                    
                case 4:    
                    System.out.println(board.listPlayers());
                    if (board.numberOfPlayers() > 0)
                    {
                        int index = validNextInt("\nPlease enter the index of the player you wish to delete: ");
                        System.out.println("\n" + board.get(index).getPlayerName() + " removed from the game");
                        board.remove(index);
                    }
                    break;
                    
                case 5:
                	if(board.numberOfPlayers() > 1) //need at least 2 players
                	{
                		gameHasStarted = true;
                		beginGame();
                	}
                	else
                	{
                		System.out.println("\nYou need to add between 2 and 6 players to begin the game");
                	}
                	break;
                	
                default:   
                    System.out.println("\nInvalid option entered: " + option);
                    break;
            }
            
            pause();
            
			//display the main menu again if not starting game
            if(gameHasStarted == false)
			{
            	option = mainMenu();
			}
			
        }
        //the user chose option 0, so exit the program
        System.out.println("\nExiting... bye");
        System.exit(0);
	}
    
    /**
     * Method to begin the game
     */
    private void beginGame()
    {
    	System.out.println("<<=====>>\n");
    	System.out.println("Welcome\n");
    	System.out.println("<<=====>>\n");
    	pause();
    	//======================================
    	//         TO DO
    	//======================================
    	//enter rules depending on number of players
    	//
    	//add an exit method to return to main menu
    	//============================================
    	
    	playerTurn(); //starts next method to continue
    }
    
    /**
     * Method to prompt the next player to move
     */
    private void playerTurn()
    {
		Player playerToMove = board.get(turnCounter);
        System.out.println("\n" + playerToMove.getPlayerName() + ", it's your turn\n");
        pause();
        displayStatus();
    }
    
    /**
     * Method to display useful info for the next player to move
     */
    private void displayStatus()
    {	
    	//======================================
    	//         TO DO
    	//======================================
		//display info e.g. current position, number of carrots/lettuces
    	//add options
    	//e.g. view full board, view list of available moves
    	//if no available moves return to start
    	//======================================
    	
    	Player playerToMove = board.get(turnCounter);
    	System.out.println("\nYour current postion is: " + playerToMove.getCurrentPosition() + "\n");
    	makeMove();
    }
    
    /**
     * Method to execute the move as directed by the player
     */
    private void makeMove()
    {
    	//======================================
    	//         TO DO
    	//======================================
    	//display options
    	//move forwards
    	//move back to tortioise
    	//draw carrots
    	//etc.
    	
    	//are you sure? this will use X carrots
    	//press * to confirm
    
    	//======================================
    	
    	
    	Player playerToMove = board.get(turnCounter);
		
		System.out.println("\nWhere do you want to move to? Enter square number: \n");
		
		int newPosition = validNextInt("");
		playerToMove.setCurrentPosition(newPosition);
		
		System.out.println("\n" + playerToMove.getPlayerName() + " moved to square #" + playerToMove.getCurrentPosition() + "!\n");
		System.out.println("\n============================");
		pause();
		
    	nextPlayer();
    }
    
    /**
     * Method to rotate the player whose turn it is
     */
    private void nextPlayer()
    {
    	//======================================
    	//         TO DO
    	//======================================
    	//take into account if a player is finished
    	//======================================
    	
    	turnCounter++; //increment turn counter by 1
    	turnCounter = turnCounter % board.players.size(); //reset to zero after all players have played
    	playerTurn();
    }
    
    /**
     * Helper method to insert a pause in the program
     * @return "Press any key to continue"
     */
    private String pause()
    {
        return validNextString("\nPress any key to continue...\n");
    }
    
    /**
     * Helper method to return to the main menu
     * 
     */
    private void menuReturn()
    {
        mainMenu();
    }
}

