package models;

/**
 * Square class creates an object required to run the game
 */
public class Square 
{
    private String squareType;
    private boolean isOccupied; 
 
    /**
     * Constructor for objects of class Square
     */
    public Square(){
    	
    }

    /**
     * Getter method to know whether it's a Hare, Tortoise, Carrot, Lettuce or Number Square
     * @return the type of square
     *
     */
    public String getSquareType() {
        return squareType;
    }

    /**
     * Allows the board constructor to set the type of square
     * @param squareType the type of square
     */
    public void setSquareType(String squareType) {
        this.squareType = squareType;
    }

    /**
     * Getter for if there is a player currently occupying square
     * @return if the square is occupied or not
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Sets a square to occupied or unoccupied
     * @param isOccupied is the square occupied or not
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Creates a string of the square type is and
     * whether it is occupied or not
     * @return a string version of the square object; the square type and the current occupied status
     */
    public String toString() {
    	String occupied = "";
    	if (isOccupied) {
    		occupied = "*";
    	}
    	return "\t" + squareType + "\t" + occupied;
    }
}

