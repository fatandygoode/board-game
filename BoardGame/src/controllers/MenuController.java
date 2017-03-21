package controllers;

import java.util.Scanner;
import static utils.ScannerInput.*;
import models.Board;
import models.Player;// test
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
    private Scanner input; 
    private Board board; //creates new game
    private int turnCounter; //keeps track of whose turn it is
    
    public static void main(String[] args)
    {
		new MenuController();
	}
    
    /**
     * Constructor for objects of class MenuController
     */
    public MenuController()
    {
    	input = new Scanner(System.in);
    	board = new Board();
    	turnCounter = 0;
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
        System.out.println("Board Game Menu");
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
        while (option != 0 || option != 5) //0 to exit, 5 to begin the game
        {  
            switch (option)
            {
                case 1:
                	if(board.numberOfPlayers() < 6) //can't add more than 6 players
                	{
	                	System.out.println("Enter the player name...\n");
	                    String playerName = input.nextLine();
	                    board.add(new Player(playerName));
	                    System.out.println(playerName + " added to the game");
	                    //pause the program so that the user can read what we just printed to the terminal window
	        			System.out.println("\nPress any key to continue...");
	        			input.nextLine();
                	}
                	else
                	{
                		System.out.println("Maximum 6 players, please remove a player first");
                	}
	                    break;           
                case 2:
                    System.out.println(board.listPlayers());
                    System.out.println("Total: " + board.numberOfPlayers());

                    break;
                case 3:    
                    System.out.println(board.listPlayers());
                    if (board.numberOfPlayers() > 0)
                    {
                        System.out.print("Please enter the index for the player you wish to edit: ");
                        int index = validNextInt("");
            			Player playerToUpdate = board.get(index);
                        System.out.print(playerToUpdate.getPlayerName());
                        System.out.print("\nEnter the new name: ");
                        String newName = input.nextLine();
                        playerToUpdate.setPlayerName(newName);
                    }
                    break;
                case 4:    
                    System.out.println(board.listPlayers());
                    if (board.numberOfPlayers() > 0)
                    {
                        System.out.print("Please enter the index of the player you wish to delete: ");
                        int index = validNextInt("");
                        board.remove(index);
                    }
                    break;
                case 5:
                	if(board.numberOfPlayers() > 1) //need at least 2 players
                	{
                		beginGame();
                	}
                	else
                	{
                		System.out.println("You need to add between 2 and 6 players to begin the game");
                	}
                	break;
                default:   
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
          //pause the program so that the user can read what we just printed to the terminal window
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			
			//display the main menu again if not starting game
			option = mainMenu();
        }
        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
	}
    
    /**
     * Method to begin the game
     */
    private void beginGame()
    {
    	System.out.println("\nWelcome\n");
    	if(board.numberOfPlayers() == 2)
    	{
    		for(Player player : board.players)
    		{
    			player.setNumberOfCarrots(95); // adjustment for 2 player game
    		}	
    	}
    	//enter rules depending on number of players
    	
    	//add an exit method to return to main menu
    	
    	playerTurn(); //starts next method to continue
    }
    
    /**
     * Method to prompt the next player to move
     */
    private void playerTurn()
    {
		Player playerToMove = board.get(turnCounter);
        System.out.println("\n" + playerToMove.getPlayerName() + ", it's your turn\n");
        
        displayStatus();
    }
    
    /**
     * Method to display useful info for the next player to move
     */
    private void displayStatus()
    {
		//display info e.g. current position, number of carrots/lettuces
    	//add options
    	//e.g. view full board, view list of available moves
    	//if no available moves return to start
    	Player playerToMove = board.get(turnCounter);
    	System.out.println("\nYour current postion is: " + playerToMove.getCurrentPosition() + "\n");
    	makeMove();
    }
    
    /**
     * Method to execute the move as directed by the player
     */
    private void makeMove()
    {
		//display options
    	//move forwards
    	//move back to tortioise
    	//draw carrots
    	//etc.
    	
    	//are you sure? this will use X carrots
    	//press * to confirm
    	
    	
    	Player playerToMove = board.get(turnCounter);
		
		System.out.println("\nWhere do you want to move to? Enter square number: \n");
		
		int newPosition = validNextInt("");
		playerToMove.setCurrentPosition(newPosition);
		System.out.println("\n" + playerToMove.getPlayerName() + " moved to square #" + playerToMove.getCurrentPosition() + "!\n");
		
		//pause the program so that the user can read what we just printed to the terminal window
		System.out.println("\nPress any key to continue...");
		System.out.println("\n============================");
		input.nextLine();
		
    	nextPlayer();
    }
    
    /**
     * Method to rotate the player whose turn it is
     */
    private void nextPlayer()
    {
    	//take into account if a player is finished
    	
    	turnCounter++; //increment turn counter by 1
    	turnCounter = turnCounter % board.players.size(); //reset to zero after all players have played
    	playerTurn();
    }
}
