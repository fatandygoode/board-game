package models;

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
     * @return
     */
    public String getSquareType() {
        return squareType;
    }

    /**
     *
     * @param squareType
     */
    public void setSquareType(String squareType) {
        this.squareType = squareType;
    }

    /**
     *
     * @return
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     *
     * @param isOccupied
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     *
     * @return
     */
    public String toString() {
    	String occupied = "";
    	if (isOccupied) {
    		occupied = "*";
    	}
    	return "\t" + squareType + "\t" + occupied;
    }
}

