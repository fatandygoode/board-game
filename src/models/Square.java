package models;

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
    
    public String getSquareType() {
        return squareType;
    }

    public void setSquareType(String squareType) {
        this.squareType = squareType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String toString() {
    	String occupied = "";
    	if (isOccupied) {
    		occupied = "*";
    	}
    	return "\t" + squareType + "\t" + occupied;
    }
}

