package controllers;

import static utils.ScannerInput.*;
import models.Board;
import models.Player;
import models.Square;

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
	private boolean firstLaunch; //to display welcome message
	private Player playerToMove; //global variable, used in multiple methods, consider merge later and use local instead
	
	public static void main(String[] args) {
		new MenuController();
	}

	/**
	 * Constructor for objects of class MenuController
	 */
	public MenuController(){
		board = new Board();
		firstLaunch = true;
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
		System.out.println(" --------");
        System.out.println("  1) View Game Rules");

        System.out.println("---------");
		System.out.println("---------");     
		System.out.println("  2) Add a player");    
		System.out.println("  3) List all players");
		System.out.println("  4) Edit a player name");        
		System.out.println("  5) Remove a player");    
		System.out.println("---------");
		if(gameInProgress){
			System.out.println("  6) Resume the game");
		}
		else{
			System.out.println("  6) Begin the game");
		}
		System.out.println("---------");
		System.out.println("  7) Save game (XML)");
		System.out.println("  8) Load game (XML)");
		System.out.println("---------");
		System.out.println("  0) Exit");

		int mainMenuOption = validNextInt("==>> ");
		return mainMenuOption;
	}

	/**
	 * This is the method that controls the loop.
	 */
	private void runMenu(){
		int mainMenuOption = mainMenu();//puts user's choice from the main menu through the switch loop
		boolean skipMainMenu = false;//keep displaying main menu until user starts the game

		while (mainMenuOption != 0){// 0 to exit  
			switch (mainMenuOption){
			
			case 1: 
            	
                System.out.println("------------------");
                System.out.println(" A game of skill based on a classic theme");
            	System.out.println("------------------");
            	System.out.println(" Number of players : 2 to 6 " + "\n");
            	System.out.println(" Playing time  : 45-60 minutes");
            	System.out.println(" Types of card  : 6 race cards, 15 hare cares, 18 lettuce cards, and carrot cards in denominations of 1, 5, 10, 15, 30, 60. ");
            	
            	System.out.println("------------------");
            	System.out.println(" Introduction");
            	System.out.println("------------------");
            	System.out.println(" Hare & Tortoise is a race game with a difference." + "\n");
            	System.out.println(" You don't move round the track by rolling dice and obeying instructions," );
            	System.out.println(" but by spending carrots and spending them wisely . In this game you can always move as far as you like");
            	System.out.println(" - so long as you have enough carrots to pay for it." + "\n");
            	System.out.println(" (You start with 65 and earn more by landing on certain square.) " + "\n");
            	System.out.println("------------------");
            	System.out.println(" The catch is the furhter you move the more you pay!");
            	System.out.println("------------------");
            	System.out.println(" So players who hare ahead too fast, run out of carrots first and the lose valuable timem to get them back " + "\n");
            	System.out.println(" Meanwhile, those who plod along like tortoises have so many carrots left that they have a good chance");
            	System.out.println(" of overtaking the hares.");
            	System.out.println("------------------");
            	System.out.println(" The skill of the game lies in choosing the right square to land on ");
            	System.out.println(" and in playing hare or tortoise depending on your position " + "\n");
            	System.out.println(" The fun of it lies in changing other runner's positions by overtaking them.");
            	System.out.println("  - or even moving backwards!" + "\n");
            	System.out.println(" Sometimes it's right to leap ahead. Sometime it's best to lag behind" + "\n");
            	System.out.println(" But all the time it's down to YOU to make the right decison " + "\n");
            	System.out.println("------------------");
            	System.out.println(" Off you go. May the best animal win! ");
            	System.out.println("------------------");
            	System.out.println(" Getting Ready");
            	System.out.println("------------------");
            	System.out.println(" Choose a runner each and place it on the board at START.");
            	System.out.println(" Deal carrot cards to each player as follows : one 30, one 15, one 10, two 5's - total 65 carrots" + "\n");
            	System.out.println(" During play , hold you carrot cards so that no-one knows excatly how many you have left.");
            	System.out.println(" Each player also takes three lettuce cards and a race card. The race card contains useful information");
            	System.out.println(" and is for reference only.");
            	System.out.println(" Seperate the undealt carrot cards and place them on the board in the numbered spaces - the 'carrot patch'.");
            	System.out.println(" Throughout play, all carrot payments are made into this patch.");
            	System.out.println("------------------");
            	System.out.println(" Object of the game");
            	System.out.println("------------------");
            	System.out.println(" To get your runner home first. But you must;");
            	System.out.println(" Get rid of all your lettuces on the way round ( This is done by landing on lettuce squares");
            	System.out.println(" Not have too many carrot left over when you reach home.");
            	System.out.println(" The first home is allowed up to 10 unused carrots, second 20, the thrid 30, and so on) ");
            	System.out.println("------------------");
            	System.out.println(" How to move");
            	System.out.println("------------------");
            	System.out.println(" You may move your runner forwards to any unoccupied square exceopt the tortoise square." + "\n");
            	System.out.println(" It can be any distance as long as you have enough carrots to pay for the move");
            	System.out.println(" The cost is shown on the race.");
            	System.out.println(" Later in the game you may move backwards instead of forwards, only to a tortoise square." + "\n");
            	System.out.println(" Moving backwards costs nothing - instead, it is a way of earning carrots.");


            		break;
			case 2:	//add player
				if(gameInProgress){
					System.out.println("Error, cannot perform this function after the game has started");
				}
				else{
					if(board.numberOfPlayers() < 6) { //can't add more than 6 players
						String playerName = validNextString("\nEnter the player name...\n");
						board.add(new Player(playerName));
						System.out.println("\n" + playerName + " added to the game\n");
					}
					else{
						System.out.println("\nMaximum 6 players, please remove a player first\n");
					}
				}
				break;
			case 3: //list players
				if (board.numberOfPlayers() > 0){
					System.out.println(board.listPlayers());
					System.out.println("\nTotal: " + board.numberOfPlayers() + "\n");
				}
				else{
					System.out.println("No players added");
				}
				break;

			case 4: //edit player name
				if(board.numberOfPlayers() > 0){
					System.out.println(board.listPlayers());

					int index = validNextInt("\nPlease enter the index for the player you wish to edit: ");
					Player playerToUpdate = board.getPlayer(index);

					String newName = validNextString("\nEnter the new name for " + playerToUpdate.getPlayerName() + ": ");
					playerToUpdate.setPlayerName(newName);
				}
				else{
					System.out.println("No players added");
				}
				break;
			case 5: //remove player
				if(gameInProgress){
					System.out.println("Error, cannot perform this function after the game has started");
				}
				else{
					if(board.numberOfPlayers() > 0){
						System.out.println(board.listPlayers());

						int index = validNextInt("\nPlease enter the index of the player you wish to delete: ");
						System.out.println("\n" + board.getPlayer(index).getPlayerName() + " removed from the game");
						board.remove(index);
					}
					else{
						System.out.println("No players added");
					}
				}
				break;
			case 6: //begin(resume) the game
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

			if(skipMainMenu == false){ //display the main menu again if not starting game
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
		if(firstLaunch) {//welcome message only on first launch
			System.out.println("<<=====>>\n");
			System.out.println("Welcome\n");
			System.out.println("<<=====>>\n");
			firstLaunch = false;
			pause();
		}
		this.playerToMove = board.getPlayer(turnCounter);
		
		if(playerToMove.isFinished()){
			skipTurn();
		}
		else{ //display status

			System.out.println("\n" + playerToMove.getPlayerName() + ", it's your turn\n");
			//===========TO-DO==============================================
			//
			//run "carry-over" moves if applicable
			//
			//===========================================================
			System.out.println(playerToMove.toString() + "\n");

			makeMove(); //starts next method to continue
		}
	}
	
	/**
	 * Method to display the options available to the current player
	 */
	private int gameMenu(){
		System.out.println("\nIn Game Menu");
		System.out.println("===============");     
		System.out.println("  1) Move forwards to a new square");
		System.out.println("  2) Move backwards to the nearest tortoise square");//(to do
		System.out.println("  3) Draw or pay carrots");//only on carrot square (to do)
		System.out.println("===============");
		System.out.println("  4) Display the full board");
		System.out.println("  5) Show available moves");//auto show if none are available (to do)
		//==========TO-DO========================
		//
		// Add or amend options and create methods
		//
		//========================================
		System.out.println("===============");
		System.out.println("  0) Return to main menu");

		int gameMenuOption = validNextInt("===>>");
		return gameMenuOption;
	}

	/**
	 * Method to execute the move as directed by the player
	 */
	private void makeMove(){	
		int gameMenuOption = gameMenu();//puts user's choice through switch loop

		switch (gameMenuOption){
		case 1://move forward to a new square
            if (player.newBoardPostion > player.boardPosition){
                moveSquare();
              }
              else {
                  System.out.println ("Invalid Move" + "\n");
                  System.out.println ("You have selected to move forward");
              }
              if (player.boardPostion > 64){
                  System.out.println ("Congratulations! You have passed the finishing line");
                }
                else{
                    moveSquare();
                }
            break;
        case 2://menu option 2
            System.out.println("\nNothing found\n");//add a method for menu option 2
            pause();
            break;
        case 3://menu option
        if (player.boardPostion () == carrot ){
            numberOfCarrots + 10 = numberOfCarrots;
         
          }
          else if (player.boardPostion () == carrot ){
              numberOfCarrots - 10 = numberOfCarrots;
        }
          else{
              System.out.println (" You must be on a carrot square to draw or pay carrots");
            }
			break;
		case 4:
			System.out.println(board.listSquares());
			pause();
			break;
			//==========TO-DO=================
			//
			// Add or amend cases as required and write methods
			//
			//==================================
		case 0://return to main menu
			runMenu();
			break;
		default:
			System.out.println("\nInvalid option entered: " + gameMenuOption);
			break;
		}
		nextPlayer();
	}

	/**
	 * Method to rotate the player whose turn it is
	 */
	private void nextPlayer(){
		//======================================
		//         TO DO
		//======================================
		//take into account if a player is finished
		//======================================
		if(turnComplete){
			turnCounter++; //increment turn counter by 1
			turnCounter = turnCounter % board.players.size(); //reset to zero after all players have played
			turnComplete = false;//back to false for the next player
		}
		runGame();
	}

	private void skipTurn(){
		turnComplete = true;
		nextPlayer();
	}

	private void moveSquare(){
		int boardPosition = validNextInt("\nWhere do you want to move to? Enter square number: \n");
		
			int i = boardPosition - playerToMove.getBoardPosition();
			int carrotCost = 0;
			while( i > 0 ){
				carrotCost = carrotCost + i;
				i--;
			}
			if(carrotCost <= playerToMove.getNumberOfCarrots()){
				String input = validNextString("This will use " + carrotCost + " carrots. Do you wish to continue? (Y/N)");

				if(input.equals("y")||input.equals("Y")){
					
					if(boardPosition < 64 || boardPosition == 64 && playerToMove.getNumberOfCarrots() <= 10 * board.numberOfPlayersFinished && playerToMove.getNumberOfLettuces() == 0 ){
						playerToMove.setBoardPosition(boardPosition);
						playerToMove.setNumberOfCarrots(playerToMove.getNumberOfCarrots() - carrotCost);
						//========TO-DO=======================
						//
						//Write method to return Race Position
						//
						//====================================
						System.out.println("\n" + playerToMove.getPlayerName() + " moved to square #" + playerToMove.getBoardPosition() + "!\n");
						System.out.println("\n============================");
						//===============TO-DO==========================================
						//
						//Run the subsequent actions that happen after moving to a new square
						//
						//===========================================================
						if (playerToMove.getBoardPosition() == 64){
							System.out.println("\n" + playerToMove.getPlayerName() + "finished the game!\n");
							pause();
							playerToMove.setFinished(true);
							board.numberOfPlayersFinished++;
						}
						else{
							System.out.println(playerToMove.toString() + "\n");
						}
						turnComplete = true;//invokes nextPlayer method
						pause();
					}
					else{
						System.out.println("Invalid move! Lose any remaining lettuces and get you number of carrots to below " + 10 * (1 + board.numberOfPlayersFinished) + " to finish.");
						pause();
						runGame();
					}
				}
				else{
					runGame();
				}
			}
			else{
				System.out.println("You do not have enough carrots for this move");
				pause();
				runGame();
			}
	}

	/**
	 * Helper method to insert a pause in the program
	 * @return "Press any key to continue"
	 */
	private String pause(){
		return validNextString("\nPress any key to continue...\n");
	}
}