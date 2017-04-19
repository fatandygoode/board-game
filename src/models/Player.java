package models;

/**
 * Player class creates an object for a user to play the game
 */
public class Player {
	private String playerName;
	private int numberOfCarrots, numberOfLettuces, playerBoardPosition, squareCounter, messageCounter;
	private boolean isFinished, skipNextTurn;

	/**
	 * Constructor for objects of class Player
	 * @param playerName Name of the player
	 */
	public Player(String playerName) {
		this.setPlayerName(playerName);
		setNumberOfCarrots(65);
		this.numberOfLettuces = 3;
	}

	/**
	 * Method to get a player's name
	 * @return the player's name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Method to change a player's name
	 * @param playerName - the new name for the player
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Method to get the number of carrots a player has
	 * @return the number of carrots the player has
	 */
	public int getNumberOfCarrots() {
		return numberOfCarrots;
	}

	/**
	 * Method to update the number of carrots a player has
	 * @param numberOfCarrots - the new number of carrots for the player
	 */
	public void setNumberOfCarrots(int numberOfCarrots) {
		this.numberOfCarrots = numberOfCarrots;
	}

	/**
	 * Method to get the number of lettuces a player has left
	 * @return the number of lettuce the player has left
	 */
	public int getNumberOfLettuces() {
		return numberOfLettuces;
	}

	/**
	 * Method to update the number of lettuces a player has. Decrements by one
	 */
	public void chewALettuce() {
		this.numberOfLettuces--;
	}

	/**
	 * Method to get a player's current board position
	 * @return the player's current board position
	 */
	public int getPlayerBoardPosition() {
		return playerBoardPosition;
	}

	/**
	 * Method to move a player to a new board position
	 * @param playerBoardPosition - the player's new board position
	 */
	public void setPlayerBoardPosition(int playerBoardPosition) {
		this.playerBoardPosition = playerBoardPosition;
	}

	/**
	 * Method to remove players from the turn sequence once they have reached the finish square
	 * @return returns true if the player has reached the final square and is no longer included
	 * in the turn sequence
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * Method to set a player's status to finished
	 */
	public void setFinished() {
		this.isFinished = true;
	}

	/**
	 * Method to return a string listing the player's attributes
	 * @return a string listing the player's attributes
	 */
	public String toString() {
		return "Player: " + playerName +
				"\nCarrots: " + numberOfCarrots +
				"\tLettuces: " + numberOfLettuces +
				"\nBoard Position: " + playerBoardPosition;
	}

	/**
	 *
	 * Method to determine the number of turns a player has been at a certain square
	 * significant to Lettuce and Number Squares
	 * @return the squareCounter
	 */
	public int getSquareCounter() {
		return squareCounter;
	}

	/**
	 * Method to increase the square counter
	 * @param squareCounter the squareCounter to set
	 */
	public void setSquareCounter(int squareCounter) {
		this.squareCounter = squareCounter;
	}

	/**
	 * Method to invoke skipping of turns
	 * @return true if the player is to skip the next turn
	 */
	public boolean isSkipNextTurn() {
		return skipNextTurn;
	}

	/**
	 * Method to skip turn as caused by hare cards or lettuce squares
	 * @param skipNextTurn false by default until the player has to miss a turn
	 */
	public void setSkipNextTurn(boolean skipNextTurn) {
		this.skipNextTurn = skipNextTurn;
	}

	/**
	 * @return a message to display carrots to other players when this hare card is drawn
	 */
	public String showMessage() {
		return playerName + " has " + numberOfCarrots + " carrots!";
	}

	/**
	 * Method controls the show carrots message display to each player once
	 * @return the amount of players that have seen the show carrots message
	 */
	public int getMessageCounter() {
		return messageCounter;
	}

	/**
	 * Method to control the message loop to cycle over all players once
	 * @param messageCounter The message counter increments until it reaches the number of players
	 */
	public void setMessageCounter(int messageCounter) {
		this.messageCounter = messageCounter;
	}
}