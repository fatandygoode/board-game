package models;

public class Player {
	private String playerName;
	private int numberOfCarrots;
	private int numberOfLettuces;
	private int boardPosition;
	private int racePosition;
	
	/**
     * Constructor for objects of class Player
     * 
     * @param playerName Name of the player
     * 
     */
	public Player(String playerName) {
		
        this.setPlayerName(playerName);
		setNumberOfCarrots(65);
		setNumberOfLettuces(3);
		setRacePosition(0);	
		setBoardPosition(0);
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
	 * Method to update the number of lettuces a player has
	 * @param numberOfLettuces - the new number of lettuces for the player
	 */
	public void setNumberOfLettuces(int numberOfLettuces) {
		this.numberOfLettuces = numberOfLettuces;
	}

	/**
	 * Method to get a player's current board position
	 * @return the player's current board position
	 */
	public int getBoardPosition() {
		return boardPosition;
	}

	/**
	 * Method to move a player to a new board position
	 * @param boardPosition - the player's new board position
	 */
	public void setBoardPosition(int boardPosition) {
		this.boardPosition = boardPosition;
	}

	/**
	 * Method to get a player's race position
	 * @return the player's current place in the race
	 */
	public int getRacePosition() {
		return racePosition;
	}

	/**
	 * Method to update a player's race postion
	 * @param racePosition - the player's new place in the race
	 */
	public void setRacePosition(int racePosition) {
		this.racePosition = racePosition;
	}
	
	/**
	 * Method to return a string listing the player's attributes
	 * @return a string listing the player's attributes
	 */
	public String toString() {
		return 	"Player: " + playerName +
				"\nCarrots: " + numberOfCarrots +
				"\tLettuces: " + numberOfLettuces +
				"\nBoard Position: " + boardPosition +
				"\tRace Position: " + racePosition + "";
	}
}
