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
     
    //Getters
    
    public String getSquareType() {
        return squareType;
    }
     
    public boolean isOccupied() {
        return isOccupied;
    }
    
    //Setters
    
    public void setSquareType(String squareType) {
    	this.squareType = squareType;
    	}
    
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String toString() {
    	String occupied = "";
    	if (isOccupied) {
    		occupied = "occupied";
    	}
    	return "\t" + squareType + "\t" + occupied;
    }
}

