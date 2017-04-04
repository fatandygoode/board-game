package models;

public class Player {
		private String playerName;
		private int numberOfCarrots, numberOfLettuces, playerBoardPosition, squareCounter, messageCounter;
		private boolean isFinished, skipNextTurn;

	/**
	 * Constructor for objects of class Player
	 *
	 * @param playerName Name of the player
	 */
	public Player(String playerName) {
		this.setPlayerName(playerName);
		setNumberOfCarrots(65);
		this.numberOfLettuces = 3;
	}

	/**
	 * Method to get a player's name
	 *
	 * @return the player's name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Method to change a player's name
	 *
	 * @param playerName - the new name for the player
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Method to get the number of carrots a player has
	 *
	 * @return the number of carrots the player has
	 */
	public int getNumberOfCarrots() {
		return numberOfCarrots;
	}

	/**
	 * Method to update the number of carrots a player has
	 *
	 * @param numberOfCarrots - the new number of carrots for the player
	 */
	public void setNumberOfCarrots(int numberOfCarrots) {
		this.numberOfCarrots = numberOfCarrots;
	}

	/**
	 * Method to get the number of lettuces a player has left
	 *
	 * @return the number of lettuce the player has left
	 */
	public int getNumberOfLettuces() {
		return numberOfLettuces;
	}

	/**
	 * Method to update the number of lettuces a player has
	 */
	public void chewALettuce() {
		this.numberOfLettuces--;
	}

	/**
	 * Method to get a player's current board position
	 *
	 * @return the player's current board position
	 */
	public int getPlayerBoardPosition() {
		return playerBoardPosition;
	}

	/**
	 * Method to move a player to a new board position
	 *
	 * @param playerBoardPosition - the player's new board position
	 */
	public void setPlayerBoardPosition(int playerBoardPosition) {
		this.playerBoardPosition = playerBoardPosition;
	}

	/**
	 * @return the isFinished
	 */
	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished() {
		this.isFinished = true;
	}

	/**
	 * Method to return a string listing the player's attributes
	 *
	 * @return a string listing the player's attributes
	 */
	public String toString() {
		return "Player: " + playerName +
				"\nCarrots: " + numberOfCarrots +
				"\tLettuces: " + numberOfLettuces +
				"\nBoard Position: " + playerBoardPosition;
	}

	/**
	 * @return the squareCounter
	 */
	public int getSquareCounter() {
		return squareCounter;
	}

	/**
	 * @param squareCounter the squareCounter to set
	 */
	public void setSquareCounter(int squareCounter) {
		this.squareCounter = squareCounter;
	}


	public boolean isSkipNextTurn() {
		return skipNextTurn;
	}

	public void setSkipNextTurn(boolean skipNextTurn) {
		this.skipNextTurn = skipNextTurn;
	}

	public String showMessage() {
		return playerName + " has " + numberOfCarrots + " carrots!";
	}

	public int getMessageCounter() {
		return messageCounter;
	}

	public void setMessageCounter(int messageCounter) {
		this.messageCounter = messageCounter;
	}
}
