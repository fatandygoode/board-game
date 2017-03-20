package models;

public class Player
{
	private String playerName;
	private int numberOfCarrots;
	private int currentPosition;
	private int numberOfLettuces;
	
	/**
     * Constructor for objects of class Player
     * 
     * @param playerName Name of the player
     * 
     */
	
	public Player(String playerName)
    {
        this.setPlayerName(playerName);
  
		setNumberOfCarrots(65);
		
		setCurrentPosition(64);
		
		setNumberOfLettuces(3);
    }

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getNumberOfCarrots() {
		return numberOfCarrots;
	}

	public void setNumberOfCarrots(int numberOfCarrots) {
		this.numberOfCarrots = numberOfCarrots;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getNumberOfLettuces() {
		return numberOfLettuces;
	}

	public void setNumberOfLettuces(int numberOfLettuces) {
		this.numberOfLettuces = numberOfLettuces;
	}
}
