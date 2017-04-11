package src.models;

/**
 *
 */
public class Square 
{
    private String squareType;
    private boolean isOccupied; 
 
    /**
     * Constructor for objects of class Square
     * 
     */
    public Square(){
    	
    }

    /**
     *
     * This method lets user know whether its a Hare,Tortise,Carrot,Lettuce or Number Square
     * @return the type of square
     * 
     */
    public String getSquareType() {
        return squareType;
    }

    /**
     * Allows to set the type of square
     * @param squareType the type of square
     */
    public void setSquareType(String squareType) {
        this.squareType = squareType;
    }

    /**
     * Returns if there another player currently occupying a certain square
     * @return if the square is occupied or not
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Allows the user to set if the square is occupied or not
     * @param isOccupied is the square occupied or not
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Allows the user to see what the square type is and
     * whether it is occupied or not
     * @return a string version of the square object .the square type and the current occupied status
     */
    public String toString() {
    	String occupied = "";
    	if (isOccupied) {
    		occupied = "*";
    	}
    	return "\t" + squareType + "\t" + occupied;
    }
}

